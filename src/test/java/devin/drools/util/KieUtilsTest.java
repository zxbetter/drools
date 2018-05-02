package devin.drools.util;

import devin.drools.data.Order;
import org.junit.Before;
import org.junit.Test;
import org.kie.api.runtime.KieSession;

import java.math.BigDecimal;

public class KieUtilsTest {
    private KieUtils kieUtils;

    @Before
    public void init() {
        kieUtils = KieUtils.build();
    }

    @Test
    public void testClasspathSession() {
        kieUtils.createClasspathSession();
        KieSession session = kieUtils.getSession();
        Order order = new Order();
        order.setAmount(new BigDecimal("100.1"));
        session.insert(order);
        session.fireAllRules();
    }
}
