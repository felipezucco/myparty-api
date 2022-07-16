package com.myparty.controller;

import com.myparty.converter.DataConverterEngine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

@Component
public class ControllerComponent {

    @Autowired
    private DataConverterEngine converter;

    @SuppressWarnings("unchecked")
    public <T> T _8(Object o, Class end) {
        return (T) converter.start(o, end);
    }

    @SuppressWarnings("unchecked")
    public <T> T _8(Object o) {
        return (T) converter.start(o);
    }
}
