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
@Entity(tableName = "game_playfield_action",label = "")
public class GamePlayfieldAction implements Serializable {

	/*主键，自增*/
	@Column(isPK = true,label = "id")
	private Integer id;
	
    /*对局记录 id*/
	@Column(label = "playfield_id")
	private Integer playfieldId;
	
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
	
    /*根据 playfield_id 和 actions hash后的结果，确保记录唯一*/
	@Column(label = "hash")
	private String hash;
	

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setPlayfieldId(Integer value) {
		this.playfieldId = value;
	}
	public Integer getPlayfieldId() {
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
	public void setHash(String value) {
		this.hash = value;
	}
	public String getHash() {
		return this.hash;
	}

}

