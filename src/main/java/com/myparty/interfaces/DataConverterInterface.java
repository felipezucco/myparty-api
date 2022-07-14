/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.myparty.interfaces;

/**
 *
 * @author Felipe Zucco
 */
public interface DataConverterInterface<E> {

    <T> T convert(E entity, T destinationClass);
    E revert(Object o);

}
