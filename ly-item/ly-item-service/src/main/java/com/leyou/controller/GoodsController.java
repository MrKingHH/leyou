package com.leyou.controller;

import com.leyou.common.pojo.PageResult;
import com.leyou.dto.GoodsDTO;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.vo.GoodsVo;
import com.leyou.item.vo.SpuVo;
import com.leyou.service.GoodsService;
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

/**
 * @author 金余新
 * @date 2021/1/5 14:06
 */
@RestController
public class GoodsController {

    public static final Logger LOGGER = LoggerFactory.getLogger(GoodsController.class);

    @Autowired
    private GoodsService goodsService;


    /**
     * 分页查询商品
     * */
    @PostMapping("spu/page")
    public ResponseEntity<PageResult<SpuVo>> querySpuByPage(@Validated @RequestBody GoodsVo goodsVo, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            LOGGER.error("前端传参出错");
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        GoodsDTO goodsDTO = new GoodsDTO();
        BeanUtils.copyProperties(goodsVo, goodsDTO);
        PageResult<SpuVo> spuDTOPageResult = this.goodsService.querySpuByPageAndSort(goodsDTO);
        return  ResponseEntity.ok(spuDTOPageResult);
    }

    @GetMapping("spu/detail/{id}")
    public ResponseEntity<SpuDetail> querySpecDetailById (@PathVariable(name = "id") Long spuId) {
        SpuDetail spuDetail = this.goodsService.querySpuDetailBySpuId(spuId);
        if (spuDetail == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(spuDetail);
    }

    @GetMapping("sku/list")
    public ResponseEntity<List<Sku>> querySkuListBySpuId(@RequestParam("id") Long spuId) {
        List<Sku> skus = this.goodsService.querySkuListById(spuId);
        if (skus == null ||skus.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return ResponseEntity.ok(skus);
    }
}

