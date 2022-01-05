package com.mercbuddy.mercenaryassistant.filter;

import com.mercbuddy.mercenaryassistant.service.CommonService;
import com.mercbuddy.mercenaryassistant.util.CommonUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.Enumeration;

/**
 * @author 李文浩
 * @date 2021/12/29 10:13
 * 简单防注入处理
 */
@Component
public class SqlInjectionFilter implements Filter {
//public class SqlInjectionFilter  {

    @Autowired
    CommonService commonService;

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req=(HttpServletRequest)servletRequest;
        //获得所有请求参数名
        Enumeration params = req.getParameterNames();
        String sql = "";
        while (params.hasMoreElements()) {
            // 得到参数名
            String name = params.nextElement().toString();
            // 得到参数对应值
            String[] value = req.getParameterValues(name);
            for (int i = 0; i < value.length; i++) {
                sql = sql + value[i];
            }
        }
        Integer keyWord = sqlValidate(sql);
        if (sqlValidate(sql) != null) {
            commonService.insertLog(CommonUtils.getIpAddr(req), req.getHeader("userId"), 0, "非法字符：" + keyWord, "");
            throw new IOException("您发送请求中的参数中含有非法字符，错误代码" + keyWord);
        } else {
            chain.doFilter(servletRequest,servletResponse);
        }
    }

    // TODO 验证 Token

    /**
     * 关键词校验
     * @param str
     * @return
     */
    public static Integer sqlValidate(String str) {
        // 统一转为小写
        str = str.toLowerCase();
        // 过滤掉的sql关键字，可以手动添加
//        String badStr = "'|and|exec|execute|insert|select|delete|update|count|drop|*|%|chr|mid|master|truncate|" +
//                "char|declare|sitename|net user|xp_cmdshell|;|or|-|+|like'|and|exec|execute|insert|create|drop|" +
//                "table|from|grant|use|group_concat|column_name|" +
//                "information_schema.columns|table_schema|union|where|select|delete|update|order|by|count|*|" +
//                "chr|mid|master|truncate|char|declare|or|;|-|--|+|,|like|//|/|%|#";
        String badStr = "'|*|%|;|--|//|/|%|#";
        String[] badStrs = badStr.split("\\|");
        for (int i = 0; i < badStrs.length; i++) {
            if (str.indexOf(badStrs[i]) >= 0) {
                System.out.println(str);
                return i;
            }
        }
        return null;
    }
}