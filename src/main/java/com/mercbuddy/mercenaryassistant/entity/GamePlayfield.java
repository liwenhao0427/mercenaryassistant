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
@Entity(tableName = "game_playfield",label = "")
public class GamePlayfield implements Serializable {

    /*主键*/
	@Column(isPK = true,label = "id")
	private Integer id;
	
    /*我方佣兵生命值，根据佣兵顺序排序，逗号隔开*/
	@Column(label = "own_minions_hp")
	private String ownMinionsHp;
	
    /*敌方佣兵生命值，根据佣兵顺序排序，逗号隔开*/
	@Column(label = "enemy_minions_hp")
	private String enemyMinionsHp;
	
    /*我方佣兵附魔，单佣兵逗号隔开，多佣兵分号隔开，技能附魔标识123*/
	@Column(label = "own_minions_enchs")
	private String ownMinionsEnchs;
	
    /*敌方佣兵附魔，单佣兵逗号隔开，多佣兵分号隔开*/
	@Column(label = "enemy_minions_enchs")
	private String enemyMinionsEnchs;
	
    /*我方佣兵攻击力，根据佣兵顺序排序，逗号隔开*/
	@Column(label = "own_minions_atk")
	private String ownMinionsAtk;
	
    /*敌方佣兵攻击力，根据佣兵顺序排序，逗号隔开*/
	@Column(label = "enemy_minions_atk")
	private String enemyMinionsAtk;
	
    /*我方佣兵名称，排序，逗号隔开*/
	@Column(label = "own_minions_name")
	private String ownMinionsName;
	
    /*敌方佣兵名称，排序，逗号隔开*/
	@Column(label = "enemy_minions_name")
	private String enemyMinionsName;
	
    /*以上信息相加 hash，确保不重复*/
	@Column(label = "hash")
	private String hash;
	
    /*仅根据佣兵 hash 结果*/
	@Column(label = "minions_hash")
	private String minionsHash;
	

	public void setId(Integer value) {
		this.id = value;
	}
	public Integer getId() {
		return this.id;
	}
	public void setOwnMinionsHp(String value) {
		this.ownMinionsHp = value;
	}
	public String getOwnMinionsHp() {
		return this.ownMinionsHp;
	}
	public void setEnemyMinionsHp(String value) {
		this.enemyMinionsHp = value;
	}
	public String getEnemyMinionsHp() {
		return this.enemyMinionsHp;
	}
	public void setOwnMinionsEnchs(String value) {
		this.ownMinionsEnchs = value;
	}
	public String getOwnMinionsEnchs() {
		return this.ownMinionsEnchs;
	}
	public void setEnemyMinionsEnchs(String value) {
		this.enemyMinionsEnchs = value;
	}
	public String getEnemyMinionsEnchs() {
		return this.enemyMinionsEnchs;
	}
	public void setOwnMinionsAtk(String value) {
		this.ownMinionsAtk = value;
	}
	public String getOwnMinionsAtk() {
		return this.ownMinionsAtk;
	}
	public void setEnemyMinionsAtk(String value) {
		this.enemyMinionsAtk = value;
	}
	public String getEnemyMinionsAtk() {
		return this.enemyMinionsAtk;
	}
	public void setOwnMinionsName(String value) {
		this.ownMinionsName = value;
	}
	public String getOwnMinionsName() {
		return this.ownMinionsName;
	}
	public void setEnemyMinionsName(String value) {
		this.enemyMinionsName = value;
	}
	public String getEnemyMinionsName() {
		return this.enemyMinionsName;
	}
	public void setHash(String value) {
		this.hash = value;
	}
	public String getHash() {
		return this.hash;
	}
	public void setMinionsHash(String value) {
		this.minionsHash = value;
	}
	public String getMinionsHash() {
		return this.minionsHash;
	}

}

