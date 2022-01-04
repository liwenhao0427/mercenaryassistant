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
@Entity(tableName = "view_action_statistics",label = "")
public class ViewActionStatistics implements Serializable {

	/**/
	@Column(label = "action_id")
	private Integer actionId;
	
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
	

	public void setActionId(Integer value) {
		this.actionId = value;
	}
	public Integer getActionId() {
		return this.actionId;
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

