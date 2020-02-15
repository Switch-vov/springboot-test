package com.switchvov.externalized.configuration.bootstrap;

import com.switchvov.externalized.configuration.domain.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @author switch
 * @since 2020/2/14
 */
public class SpringXmlConfigPlaceholderBootstrap {
    public static void main(String[] args) {
        String[] locations = {"META-INF/spring/spring-context.xml"};
        ClassPathXmlApplicationContext applicationContext = new ClassPathXmlApplicationContext(locations);

        User user = applicationContext.getBean("user", User.class);

        System.out.println("用户对象：" + user);

        // 关闭上下文
        applicationContext.close();
    }
}
