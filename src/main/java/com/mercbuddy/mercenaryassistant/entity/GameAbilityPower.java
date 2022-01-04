package com.mercbuddy.mercenaryassistant.entity;

import java.io.Serializable;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercbuddy.mercenaryassistant.util.Column;
import com.mercbuddy.mercenaryassistant.util.Entity;
/**
 * All rights reserved.
 *
 * @Description: 
 * @author 李文浩
 * @createTime - 2021-12-31
 */
@Entity(tableName = "game_ability_power",label = "")
public class GameAbilityPower implements Serializable {

    /**/
	@Column(isPK = true,label = "id")
	private Integer id;
	
    /*佣兵名称*/
	@Column(label = "card_name")
	private String cardName;
	
    /*技能id*/
	@Column(label = "ability_id")
	private String abilityId;
	
    /*技能名称*/
	@Column(label = "ability_name")
	private String abilityName;
	
    /*权重*/
	@Column(label = "power")
	private Double power;
	
    /*次数*/
	@Column(label = "cnt")
	private Integer cnt;
	
    /*胜利次数*/
	@Column(label = "win_cnt")
	private Integer winCnt;
	

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setCardName(String value) {
		this.cardName = value;
	}
	public String getCardName() {
		return this.cardName;
	}
	public void setAbilityId(String value) {
		this.abilityId = value;
	}
	public String getAbilityId() {
		return this.abilityId;
	}
	public void setAbilityName(String value) {
		this.abilityName = value;
	}
	public String getAbilityName() {
		return this.abilityName;
	}
	public void setPower(Double value) {
		this.power = value;
	}
	public Double getPower() {
		return this.power;
	}
	public void setCnt(Integer value) {
		this.cnt = value;
	}
	public Integer getCnt() {
		return this.cnt;
	}
	public void setWinCnt(Integer value) {
		this.winCnt = value;
	}
	public Integer getWinCnt() {
		return this.winCnt;
	}

}

