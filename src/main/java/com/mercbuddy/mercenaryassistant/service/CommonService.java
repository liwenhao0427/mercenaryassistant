package com.mercbuddy.mercenaryassistant.service;

import com.mercbuddy.mercenaryassistant.util.GenerateSql;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

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
}
