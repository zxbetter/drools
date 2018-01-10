package devin.drools;

import devin.drools.data.Order;
import devin.drools.util.KieUtils;
import devin.drools.util.OrderUtils;
import org.kie.api.runtime.KieSession;

/**
 * @author devin
 */
public class Main {
    public static void main(String[] args) {
        KieSession session = KieUtils.getClasspathSession();
        Order order = OrderUtils.build().getOrder();

        session.insert(order);
        int result = session.fireAllRules();
        System.out.println(result + "------------");

        session.dispose();

        System.out.println("drools 执行完毕，order 的信息为 " + order);
    }
}
