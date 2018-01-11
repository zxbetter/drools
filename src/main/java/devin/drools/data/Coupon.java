package devin.drools.data;

import java.math.BigDecimal;

/**
 * 优惠券
 * @author devin
 */
public class Coupon {
    /** 优惠券 id */
    private Long id;

    /** 优惠券的编码 */
    private String code;

    /** 优惠券的名称 */
    private String name;

    /**
     * 作用的类型
     * 取值为 ORDER/PRODUCT
     * */
    private String type;

    /** 优惠金额 */
    private BigDecimal discountAmout;

    /**
     * 标识优惠券是否通过 drools 校验
     */
    private boolean usable;

    public boolean isUsable() {
        return usable;
    }

    public void setUsable(boolean usable) {
        this.usable = usable;
    }

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
                ", code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", discountAmout=" + discountAmout +
                '}';
    }
}
