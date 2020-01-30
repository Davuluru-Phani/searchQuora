package com.quora.search.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class UserOutputDto {

    private String userId;
    private String userName;
    private String userImage;
    private boolean privateFlag;

}