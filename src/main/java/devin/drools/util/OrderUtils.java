package devin.drools.util;

import devin.drools.data.Coupon;
import devin.drools.data.Order;
import devin.drools.data.OrderLine;
import devin.drools.data.Product;
import devin.drools.service.OrderService;
import org.apache.commons.lang3.RandomStringUtils;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 订单工具类
 * @author devin
 */
public class OrderUtils {
    private OrderUtils() {}

    private static OrderService orderService;
    private Order order;

    public static OrderUtils build() {
        counter = 1000L;
        orderService = new OrderService();
        return new OrderUtils();
    }

    public Order getOrder() {
        if (order == null) {
            createOrderHeader();
        }
        if (order.getLines() == null) {
            addOrderLine();
        }
        Order result = order;
        order = null;

        // 计算订单的金额
        orderService.calculatePrice(result);
        return result;
    }

    /**
     * 创建订单头
     * @param channel    订单渠道
     */
    public OrderUtils createOrderHeader(String channel) {
        if (order != null) {
            if (channel != null) {
                order.setChannel(channel);
            }
            return this;
        }
        // 默认渠道是实体店
        channel = channel != null ? channel : Constants.ORDER_CHANNEL_SHOP;
        order = new Order();
        Long id = getCounter();
        order.setId(id);
        order.setCode("ORDER" + id);
        order.setChannel(channel);
        return this;
    }

    /**
     * 创建默认订单头
     */
    public OrderUtils createOrderHeader() {
        return createOrderHeader(null);
    }

    /**
     * 给订单添加订单行
     * @param number      商品的编号
     * @param name        商品名称
     * @param price       商品价格
     * @param quantity    商品数量
     */
    public OrderUtils addOrderLine(String number, String name, BigDecimal price, Long quantity) {
        // 处理 null 参数
        number = number != null ? number : Constants.PRODUCT_DEFAULT_NUMBER;
        name = name != null ? name : Constants.PRODUCT_DEFAULT_NAME;
        price = price != null ? price : Constants.PRODUCT_DEFAULT_PRICE;
        quantity = quantity != null ? quantity : Constants.PRODUCT_DEFAULT_QUANTITY;

        if (order == null) {
            createOrderHeader();
        }

        OrderLine line = new OrderLine();
        line.setLineId(getCounter());
        line.setQuantity(quantity);
        line.setProduct(createProduct(number, name, price));

        List<OrderLine> lines = order.getLines();
        if (lines == null) {
            lines = new ArrayList<>();
            order.setLines(lines);
        }
        lines.add(line);

        return this;
    }

    /**
     * 给订单添加默认订单行
     */
    public OrderUtils addOrderLine() {
        return addOrderLine(null, null, null, null);
    }

    /**
     * 创建商品
     * @param number   商品的编号
     * @param name     商品名称
     * @param price    商品价格
     * @return         商品对象
     */
    private Product createProduct(String number, String name, BigDecimal price) {
        Product product = new Product();
        product.setId(getCounter());
        product.setNumber(number);
        product.setName(name);
        product.setPrice(price);
        return product;
    }

    /**
     * 添加优惠券
     * @param code        优惠券的编码
     * @param name        优惠券名称
     * @param type        优惠券类型
     * @param discount    优惠券优惠金额
     */
    public OrderUtils addCoupon(String code, String name, String type, double discount) {
        if (order == null) {
            createOrderHeader();
        }

        List<Coupon> coupons = order.getCoupons();
        if (coupons == null) {
            coupons = new ArrayList<>();
            order.setCoupons(coupons);
        }
        coupons.add(createCoupon(code, name, type, discount));
        return this;
    }

    /**
     * 创建优惠券
     * @param code        优惠券的编码
     * @param name        优惠券名称
     * @param type        优惠券类型
     * @param discount    优惠券优惠金额
     * @return            优惠券对象
     */
    private Coupon createCoupon(String code, String name, String type, double discount) {
        // 处理 null 参数
        code = code != null ? code : Constants.COUPON_DEFAULT_CODE;
        name = name != null ? name : Constants.COUPON_DEFAULT_NAME;
        type = type != null ? type : Constants.COUPON_DEFAULT_TYPE;

        Coupon coupon = new Coupon();
        coupon.setId(getCounter());
        coupon.setCode(code);
        coupon.setUsable(false);
        coupon.setName(name);
        coupon.setType(type);
        coupon.setDiscountAmout(CommonUtils.afterPrecision(discount));
        return coupon;
    }

    /** 计数器 */
    private static Long counter;

    private Long getCounter() {
        return counter++;
    }
}
