package com.wt.orange.util;

import org.springframework.cglib.beans.BeanMap;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

/**
 * <p> javabean工具类 </p>
 *
 * @author Wang Tao
 * @date 2021-01-14 22:11:45
 */
public class BeanUtil {

    private static final DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    /**
     * <p>javabean转map</p>
     *
     * @param bean       javabean
     * @param ignoreNull 是否忽略空值
     * @return Map<String, Object>
     * @author Wang Tao
     * @date 2021-01-14 22:46:59
     */
    public static <T> Map<String, Object> beanToMap(T bean, boolean ignoreNull) {
        if (bean == null) return null;
        Map<String, Object> map = new HashMap<>();
        Map<String, Object> beanMap = BeanMap.create(bean);
        for (String key : beanMap.keySet()) {
            Object value = beanMap.get(key);
            if (ignoreNull && value == null) {
                continue;
            }
            if (value instanceof LocalDateTime) {
                value = ((LocalDateTime) value).format(dtf);
            }
            map.put(key, value);
        }
        return map;
    }

}
