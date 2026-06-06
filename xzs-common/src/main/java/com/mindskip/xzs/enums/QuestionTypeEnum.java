package com.mindskip.xzs.enums;

import java.util.HashMap;
import java.util.Map;

public enum QuestionTypeEnum {
    SingleChoice(1, "单选题"),
    MultipleChoice(2, "多选题"),
    TrueFalse(3, "判断题"),
    GapFilling(4, "填空题"),
    ShortAnswer(5, "简答题");

    private final int code;
    private final String name;
    private static final Map<Integer, QuestionTypeEnum> cache = new HashMap<>();

    static {
        for (QuestionTypeEnum e : values()) cache.put(e.code, e);
    }

    QuestionTypeEnum(int code, String name) {
        this.code = code;
        this.name = name;
    }

    public static QuestionTypeEnum fromCode(Integer code) {
        return cache.get(code);
    }

    public static boolean needSaveTextContent(Integer code) {
        QuestionTypeEnum type = fromCode(code);
        return type == GapFilling || type == ShortAnswer;
    }

    public int getCode() { return code; }
    public String getName() { return name; }
}
