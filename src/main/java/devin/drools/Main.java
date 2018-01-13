package devin.drools;

import devin.drools.data.Coupon;
import devin.drools.data.Order;
import devin.drools.service.CouponService;
import devin.drools.util.Constants;
import devin.drools.util.KieUtils;
import devin.drools.util.OrderUtils;
import org.kie.api.runtime.KieSession;

import java.util.ArrayList;
import java.util.List;

/**
 * @author devin
 */
public class Main {
    public static void main(String[] args) {
        testDrools();
    }

    private static void testDrools() {
         KieUtils kieUtils = KieUtils.build().createClasspathSession();
//        KieUtils kieUtils = KieUtils.build().createStrSession(Constants.DEFAULT_DROOLS_SCRIPT);

        Order order = OrderUtils.build().createOrderHeader(Constants.ORDER_CHANNEL_SHOP)
                .addOrderLine("PRODUCT0001", "《Java》", 118.3, 2L)
                .addCoupon("COUPON001","订单渠道-ORDER", Constants.COUPON_APPLY_ON_ORDER, 10)
                .addCoupon("COUPON002", "优惠商品", Constants.COUPON_APPLY_ON_PRODUCT, 5)
                .addCoupon("COUPON003", "优惠订单", Constants.COUPON_APPLY_ON_ORDER, 5)
                .getOrder();

        int result = 0;
        List<Coupon> coupons = new ArrayList<>();
        if (order.getCoupons() != null) {
            for (Coupon coupon : order.getCoupons()) {
                KieSession session = kieUtils.setGlobal("couponService", new CouponService())
                        .getSession();
                order.setCurrentCoupon(coupon);
                session.insert(order);
                result += session.fireAllRules();
                if (coupon.isUsable()) {
                    coupons.add(coupon);
                }
                session.dispose();
            }
        }
        order.setCoupons(coupons);
        System.out.println("中了 " + result + " 条优惠券");
        System.out.println("drools 执行完毕，order 的信息为: \n" + order);
    }
}
