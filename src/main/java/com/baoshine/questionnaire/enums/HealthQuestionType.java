package com.baoshine.questionnaire.enums;

public enum HealthQuestionType {

    ILLNESS(1L, "疾病相关"),

    NON_ILLNESS(2L, "疾病无关");
    
    private Long key;

    private String desc;

    public Long getKey() {
        return key;
    }

    public String getDesc(){
        return desc;
    }

    HealthQuestionType(Long key, String desc) {
        this.key = key;
        this.desc = desc;
    }
}
