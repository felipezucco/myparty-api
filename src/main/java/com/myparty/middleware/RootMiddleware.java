/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.middleware;

import com.myparty.configs.DataConverterConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Felipe Zucco
 */
@Service
public class RootMiddleware {
    
    @Autowired
    private DataConverterConfig dataConverter;
    
    public <T> T convert(Object o) {
        return (T) dataConverter.engine().start(o);
    }
}
