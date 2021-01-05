package com.leyou.service;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.mapper.SpecGroupMapper;
import com.leyou.mapper.SpecParamMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 金余新
 * @date 2020/12/31 10:14
 */
@Service
public class SpecificationService {

    public static final Logger LOGGER = LoggerFactory.getLogger(SpecificationService.class);

    @Autowired
    private SpecGroupMapper specGroupMapper;

    @Autowired
    private SpecParamMapper specParamMapper;

    public List<SpecGroup> querySpecGroups(Long cid) {
        SpecGroup t = new SpecGroup();
        t.setCid(cid);
        return this.specGroupMapper.select(t);
    }

    public List<SpecParam> querySpecParams(Long gid){
        SpecParam t = new SpecParam();
        t.setGroupId(gid);
        return this.specParamMapper.select(t);
    }

    public void addSpecGroup(SpecGroup specGroup) {
        LOGGER.info("开始规格组增加服务");
        this.specGroupMapper.insert(specGroup);
        LOGGER.info("结束规格组增加服务");
    }

    public void editSpecGroup(SpecGroup specGroup) {
        LOGGER.info("开始规格组编辑服务");
        this.specGroupMapper.updateByPrimaryKey(specGroup);
        LOGGER.info("结束规格组编辑服务");
    }

    public void delSpecGroup(Long gid) {
        LOGGER.info("开始规格组删除服务");
        this.specGroupMapper.deleteByPrimaryKey(gid);
        LOGGER.info("结束规格组删除服务");
    }

    public void addSpecParam(SpecParam specParam) {
        LOGGER.info("开始规格参数增加服务");
        this.specParamMapper.insert(specParam);
        LOGGER.info("结束规格参数增加服务");
    }

    public void editSpecParam(SpecParam specParam) {
        LOGGER.info("开始规格参数编辑服务");
        this.specParamMapper.updateByPrimaryKey(specParam);
        LOGGER.info("结束规格参数编辑服务");
    }

    public void delSpecParam(Long id) {
        LOGGER.info("开始规格参数删除服务");
        this.specParamMapper.deleteByPrimaryKey(id);
        LOGGER.info("结束规格参数删除服务");
    }

}
