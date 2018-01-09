package com.devin.data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单
 * @author devin
 */
public class Order {
    /** 订单头 ID */
    private Long headerId;

    /** 订单 code */
    private String code;

    /** 订单金额，行金额的汇总 */
    private BigDecimal amount;

    /** 实际金额 */
    private BigDecimal actualAmount;

    /** 优惠的金额 */
    private BigDecimal discountAmount;

    /** 订单的所有订单行 */
    private List<OrderLine> lines;

    /** 订单用的优惠券 */
    private List<Coupon> coupons;

    public Long getHeaderId() {
        return headerId;
    }

    public void setHeaderId(Long headerId) {
        this.headerId = headerId;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(BigDecimal discountAmount) {
        this.discountAmount = discountAmount;
    }

    public List<OrderLine> getLines() {
        return lines;
    }

    public void setLines(List<OrderLine> lines) {
        this.lines = lines;
    }

    public List<Coupon> getCoupons() {
        return coupons;
    }

    public void setCoupons(List<Coupon> coupons) {
        this.coupons = coupons;
    }

    @Override
    public String toString() {
        return "Order{" +
        "headerId=" + headerId +
        ", code='" + code + '\'' +
        ", amount=" + amount +
        ", actualAmount=" + actualAmount +
        ", discountAmount=" + discountAmount +
        ", lines=" + lines +
        ", coupons=" + coupons +
        '}';
    }
}
