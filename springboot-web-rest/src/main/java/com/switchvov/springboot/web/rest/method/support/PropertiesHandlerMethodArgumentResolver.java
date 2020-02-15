package com.switchvov.springboot.web.rest.method.support;

import com.switchvov.springboot.web.rest.http.converter.properties.PropertiesHttpMessageConverter;
import org.springframework.core.MethodParameter;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServletServerHttpRequest;
import org.springframework.util.Assert;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import java.util.Properties;

/**
 * @author switch
 * @since 2020/2/9
 */
public class PropertiesHandlerMethodArgumentResolver implements HandlerMethodArgumentResolver {
    private PropertiesHttpMessageConverter messageConverter;


    public PropertiesHandlerMethodArgumentResolver(PropertiesHttpMessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    @Override
    public boolean supportsParameter(MethodParameter parameter) {
        return Properties.class.equals(parameter.getParameterType());
    }

    @Override
    public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
                                  NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        Assert.state(servletRequest != null, "No HttpServletRequest");
        ServletServerHttpRequest inputMessage = new ServletServerHttpRequest(servletRequest);
        Class<?> contextClass = parameter.getContainingClass();
        return messageConverter.read(Properties.class, contextClass, inputMessage);
    }
}
