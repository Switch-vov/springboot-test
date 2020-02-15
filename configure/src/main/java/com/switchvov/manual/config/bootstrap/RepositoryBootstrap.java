package com.switchvov.manual.config.bootstrap;

import com.switchvov.manual.config.repository.MyFirstLevelRepository;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * @author switch
 * @since 2020/2/6
 */
@ComponentScan(basePackages = "com.switchvov.manual.config.repository")
public class RepositoryBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(RepositoryBootstrap.class)
                .web(WebApplicationType.NONE)
                .run(args);

        MyFirstLevelRepository repository = context.getBean("myFirstLevelRepository", MyFirstLevelRepository.class);
        System.out.println("Repository: " + repository);

        context.close();
    }
}
