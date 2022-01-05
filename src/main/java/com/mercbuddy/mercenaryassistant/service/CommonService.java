package com.mercbuddy.mercenaryassistant.service;

import com.mercbuddy.mercenaryassistant.entity.Log;
import com.mercbuddy.mercenaryassistant.util.GenerateSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;

/**
 * @author 李文浩
 * @date 2021/12/31 10:58
 */
@Service
public class CommonService {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * 插入语句
     * @param object
     */
    public void insert(Object object){
        String sql = GenerateSql.insertSQL(object.getClass(), object);
        jdbcTemplate.update(sql);
    }

    /**
     * 插入语句
     * @param object
     */
    public int insertWithIdReturn(Object object) {
        String sql = GenerateSql.insertSQL(object.getClass(), object);
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection conn) throws SQLException {
                PreparedStatement ps = conn.prepareStatement(sql, new String[]{"id"});
                return ps;
            }
        }, keyHolder);
        return keyHolder.getKey().intValue();
    }

    /**
     * 插入语句
     * @param object
     */
    public void replace(Object object){
        String sql = GenerateSql.replaceIntoSQL(object.getClass(), object);
        jdbcTemplate.update(sql);
    }

    /**
     * 记录日志
     * @param ip
     * @param userId
     * @param result
     * @param msg
     */
    public void insertLog(String ip, String userId, Integer result, String msg, String interfaceName){
        Log log = new Log();
        log.setCreateTime(new Date());
        log.setInterfaceName(interfaceName);
        log.setIp(ip);
        log.setMsg(msg);
        log.setResult(result);
        log.setUserId(userId);
        insert(log);
    }
}
