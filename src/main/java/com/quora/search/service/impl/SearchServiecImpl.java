package com.quora.search.service.impl;

import com.quora.search.document.SearchDocument;
import com.quora.search.repository.SearchRepository;
import com.quora.search.service.Helper;
import com.quora.search.service.SearchService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.params.DefaultSolrParams;
import org.apache.solr.common.params.MapSolrParams;
import org.apache.solr.common.params.SolrParams;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.solr.core.SolrCallback;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.core.query.Criteria;
import org.springframework.data.solr.core.query.Query;
import org.springframework.data.solr.core.query.SimpleQuery;
import org.springframework.data.solr.core.query.SpellcheckOptions;
import org.springframework.data.solr.core.query.result.SpellcheckedPage;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.*;

@Service
public class SearchServiecImpl implements SearchService {

    @Autowired
    SearchRepository searchRepository;

    @Autowired
    SolrTemplate solrTemplate;


    public void add(SearchDocument searchDocument){
        if(!searchRepository.existsById(searchDocument.getDocumentId())) {
            System.out.println(searchDocument.toString());
            searchRepository.save(searchDocument);
        }
    }

    public List<SearchDocument> search(String searchTerm){

        System.out.println(searchTerm);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("body");
        stringBuilder.append(":");
        stringBuilder.append(searchTerm);
        System.out.println(stringBuilder.toString());
        SimpleQuery simpleQuery = new SimpleQuery(stringBuilder.toString());
        simpleQuery.setPageRequest(PageRequest.of(0, Integer.MAX_VALUE));
        simpleQuery.setRequestHandler("/select");
        simpleQuery.setSpellcheckOptions(SpellcheckOptions.spellcheck());
//        Page<SearchDocument> page=solrTemplate.queryForPage("search",simpleQuery,SearchDocument.class);
//        solrTemplate.execute(new SolrCallback<List<SearchDocument>>() {
//            @Override
//            public List<SearchDocument> doInSolr(SolrClient solrClient) throws SolrServerException, IOException {
//                Map<String, String> p = new HashMap<String, String>();
//                p.put("q", stringBuilder.toString());
//
//                SolrParams params = new MapSolrParams(p);
//
//                QueryResponse res = solrClient.query(params);
//                return Helper.toSearchDocumentList(res.getResults());
//            }
//        });
        SpellcheckedPage<SearchDocument> searchDocument1 = solrTemplate.query("searchDocument", simpleQuery, SearchDocument.class);
        if(searchDocument1.getContent().size()!=0){
            return searchDocument1.getContent();
        }
        System.out.println(searchDocument1);
        System.out.println(searchDocument1.getSuggestions());
//        simpleQuery = new SimpleQuery(Criteria.where("body").in(searchDocument1.getSuggestions()));
        if(!CollectionUtils.isEmpty(searchDocument1.getSuggestions())) {
            simpleQuery = new SimpleQuery(Helper.getQueryForSuggestions((List<String>) searchDocument1.getSuggestions()));
            simpleQuery.setPageRequest(PageRequest.of(0, Integer.MAX_VALUE));
            System.out.println(simpleQuery);
            Page<SearchDocument> data = solrTemplate.query("searchDocument", simpleQuery, SearchDocument.class);

            //        System.out.println(solrTemplate.query("searchDocument",simpleQuery,SearchDocument.class).getContent().toString());
//        Page<SearchDocument> searchDocument = solrTemplate.query("searchDocument", simpleQuery, SearchDocument.class);
//        if(searchDocument.getTotalElements()==0){
//
//            SimpleQuery simpleQuery1=new SimpleQuery(stringBuilder.toString());
//            simpleQuery1.setSpellcheckOptions(SpellcheckOptions.spellcheck().
//            simpleQuery1.setRequestHandler("/select");
//            Page<SearchDocument> searchDocuments1=solrTemplate.query("searchDocument",simpleQuery1,SearchDocument.class);
//            return searchDocuments1.getContent();
//
//        }
            return data.getContent();
        }else{
            return Collections.emptyList();
        }
    }

}

