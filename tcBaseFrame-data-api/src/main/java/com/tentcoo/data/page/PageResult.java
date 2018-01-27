package com.tentcoo.data.page;

import java.io.Serializable;
import java.util.List;

/**
 * @author Administrator
 * @date 2018/1/27 0027
 */
public class PageResult implements Serializable {
    private Integer currentPage;
    private Integer pageSize;
    private List<?> list;
    private int     totalCount;
    private int     totalPage;
    private int     prevPage;
    private int     nextPage;

    public PageResult(int currentPage, int pageSize, List<?> list, int totalCount) {
        this.currentPage = currentPage;
        this.pageSize = pageSize;
        this.list = list;
        this.totalCount = totalCount;

        totalPage = totalCount % pageSize == 0 ? totalCount / pageSize : totalCount / pageSize + 1;
        prevPage = currentPage - 1 > 1 ? currentPage - 1 : 1;
        nextPage = currentPage + 1 > totalPage ? totalPage : currentPage + 1;
    }

    public PageResult() {
    }

    public Integer getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {
        this.currentPage = currentPage;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public List<?> getList() {
        return list;
    }

    public void setList(List<?> list) {
        this.list = list;
    }

    public int getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(int totalCount) {
        this.totalCount = totalCount;
    }

    public int getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getPrevPage() {
        return prevPage;
    }

    public void setPrevPage(int prevPage) {
        this.prevPage = prevPage;
    }

    public int getNextPage() {
        return nextPage;
    }

    public void setNextPage(int nextPage) {
        this.nextPage = nextPage;
    }
}
