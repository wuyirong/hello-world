package com.tentcoo.data.page;

import java.io.Serializable;
import java.util.List;

/**
 * 页面内容
 *
 * @author Administrator
 * @date 2018/1/27 0027
 */
public class PageResult implements Serializable {
    private Integer currentPage;
    private Integer pageSize;
    private List<?> rows;
    private int     totalCount;
    private int     totalPage;
    private int     prevPage;
    private int     nextPage;

    public PageResult(int currentPage, int pageSize, List<?> list, int totalCount) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.rows = list;
        this.totalCount = totalCount;

        totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        prevPage = currentPage - 1 > 1 ? currentPage - 1 : 1;
        nextPage = currentPage + 1 > totalPage ? totalPage : currentPage + 1;
    }

    public PageResult() {
    }

    public PageResult(int totalCount, List<?> rows) {
        this.totalCount = totalCount;
        this.rows = rows;
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public List<?> getRows() {
        return rows;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }
}
