package com.mercbuddy.mercenaryassistant.model;

import com.mercbuddy.mercenaryassistant.entity.GamePlayfield;

/**
 * @author 李文浩
 * @date 2022/1/4 15:27
 */
public class PlayfieldRequestModel {
    private GamePlayfield gamePlayfield;
    private Integer rank;
    private Integer maxGap;

    public GamePlayfield getGamePlayfield() {
        return gamePlayfield;
    }

    public void setGamePlayfield(GamePlayfield gamePlayfield) {
        this.gamePlayfield = gamePlayfield;
    }

    public Integer getRank() {
        return rank;
    }

    public void setRank(Integer rank) {
        this.rank = rank;
    }

    public Integer getMaxGap() {
        return maxGap;
    }

    public void setMaxGap(Integer maxGap) {
        this.maxGap = maxGap;
    }
}
