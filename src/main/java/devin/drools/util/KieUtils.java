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

    private KieContainer container;
    private KieSession session;

    public static KieUtils builder() {
        return new KieUtils();
    }

    /**
     * 获取 KieSession
     */
    public KieSession getSession() {
        if (session == null) {
            createClasspathSession();
        }
        KieSession result = session;
        session = null;
        return result;
    }

    /**
     * 通过类路径下的配置文件创建 kieSession
     */
    public KieUtils createClasspathSession() {
        if (session != null) {
            return this;
        }

        KieServices kieServices = KieServices.get();

        // KieContainer 是对 KieModule 的封装，能够方便地创建 KieSession 对象。
        if (container == null) {
            container = kieServices.getKieClasspathContainer();
        }

        // 创建指定名称的 KieSession 对象。
        session = container.newKieSession("ksession-rule");
        return this;
    }

    /**
     * 设置全局变量
     * @param identifier    全局变量的标识符
     * @param value         全局变量
     */
    public KieUtils setGlobal(String identifier, Object value) {
        if (session == null) {
            createClasspathSession();
        }
        session.setGlobal(identifier, value);
        return this;
    }
}
