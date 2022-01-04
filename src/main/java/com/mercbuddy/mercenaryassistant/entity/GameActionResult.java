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
@Entity(tableName = "game_action_result",label = "")
public class GameActionResult implements Serializable {

    /*主键*/
	@Column(isPK = true,label = "id")
	private Integer id;
	
    /*对局记录id*/
	@Column(label = "record_id")
	private Integer recordId;
	
    /*操作id*/
	@Column(label = "action_id")
	private Integer actionId;
	
    /*场面评分变化*/
	@Column(label = "point")
	private Double point;
	
    /*最终胜负 -1 败 0 未知 1 胜*/
	@Column(label = "result")
	private Integer result;
	
    /*天梯排名 -1 表示为 pve*/
	@Column(label = "rank")
	private Integer rank;
	
    /*创建日期*/
	@Column(label = "create_time")
	private Date createTime;
	

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setRecordId(Integer value) {
		this.recordId = value;
	}
	public Integer getRecordId() {
		return this.recordId;
	}
	public void setActionId(Integer value) {
		this.actionId = value;
	}
	public Integer getActionId() {
		return this.actionId;
	}
	public void setPoint(Double value) {
		this.point = value;
	}
	public Double getPoint() {
		return this.point;
	}
	public void setResult(Integer value) {
		this.result = value;
	}
	public Integer getResult() {
		return this.result;
	}
	public void setRank(Integer value) {
		this.rank = value;
	}
	public Integer getRank() {
		return this.rank;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getCreateTime() {
		return this.createTime;
	}

}

