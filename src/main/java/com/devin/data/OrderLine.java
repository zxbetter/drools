package com.devin.data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单行
 * @author devin
 */
public class OrderLine {
    /** 订单行 ID */
    private Long lineId;

    /** 关联的商品 ID */
    private Long productId;

    /** 订单行的单价 */
    private BigDecimal price;

    /** 订单行的金额汇总 */
    private BigDecimal amount;

    /** 订单行的商品数量 */
    private Long quantity;

    /** 订单行的实际金额 */
    private BigDecimal actualAmount;

    /** 优惠的金额 */
    private BigDecimal discountAmout;

    private List<Long> categorys;

    public List<Long> getCategorys() {
        return categorys;
    }

    public void setCategorys(List<Long> categorys) {
        this.categorys = categorys;
    }

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public Long getProductId() {
        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getActualAmount() {
        return actualAmount;
    }

    public void setActualAmount(BigDecimal actualAmount) {
        this.actualAmount = actualAmount;
    }

    public BigDecimal getDiscountAmout() {
        return discountAmout;
    }

    public void setDiscountAmout(BigDecimal discountAmout) {
        this.discountAmout = discountAmout;
    }

    @Override
    public String toString() {
        return "OrderLine{" +
        "lineId=" + lineId +
        ", productId=" + productId +
        ", price=" + price +
        ", amount=" + amount +
        ", quantity=" + quantity +
        ", actualAmount=" + actualAmount +
        ", discountAmout=" + discountAmout +
        '}';
    }
}
