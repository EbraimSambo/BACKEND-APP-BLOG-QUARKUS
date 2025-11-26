package org.acme.features.post.adapters.in.rest;

import org.acme.features.post.domain.ports.CreatePostUseCase;

import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/posts")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class PostController {

    private final CreatePostUseCase createPostUseCase;

    public PostController(CreatePostUseCase createPostUseCase) {
        this.createPostUseCase = createPostUseCase;
    }

    @POST
    public Response createPost(@Valid PostRequest request) {
        var command = new org.acme.features.post.domain.ports.CreatePostUseCase.CreatePostCommand(
            request.title(),
            request.content(),
            request.bannerPath()
        );
        return Response.status(201).entity(createPostUseCase.createPost(command)).build();
    }

}
