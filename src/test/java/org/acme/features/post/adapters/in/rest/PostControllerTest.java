package org.acme.features.post.adapters.in.rest;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.Optional;
import java.util.UUID;

import org.acme.features.post.domain.entity.Post;
import org.acme.features.post.domain.ports.CreatePostUseCase;
import org.acme.features.post.domain.ports.PostService;
import org.acme.root.domain.pagination.DataPagination;
import org.acme.root.domain.pagination.Pagination;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@QuarkusTest
public class PostControllerTest {

    @InjectMock
    CreatePostUseCase createPostUseCase;

    @InjectMock
    PostService postService;

    @Test
    void testCreatePost() {
        var command = new CreatePostUseCase.CreatePostCommand("title", "content", "banner", Optional.empty());
        var post = new Post();
        post.setId(UUID.randomUUID());
        when(createPostUseCase.execute(any())).thenReturn(post);
        given()
                .contentType(MediaType.APPLICATION_JSON)
                .body("{\"title\":\"title\",\"content\":\"content\",\"bannerPath\":\"banner\",\"excerpt\":null}")
                .when()
                .post("/posts")
                .then()
                .statusCode(201)
                .body(is(post.getId().toString()));
    }

    @Test
    void testGetPostById() {
        var id = UUID.randomUUID();
        var post = new Post();
        post.setId(id);
        when(postService.findById(id)).thenReturn(post);
        given()
                .accept(MediaType.APPLICATION_JSON)
                .when()
                .get("/posts/" + id)
                .then()
                .statusCode(200)
                .body("id", is(id.toString()));
    }

    @Test
    void testGetPostBySlug() {
        var slug = "my-post";
        var post = new Post();
        post.setSlug(slug);
        when(postService.findBySlug(slug)).thenReturn(post);
        given()
                .accept(MediaType.APPLICATION_JSON)
                .when()
                .get("/posts/slug/" + slug)
                .then()
                .statusCode(200)
                .body("slug", is(slug));
    }

}
