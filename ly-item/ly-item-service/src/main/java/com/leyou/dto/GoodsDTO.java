package com.leyou.dto;


/**
 * @author 金余新
 * @date 2021/1/5 10:54
 */
public class GoodsDTO {
    //当前页索引
    private Integer pageIndex;
    //每页大小
    private Integer rows;
    //按是否下架
    private Boolean saleAble;

    public Boolean getSaleAble() {
        return saleAble;
    }

    public void setSaleAble(Boolean saleAble) {
        this.saleAble = saleAble;
    }

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


    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}
