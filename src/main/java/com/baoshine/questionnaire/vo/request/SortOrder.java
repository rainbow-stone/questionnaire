package com.baoshine.questionnaire.vo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SortOrder {

    private String property;

    private String direction;
}
