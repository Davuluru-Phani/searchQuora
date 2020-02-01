package com.quora.search.listener;

import com.quora.search.document.SearchDocument;
import com.quora.search.dto.QuestionInputDto;
import com.quora.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class QuestionKafkaListener {

    @Autowired
    SearchService searchService;

    @KafkaListener(topics = "questionJson",groupId = "group_id",containerFactory = "QuestionKafkaListenerContainerFactory")
    public void addQuestion(QuestionInputDto questionInputDto){

        System.out.println(questionInputDto.toString());

        SearchDocument searchDocument=new SearchDocument();
        searchDocument.setDocumentId(questionInputDto.getQuestionId());
        searchDocument.setCategory("question");
        searchDocument.setBody(questionInputDto.getQuestionBody());
        searchDocument.setImgUrl("static");
        searchDocument.setFlag(questionInputDto.getApprovalFlag());
        searchService.add(searchDocument);

    }


}
