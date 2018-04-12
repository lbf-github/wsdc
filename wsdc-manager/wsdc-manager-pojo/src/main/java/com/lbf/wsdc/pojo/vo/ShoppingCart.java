package com.lbf.wsdc.pojo.vo;

import com.lbf.wsdc.pojo.po.Storemenu;

/**
 * User: Administrator
 * Date: 2018/4/6
 * Time: 21:22
 * Version:V1.0
 */
public class ShoppingCart extends Storemenu {
    private Integer num;
    private Float totalPrice;

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Float getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Float totalPrice) {
        this.totalPrice = totalPrice;
    }
}
