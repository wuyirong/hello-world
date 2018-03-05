package com.tentcoo.data.page;

import java.io.Serializable;

/**
 * @author Administrator
 * @date 2018/1/27 0027
 */
public class QueryObject implements Serializable {
    //当前页
    private Integer currentPage = 1;
    //分页页数
    private Integer rows        = 2;

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public int getStart() {
        return (this.currentPage - 1) * this.rows;
    }
}
