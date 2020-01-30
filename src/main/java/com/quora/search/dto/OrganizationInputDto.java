package com.quora.search.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter @Setter
public class OrganizationInputDto {

    private String organizationId;
    private String organizationName;
    private String organizationEmail;
    private String organizationImage;
    private List<String> moderatorId;
    private List<String> organizationFollowers;
    private List<String> organizationMembers;
}
