package devin.drools;

import devin.drools.data.Coupon;
import devin.drools.data.Order;
import devin.drools.service.CouponService;
import devin.drools.util.Constants;
import devin.drools.util.KieUtils;
import devin.drools.util.OrderUtils;
import org.kie.api.runtime.KieSession;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author devin
 */
public class Main {
    public static void main(String[] args) {
        classpathDrools();
    }

    private static void classpathDrools() {
        KieUtils kieUtils = KieUtils.build();

        Order order = OrderUtils.build().createOrderHeader()
                .addCoupon("COUPON001","订单渠道-ORDER", Constants.COUPON_APPLY_ON_ORDER, 10)
                .addCoupon("COUPON002", "订单金额", Constants.COUPON_APPLY_ON_ORDER, 5)
                .addCoupon("COUPON003", "订单金额", Constants.COUPON_APPLY_ON_ORDER, 5)
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
        System.out.println(result + "------------>");
        System.out.println("drools 执行完毕，order 的信息为 " + order);
    }
}
