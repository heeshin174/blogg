package com.example.demo.controller;

import com.example.demo.ApiTest;
import com.example.demo.domain.Article;
import com.example.demo.dto.AddArticleRequest;
import com.example.demo.repository.BlogRepository;
import io.restassured.RestAssured;
import io.restassured.mapper.ObjectMapper;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlogApiControllerTest extends ApiTest {

    @Test
    void 아티클추가() {
        final AddArticleRequest request = 아티클추가요청_생성();

        // API 요청
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request).when().post("/api/articles")
                .then().log().all().extract();
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }

    private static AddArticleRequest 아티클추가요청_생성() {
        String title = "새 글";
        String content = "새 글입니다. 잘 부탁해요";
        return new AddArticleRequest(title, content);
    }
}