package com.leyou.service;

import com.github.pagehelper.Page;
import com.leyou.common.pojo.PageResult;
import com.leyou.dto.GoodsDTO;
import com.leyou.item.pojo.Brand;
import com.leyou.item.pojo.Sku;
import com.leyou.item.pojo.Spu;
import com.leyou.item.pojo.SpuDetail;
import com.leyou.item.vo.SpuVo;
import com.leyou.mapper.SkuMapper;
import com.leyou.mapper.SpuDetailMapper;
import com.leyou.mapper.SpuMapper;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static com.github.pagehelper.PageHelper.startPage;

/**
 * @author 金余新
 * @date 2021/1/5 10:51
 * 商品服务包括商品SPU和SKU的操作
 */
@Service
public class GoodsService {

    private static final Logger LOGGER = LoggerFactory.getLogger(GoodsService.class);

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuDetailMapper spuDetailMapper;

    @Autowired
    private SkuMapper skuMapper;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private BrandService brandService;

    public PageResult<SpuVo> querySpuByPageAndSort(GoodsDTO goodsDTO) {
        LOGGER.info("开始SPU分页查询服务");
        //1.查询SPU
        //开始分页,最多允许一页50条数据
        startPage(goodsDTO.getPageIndex(), Math.min(goodsDTO.getRows(), 50));

        //创建查询条件
        Example example = new Example(Spu.class);
        Example.Criteria criteria = example.createCriteria();

        //是否上架
        if (goodsDTO.getSaleAble() != null) {
            criteria.orEqualTo("saleable", goodsDTO.getSaleAble());
        }

        //是否模糊查询
        if (StringUtils.isNotBlank(goodsDTO.getKey())) {
            criteria.andLike("title", "%" + goodsDTO.getKey() + "%");
        }

        //得到查询结果
        Page<Spu> pageInfo = (Page<Spu>) this.spuMapper.selectByExample(example);

        List<SpuVo> spuVoList = pageInfo.getResult().stream().map(spu -> {
            //将查询结果的属性拷贝给前端展示的VO
            SpuVo spuVo = new SpuVo();
            BeanUtils.copyProperties(spu, spuVo);
            //2. 查询SPU的商品分类名称，要查三级分类名称
            List<String> names = this.categoryService.queryCategoryNameByIds(
                    Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3()));
            //将分类名称按/拼接，并设置到vo中
            spuVo.setCategoryName(StringUtils.join(names, "/"));
            //3. 根据spu的商品ID查询当前spu品牌名称
            Brand brand = this.brandService.queryBrandByBrandId(spu.getBrandId());
            //将商品名称设置给vo
            spuVo.setBrandName(brand.getName());
            return spuVo;
        }).collect(Collectors.toList());
        LOGGER.info("结束SPU分页查询服务");
        return new PageResult<>(pageInfo.getTotal(), spuVoList);
    }

    public SpuDetail querySpuDetailBySpuId(Long spuId) {
        LOGGER.info("开始SPU详情查询");
        SpuDetail spuDetail = this.spuDetailMapper.selectByPrimaryKey(spuId);
        LOGGER.info("结束SPU详情查询");
        return spuDetail;
    }

    public List<Sku> querySkuListById(Long id) {
        LOGGER.info("开始SKU查询");
        Example example = new Example(Sku.class);
        Example.Criteria criteria = example.createCriteria();
        criteria.andEqualTo("spuId", id);
        List<Sku> skuList = this.skuMapper.selectByExample(example);
        LOGGER.info("结束SKU查询");
        return skuList;
    }


}
