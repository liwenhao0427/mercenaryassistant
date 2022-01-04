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
 * @createTime - 2021-12-31
 */
@Entity(tableName = "view_action",label = "")
public class ViewAction implements Serializable {

	/*主键，自增*/
	@Column(label = "id")
	private Integer id;
	
    /*操作序列，json 文本*/
	@Column(label = "actions")
	private String actions;
	
    /*对局记录 id*/
	@Column(label = "playfield_id")
	private Integer playfieldId;
	
    /*权重*/
	@Column(label = "weight")
	private Double weight;
	
    /**/
	@Column(label = "cnt")
	private Long cnt;
	
    /**/
	@Column(label = "point")
	private Double point;
	
    /**/
	@Column(label = "win_rate")
	private BigDecimal winRate;
	
    /**/
	@Column(label = "rank")
	private Long rank;
	

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setActions(String value) {
		this.actions = value;
	}
	public String getActions() {
		return this.actions;
	}
	public void setPlayfieldId(Integer value) {
		this.playfieldId = value;
	}
	public Integer getPlayfieldId() {
		return this.playfieldId;
	}
	public void setWeight(Double value) {
		this.weight = value;
	}
	public Double getWeight() {
		return this.weight;
	}
	public void setCnt(Long value) {
		this.cnt = value;
	}
	public Long getCnt() {
		return this.cnt;
	}
	public void setPoint(Double value) {
		this.point = value;
	}
	public Double getPoint() {
		return this.point;
	}
	public void setWinRate(java.math.BigDecimal value) {
		this.winRate = value;
	}
	public BigDecimal getWinRate() {
		return this.winRate;
	}
	public void setRank(Long value) {
		this.rank = value;
	}
	public Long getRank() {
		return this.rank;
	}

}

