/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.service;

import com.myparty.interfaces.DataConverterInterface;
import com.myparty.model.Organization;
import java.lang.reflect.Type;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Felipe Zucco
 */
@Service
public class RootService<E, O> implements DataConverterInterface<E, O> {

    @Autowired
    protected ModelMapper mapper;
    
    @Override
    public O dto(E e) {
        Type type = new TypeToken<O>(){}.getType();
        return mapper.map(e, type);
    }

}
