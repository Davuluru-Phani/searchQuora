package com.quora.search.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class QuestionOutputDto {

    private String questionId;
    private String questionBody;
    private String questionImage;
    private boolean approvalFlag;

}
