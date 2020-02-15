package com.switchvov.springboot.web.servlet;

import com.switchvov.spring.web.servlet.AsyncServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.boot.web.servlet.ServletContextInitializer;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.filter.CharacterEncodingFilter;

import javax.servlet.DispatcherType;
import javax.servlet.FilterRegistration;
import java.util.Collections;
import java.util.EnumSet;

/**
 * @author switch
 * @since 2020/2/11
 */
@EnableAutoConfiguration
//@ServletComponentScan("com.switchvov.spring.web.servlet")
public class SpringBootServletBootstrap {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootServletBootstrap.class, args);
    }

    @Bean
    @Order(Ordered.HIGHEST_PRECEDENCE)
    public ServletRegistrationBean<AsyncServlet> asyncServletServletRegistrationBean() {
        ServletRegistrationBean<AsyncServlet> registrationBean = new ServletRegistrationBean<>();
        registrationBean.setServlet(new AsyncServlet());
        registrationBean.setUrlMappings(Collections.singleton("/async-servlet"));
        return registrationBean;
    }

    @Bean
    public ServletContextInitializer servletContextInitializer() {
        return servletContext -> {
            CharacterEncodingFilter filter = new CharacterEncodingFilter();
            FilterRegistration.Dynamic registration = servletContext.addFilter("filter", filter);
            registration.addMappingForUrlPatterns(EnumSet.of(DispatcherType.REQUEST), false, "/");
        };
    }
}
