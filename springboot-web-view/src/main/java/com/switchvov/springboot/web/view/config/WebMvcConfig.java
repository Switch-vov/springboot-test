package com.switchvov.springboot.web.view.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcProperties;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.ContentNegotiationConfigurer;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;
import org.springframework.web.servlet.view.JstlView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author switch
 * @since 2020/2/8
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Autowired
    private WebMvcProperties webMvcProperties;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new HandlerInterceptor() {
            @Override
            public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
                System.out.println("拦截中...");
                return true;
            }
        });
    }

    @Override
    public void configureContentNegotiation(ContentNegotiationConfigurer configurer) {
        configurer.favorParameter(true).favorPathExtension(true);
    }

    @Bean
    public ViewResolver myViewResolver() {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setViewClass(JstlView.class);
        WebMvcProperties.View view = webMvcProperties.getView();
        viewResolver.setPrefix(view.getPrefix());
        viewResolver.setSuffix(view.getSuffix());
        // ThymeleafViewResolver Ordered.LOWEST_PRECEDENCE - 5
        // 优先级高于ThymeleafViewResolver
        viewResolver.setOrder(Ordered.LOWEST_PRECEDENCE - 10);
        // 配置 ViewResolver 的 Content-Type
        viewResolver.setContentType("text/xml;charset=UTF-8");
        return viewResolver;
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> customizer() {
        return (factory) -> {
            factory.addContextCustomizers(context -> {
                String relativePath = "springboot-web-view/src/main/webapp";
                // 相对于 user.dir /Users/switch/IdeaProjects/springboot-test
                File docBaseFile = new File(relativePath);
                // 路径是否存在
                if (docBaseFile.exists()) {
                    // 解决 Maven 多模块 JSP 无法读取的问题
                    context.setDocBase(docBaseFile.getAbsolutePath());
                }
            });
        };
    }
}
