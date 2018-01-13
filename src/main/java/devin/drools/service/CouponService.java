package devin.drools.service;

import devin.drools.data.Order;

/**
 * 优惠券服务
 * @author devin
 */
public class CouponService {
    private OrderService orderService;

    public CouponService() {
        orderService = new OrderService();
    }

    /**
     * 添加优惠券后重新计算订单的金额
     * @param order    订单对象
     */
    public void calculateAfterCoupon(Order order) {
        orderService.calculatePrice(order);
    }
}
