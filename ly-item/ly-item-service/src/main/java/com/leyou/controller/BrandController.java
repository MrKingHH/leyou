package com.leyou.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.dto.BrandDTO;
import com.leyou.item.pojo.Brand;
import com.leyou.item.vo.BrandVo;
import com.leyou.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("brand")
public class BrandController {

    @Autowired
    private BrandService brandService;

    @GetMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(@Validated @RequestBody BrandVo brandVo, BindingResult bindingResult) {
        BrandDTO brandDTO = new BrandDTO();

        PageResult<Brand> brandPageResult = brandService.queryBrandByPageAndSort(brandDTO);

        return ResponseEntity.ok(brandPageResult);
    }

}
