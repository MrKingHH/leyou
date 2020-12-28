package com.leyou.service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.leyou.common.pojo.PageResult;
import com.leyou.dto.BrandDTO;
import com.leyou.item.pojo.Brand;
import com.leyou.mapper.BrandMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

import static com.github.pagehelper.PageHelper.startPage;


@Service
public class BrandService {

    public static final Logger LOGGER = LoggerFactory.getLogger(BrandService.class);

    @Autowired
    private BrandMapper brandMapper;

    public PageResult<Brand> queryBrandByPageAndSort(BrandDTO brandDTO) {
        LOGGER.info("开始商品分页查询服务");
        startPage(brandDTO.getPageIndex(), brandDTO.getRows());
        // 过滤
        Example example  = new Example(Brand.class);
        if (brandDTO.getKey() != null) {
            example.createCriteria().andLike("name", "%"+brandDTO.getKey()+"%").orEqualTo("letter", brandDTO.getKey().toUpperCase());
        }

        if (brandDTO.getSortBy() != null) {
            String orderByClause = brandDTO.getSortBy() + (brandDTO.getDesc() ? " DESC" : " ASC");
            example.setOrderByClause(orderByClause);
        }

        Page<Brand> pageInfo = (Page<Brand>)brandMapper.selectByExample(example);
        LOGGER.info("结束商品分页查询服务");
        return new PageResult<>(pageInfo.getTotal(), pageInfo);
    }

    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        this.brandMapper.insert(brand);
        for (Long cid : cids) {
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
        }
    }
}
