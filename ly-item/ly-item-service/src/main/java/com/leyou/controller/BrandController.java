package com.leyou.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.dto.BrandDTO;
import com.leyou.item.pojo.Brand;
import com.leyou.item.vo.BrandVo;
import com.leyou.service.BrandService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("brand")
public class BrandController {

    public static final Logger LOGGER = LoggerFactory.getLogger(BrandController.class);

    @Autowired
    private BrandService brandService;

    @PostMapping("page")
    public ResponseEntity<PageResult<Brand>> queryBrandByPage(@Validated @RequestBody BrandVo brandVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOGGER.error("前端传参出错");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        BrandDTO brandDTO = new BrandDTO();
        BeanUtils.copyProperties(brandVo, brandDTO);
        PageResult<Brand> brandPageResult = brandService.queryBrandByPageAndSort(brandDTO);

        return ResponseEntity.ok(brandPageResult);
    }

    /**
     * 新增品牌
     * @param brand
     * @return
     */
    @PostMapping
    public ResponseEntity<Void> saveBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        this.brandService.saveBrand(brand, cids);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    /**
     * 修改品牌
     * @param brand
     * @return
     */
    @PutMapping
    public ResponseEntity<Void> editBrand(Brand brand, @RequestParam("cids") List<Long> cids) {
        this.brandService.editBrand(brand, cids);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
