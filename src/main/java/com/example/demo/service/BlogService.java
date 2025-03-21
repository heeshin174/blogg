package com.example.demo.service;

import com.example.demo.domain.Article;
import com.example.demo.dto.AddArticleRequest;
import com.example.demo.dto.UpdateArticleRequest;
import com.example.demo.repository.BlogRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor // final이나 @NotNull이 붙은 필드의 생성자 추가
public class BlogService {
    private final BlogRepository blogRepository;

    // 블로그 글 추가
    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    // 블로그 글 전체 조회
    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    // 블로그 글 하나 조회
    public Article findById(long id) {
        return blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found: " + id));
    }

    public void delete(Long articleId) {
        blogRepository.deleteById(articleId);
    }

    @Transactional
    public Article update(long id, UpdateArticleRequest request) {
        Article article = blogRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Not found: " + id));

        article.update(request.getTitle(), request.getContent());
        return article;
    }
}
