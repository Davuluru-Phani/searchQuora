package com.quora.search.document;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.solr.core.mapping.Indexed;
import org.springframework.data.solr.core.mapping.SolrDocument;

@Getter @Setter
@ToString
@SolrDocument(solrCoreName = "searchDocument")
public class SearchDocument {

    @Id
    @Indexed
    private String documentId;

    @Indexed
    private String category;

    @Indexed
    private String body;

    @Indexed
    private String imgUrl;

    @Indexed
    private boolean flag;

}
