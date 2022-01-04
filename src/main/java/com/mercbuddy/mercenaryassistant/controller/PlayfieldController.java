package com.mercbuddy.mercenaryassistant.controller;

import com.mercbuddy.mercenaryassistant.base.Result;
import com.mercbuddy.mercenaryassistant.base.ResultUtils;
import com.mercbuddy.mercenaryassistant.model.GameRecordRequestModel;
import com.mercbuddy.mercenaryassistant.service.CommonService;
import org.apache.catalina.util.RequestUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        String sql = "select * from game_playfield";
        result = jdbcTemplate.queryForList(sql);
        return ResultUtils.createSuccess(result);
    }

    @RequestMapping(value = "saveGameRecord",method = RequestMethod.POST)
    @ResponseBody
    public Result saveGameRecord(@RequestParam GameRecordRequestModel gameRecordRequestModel){
        if(gameRecordRequestModel == null || gameRecordRequestModel.getGameRecord() == null){
            return ResultUtils.createFail("参数异常！");
        }
        commonService.insert(gameRecordRequestModel.getGameRecord());
        return ResultUtils.createSuccess("保存成功！");
    }
}
