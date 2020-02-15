package com.switchvov.externalized.configuration.bootstrap;

import com.switchvov.externalized.configuration.domain.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;

/**
 * @author switch
 * @since 2020/2/14
 */
@EnableAutoConfiguration
public class ValueAnnotationBootstrap {

    @Bean
    public User user(@Value("${user.id}") Long id, @Value("${user.name}") String name) {
        return new User(id, name);
    }


    public static void main(String[] args) {
        ConfigurableApplicationContext context =
                new SpringApplicationBuilder(XmlPlaceholderExternalizedConfigurationBootstrap.class)
                        .web(WebApplicationType.NONE)
                        .run(args);

        User user = context.getBean("user", User.class);

        System.out.println("用户对象：" + user);

        System.out.printf("System.getProperty(\"%s\"): %s\n", "user.name", System.getProperty("user.name"));

        // 关闭上下文
        context.close();
    }
}
