package com.mindskip.xzs.enums;

import java.util.HashMap;
import java.util.Map;

public enum ExamPaperTypeEnum {
    Fixed(1, "固定试卷"),
    TimeLimit(4, "时段试卷"),
    Task(6, "任务试卷");

    private final int code;
    private final String name;
    private static final Map<Integer, ExamPaperTypeEnum> cache = new HashMap<>();

    static {
        for (ExamPaperTypeEnum e : values()) cache.put(e.code, e);
    }

    ExamPaperTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static ExamPaperTypeEnum fromCode(Integer code) {
        return cache.get(code);
    }

    public int getCode() { return code; }
    public String getName() { return name; }
}
