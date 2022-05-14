package com.myparty.enums;

import java.util.stream.Stream;

public enum RoleEnum {
    ADMIN(1), MANAGER(2), USER(3);

    private Integer id;

    private RoleEnum(Integer id) {
        this.id = id;
    }

    public static RoleEnum getRoleEnumById(Integer id) {
        if (id == null) {
            return null;
        }
        return Stream.of(RoleEnum.values()).filter(role -> role.getId().equals(id)).findFirst().orElseThrow(IllegalArgumentException::new);
    }

    public Integer getId() {
        return id;
    }

}
