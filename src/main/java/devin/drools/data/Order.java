package devin.drools.data;

import java.math.BigDecimal;
import java.util.List;

/**
 * 订单头
 * @author devin
 */
public class Order {
    /** 订单 ID */
    private Long id;

    /** 订单编码 */
    private String code;

    /** 订单的渠道 */
    private String channel;

    /** 订单金额 */
    private BigDecimal amount;

    /** 优惠的金额 */
    private BigDecimal discountAmount;

    /** 订单的所有订单行 */
    private List<OrderLine> lines;

    /** 订单用的优惠券 */
    private List<Coupon> coupons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
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
                "id=" + id +
                ", code='" + code + '\'' +
                ", channel='" + channel + '\'' +
                ", amount=" + amount +
                ", discountAmount=" + discountAmount +
                ", lines=" + lines +
                ", coupons=" + coupons +
                '}';
    }
}
