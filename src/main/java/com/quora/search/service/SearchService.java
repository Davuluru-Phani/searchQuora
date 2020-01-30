package com.quora.search.service;

import com.quora.search.document.SearchDocument;

import java.util.List;

public interface SearchService {

    public void add(SearchDocument searchDocument);

    public List<SearchDocument> search(String searchTerm);

}
