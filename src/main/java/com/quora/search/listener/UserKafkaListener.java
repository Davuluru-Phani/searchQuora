package com.quora.search.listener;

import com.quora.search.document.SearchDocument;
import com.quora.search.dto.UserInputDto;
import com.quora.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class UserKafkaListener {

    @Autowired
    SearchService searchService;

    @KafkaListener(topics = "userJson",groupId = "group_id",containerFactory = "kafkaListenerContainerFactory")
    public void addUser(UserInputDto userInputDto){

        System.out.println(userInputDto.toString());

        SearchDocument searchDocument=new SearchDocument();
        searchDocument.setDocumentId(userInputDto.getUserId());
        searchDocument.setCategory("user");
        searchDocument.setBody(userInputDto.getUserName());
        searchDocument.setImgUrl(userInputDto.getUserImage());
        searchDocument.setFlag(userInputDto.isPrivateFlag());
        searchService.add(searchDocument);

    }

}
