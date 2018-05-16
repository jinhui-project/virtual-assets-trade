package com.jinhui.api.controller;

import com.jinhui.api.entity.dto.BuyProduct;
import com.jinhui.api.entity.po.RegularProduct;
import com.jinhui.api.entity.vo.RegularProductVo;
import com.jinhui.api.entity.vo.WebResult;
import com.jinhui.api.mapper.RegularProductMapper;
import com.jinhui.api.service.product.ProductService;
import com.jinhui.common.utils.validator.ValidatorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/4/3 0003.
 */
@RestController
@RequestMapping("/product")
public class ProductController {


    private static Logger logger = LoggerFactory.getLogger(ProductController.class);

    @Autowired
    private ProductService productService;


    @Autowired
    private RegularProductMapper regularProductMapper;

    @GetMapping("queryProduct")
    WebResult buyProduct(@RequestParam("productName") String productName) {
        RegularProduct regularProduct = regularProductMapper.selectByName(productName);

        RegularProductVo productVo = RegularProductVo.createRegularProductVo(regularProduct);
        WebResult result = WebResult.ok();
        result.setData(productVo);

        return result;

    }


    @GetMapping("queryProducts")
    WebResult queryProducts() {
        List<RegularProduct> regularProducts = regularProductMapper.selectAll();

        List<RegularProductVo> voList = new ArrayList();
        for (RegularProduct regularProduct : regularProducts) {
            RegularProductVo productVo = RegularProductVo.createRegularProductVo(regularProduct);
            voList.add(productVo);
        }
        WebResult result = WebResult.ok();
        result.setData(voList);

        return result;

    }


    @PostMapping("buyProduct")
    WebResult buyProduct(@RequestBody BuyProduct buyProduct) {

        //验证
        ValidatorUtils.validateEntity(buyProduct);

        //检查资金密码

        //购买定期产品
        productService.buyProduct(buyProduct.getProductId(), buyProduct.getProductName(), new BigDecimal(buyProduct.getBuyVol()));

        return WebResult.ok();

    }
}
