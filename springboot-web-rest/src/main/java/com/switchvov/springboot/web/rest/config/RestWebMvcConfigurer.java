package com.switchvov.springboot.web.rest.config;

import com.switchvov.springboot.web.rest.http.converter.properties.PropertiesHttpMessageConverter;
import com.switchvov.springboot.web.rest.method.support.PropertiesHandlerMethodArgumentResolver;
import com.switchvov.springboot.web.rest.method.support.PropertiesHandlerMethodReturnValueHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author switch
 * @since 2020/2/8
 */
@Configuration
public class RestWebMvcConfigurer implements WebMvcConfigurer {
    private RequestMappingHandlerAdapter requestMappingHandlerAdapter;

    public RestWebMvcConfigurer(@Lazy RequestMappingHandlerAdapter requestMappingHandlerAdapter) {
        this.requestMappingHandlerAdapter = requestMappingHandlerAdapter;
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**").allowedOrigins("*");
    }

    @PostConstruct
    public void init() {
        // 获取当前 RequestMappingHandlerAdapter 所有的 HandlerMethodArgumentResolver 对象
        List<HandlerMethodArgumentResolver> resolvers = requestMappingHandlerAdapter.getArgumentResolvers();
        List<HandlerMethodArgumentResolver> newResolvers = new ArrayList<>(resolvers.size() + 1);
        newResolvers.add(propertiesHandlerMethodArgumentResolver());
        newResolvers.addAll(resolvers);
        requestMappingHandlerAdapter.setArgumentResolvers(newResolvers);

        // 获取当前 RequestMappingHandlerAdapter 所有的 HandlerMethodReturnValueHandler 对象
        List<HandlerMethodReturnValueHandler> returnValueHandlers = requestMappingHandlerAdapter.getReturnValueHandlers();
        List<HandlerMethodReturnValueHandler> newReturnValueHandlers = new ArrayList<>(returnValueHandlers.size() + 1);
        newReturnValueHandlers.add(propertiesHandlerMethodReturnValueHandler());
        newReturnValueHandlers.addAll(returnValueHandlers);
        requestMappingHandlerAdapter.setReturnValueHandlers(newReturnValueHandlers);
    }

    @Bean
    public HttpMessageConverter<Properties> propertiesHttpMessageConverter() {
        return new PropertiesHttpMessageConverter();
    }

    @Bean
    public PropertiesHandlerMethodArgumentResolver propertiesHandlerMethodArgumentResolver() {
        return new PropertiesHandlerMethodArgumentResolver((PropertiesHttpMessageConverter) propertiesHttpMessageConverter());
    }

    @Bean
    public PropertiesHandlerMethodReturnValueHandler propertiesHandlerMethodReturnValueHandler() {
        return new PropertiesHandlerMethodReturnValueHandler((PropertiesHttpMessageConverter) propertiesHttpMessageConverter());
    }

    @Override
    public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
        // 添加自定义 HandlerMethodArgumentResolver ，优先级低于内建 HandlerMethodArgumentResolver
//        if (resolvers.isEmpty()) {
//            resolvers.add(propertiesHandlerMethodArgumentResolver());
//        } else {
//            resolvers.set(0, propertiesHandlerMethodArgumentResolver());
//        }
    }


//    org.springframework.boot.autoconfigure.http.HttpMessageConvertersAutoConfiguration
//    自动装配类会通过 ObjectProvider 获取 Spring 容器中所有的 HttpMessageConverter 类型对象，所以可以不用手动添加
//    public HttpMessageConvertersAutoConfiguration(
//            ObjectProvider<List<HttpMessageConverter<?>>> convertersProvider) {
//        this.converters = convertersProvider.getIfAvailable();
//    }
//    @Override
//    public void extendMessageConverters(List<HttpMessageConverter<?>> converters) {
//        converters.set(0, new PropertiesHttpMessageConverter());
//    }
}
