package com.switchvov.springboot.web.rest.method.support;

import com.switchvov.springboot.web.rest.http.converter.properties.PropertiesHttpMessageConverter;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodReturnValueHandler;
import org.springframework.web.method.support.ModelAndViewContainer;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Properties;

/**
 * @author switch
 * @since 2020/2/9
 */
public class PropertiesHandlerMethodReturnValueHandler implements HandlerMethodReturnValueHandler {
    private PropertiesHttpMessageConverter messageConverter;

    public PropertiesHandlerMethodReturnValueHandler(PropertiesHttpMessageConverter messageConverter) {
        this.messageConverter = messageConverter;
    }

    @Override
    public boolean supportsReturnType(MethodParameter returnType) {
        return Properties.class.equals(returnType.getMethod().getReturnType());
    }

    @Override
    public void handleReturnValue(Object returnValue, MethodParameter returnType,
                                  ModelAndViewContainer mavContainer, NativeWebRequest webRequest) throws Exception {
        Properties properties = (Properties) returnValue;
        HttpServletRequest servletRequest = webRequest.getNativeRequest(HttpServletRequest.class);
        HttpServletResponse servletResponse = webRequest.getNativeResponse(HttpServletResponse.class);
        Assert.state(servletRequest != null, "No HttpServletRequest");
        Assert.state(servletResponse != null, "No HttpServletResponse");
        String contentType = servletRequest.getHeader(HttpHeaders.CONTENT_TYPE);
        MediaType mediaType = (StringUtils.hasLength(contentType) ? MediaType.parseMediaType(contentType) : null);
        ServletServerHttpResponse outputMessage = new ServletServerHttpResponse(servletResponse);
        messageConverter.write(properties, mediaType, outputMessage);
        // 消息已响应，不需要 ViewResolver 再处理
        mavContainer.setRequestHandled(true);
    }
}
