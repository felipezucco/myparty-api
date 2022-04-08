package com.myparty.interfaces;

public interface DataConverterInterface<E, D> {  
    
    D dto(E e);
}
