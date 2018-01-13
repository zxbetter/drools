package devin.drools.util;

import org.kie.api.KieServices;
import org.kie.api.builder.KieBuilder;
import org.kie.api.builder.KieFileSystem;
import org.kie.api.builder.Message;
import org.kie.api.builder.Results;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

import java.io.StringReader;

/**
 * drools 工具类
 * @author devin
 */
public class KieUtils {
    private KieUtils() {}

    private KieContainer container;
    private KieSession session;
    private String sessionName;

    public static KieUtils build() {
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

        if (container != null) {
            if (sessionName != null) {
                session = container.newKieSession(sessionName);
            } else {
                session = container.newKieSession();
            }
            return this;
        }

        KieServices kieServices = KieServices.get();

        // KieContainer 是对 KieModule 的封装，能够方便地创建 KieSession 对象。
        if (container == null) {
            container = kieServices.getKieClasspathContainer();
        }

        // 创建指定名称的 KieSession 对象。
        sessionName = "ksession-rule";
        session = container.newKieSession(sessionName);
        return this;
    }

    /**
     * 根据 drools 脚本创建 KieSession
     * @param ruleScirpt    drools 脚本
     */
    public KieUtils createStrSession(String ruleScirpt) {
        if (session != null) {
            return this;
        }
        if (ruleScirpt == null) {
            return this;
        }
        // 构建 KieServices
        KieServices kieServices = KieServices.get();

        // 将 drools 脚本写入 Kie 的文件系统
        KieFileSystem fileSystem = kieServices.newKieFileSystem();
        String path = "/src/main/resources/drools.drl";
        fileSystem.write(path, kieServices.getResources().newReaderResource(new StringReader(ruleScirpt)));

        // 读取 Kie 文件系统中的文件进行构建
        KieBuilder kieBuilder = kieServices.newKieBuilder(fileSystem).buildAll();
        Results results = kieBuilder.getResults();
        if (results.hasMessages(Message.Level.ERROR)) {
            throw new IllegalStateException(results.getMessages().toString());
        }

        // 构建 Kie 容器
        container = kieServices.newKieContainer(kieBuilder.getKieModule().getReleaseId());

        // 创建 session
        session = container.newKieSession();
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
