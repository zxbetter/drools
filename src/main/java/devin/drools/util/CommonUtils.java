package devin.drools.util;

import devin.drools.data.Coupon;

import java.math.BigDecimal;

public class CommonUtils {
    private CommonUtils() {}

    /**
     * 金额按精度四舍五入
     * @param amount    原金额
     * @return          按精度四舍五入后的金额
     */
    public static BigDecimal afterPrecision(BigDecimal amount) {
        return amount.setScale(Constants.PRICE_PRECISION, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 金额按精度四舍五入
     * @param amount    原金额
     * @return          按精度四舍五入后的金额
     */
    public static BigDecimal afterPrecision(double amount) {
        return new BigDecimal(amount).setScale(Constants.PRICE_PRECISION, BigDecimal.ROUND_HALF_UP);
    }

    /**
     * 将 coupon 的 null 值设置默认值
     * @param coupon    Coupon 对象
     */
    public static Coupon extend(Coupon coupon) {
        coupon = coupon != null ? coupon : new Coupon();
        if (coupon.getId() == null) {
            coupon.setId(Constants.COUPON_DEFAULT_ID);
        }
        if (coupon.getCode() == null) {
            coupon.setCode(Constants.COUPON_DEFAULT_CODE);
        }
        if (coupon.getName() == null) {
            coupon.setName(Constants.COUPON_DEFAULT_NAME);
        }
        if (coupon.getType() == null) {
            coupon.setType(Constants.COUPON_DEFAULT_TYPE);
        }
        if (coupon.getDiscountAmout() == null) {
            coupon.setDiscountAmout(Constants.COUPON_DEFAULT_AMT);
        }
        return coupon;
    }
}
