package com.example.demo.controller;

import com.example.demo.ApiTest;
import com.example.demo.dto.AddArticleRequest;
import io.restassured.RestAssured;
import io.restassured.response.ExtractableResponse;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class BlogApiRestAssuredTest extends ApiTest {

    @Test
    void 아티클추가() {
        String title = "새 글";
        String content = "새 글입니다. 잘 부탁해요";
        final AddArticleRequest request = new AddArticleRequest(title, content);

        // API 요청
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request).when().post("/api/articles")
                .then().log().all().extract();
        assertThat(response.statusCode()).isEqualTo(HttpStatus.CREATED.value());
    }


    @Test
    void 아티클전체조회() {
        String title = "새 글";
        String content = "새 글입니다. 잘 부탁해요";
        final AddArticleRequest request = new AddArticleRequest(title, content);
        RestAssured.given().log().all()
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .body(request).when().post("/api/articles")
                .then().log().all().extract();

        // API 요청
        final ExtractableResponse<Response> response = RestAssured.given().log().all()
                .when().get("/api/articles")
                .then().log().all().extract();;
        assertThat(response.statusCode()).isEqualTo(HttpStatus.OK.value());
    }
}