package org.acme.features.post.application.ports.in;


import java.util.UUID;

import org.acme.features.post.domain.entity.Post;
import org.acme.features.post.domain.ports.PostRepository;
import org.acme.features.post.domain.ports.PostService;
import org.acme.root.domain.exceptions.NotfoundException;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;
    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    public Post findById(UUID id) {
        return this.postRepository.findById(id).orElseThrow(() -> new NotfoundException("Post not found"));
    }
}
