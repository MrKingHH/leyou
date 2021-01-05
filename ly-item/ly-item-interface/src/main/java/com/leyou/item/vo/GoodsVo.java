package com.leyou.item.vo;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author 金余新
 * @date 2021/1/5 14:42
 */
@Data
public class GoodsVo {
    /**当前页索引*/
    @NotNull
    private Integer pageIndex;
    /**每页大小*/
    @NotNull
    private Integer rows;
    /**按哪个字段排序*/
    private Boolean saleAble;
    /**搜索关键字*/
    private String key;
}
