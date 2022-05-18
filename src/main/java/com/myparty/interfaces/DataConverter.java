/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.myparty.interfaces;

/**
 *
 * @author Felipe Zucco
 */
public interface DataConverter<S, D> {
    
    D convert(S entity);
    S revert(D dto);
    
}
