package com.example.demo.controller;

import com.example.demo.domain.Article;
import com.example.demo.dto.AddArticleRequest;
import com.example.demo.dto.GetArticleResponse;
import com.example.demo.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // HTTP Response Body에 객체 데이터를 JSON 형식으로 반환
@RequiredArgsConstructor
public class BlogApiController {
    private final BlogService blogService;

    @PostMapping("/api/articles")
    public ResponseEntity<Article> addArticle(@RequestBody AddArticleRequest request) {
        Article savedArticle = blogService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedArticle);
    }

    @GetMapping("/api/articles")
    public ResponseEntity<List<GetArticleResponse>> findAllArticles() {
        List<GetArticleResponse> articles = blogService.findAll()
                .stream().map(GetArticleResponse::new).toList();
        return ResponseEntity.ok().body(articles);
    }

    @GetMapping("/api/articles/{articleId}")
    public ResponseEntity<GetArticleResponse> findArticle(@PathVariable final Long articleId) {
        final Article article = blogService.findById(articleId);
        final GetArticleResponse response = new GetArticleResponse(article);
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/api/articles/{articleId}")
    public ResponseEntity<Void> deleteArticle(@PathVariable final Long articleId) {
        blogService.delete(articleId);
        return ResponseEntity.ok().build();
    }


}
