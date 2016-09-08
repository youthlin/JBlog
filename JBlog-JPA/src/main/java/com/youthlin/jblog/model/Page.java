package com.youthlin.jblog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2016-09-08-008.
 * 分页
 */
public class Page<T> implements Serializable {
    private long pageIndex = 0;//当前第几页
    private long pageTotal = 1L;//共几页
    private long countPerPage = 5;//每页多少个
    private List<T> item = new ArrayList<>(0);

    public Page() {
    }

    public Page(long pageIndex, long pageTotal, long countPerPage, List<T> item) {
        this.pageIndex = pageIndex;
        this.pageTotal = pageTotal;
        this.countPerPage = countPerPage;
        this.item = item;
    }

    @Override
    public String toString() {
        return "Page{" +
                "pageIndex=" + pageIndex +
                ", pageTotal=" + pageTotal +
                ", countPerPage=" + countPerPage +
                ", itemSize=" + item.size() +
                '}';
    }

    public long getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(long pageIndex) {
        this.pageIndex = pageIndex;
    }

    public long getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(long pageTotal) {
        this.pageTotal = pageTotal;
    }

    public long getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(long countPerPage) {
        this.countPerPage = countPerPage;
    }

    public List<T> getItem() {
        return item;
    }

    public void setItem(List<T> item) {
        this.item = item;
    }
}
