package com.mindskip.xzs.enums;

import java.util.HashMap;
import java.util.Map;

public enum RoleEnum {
    STUDENT(1, "学生"),
    ADMIN(3, "管理员");

    private final int code;
    private final String name;
    private static final Map<Integer, RoleEnum> cache = new HashMap<>();

    static {
        for (RoleEnum e : values()) cache.put(e.code, e);
    }

    RoleEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static RoleEnum fromCode(Integer code) {
        return cache.get(code);
    }

    public int getCode() { return code; }
    public String getName() { return name; }
}
