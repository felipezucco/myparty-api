package com.myparty.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DateFormatEnum {
    ptBR("dd/MM/yyyy HH:mm:ss");

    private final String format;
}
