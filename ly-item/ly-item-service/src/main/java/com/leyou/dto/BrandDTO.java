package com.leyou.dto;

import javax.validation.constraints.NotBlank;


public class BrandDTO {
    //当前页索引
    private Integer pageIndex;
    //每页大小
    private Integer rows;
    //按哪个字段排序
    private String sortBy;
    //是否降序
    private Boolean desc;
    //搜索关键字
    private String key;

    public Integer getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(Integer pageIndex) {
        this.pageIndex = pageIndex;
    }

    public Integer getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        this.rows = rows;
    }

    public String getSortBy() {
        return sortBy;
    }

    public void setSortBy(String sortBy) {
        this.sortBy = sortBy;
    }

    public Boolean getDesc() {
        return desc;
    }

    public void setDesc(Boolean desc) {
        this.desc = desc;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
