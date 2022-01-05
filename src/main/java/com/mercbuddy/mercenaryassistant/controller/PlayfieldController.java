package com.mercbuddy.mercenaryassistant.controller;

import com.mercbuddy.mercenaryassistant.base.Result;
import com.mercbuddy.mercenaryassistant.base.ResultUtils;
import com.mercbuddy.mercenaryassistant.entity.*;
import com.mercbuddy.mercenaryassistant.model.GameRecordRequestModel;
import com.mercbuddy.mercenaryassistant.model.PlayfieldActionResult;
import com.mercbuddy.mercenaryassistant.model.PlayfieldRequestModel;
import com.mercbuddy.mercenaryassistant.service.CommonService;
import com.mercbuddy.mercenaryassistant.util.CommonUtils;
import org.apache.catalina.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.util.DigestUtils;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author 李文浩
 * @date 2021/12/29 9:57
 */

@Controller
@RequestMapping("/playfield")
public class PlayfieldController {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private CommonService commonService;

    @RequestMapping(value = "/getPlayfield",method = RequestMethod.POST)
    @ResponseBody
    public Result hello() {
        List<Map<String, Object>> result = new ArrayList<>();
        String sql = "select * from game_playfield limit 0, 1";
        result = jdbcTemplate.queryForList(sql);
        return ResultUtils.createSuccess(result);
    }

    /**
     * 保存一局对局记录
     * @param gameRecordRequestModel
     * @param request
     * @return
     */
    @RequestMapping(value = "saveGameRecord",method = RequestMethod.POST)
    @ResponseBody
    public Result saveGameRecord(@RequestBody GameRecordRequestModel gameRecordRequestModel, HttpServletRequest request){
        String ip = CommonUtils.getIpAddr(request);
        String userId = request.getHeader("userId");
        if(gameRecordRequestModel == null || gameRecordRequestModel.getGameRecord() == null){
            commonService.insertLog(ip, userId, 0, "参数格式错误！", "saveGameRecord");
            return ResultUtils.createFail("参数异常！");
        }
        try{
            GameRecord gameRecord = gameRecordRequestModel.getGameRecord();
            gameRecord.setId(null);
            gameRecord.setUserId(userId);
            gameRecord.setCreateTime(new Date());
            int gameRecordId = commonService.insertWithIdReturn(gameRecord);

            String msg = "对局记录保存成功，但是未检测到场面信息！";
            if(gameRecordRequestModel.getPlayfieldActionResultList() != null && gameRecordRequestModel.getPlayfieldActionResultList().size() > 0){
                int count = gameRecordRequestModel.getPlayfieldActionResultList().size();
                msg = "对局记录保存成功，场面信息共" + count + "条！";
                for( int i = 0; i < count; i ++){
                    // 场面
                    PlayfieldActionResult playfieldActionResult = gameRecordRequestModel.getPlayfieldActionResultList().get(i);
                    GamePlayfield gamePlayfield = playfieldActionResult.getGamePlayfield();
                    String playFieldHash = DigestUtils.md5DigestAsHex("".concat(gamePlayfield.getOwnMinionsName())
                            .concat(gamePlayfield.getEnemyMinionsName())
                            .concat(gamePlayfield.getOwnMinionsAtk())
                            .concat(gamePlayfield.getEnemyMinionsAtk())
                            .concat(gamePlayfield.getOwnMinionsHp())
                            .concat(gamePlayfield.getEnemyMinionsHp())
                            .concat(gamePlayfield.getOwnMinionsEnchs())
                            .concat(gamePlayfield.getEnemyMinionsEnchs())
                            .concat(gamePlayfield.getOwnMinionsAbility())
                            .concat(gamePlayfield.getEnemyMinionsAbility())
                            .concat(gamePlayfield.getRounds().toString())
                            .getBytes());
                    String minionsHash = DigestUtils.md5DigestAsHex("".concat(gamePlayfield.getOwnMinionsName())
                            .concat(gamePlayfield.getEnemyMinionsName())
                            .concat(gamePlayfield.getRounds().toString())
                            .getBytes());
                    gamePlayfield.setId(playFieldHash);
                    gamePlayfield.setMinionsHash(minionsHash);
                    commonService.replace(gamePlayfield);

                    // 操作
                    GamePlayfieldAction gamePlayfieldAction = playfieldActionResult.getGamePlayfieldAction();
                    gamePlayfieldAction.setPlayfieldId(playFieldHash);
                    gamePlayfieldAction.setMinionsHash(minionsHash);
                    String actionId = DigestUtils.md5DigestAsHex("".concat(playFieldHash)
                            .concat(gamePlayfieldAction.getActions())
                            .concat(gamePlayfieldAction.getDataFrom().toString())
                            .getBytes());
                    gamePlayfieldAction.setId(actionId);
                    commonService.replace(gamePlayfieldAction);

                    // 结果
                    GameActionResult gameActionResult = playfieldActionResult.getGameActionResult();
                    gameActionResult.setActionId(actionId);
                    gameActionResult.setId(null);
                    gameActionResult.setCreateTime(new Date());
                    gameActionResult.setRecordId(gameRecordId);
                    commonService.insert(gameActionResult);
                }
            }

            commonService.insertLog(ip, gameRecordRequestModel.getGameRecord().getUserId(), 1, msg, "saveGameRecord");
        }catch (Exception e){
            commonService.insertLog(ip, gameRecordRequestModel.getGameRecord().getUserId(), 0, e.getMessage().substring(0, 140), "saveGameRecord");
            return ResultUtils.createFail("服务器出错，保存失败！");
        }
        return ResultUtils.createSuccess("保存成功！");
    }

    /**
     * 对于一个场面，给出历史行动
     * @param playfieldRequestModel
     * @return
     */
    @RequestMapping(value = "getActions",method = RequestMethod.POST)
    @ResponseBody
    public Result getActions(@RequestBody PlayfieldRequestModel playfieldRequestModel) {
        Map<String, Object> result = new HashMap<>();
        GamePlayfield gamePlayfield = playfieldRequestModel.getGamePlayfield();
        String playFieldHash = DigestUtils.md5DigestAsHex("".concat(gamePlayfield.getOwnMinionsName())
                .concat(gamePlayfield.getEnemyMinionsName())
                .concat(gamePlayfield.getOwnMinionsAtk())
                .concat(gamePlayfield.getEnemyMinionsAtk())
                .concat(gamePlayfield.getOwnMinionsHp())
                .concat(gamePlayfield.getEnemyMinionsHp())
                .concat(gamePlayfield.getOwnMinionsEnchs())
                .concat(gamePlayfield.getEnemyMinionsEnchs())
                .concat(gamePlayfield.getOwnMinionsAbility())
                .concat(gamePlayfield.getEnemyMinionsAbility())
                .concat(gamePlayfield.getRounds().toString())
                .getBytes());

        String minionsHash = DigestUtils.md5DigestAsHex("".concat(gamePlayfield.getOwnMinionsName())
                .concat(gamePlayfield.getEnemyMinionsName())
                .concat(gamePlayfield.getRounds().toString())
                .getBytes());
        // 完全匹配
        String sqlAll = "select * from view_action where playfield_id = '" + playFieldHash + "' order by win_rate desc limit 0, 10";
        List<Map<String, Object>> allMatchedList = jdbcTemplate.queryForList(sqlAll);
        result.put("allMatchedList", allMatchedList);
        // 佣兵/回合数匹配
        String sqlMer = "select * from view_action where minions_hash = '" + minionsHash + "' order by win_rate desc limit 0, 10";
        List<Map<String, Object>> merMatchedList = jdbcTemplate.queryForList(sqlMer);
        result.put("merMatchedList", merMatchedList);
        return ResultUtils.createSuccess(result);
    }

    /**
     * 给出一个行动的所有结果
     * @return
     */
    @RequestMapping(value = "getGameRecord")
    @ResponseBody
    public Result getGameRecord(String actionId) {
        String sql = "select result_info, count(0) as cnt, avg( case when(result = 1) then 100 else 0 end ) as win_rate, max(record_id) as record_id  from game_action_result\n" +
                "where action_id = '"+actionId+"'\n" +
                "GROUP BY result_info \n" +
                "order by count(0) desc limit 0, 30";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        return ResultUtils.createSuccess(result);
    }

    /**
     * 给出一个场对局记录的所有行动
     * @return
     */
    @RequestMapping(value = "getRecordLog")
    @ResponseBody
    public Result getRecordLog(String recordId) {
        String sql = "select a.before_info, b.actions, a.result_info from game_action_result a \n" +
                "left join game_playfield_action b \n" +
                "on a.action_id = b.id\n" +
                "where a.record_id = '"+recordId+"'";
        List<Map<String, Object>> result = jdbcTemplate.queryForList(sql);
        return ResultUtils.createSuccess(result);
    }

}
