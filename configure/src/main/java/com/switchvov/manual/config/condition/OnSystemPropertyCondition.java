package com.switchvov.manual.config.condition;

import org.springframework.context.annotation.Condition;
import org.springframework.context.annotation.ConditionContext;
import org.springframework.core.type.AnnotatedTypeMetadata;

import java.util.Map;
import java.util.Objects;

/**
 * 系统属性条件判断
 *
 * @author switch
 * @since 2020/2/6
 */
public class OnSystemPropertyCondition implements Condition {
    @Override
    public boolean matches(ConditionContext context, AnnotatedTypeMetadata metadata) {
        Map<String, Object> attributes = metadata.getAnnotationAttributes(ConditionOnSystemProperty.class.getName());
        if (Objects.isNull(attributes)) {
            return false;
        }
        String name = String.valueOf(attributes.get("name"));
        String value = String.valueOf(attributes.get("value"));
        String systemValue = System.getProperty(name);
        return value.equals(systemValue);
    }
}
