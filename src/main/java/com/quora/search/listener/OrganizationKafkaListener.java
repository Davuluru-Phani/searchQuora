package com.quora.search.listener;

import com.quora.search.document.SearchDocument;
import com.quora.search.dto.OrganizationInputDto;
import com.quora.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrganizationKafkaListener {
    @Autowired
    SearchService searchService;
    @KafkaListener(topics = "organizationJson",groupId = "group_id",containerFactory = "OrganizationKafkaListenerContainerFactory")
    public void addOrganization(OrganizationInputDto organizationInputDto){

        System.out.println(organizationInputDto.toString());

        SearchDocument searchDocument=new SearchDocument();
        searchDocument.setDocumentId(organizationInputDto.getOrganizationId());
        searchDocument.setCategory("organization");
        searchDocument.setBody(organizationInputDto.getOrganizationName());
        searchDocument.setImgUrl(organizationInputDto.getOrganizationImage());
        searchDocument.setFlag(false);
        searchService.add(searchDocument);

    }
}
