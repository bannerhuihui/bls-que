package com.bls.que.controller;

import com.bls.que.pojo.Question;
import com.bls.que.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

/**
 * @projectName: bls-que
 * @package: com.bls.que.controller
 * @className: QuestionController
 * @author: huihui
 * @description: TODO
 * @date: 2024/5/14 14:32
 * @version: 1.0
 */
@RestController
@RequestMapping("/question")
public class QuestionController {

    @Autowired
    private QuestionService questionService;

//  /question/queryQuestionByQid?qid=

    @PostMapping(value = "/createQuestion")
    public String createQuestion(@RequestBody Question question){
        return questionService.createQuestion(question);
    }



    @GetMapping(value = "/queryQuestionByQid")
    @ResponseBody
    public Question queryQuestionByQid(String qid){
        return questionService.queryQuestionByQid(qid);
    }



}
