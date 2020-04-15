package com.mgl.questionnaire.computernetwork.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Question {
    private Integer id;
    private Integer type;
    private String title;
    private String name;
    private Boolean isNecessary = true;
    private Boolean isFocus;
    private Integer minSelected = 0;
    private Integer maxSelected = 0;
    private Integer minCount = 0;
    private Integer maxCount = 0;
    private Integer row = 1;
    private String classify = "无";
    private List<Other> data = new ArrayList<>();


}

@Data
class Other {
    private Boolean normal;
    private String value;
}