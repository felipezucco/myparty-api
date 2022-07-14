/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter;

import com.myparty.annotations.DataConverterType;
import com.myparty.enums.Scenarios;
import com.myparty.interfaces.DataConverterInterface;
import com.myparty.interfaces.OldDataConverter;
import java.util.Collection;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.beans.factory.BeanFactory;

/**
 *
 * @author Felipe Zucco
 */
@AllArgsConstructor
public class DataConverterEngine {

    private BeanFactory beanFactory;

    private <T> T beanFactory(Class clazz) {
        return (T) beanFactory.getBean(clazz);
    }

    public <T> T start(Object o, Class end) {
        return (T) preEngine(o, end);
    }

    public <T> T start(Object o) {
        return (T) preEngine(o, null);
    }

    public <T> T preEngine(Object o, Class end) {
        if (o instanceof Collection) {
            Collection collection = (Collection) o;
            return (T) collection.stream().map(obj -> converter(obj, null)).collect(Collectors.toList());
        } else {
            return (T) converter(o, null);
        }
    }

    private <T> T converter(Object o, Class end) {
        o = initializeAndUnproxy(o);
        DataConverterType dataConverterType = o.getClass().getAnnotation(DataConverterType.class);
        Object obj = beanFactory(dataConverterType.value());
        if (obj instanceof DataConverterInterface) {
            DataConverterInterface dataConverter = (DataConverterInterface) obj;
            if (isDTOClass(o)) {
                return (T) dataConverter.revert(o);
            } else {
                return (T) dataConverter.convert(o, end);
            }
        }
        return null;
    }

    private <T> T initializeAndUnproxy(T entity) {
        if (entity == null) {
            throw new NullPointerException("Entity passed for initialization is null");
        }

        Hibernate.initialize(entity);
        if (entity instanceof HibernateProxy) {
            entity = (T) ((HibernateProxy) entity).getHibernateLazyInitializer()
                    .getImplementation();
        }
        return entity;
    }

    private boolean isDTOClass(Object clazz) {
        final String DTOpackage = "com.myparty.dto";
        String packageName = clazz.getClass().getPackage().getName();

        return packageName.contains(DTOpackage);
    }
}
