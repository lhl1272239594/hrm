package com.jims.sys.vo;

/**
 * Created by heren on 2016/7/12.
 */
public class SqlAdapter {
    private String sql ;

    public SqlAdapter(String sql) {
        this.sql = sql;
    }

    public String getSql() {
        return sql;
    }

    public void setSql(String sql) {
        this.sql = sql;
    }
}
