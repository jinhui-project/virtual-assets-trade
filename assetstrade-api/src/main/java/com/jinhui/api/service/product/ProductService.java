package com.jinhui.api.service.product;

import java.math.BigDecimal;

/**
 * Created by Administrator on 2018/4/3 0003.
 */
public interface
ProductService {

    void buyProduct(String productId,String productName,BigDecimal buyVol);



    //到期赎回
    void redeem();
}
