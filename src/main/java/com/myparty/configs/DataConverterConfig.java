package com.myparty.configs;

import com.myparty.converter.DataConverterEngine;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataConverterConfig {

    @Autowired
    private BeanFactory beanFactory;

    @Bean
    public DataConverterEngine engine() {
        return new DataConverterEngine(beanFactory);
    }
}
