package com.yy.core.common;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.yy.core.utils.CollectionCopyUtil;

import java.util.List;

/**
 * CommonPage: 分页数据封装类
 *
 * @Author: yangyang
 * @Date: 2021/1/17 14:30
 */
public class CommonPage<T> {

    /**
     * 当前页
     */
    private Integer pageNum;
    /**
     * 每页行数
     */
    private Integer pageSize;
    /**
     * 总页数
     */
    private Integer totalPage;
    /**
     * 总条数
     */
    private Long total;
    /**
     * 数据
     */
    private List<T> list;

    /**
     * 将Page分页后的list转为分页信息
     */
    public static <T> CommonPage<T> restPage(IPage<T> page) {
        CommonPage<T> result = new CommonPage<T>();
        result.setTotalPage((int) page.getPages());
        result.setPageNum((int) page.getCurrent());
        result.setPageSize((int) page.getSize());
        result.setTotal(page.getTotal());
        result.setList(page.getRecords());
        return result;
    }

    /**
     * 将Page分页后的list转为分页信息
     */
    public static <T,S> CommonPage<T> restPage(IPage<S> page, Class tClass) {
        CommonPage<T> result = new CommonPage<>();
        result.setTotalPage((int) page.getPages());
        result.setPageNum((int) page.getCurrent());
        result.setPageSize((int) page.getSize());
        result.setTotal(page.getTotal());
        result.setList(CollectionCopyUtil.copyProperties(page.getRecords(), tClass));
        return result;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getTotalPage() {
        return totalPage;
    }

    public void setTotalPage(Integer totalPage) {
        this.totalPage = totalPage;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }

    public List<T> getList() {
        return list;
    }

    public void setList(List<T> list) {
        this.list = list;
    }
}
