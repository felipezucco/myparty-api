/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myparty.exception;

/**
 *
 * @author Felipe Zucco
 */
public class OrganizationException extends RuntimeException {

    public OrganizationException(String string) {
        super(string);
    }        
    
    public static class OrganizationNotFoundException extends OrganizationException {

        public OrganizationNotFoundException() {
            super("Organization not found.");
        }                    
        
    }

}
