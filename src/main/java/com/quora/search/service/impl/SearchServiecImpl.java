package com.quora.search.service.impl;

import com.quora.search.document.SearchDocument;
import com.quora.search.repository.SearchRepository;
import com.quora.search.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SearchServiecImpl implements SearchService {

    @Autowired
    SearchRepository searchRepository;

    @Resource
    SolrTemplate solrTemplate;

    public void add(SearchDocument searchDocument){
        System.out.println(searchDocument.toString());
        searchRepository.save(searchDocument);
    }

    public List<SearchDocument> search(String searchTerm){

        System.out.println(searchTerm);

        SimpleQuery simpleQuery=new SimpleQuery(searchTerm);
        simpleQuery.setRequestHandler("/spellCheckCompRH");
        System.out.println(solrTemplate.query("searchDocument",simpleQuery,SearchDocument.class).getContent().toString());
        return solrTemplate.query("searchDocument",simpleQuery,SearchDocument.class).getContent();


    }

}

