/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.exception;

/**
 *
 * @author Felipe Zucco
 */
public class NoValueFoundException extends RuntimeException {

    public NoValueFoundException() {
        super("Value not found");
    }
    
}
