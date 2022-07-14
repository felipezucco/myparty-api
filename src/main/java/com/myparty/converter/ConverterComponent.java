package com.myparty.converter;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
@NoArgsConstructor
public class ConverterComponent {

    @Autowired
    private DataConverterEngine converter;

    public <T> T transform(Object o) {
        return (T) converter.start(o);
    }

    public <T> T transform(Object o, Class clazz) {
        return (T) converter.start(o, clazz);
    }

}
