/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.converter;

import com.myparty.annotations.DataConverterType;
import com.myparty.interfaces.DataConverter;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import org.hibernate.Hibernate;
import org.hibernate.proxy.HibernateProxy;
import org.springframework.stereotype.Component;

/**
 *
 * @author Felipe Zucco
 */
@Component
public class DataConverterImplement {

    public <T> T convert(Object o) {
        if (o instanceof Collection) {
            Collection collection = (Collection) o;
            return (T) collection.stream().map(obj -> converter(obj)).collect(Collectors.toList());
        } else {
            return (T) converter(o);
        }
    }

    private <T> T converter(Object o) {
        try {
            o = initializeAndUnproxy(o);
            DataConverterType dataConverterType = o.getClass().getAnnotation(DataConverterType.class);
            Object obj = dataConverterType.value().newInstance();
            if (obj instanceof DataConverter) {
                DataConverter dataConverter = (DataConverter) obj;
                if (dataConverterType.dto()) {
                    return (T) dataConverter.revert(o);
                } else {
                    return (T) dataConverter.convert(o);
                }
            } else {
                return null;
            }
        } catch (InstantiationException | IllegalAccessException ex) {
            Logger.getLogger(DataConverterImplement.class.getName()).log(Level.SEVERE, null, ex);
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
}
