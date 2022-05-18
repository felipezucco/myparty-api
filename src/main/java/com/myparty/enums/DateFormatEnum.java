package com.myparty.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum DateFormatEnum {
    ptBR("dd/MM/yyyy HH:mm:ss"),
    Default("yyyy-MM-dd'T'HH:mm");

    private final String format;
}
