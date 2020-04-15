package com.mgl.questionnaire.computernetwork.controller;

import com.mgl.questionnaire.computernetwork.bean.Paper;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/question")
public class QuestionController {

    @PostMapping("/create")
    public String createQuestion(@RequestBody Paper paper){

        System.out.println(paper);
        return "success";
    }
}
