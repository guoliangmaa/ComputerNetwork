package com.mgl.questionnaire.computernetwork.bean;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Paper {
    private Integer id;
    private String mainTitle;
    private String mainDesc;
    private String footerDesc;

    private List<Question> questions = new ArrayList<>();
}
