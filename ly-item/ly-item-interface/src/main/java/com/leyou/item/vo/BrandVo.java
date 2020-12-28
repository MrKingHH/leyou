package com.leyou.item.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class BrandVo {
    /**当前页索引*/
    @NotNull
    private Integer pageIndex;
    /**每页大小*/
    @NotNull
    private Integer rows;
    /**按哪个字段排序*/
    private String sortBy;
    /**是否降序*/
    private Boolean desc;
    /**搜索关键字*/
    private String key;
}
