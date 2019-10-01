package com.design.yang.BaseResponse;

import javax.validation.constraints.Max;
import java.util.List;

/**
 * @program: yang
 * @description:
 * @author: 阳
 * @create: 2019-05-23 21:06
 */
public class BasePageInfo<E> {
    int pageNum;
    int pageSize;
    long count;
    List<E> list ;


    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<E> getList() {
        return list;
    }

    public void setList(List<E> list) {
        this.list = list;
    }

    public BasePageInfo(int pageNum, int pageSize,long count, List<E> list) {
        this.pageNum = pageNum;
        this.pageSize = pageSize;
        this.list = list;
        this.count = count;
    }

    public BasePageInfo() {
    }
}
