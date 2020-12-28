package com.leyou.item.vo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class BrandVo {
    //当前页索引
    @NotBlank
    private Integer pageIndex;
    //每页大小
    @NotBlank
    private Integer rows;
    //按哪个字段排序
    private String sortBy;
    //是否降序
    private Boolean desc;
    //搜索关键字
    private String key;
}
