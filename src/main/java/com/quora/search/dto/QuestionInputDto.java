package com.quora.search.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter @Setter
public class QuestionInputDto {

    private String questionId;
    private String userId;
    private Integer likeCount;
    private Integer dislikeCount;
    private List<String> answersList;
    private String categoryId;
    private String questionBody;
    private Date date;
    private Boolean approvalFlag;
    private String bestAnswerId;
    private String orgId;

}