package com.leyou.service;

import com.leyou.item.pojo.Category;
import com.leyou.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryListByParent(Long pid) {
        LOGGER.info("开始分类查询服务");
        Category category = new Category();
        category.setParentId(pid);
        LOGGER.info("开始分类查询服务");
        return this.categoryMapper.select(category);
    }

    public List<Category> queryByBrandId(Long bid) {
        return this.categoryMapper.queryByBrandId(bid);
    }
}
