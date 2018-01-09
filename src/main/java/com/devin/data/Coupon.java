package com.devin.data;

import java.math.BigDecimal;

/**
 * 优惠券
 * @author devin
 */
public class Coupon {
    /** 标识优惠券作用于订单层 */
    public static final String ORDER = "ORDER";

    /** 标识优惠券作用于商品层 */
    public static final String PRODUCT = "PRODUCT";

    /** 优惠券 id */
    private Long id;

    /**
     * 作用的类型
     * 取值为 ORDER/PRODUCT
     * */
    private String type;

    /** 优惠金额 */
    private BigDecimal discountAmout;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public BigDecimal getDiscountAmout() {
        return discountAmout;
    }

    public void setDiscountAmout(BigDecimal discountAmout) {
        this.discountAmout = discountAmout;
    }

    @Override
    public String toString() {
        return "Coupon{" +
        "id=" + id +
        ", type='" + type + '\'' +
        ", discountAmout=" + discountAmout +
        '}';
    }
}
