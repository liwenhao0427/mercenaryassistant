package com.mercbuddy.mercenaryassistant.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.*;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.mercbuddy.mercenaryassistant.util.Column;
import com.mercbuddy.mercenaryassistant.util.Entity;
/**
 * All rights reserved.
 *
 * @Description: 
 * @author 李文浩
 * @createTime - 2022-01-04
 */
@Entity(tableName = "game_playfield_action",label = "")
public class GamePlayfieldAction implements Serializable {

	/*根据 playfield_id 和 actions hash后的结果，确保记录唯一*/
	@Column(isPK = true,label = "id")
	private String id;
	
    /*对局记录 id*/
	@Column(label = "playfield_id")
	private String playfieldId;
	
    /*仅根据佣兵 hash 结果*/
	@Column(label = "minions_hash")
	private String minionsHash;
	
    /*权重*/
	@Column(label = "weight")
	private Double weight;
	
    /*评分变动*/
	@Column(label = "points")
	private Integer points;
	
    /*0 我方数据 1敌方数据*/
	@Column(label = "data_from")
	private Integer dataFrom;
	
    /*操作序列，json 文本*/
	@Column(label = "actions")
	private String actions;
	

	public void setId(String value) {
		this.id = value;
	}
	public String getId() {
		return this.id;
	}
	public void setPlayfieldId(String value) {
		this.playfieldId = value;
	}
	public String getPlayfieldId() {
		return this.playfieldId;
	}
	public void setMinionsHash(String value) {
		this.minionsHash = value;
	}
	public String getMinionsHash() {
		return this.minionsHash;
	}
	public void setWeight(Double value) {
		this.weight = value;
	}
	public Double getWeight() {
		return this.weight;
	}
	public void setPoints(Integer value) {
		this.points = value;
	}
	public Integer getPoints() {
		return this.points;
	}
	public void setDataFrom(Integer value) {
		this.dataFrom = value;
	}
	public Integer getDataFrom() {
		return this.dataFrom;
	}
	public void setActions(String value) {
		this.actions = value;
	}
	public String getActions() {
		return this.actions;
	}

}

