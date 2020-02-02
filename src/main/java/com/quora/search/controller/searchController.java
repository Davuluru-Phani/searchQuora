package com.quora.search.controller;


import com.quora.search.document.SearchDocument;
import com.quora.search.dto.*;
import com.quora.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/search")
@CrossOrigin(origins = "*",allowedHeaders = "*")
public class searchController {

    @Autowired
    SearchService searchService;

    @PostMapping(path = "addUser")
    public void addUser(@RequestBody UserInputDto userInputDto) {

        SearchDocument searchDocument=new SearchDocument();
        searchDocument.setDocumentId(userInputDto.getUserId());
        searchDocument.setCategory("user");
        searchDocument.setBody(userInputDto.getUserName());
        searchDocument.setImgUrl(userInputDto.getUserImage());
        searchDocument.setFlag(userInputDto.isPrivateFlag());
        searchService.add(searchDocument);

    }

    @PostMapping(path = "addOrganization")
    public void addOrganisation(@RequestBody OrganizationInputDto organizationInputDto) {

        SearchDocument searchDocument=new SearchDocument();
        searchDocument.setDocumentId(organizationInputDto.getOrganizationId());
        searchDocument.setCategory("organization");
        searchDocument.setBody(organizationInputDto.getOrganizationName());
        searchDocument.setImgUrl(organizationInputDto.getOrganizationImage());
        searchDocument.setFlag(false);
        searchService.add(searchDocument);

    }

    @PostMapping(path = "addQuestion")
    public void addQuestion(@RequestBody QuestionInputDto questionInputDto) {

        SearchDocument searchDocument=new SearchDocument();
        searchDocument.setDocumentId(questionInputDto.getQuestionId());
        searchDocument.setCategory("question");
        searchDocument.setBody(questionInputDto.getQuestionBody());
        searchDocument.setImgUrl("static img");
        searchDocument.setFlag(questionInputDto.getApprovalFlag());
        searchService.add(searchDocument);

    }

    @PostMapping(path = "searchUser")
    public ResponseEntity<List<UserOutputDto>> searchUser(@RequestBody SearchDto searchDto){
        List<UserOutputDto> userOutputDtos=new ArrayList<>();
        List<SearchDocument> searchDocuments=searchService.search(searchDto.getSearchTerm());
        for(SearchDocument x:searchDocuments){
            if (x.getCategory().equals("user")) {
                UserOutputDto userOutputDto = new UserOutputDto();
                userOutputDto.setUserId(x.getDocumentId());
                userOutputDto.setUserName(x.getBody());
                userOutputDto.setUserImage(x.getImgUrl());
                userOutputDto.setPrivateFlag(x.isFlag());
                userOutputDtos.add(userOutputDto);
            }
        }
        return new ResponseEntity<List<UserOutputDto>>(userOutputDtos, HttpStatus.CREATED);
    }

    @PostMapping(path = "searchQuestion")
    public ResponseEntity<List<QuestionOutputDto>> searchQuestion(@RequestBody SearchDto searchDto){
        List<QuestionOutputDto> questionOutputDtos=new ArrayList<>();
        List<SearchDocument> searchDocuments=searchService.search(searchDto.getSearchTerm());
        for(SearchDocument x:searchDocuments){
            if (x.getCategory().equals("question")) {
                QuestionOutputDto questionOutputDto = new QuestionOutputDto();
                questionOutputDto.setQuestionId(x.getDocumentId());
                questionOutputDto.setQuestionBody(x.getBody());
                questionOutputDto.setQuestionImage(x.getImgUrl());
                questionOutputDto.setApprovalFlag(x.isFlag());
                questionOutputDtos.add(questionOutputDto);
            }
        }
        return new ResponseEntity<List<QuestionOutputDto>>(questionOutputDtos, HttpStatus.CREATED);
    }

    @PostMapping(path = "searchOrganization")
    public ResponseEntity<List<OrganizationOutputDto>> searchOrganization(@RequestBody SearchDto searchDto){
        List<OrganizationOutputDto> organizationOutputDtos=new ArrayList<>();
        List<SearchDocument> searchDocuments=searchService.search(searchDto.getSearchTerm());
        for(SearchDocument x:searchDocuments){
            if(x.getCategory().equals("organization")) {
                OrganizationOutputDto organizationOutputDto = new OrganizationOutputDto();
                organizationOutputDto.setOrganizationId(x.getDocumentId());
                organizationOutputDto.setOrganizationName(x.getBody());
                organizationOutputDto.setOrganizationImage(x.getImgUrl());
                organizationOutputDto.setFlag(x.isFlag());
                organizationOutputDtos.add(organizationOutputDto);
            }
        }
        return new ResponseEntity<List<OrganizationOutputDto>>(organizationOutputDtos, HttpStatus.CREATED);
    }

}