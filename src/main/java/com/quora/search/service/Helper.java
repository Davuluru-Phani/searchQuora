package com.quora.search.service;

import com.quora.search.document.SearchDocument;
import org.apache.commons.lang3.StringUtils;
import org.apache.solr.common.SolrDocumentList;

import java.util.Collection;
import java.util.List;

public class Helper {
    public static List<SearchDocument> toSearchDocumentList(SolrDocumentList results) {
        return null;
    }

    public static String getQueryForSuggestions(List<String> suggestions) {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("body").append(":");
        stringBuilder.append(getOrValues(suggestions));
        return stringBuilder.toString();
    }

    private static String getOrValues(List<String> suggestions) {
        StringBuilder value =new StringBuilder();
        for(int i=0;i<suggestions.size();i++){
            if(StringUtils.isNotEmpty(value.toString())){
                value.append(" OR ");
            }
            if(i==0){
                value.append("(");
            }
            value.append(suggestions.get(i));
        }
        value.append(")");
        return value.toString();
    }
}
