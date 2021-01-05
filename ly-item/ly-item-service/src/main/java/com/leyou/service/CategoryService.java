package com.leyou.service;

import com.leyou.item.pojo.Category;
import com.leyou.mapper.CategoryMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CategoryService {

    public static final Logger LOGGER = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryListByParent(Long pid) {
        LOGGER.info("开始分类查询服务");
        Category category = new Category();
        category.setParentId(pid);
        List<Category> categories = this.categoryMapper.select(category);
        LOGGER.info("结束分类查询服务");
        return categories;
    }

    public List<Category> queryByBrandId(Long bid) {
        return this.categoryMapper.queryByBrandId(bid);
    }

    /**
     * 根据商品分类ID批量查询商品分类名称
     * */
    public List<String> queryCategoryNameByIds(List<Long> categoryIds) {
        return categoryIds.stream().map(categoryId -> {
            //根据主键查询商品ID
            Category category = this.categoryMapper.selectByPrimaryKey(categoryId);
            //返回每一个商品的名称
            return category.getName();
        }).collect(Collectors.toList());
    }
}
