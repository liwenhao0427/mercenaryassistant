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
@Entity(tableName = "game_record",label = "")
public class GameRecord implements Serializable {

	/*主键自增*/
	@Column(isPK = true,label = "id")
	private Integer id;
	
    /*战网id，外键*/
	@Column(label = "user_id")
	private String userId;
	
    /*对手战网id*/
	@Column(label = "enemy_id")
	private String enemyId;
	
    /*我方阵容，战斗中出场的佣兵，逗号隔开*/
	@Column(label = "own_team")
	private String ownTeam;
	
    /*敌方阵容，战斗中出场的佣兵，逗号隔开*/
	@Column(label = "enemy_team")
	private String enemyTeam;
	
    /*-1 失败 0 未知 1 胜利 2平局*/
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
	public void setUserId(String value) {
		this.userId = value;
	}
	public String getUserId() {
		return this.userId;
	}
	public void setEnemyId(String value) {
		this.enemyId = value;
	}
	public String getEnemyId() {
		return this.enemyId;
	}
	public void setOwnTeam(String value) {
		this.ownTeam = value;
	}
	public String getOwnTeam() {
		return this.ownTeam;
	}
	public void setEnemyTeam(String value) {
		this.enemyTeam = value;
	}
	public String getEnemyTeam() {
		return this.enemyTeam;
	}
	public void setResult(Integer value) {
		this.result = value;
	}
	public Integer getResult() {
		return this.result;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getCreateTime() {
		return this.createTime;
	}
	public Integer getRank() {
		return rank;
	}
	public void setRank(Integer rank) {
		this.rank = rank;
	}

}

