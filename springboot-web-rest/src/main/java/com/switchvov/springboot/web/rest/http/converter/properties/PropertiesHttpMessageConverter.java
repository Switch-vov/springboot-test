package com.switchvov.springboot.web.rest.http.converter.properties;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpInputMessage;
import org.springframework.http.HttpOutputMessage;
import org.springframework.http.MediaType;
import org.springframework.http.converter.AbstractGenericHttpMessageConverter;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.http.converter.HttpMessageNotWritableException;

import java.io.*;
import java.lang.reflect.Type;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Objects;
import java.util.Properties;

/**
 * @author switch
 * @since 2020/2/8
 */
public class PropertiesHttpMessageConverter extends AbstractGenericHttpMessageConverter<Properties> {
    public PropertiesHttpMessageConverter() {
        super(new MediaType("text", "properties"));
    }

    @Override
    protected void writeInternal(Properties properties, Type type, HttpOutputMessage outputMessage) throws IOException, HttpMessageNotWritableException {
        HttpHeaders headers = outputMessage.getHeaders();
        Charset charset = getCharset(headers);
        OutputStream bodyOutputStream = outputMessage.getBody();
        Writer writer = new OutputStreamWriter(bodyOutputStream, charset);
        properties.store(writer, "From PropertiesHttpMessageConverter");
    }

    @Override
    protected Properties readInternal(Class<? extends Properties> clazz, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        HttpHeaders headers = inputMessage.getHeaders();
        Charset charset = getCharset(headers);
        InputStream bodyInputStream = inputMessage.getBody();
        InputStreamReader bodyInputStreamReader = new InputStreamReader(bodyInputStream, charset);
        Properties properties = new Properties();
        properties.load(bodyInputStreamReader);
        return properties;
    }

    private Charset getCharset(HttpHeaders headers) {
        MediaType contentType = headers.getContentType();
        Charset charset = StandardCharsets.UTF_8;
        if (Objects.nonNull(contentType) && Objects.nonNull(contentType.getCharset())) {
            charset = contentType.getCharset();
        }
        return charset;
    }

    @Override
    public Properties read(Type type, Class<?> contextClass, HttpInputMessage inputMessage) throws IOException, HttpMessageNotReadableException {
        return readInternal(null, inputMessage);
    }
}
