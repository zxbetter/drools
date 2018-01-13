package devin.drools.util;

import java.math.BigDecimal;

/**
 * 常量类
 * @author devin
 */
public class Constants {
    private Constants() {}

    /**
     * 优惠券类型-订单层
     */
    public static final String COUPON_APPLY_ON_ORDER = "ORDER";

    /**
     * 优惠券类型-商品层
     */
    public static final String COUPON_APPLY_ON_PRODUCT = "PRODUCT";

    /**
     * 订单的渠道-实体店
     */
    public static final String ORDER_CHANNEL_SHOP = "SHOP";

    /**
     * 订单的渠道-网店
     */
    public static final String ORDER_CHANNEL_ONLINE = "ONLINE";

    /**
     * 订单的渠道-邮件
     */
    public static final String ORDER_CHANNEL_EMAIL = "EMAIL";

    /**
     * 订单的渠道-电话
     */
    public static final String ORDER_CHANNEL_PHONE = "PHONE";

    /**
     * 默认商品的编号
     */
    public static final String PRODUCT_DEFAULT_NUMBER = "PRODUCT0001";

    /**
     * 默认商品名
     */
    public static final String PRODUCT_DEFAULT_NAME = "《JavaScript 权威指南》";

    /**
     * 默认商品数量
     */
    public static final Long PRODUCT_DEFAULT_QUANTITY = 1L;

    /**
     * 价格的精度
     */
    public static final int PRICE_PRECISION = 2;

    /**
     * 默认商品价格
     */
    public static final BigDecimal PRODUCT_DEFAULT_PRICE = new BigDecimal(111.2)
            .setScale(PRICE_PRECISION, BigDecimal.ROUND_HALF_UP);

    /**
     * 优惠券默认 id
     */
    public static final Long COUPON_DEFAULT_ID = 1000L;

    /**
     * 优惠券默认 code
     */
    public static final String COUPON_DEFAULT_CODE = "COUPON0001";

    /**
     * 优惠券默认名称
     */
    public static final String COUPON_DEFAULT_NAME = "优惠券";

    /**
     * 优惠券默认类型
     */
    public static final String COUPON_DEFAULT_TYPE = COUPON_APPLY_ON_ORDER;

    /**
     * 优惠券默认优惠金额
     */
    public static final BigDecimal COUPON_DEFAULT_AMT = new BigDecimal(10)
            .setScale(PRICE_PRECISION, BigDecimal.ROUND_HALF_UP);
}
