package com.mindskip.xzs.enums;

import java.util.HashMap;
import java.util.Map;

public enum UserStatusEnum {
    NORMAL(1, "启用"),
    DISABLED(2, "禁用");

    private final int code;
    private final String name;
    private static final Map<Integer, UserStatusEnum> cache = new HashMap<>();

    static {
        for (UserStatusEnum e : values()) cache.put(e.code, e);
    }

    UserStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static UserStatusEnum fromCode(Integer code) {
        return cache.get(code);
    }

    public int getCode() { return code; }
    public String getName() { return name; }
}
