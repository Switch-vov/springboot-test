package com.switchvov.externalized.configuration.bootstrap;

import com.switchvov.externalized.configuration.domain.User;
import com.switchvov.externalized.configuration.domain.User2;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author switch
 * @since 2020/2/14
 */
@SpringBootApplication
@EnableConfigurationProperties(User.class)
public class ConfigurationPropertiesBootstrap {
//    @Bean
//    public User user() {
//        return new User();
//    }

    @Bean
    @ConfigurationProperties("user2")
    @ConditionalOnProperty(name = "user2.switch", havingValue = "true")
    public User2 user2() {
        return new User2();
    }

    public static void main(String[] args) {

        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(ConfigurationPropertiesBootstrap.class)
                        .web(WebApplicationType.NONE)
                        .run(args);

//        User user = context.getBean("user", User.class);
        User user = context.getBean(User.class);
        System.out.println("用户对象：" + user);
        User2 user2 = context.getBean(User2.class);
        System.out.println("用户对象2：" + user2);

        System.out.printf("System.getProperty(\"%s\"): %s\n", "user.name", System.getProperty("user.name"));

        // 关闭上下文
        context.close();
    }
}
