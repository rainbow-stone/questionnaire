package com.baoshine.questionnaire.enums;

public enum PresentationType {

    SINGLE_CHOICE(1L, "单项选择"),

    MULTIPLE_CHOICE(2L, "多项选择"),

    TEXT_INPUT_BOX(3L, "文本输入框"),

    UPLOAD_FILE(4L, "上传文件"),

    END_RESULT(5L, "结束");

    private Long key;

    private String desc;

    PresentationType(Long key, String desc) {
        this.key = key;
        this.desc = desc;
    }

    public Long getKey() {
        return key;
    }

    public String getDesc() {
        return desc;
    }
}
