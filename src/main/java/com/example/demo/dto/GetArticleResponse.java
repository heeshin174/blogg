package com.example.demo.dto;

import com.example.demo.domain.Article;
import lombok.Getter;

@Getter
public class GetArticleResponse {
    private final String title;
    private final String content;

    public GetArticleResponse(Article article) {
        this.title = article.getTitle();
        this.content = article.getContent();
    }
}
