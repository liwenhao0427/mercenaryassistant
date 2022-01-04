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

    private String userId;
    private GameRecord gameRecord;
    private List<GamePlayfield> gamePlayfieldList;
    private List<GamePlayfieldAction> gamePlayfieldActionList;
    private List<GameActionResult> gameActionResultList;

    public GameRecord getGameRecord() {
        return gameRecord;
    }

    public void setGameRecord(GameRecord gameRecord) {
        this.gameRecord = gameRecord;
    }

    public List<GamePlayfield> getGamePlayfieldList() {
        return gamePlayfieldList;
    }

    public void setGamePlayfieldList(List<GamePlayfield> gamePlayfieldList) {
        this.gamePlayfieldList = gamePlayfieldList;
    }

    public List<GamePlayfieldAction> getGamePlayfieldActionList() {
        return gamePlayfieldActionList;
    }

    public void setGamePlayfieldActionList(List<GamePlayfieldAction> gamePlayfieldActionList) {
        this.gamePlayfieldActionList = gamePlayfieldActionList;
    }

    public List<GameActionResult> getGameActionResultList() {
        return gameActionResultList;
    }

    public void setGameActionResultList(List<GameActionResult> gameActionResultList) {
        this.gameActionResultList = gameActionResultList;
    }
}
