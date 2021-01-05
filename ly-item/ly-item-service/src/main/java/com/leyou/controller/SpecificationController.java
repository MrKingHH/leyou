package com.leyou.controller;

import com.leyou.item.pojo.SpecGroup;
import com.leyou.item.pojo.SpecParam;
import com.leyou.service.SpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author 金余新
 * @date 2020/12/31 10:15
 */
@RestController
@RequestMapping("spec")
public class SpecificationController {

    @Autowired
    private SpecificationService specificationService;

    /**
     * 查询规格参数组
     * */
    @GetMapping("groups/{cid}")
    public ResponseEntity<List<SpecGroup>> querySpecGroups(@PathVariable("cid") Long cid){
        List<SpecGroup> list = this.specificationService.querySpecGroups(cid);
        if(list == null || list.size() == 0){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }


    /**
     * 新增规格参数组
     * */
    @PostMapping("group")
    public ResponseEntity<Void> addSpecGroup(@RequestBody SpecGroup group){
        this.specificationService.addSpecGroup(group);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 编辑规格参数组
     * */
    @PutMapping("group")
    public ResponseEntity<Void> editSpecGroup(@RequestBody SpecGroup group){
        this.specificationService.editSpecGroup(group);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 删除规格参数组
     * */
    @DeleteMapping("group/{gid}")
    public ResponseEntity<Void> deleteSpecGroup(@PathVariable(value="gid", required = false) Long gid){
        this.specificationService.delSpecGroup(gid);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 查询规格参数
     * */
    @GetMapping("/params")
    public ResponseEntity<List<SpecParam>> querySpecParam(@RequestParam(value="gid", required = false) Long gid){
        List<SpecParam> list =
                this.specificationService.querySpecParams(gid);
        if(list == null || list.isEmpty()){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(list);
    }

    /**
     * 增加规格参数
     * */
    @PostMapping("param")
    public ResponseEntity<Void> addSpecParam(@RequestBody SpecParam specParam){
        this.specificationService.addSpecParam(specParam);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 编辑规格参数
     * */
    @PutMapping("param")
    public ResponseEntity<Void> editSpecParam(@RequestBody SpecParam specParam){
        this.specificationService.editSpecParam(specParam);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    /**
     * 删除规格参数
     * */
    @DeleteMapping("param/{id}")
    public ResponseEntity<Void> deleteSpecParam(@PathVariable(value="id", required = false) Long id){
        this.specificationService.delSpecParam(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}