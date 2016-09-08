package com.youthlin.jblog.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by lin on 2016-09-08-008.
 * 分页
 */
public class Page<T> implements Serializable {
    private long index = 0;//当前第几页
    private long count = 1L;//共几页
    private long size = 5;//每页多少个
    private List<T> item = new ArrayList<>(0);

    public Page() {
    }

    public Page(long index, long count, long size, List<T> item) {
        this.index = index;
        this.count = count;
        this.size = size;
        this.item = item;
    }

    @Override
    public String toString() {
        return "Page{" +
                "index=" + index +
                ", count=" + count +
                ", size=" + size +
                ", itemSize=" + item.size() +
                '}';
    }

    public long getIndex() {
        return index;
    }

    public void setIndex(long index) {
        this.index = index;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public List<T> getItem() {
        return item;
    }

    public void setItem(List<T> item) {
        this.item = item;
    }
}
