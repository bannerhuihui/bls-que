package com.bls.que.service;

import com.bls.que.pojo.Question;

public interface QuestionService {

    String createQuestion(Question question);

    Question queryQuestionByQid(String qid);

}
