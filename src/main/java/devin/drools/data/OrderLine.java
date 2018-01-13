package devin.drools.data;

import java.math.BigDecimal;

/**
 * 订单行
 * @author devin
 */
public class OrderLine {
    /** 订单行 ID */
    private Long lineId;

    /** 订单行对应的商品 */
    private Product product;

    /** 订单行的商品数量 */
    private Long quantity;

    /** 订单行的金额 */
    private BigDecimal amount;

    /** 优惠的金额 */
    private BigDecimal discountAmout;

    public Long getLineId() {
        return lineId;
    }

    public void setLineId(Long lineId) {
        this.lineId = lineId;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
                ", product=" + product +
                ", quantity=" + quantity +
                ", amount=" + amount +
                ", discountAmout=" + discountAmout +
                '}' + "\n    ";
    }
}
