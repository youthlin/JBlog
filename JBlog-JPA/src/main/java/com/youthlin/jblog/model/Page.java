package com.youthlin.jblog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2016-09-08-008.
 * 分页
 */
public class Page<T> implements Serializable {
    private int pageIndex = 0;//当前第几页
    private int pageTotal = 1;//共几页
    private int countPerPage = 5;//每页多少个
    private List<T> item = new ArrayList<>(0);

    public Page() {
    }

    public Page(int pageIndex, int pageTotal, int countPerPage, List<T> item) {
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

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageTotal() {
        return pageTotal;
    }

    public void setPageTotal(int pageTotal) {
        this.pageTotal = pageTotal;
    }

    public int getCountPerPage() {
        return countPerPage;
    }

    public void setCountPerPage(int countPerPage) {
        this.countPerPage = countPerPage;
    }

    public List<T> getItem() {
        return item;
    }

    public void setItem(List<T> item) {
        this.item = item;
    }
}
