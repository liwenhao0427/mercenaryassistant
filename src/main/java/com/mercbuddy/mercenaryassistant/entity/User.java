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
@Entity(tableName = "sys_user",label = "")
public class User implements Serializable {

	/*主键，直接使用战网 id 加密后做主键*/
	@Column(isPK = true,label = "id")
	private String id;
	
    /*盐*/
	@Column(label = "salt")
	private String salt;
	
    /*预留字段，id+password+salt md5 加密2次*/
	@Column(label = "password")
	private String password;
	
    /*删除 0 正常 1 删除*/
	@Column(label = "is_delete")
	private Integer isDelete;
	
    /*状态 0 正常 1 封禁*/
	@Column(label = "status")
	private Integer status;
	
    /*创建日期*/
	@Column(label = "create_time")
	private Date createTime;
	
    /*验证token*/
	@Column(label = "token")
	private String token;
	
    /*更新时间*/
	@Column(label = "update_time")
	private Date updateTime;
	
    /*上次登录 ip，请求数据时验证 ip 是否一致*/
	@Column(label = "ip")
	private String ip;
	

	public void setId(String value) {
		this.id = value;
	}
	public String getId() {
		return this.id;
	}
	public void setSalt(String value) {
		this.salt = value;
	}
	public String getSalt() {
		return this.salt;
	}
	public void setPassword(String value) {
		this.password = value;
	}
	public String getPassword() {
		return this.password;
	}
	public void setIsDelete(Integer value) {
		this.isDelete = value;
	}
	public Integer getIsDelete() {
		return this.isDelete;
	}
	public void setStatus(Integer value) {
		this.status = value;
	}
	public Integer getStatus() {
		return this.status;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getCreateTime() {
		return this.createTime;
	}
	public void setToken(String value) {
		this.token = value;
	}
	public String getToken() {
		return this.token;
	}
	public void setUpdateTime(Date value) {
		this.updateTime = value;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getUpdateTime() {
		return this.updateTime;
	}
	public void setIp(String value) {
		this.ip = value;
	}
	public String getIp() {
		return this.ip;
	}

}

