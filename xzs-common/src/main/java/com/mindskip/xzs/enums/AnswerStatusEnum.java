package com.mindskip.xzs.enums;

import java.util.HashMap;
import java.util.Map;

public enum AnswerStatusEnum {
    WAIT_JUDGE(1, "待批改"),
    COMPLETE(2, "已完成");

    private final int code;
    private final String name;
    private static final Map<Integer, AnswerStatusEnum> cache = new HashMap<>();

    static {
        for (AnswerStatusEnum e : values()) cache.put(e.code, e);
    }

    AnswerStatusEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static AnswerStatusEnum fromCode(Integer code) {
        return cache.get(code);
    }

    public int getCode() { return code; }
    public String getName() { return name; }
}
