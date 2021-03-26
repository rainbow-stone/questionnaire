package com.ebao.questionnaire.tree;

import lombok.Data;

@Data
public class Widget {

    private static final long serialVersionUID = 811210837385861852L;

    private String widgetType; //Text, Document

    private String bizType; //疾病, 体检报告
}
