package org.acme.features.post.adapters.in.rest;

import java.util.Optional;
import java.util.UUID;

import org.acme.features.post.adapters.out.mappers.PostMapper;
import org.acme.features.post.domain.ports.CreatePostUseCase;
import org.acme.features.post.domain.ports.PostService;
import org.acme.root.domain.pagination.DataPagination;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DefaultValue;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.QueryParam;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/posts")

@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostController {

    private final CreatePostUseCase createPostUseCase;
    private final PostService postService;

    public PostController(CreatePostUseCase createPostUseCase, PostService postService) {
        this.createPostUseCase = createPostUseCase;
        this.postService = postService;
    }

    @POST
    public Response createPost(@Valid PostRequest request) {
        return Response.status(201).entity(createPostUseCase.execute(
                PostMapper.toCreatePostCommand(request))).build();
    }

    @GET
    @Path("/slug/{slug}")
    public Response getPostBySlug(@PathParam("slug") String slug) {
        return Response.ok(postService.findBySlug(slug)).build();
    }

    @GET
    @Path("/{id}")
    public Response getPostById(@PathParam("id") UUID id) {
        return Response.ok(postService.findById(id)).build();
    }

    @GET
    public Response getAllPosts(
            @DefaultValue("0") @QueryParam("page") int page,
            @DefaultValue("10") @QueryParam("size") int size,
            @QueryParam("query") String query) {
        return Response.ok(postService.findAll(
                new DataPagination(page, size, Optional.ofNullable(query)))).build();
    }

}
