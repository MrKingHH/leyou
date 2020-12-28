package com.leyou.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.common.pojo.PageResult;
import com.leyou.dto.BrandDTO;
import com.leyou.item.pojo.Brand;
import com.leyou.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;


@Service
public class BrandService {

    @Autowired
    private BrandMapper brandMapper;

    public PageResult<Brand> queryBrandByPageAndSort(BrandDTO brandDTO) {
        PageHelper.startPage(brandDTO.getPageIndex(), brandDTO.getRows());
        // 过滤
        Example example = new Example(Brand.class);
        Page<Brand> pageInfo = (Page<Brand>)brandMapper.selectByExample(example);
        return new PageResult<>(pageInfo.getTotal(), pageInfo);
    }
}
