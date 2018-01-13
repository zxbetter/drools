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

    /**
     * 默认的 drools 脚本
     */
    public static final String DEFAULT_DROOLS_SCRIPT = "package devin.drools.rules\n" +
            "\n" +
            "import devin.drools.data.Order;\n" +
            "import devin.drools.data.OrderLine;\n" +
            "import devin.drools.data.Coupon;\n" +
            "import devin.drools.util.Constants;\n" +
            "import devin.drools.util.CommonUtils;\n" +
            "import java.math.BigDecimal;\n" +
            "\n" +
            "global devin.drools.service.CouponService couponService;\n" +
            "\n" +
            "/**\n" +
            " * 处理订单层优惠券\n" +
            " * @param order     订单\n" +
            " * @param coupon    优惠券\n" +
            " */\n" +
            "function void handleOrderCoupon(Order order, Coupon coupon) {\n" +
            "    coupon.setUsable(true);\n" +
            "\n" +
            "    // 优惠券的优惠金额\n" +
            "    BigDecimal discount = coupon.getDiscountAmout();\n" +
            "    discount = discount != null ? discount : BigDecimal.ZERO;\n" +
            "    // 订单总的优惠金额\n" +
            "    BigDecimal discountAmt = order.getDiscountAmount();\n" +
            "    discountAmt = discountAmt != null ? discountAmt : BigDecimal.ZERO;\n" +
            "    order.setDiscountAmount(discountAmt.add(discount));\n" +
            "}\n" +
            "\n" +
            "/**\n" +
            " * 处理商品层优惠券\n" +
            " * @param order     订单\n" +
            " * @param coupon    优惠券\n" +
            " * @param number    商品的编号\n" +
            " */\n" +
            "function void handleLineCoupon(Order order, Coupon coupon, String number) {\n" +
            "    coupon.setUsable(true);\n" +
            "\n" +
            "    // 优惠券的优惠金额\n" +
            "    BigDecimal discount = coupon.getDiscountAmout();\n" +
            "    discount = discount != null ? discount : BigDecimal.ZERO;\n" +
            "\n" +
            "    for (OrderLine line : order.getLines()) {\n" +
            "        if (line.getProduct() == null || !number.equals(line.getProduct().getNumber())) {\n" +
            "            continue;\n" +
            "        }\n" +
            "        // 商品行优惠总额\n" +
            "        BigDecimal discountLine = line.getDiscountAmout();\n" +
            "        discountLine = discountLine != null ? discountLine : BigDecimal.ZERO;\n" +
            "        line.setDiscountAmout(discountLine.add(discount));\n" +
            "    }\n" +
            "}\n" +
            "\n" +
            "rule \"rule_COUPON0001\"\n" +
            "    no-loop true\n" +
            "    lock-on-active true\n" +
            "    when\n" +
            "        $order: Order(amount > 100 && channel == Constants.ORDER_CHANNEL_SHOP);\n" +
            "        forall(OrderLine(product.number != \"PRODUCT0002\") from $order.lines);\n" +
            "        $coupon: Coupon(code == \"COUPON001\") from $order.currentCoupon;\n" +
            "    then\n" +
            "        handleOrderCoupon($order, $coupon);\n" +
            "        couponService.calculateAfterCoupon($order);\n" +
            "end\n" +
            "\n" +
            "rule \"rule_COUPON0002\"\n" +
            "    no-loop true\n" +
            "    lock-on-active true\n" +
            "    when\n" +
            "        $order: Order();\n" +
            "        Number(intValue > 0) from accumulate(\n" +
            "            $line: OrderLine(product.number == \"PRODUCT0001\") from $order.lines,\n" +
            "            init(int total = 0;),\n" +
            "            action(total += $line.getQuantity().intValue();),\n" +
            "            result(total));\n" +
            "        $coupon: Coupon(code == \"COUPON002\") from $order.currentCoupon;\n" +
            "    then\n" +
            "        handleLineCoupon($order, $coupon, \"PRODUCT0001\");\n" +
            "        couponService.calculateAfterCoupon($order);\n" +
            "end";
}
