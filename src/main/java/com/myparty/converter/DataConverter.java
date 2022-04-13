package com.myparty.converter;

import com.myparty.annotations.DTO;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DataConverter {

    @Autowired
    private ModelMapper mapper;

    public <T> T convert(Object o) {
        if (o instanceof List) return (T) ((List) o).parallelStream().map(el -> clazz(el)).collect(Collectors.toList());
        else return clazz(o);
    }

    private <T> T clazz(Object o) {
        DTO clazz = o.getClass().getAnnotation(DTO.class);
        return (T) mapper.map(o, clazz.value());
    }
    
    public <T> T convert(Object o, Class clazz) {
        return (T) mapper.map(o, clazz);
    }

}
