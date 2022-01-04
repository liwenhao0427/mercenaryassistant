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
 * @createTime - 2022-01-04
 */
@Entity(tableName = "sys_log",label = "")
public class Log implements Serializable {

	private static final long serialVersionUID = -5642778561888790994L;

    /*主键*/
	@Column(isPK = true,label = "id")
	private Integer id;
	
    /*战网id*/
	@Column(label = "user_id")
	private String userId;
	
    /*ip地址*/
	@Column(label = "ip")
	private String ip;
	
    /*调用接口名称*/
	@Column(label = "interface_name")
	private String interfaceName;
	
    /*0 报错 1 成功返回*/
	@Column(label = "result")
	private Integer result;
	
    /*额外信息*/
	@Column(label = "msg")
	private String msg;
	
    /*上次更新时间*/
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
	public void setIp(String value) {
		this.ip = value;
	}
	public String getIp() {
		return this.ip;
	}
	public void setInterfaceName(String value) {
		this.interfaceName = value;
	}
	public String getInterfaceName() {
		return this.interfaceName;
	}
	public void setResult(Integer value) {
		this.result = value;
	}
	public Integer getResult() {
		return this.result;
	}
	public void setMsg(String value) {
		this.msg = value;
	}
	public String getMsg() {
		return this.msg;
	}
	public void setCreateTime(Date value) {
		this.createTime = value;
	}
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+08:00")
	public Date getCreateTime() {
		return this.createTime;
	}

}

