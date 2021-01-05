package com.leyou.service;

import com.github.pagehelper.Page;
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

    /**
     * 根据商品ID查询商品实体
     * */
    public Brand queryBrandByBrandId(Long bId) {
        return this.brandMapper.selectByPrimaryKey(bId);
    }

    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        this.brandMapper.insert(brand);
        for (Long cid : cids) {
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
        }
    }

    @Transactional
    public void editBrand(Brand brand, List<Long> cids) {
        LOGGER.info("开始修改商品属性");
        //修改商品基本属性
        this.brandMapper.updateByPrimaryKey(brand);
        //更新新的商品分类ID
        //目前最简单的思路就是删除原有的所有关联ID，重新插入
        LOGGER.info("开始删除原有商品分类");
        this.brandMapper.deleteCategoryBrand(brand.getId());
        LOGGER.info("结束删除原有商品分类");
        //重新插入商品的对应的分类ID
        for (Long cid : cids) {
            this.brandMapper.insertCategoryBrand(cid, brand.getId());
            LOGGER.info("结束修改商品属性");
        }
    }

    @Transactional
    public int deleteBrand(Long bid) {
        LOGGER.info("开始删除商品");
        int row = this.brandMapper.deleteByPrimaryKey(bid);
        LOGGER.info("结束删除商品");
        return row;
    }
}
