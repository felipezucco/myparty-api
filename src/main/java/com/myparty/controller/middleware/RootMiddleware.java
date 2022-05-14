/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.controller.middleware;

import com.myparty.converter.DataConverterImplement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Felipe Zucco
 */
@Service
public class RootMiddleware {
    
    @Autowired
    private DataConverterImplement converterImplement;
    
    public <T> T convert(Object o) {
        return (T) converterImplement.convert(o);
    }
}
