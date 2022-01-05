package com.mercbuddy.mercenaryassistant.model;

import com.mercbuddy.mercenaryassistant.entity.GameActionResult;
import com.mercbuddy.mercenaryassistant.entity.GamePlayfield;
import com.mercbuddy.mercenaryassistant.entity.GamePlayfieldAction;
import com.mercbuddy.mercenaryassistant.entity.GameRecord;

import java.util.List;

/**
 * 对局记录保存请求体
 * @author 李文浩
 * @date 2021/12/31 9:54
 */
public class GameRecordRequestModel {

    public GameRecord getGameRecord() {
        return gameRecord;
    }

    public void setGameRecord(GameRecord gameRecord) {
        this.gameRecord = gameRecord;
    }

    public List<PlayfieldActionResult> getPlayfieldActionResultList() {
        return playfieldActionResultList;
    }

    public void setPlayfieldActionResultList(List<PlayfieldActionResult> playfieldActionResultList) {
        this.playfieldActionResultList = playfieldActionResultList;
    }

    private GameRecord gameRecord;
    private List<PlayfieldActionResult> playfieldActionResultList;

}
