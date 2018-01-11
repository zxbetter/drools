package devin.drools.service;

import devin.drools.data.Order;
import devin.drools.data.OrderLine;
import devin.drools.data.Product;
import devin.drools.util.CommonUtils;
import devin.drools.util.Constants;

import java.math.BigDecimal;

/**
 * 订单服务
 * @author devin
 */
public class OrderService {

    /**
     * 计算订单的金额
     * @param order    订单对象
     */
    public void calculatePrice(Order order) {
        if (order == null || order.getLines() == null) {
            return;
        }

        // 订单总金额
        BigDecimal orderAmt = BigDecimal.ZERO;
        for (OrderLine line : order.getLines()) {
            Product product = line.getProduct();
            if (product == null) {
                continue;
            }
            BigDecimal price = product.getPrice() != null ? product.getPrice() : BigDecimal.ZERO;
            Long quantity = line.getQuantity() != null
                    ? line.getQuantity() : Constants.PRODUCT_DEFAULT_QUANTITY;
            // 订单行上优惠的金额
            BigDecimal discountLine = line.getDiscountAmout() != null ? line.getDiscountAmout() : BigDecimal.ZERO;
            // 优惠后的价格
            price = CommonUtils.afterPrecision(price.subtract(discountLine));
            price = price.compareTo(BigDecimal.ZERO) > 0 ? price : BigDecimal.ZERO;

            // 订单行的金额
            BigDecimal lineAmt = CommonUtils.afterPrecision(price.multiply(new BigDecimal(quantity)));
            line.setAmount(lineAmt);
            orderAmt = orderAmt.add(lineAmt);
        }
        // 订单层优惠的金额
        BigDecimal discountOrder = order.getDiscountAmount() != null ? order.getDiscountAmount() : BigDecimal.ZERO;
        orderAmt = CommonUtils.afterPrecision(orderAmt.subtract(discountOrder));
        orderAmt = orderAmt.compareTo(BigDecimal.ZERO) > 0 ? orderAmt : BigDecimal.ZERO;
        order.setAmount(orderAmt);
    }
}
