package devin.drools.util;

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
        // 默认渠道是实体店
        channel = channel != null ? channel : Constants.ORDER_CHANNEL_SHOP;

        order = new Order();
        order.setId(getCounter());
        order.setCode(RandomStringUtils.randomAlphabetic(10));
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
     * @param name        商品名称
     * @param price       商品价格
     * @param quantity    商品数量
     */
    public OrderUtils addOrderLine(String name, BigDecimal price, Long quantity) {
        // 默认商品名称
        name = name != null ? name : Constants.PRODUCT_DEFAULT_NAME;
        // 默认价格
        price = price != null ? price : Constants.PRODUCT_DEFAULT_PRICE;
        // 默认商品数量是 1
        quantity = quantity != null ? quantity : Constants.PRODUCT_DEFAULT_QUANTITY;

        if (order == null) {
            createOrderHeader(null);
        }

        OrderLine line = new OrderLine();
        line.setLineId(getCounter());
        line.setQuantity(quantity);
        line.setProduct(createProduct(name, price));

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
        return addOrderLine(null, null, null);
    }

    /**
     * 创建商品
     * @param name     商品名称
     * @param price    商品价格
     * @return         商品对象
     */
    private Product createProduct(String name, BigDecimal price) {
        Product product = new Product();
        product.setId(getCounter());
        product.setNumber(RandomStringUtils.randomAlphabetic(5));
        product.setName(name);
        product.setPrice(price);
        return product;
    }

    /** 计数器 */
    private static Long counter;

    private Long getCounter() {
        return counter++;
    }
}
