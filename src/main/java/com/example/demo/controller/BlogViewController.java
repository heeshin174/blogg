package com.example.demo.controller;

import com.example.demo.domain.Article;
import com.example.demo.dto.ArticleListViewResponse;
import com.example.demo.dto.ArticleViewResponse;
import com.example.demo.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {

    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll()
                .stream().map(ArticleListViewResponse::new).toList();
        // 블로그 글 list를 넘김
        model.addAttribute("articles", articles);
        // articleList.html 조회
        return "articleList";
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable long id, Model model) {
        Article article = blogService.findById(id);
        // 블로그 글 하나를 넘김
        model.addAttribute("article", article);
        // article.html 조회
        return "article";
    }

    @GetMapping("/new-article")
    public String newArticle(@RequestParam(required = false) Long id, Model model) {
        if (id == null) {
        // id가 null이면 새 글 생성
            model.addAttribute("article", new ArticleViewResponse());
        } else {
            Article article = blogService.findById(id);
            // 블로그 글 하나를 넘김
            model.addAttribute("article", new ArticleViewResponse(article));
        }
        return "newArticle";
    }
}