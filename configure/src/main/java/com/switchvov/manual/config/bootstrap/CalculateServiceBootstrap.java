package com.switchvov.manual.config.bootstrap;

import com.switchvov.manual.config.repository.MyFirstLevelRepository;
import com.switchvov.manual.config.service.CalculateService;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author switch
 * @since 2020/2/6
 */
@ComponentScan(basePackages = "com.switchvov.manual.config.service")
public class CalculateServiceBootstrap {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = new SpringApplicationBuilder(CalculateServiceBootstrap.class)
                .web(WebApplicationType.NONE)
                .profiles("Java8")
                .run(args);

        CalculateService calculateService = context.getBean(CalculateService.class);
        System.out.println("sum: " + calculateService.sum(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        context.close();
    }
}
