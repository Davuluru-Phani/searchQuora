package com.quora.search.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class UserInputDto {

    private String userId;
    private String userName;
    private String userEmail;
    private String userImage;
    private Integer userScore;
    private List<String> userFollower;
    private List<String> userFollowing;
    private List<String> userCategory;
    private boolean privateFlag;
    private String organizationId;
    private List<String> userFollowingOrganization;

}
