package devin.drools.util;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

/**
 * drools 工具类
 * @author devin
 */
public class KieUtils {
    private KieUtils() {}

    /**
     * 通过类路径下的配置文件创建 kieSession
     * @return    KieSession 对象
     */
    public static KieSession getClasspathSession() {
        KieServices kieServices = KieServices.get();

        // KieContainer 是对 KieModule 的封装，能够方便地创建 KieSession 对象。
        KieContainer container = kieServices.getKieClasspathContainer();

        // 创建指定名称的 KieSession 对象。
        return container.newKieSession("ksession-rule");
    }

}
