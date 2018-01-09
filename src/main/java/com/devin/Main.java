package com.devin;

import com.devin.data.Coupon;
import com.devin.data.Order;
import com.devin.data.OrderLine;
import com.devin.service.impl.CommServiceImpl;
import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author devin
 */
public class Main {
    public static void main(String[] args) {
        KieServices kieServices = KieServices.get();
        KieContainer container = kieServices.getKieClasspathContainer();
        KieSession session = container.newKieSession("ksession-rule");
        session.setGlobal("commService", new CommServiceImpl());

        Order order = initData();

        session.insert(order);
        int result = session.fireAllRules();
        System.out.println(result + "------------");

        session.dispose();

        System.out.println("drools 执行完毕，order 的信息为 " + order);
    }

    private static Order initData() {
        Order order = new Order();

        // line
        OrderLine orderLine = new OrderLine();
        orderLine.setLineId(1L);
        orderLine.setQuantity(1L);
        orderLine.setProductId(1L);
        BigDecimal linePrice = new BigDecimal(20);
        orderLine.setPrice(linePrice);
        orderLine.setAmount(linePrice.multiply(BigDecimal.valueOf(orderLine.getQuantity())));
        orderLine.setActualAmount(orderLine.getAmount());
        List<Long> categorys = new ArrayList<>();
        categorys.add(1L);
        categorys.add(2L);
        orderLine.setCategorys(categorys);
        List<OrderLine> lines = new ArrayList<>(1);
        lines.add(orderLine);
        order.setLines(lines);

        // coupon
        List<Coupon> coupons = new ArrayList<>(2);
        Coupon coupon1 = new Coupon();
        coupon1.setDiscountAmout(BigDecimal.TEN);
        coupon1.setId(1L);
        coupon1.setType(Coupon.ORDER);
        coupons.add(coupon1);

        Coupon coupon2 = new Coupon();
        coupon2.setDiscountAmout(BigDecimal.ONE);
        coupon2.setId(2L);
        coupon2.setType(Coupon.PRODUCT);
        coupons.add(coupon2);
        order.setCoupons(coupons);

        // other
        order.setHeaderId(1L);
        order.setCode("ABC");
        BigDecimal amount = lines.stream().map(OrderLine::getAmount).reduce(BigDecimal.ZERO, BigDecimal::add);
        order.setAmount(amount);
        order.setActualAmount(amount);
        return order;
    }
}
