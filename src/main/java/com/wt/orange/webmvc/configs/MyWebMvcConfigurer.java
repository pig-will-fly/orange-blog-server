package com.wt.orange.webmvc.configs;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.fasterxml.jackson.datatype.jdk8.Jdk8Module;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * <p> WebMvc自定义配置 </p>
 *
 * @author Wang Tao
 * @date 2021-01-13 23:19:07
 */
@Configuration
public class MyWebMvcConfigurer implements WebMvcConfigurer {

    @Autowired
    private MappingJackson2HttpMessageConverter jackson2HttpMessageConverter;

    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {

        ObjectMapper objectMapper = new ObjectMapper()
                // Long类型数据转字符串
                .registerModule(new SimpleModule()
                        .addSerializer(Long.class, ToStringSerializer.instance)
                        .addSerializer(Long.TYPE, ToStringSerializer.instance))
                .registerModule(new Jdk8Module())
                // LocalDateTime时间类型序列化、反序列化处理
                .registerModule(new JavaTimeModule());

        jackson2HttpMessageConverter.setObjectMapper(objectMapper);

        converters.add(jackson2HttpMessageConverter);
    }
}