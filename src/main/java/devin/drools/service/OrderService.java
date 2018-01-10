package devin.drools.service;

import devin.drools.data.Order;
import devin.drools.data.OrderLine;
import devin.drools.data.Product;
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

            // 订单行的金额
            BigDecimal lineAmt = price.subtract(new BigDecimal(quantity))
                    .setScale(Constants.PRICE_PRECISION, BigDecimal.ROUND_HALF_UP);
            line.setAmount(lineAmt);
            orderAmt = orderAmt.add(lineAmt);
        }
        order.setAmount(orderAmt);
    }
}
