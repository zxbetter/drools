package devin.drools.service;

import devin.drools.data.Coupon;
import devin.drools.data.Order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * 优惠券服务
 * @author devin
 */
public class CouponService {

    /**
     * 给订单添加优惠券
     * @param order          订单对象
     * @param id             优惠券 id
     * @param code           优惠券 code
     * @param name           优惠券名称
     * @param type           优惠券类型
     * @param discountAmt    优惠券优惠金额
     */
    public static void addCoupon(Order order, Long id, String code,
                                 String name, String type, BigDecimal discountAmt) {
        // 创建优惠券
        Coupon coupon = createCoupon(id, code, name, type, discountAmt);
        List<Coupon> coupons = order.getCoupons();
        if (coupons == null) {
            coupons = new ArrayList<>();
            order.setCoupons(coupons);
        }
        coupons.add(coupon);
    }

    /**
     * 创建优惠券
     * @param id             优惠券 id
     * @param code           优惠券 code
     * @param name           优惠券名称
     * @param type           优惠券类型
     * @param discountAmt    优惠券优惠的金额
     * @return               优惠券对象
     */
    private static Coupon createCoupon(Long id, String code, String name,
                                       String type, BigDecimal discountAmt) {
        Coupon coupon = new Coupon();
        coupon.setId(id);
        coupon.setCode(code);
        coupon.setName(name);
        coupon.setType(type);
        coupon.setDiscountAmout(discountAmt);
        return coupon;
    }
}
