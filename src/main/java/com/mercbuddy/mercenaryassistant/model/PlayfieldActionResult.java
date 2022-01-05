package com.mercbuddy.mercenaryassistant.model;

import com.mercbuddy.mercenaryassistant.entity.GameActionResult;
import com.mercbuddy.mercenaryassistant.entity.GamePlayfield;
import com.mercbuddy.mercenaryassistant.entity.GamePlayfieldAction;

import java.util.List;

/**
 * @author 李文浩
 * @date 2022/1/4 9:48
 */
public class PlayfieldActionResult {
    public GamePlayfield getGamePlayfield() {
        return gamePlayfield;
    }

    public void setGamePlayfield(GamePlayfield gamePlayfield) {
        this.gamePlayfield = gamePlayfield;
    }

    public GamePlayfieldAction getGamePlayfieldAction() {
        return gamePlayfieldAction;
    }

    public void setGamePlayfieldAction(GamePlayfieldAction gamePlayfieldAction) {
        this.gamePlayfieldAction = gamePlayfieldAction;
    }

    public GameActionResult getGameActionResult() {
        return gameActionResult;
    }

    public void setGameActionResult(GameActionResult gameActionResult) {
        this.gameActionResult = gameActionResult;
    }

    private GamePlayfield gamePlayfield;
    private GamePlayfieldAction gamePlayfieldAction;
    private GameActionResult gameActionResult;

}
