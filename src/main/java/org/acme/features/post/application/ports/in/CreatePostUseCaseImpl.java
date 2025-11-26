package org.acme.features.post.application.ports.in;

import org.acme.features.post.adapters.out.mappers.PostMapper;
import org.acme.features.post.domain.entity.Post;
import org.acme.features.post.domain.ports.CreatePostUseCase;
import org.acme.features.post.domain.ports.PostRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class CreatePostUseCaseImpl implements CreatePostUseCase{

    private final PostRepository postRepository;

    public CreatePostUseCaseImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post createPost(CreatePostCommand command) {
        return this.postRepository.save(
            PostMapper.toEntityFromCommand(
                command.title(),
                command.content(),
                command.bannerPath()
            )
        );
    }
}
