package org.acme.features.post.application.ports.in;

import java.util.UUID;

import org.acme.features.post.domain.entity.Post;
import org.acme.features.post.domain.ports.PostRepository;
import org.acme.features.post.domain.ports.PostService;
import org.acme.root.domain.exceptions.NotfoundException;
import org.acme.root.domain.pagination.Pagination;

import io.quarkus.cache.CacheInvalidate;
import io.quarkus.cache.CacheResult;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostServiceImpl implements PostService {
    private final PostRepository postRepository;

    public PostServiceImpl(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    @Override
    @CacheResult(cacheName = "post-by-id")
    public Post findById(UUID id) {
        return this.postRepository.findById(id).orElseThrow(() -> new NotfoundException("Post not found"));
    }

    @Override
    public Pagination<Post> findAll(org.acme.root.domain.pagination.DataPagination dataPagination) {
        return this.postRepository.findAll(dataPagination);
    }

    @Override
    @CacheResult(cacheName = "post-by-slug")
    public Post findBySlug(String slug) {
        return this.postRepository.findBySlug(slug).orElseThrow(() -> new NotfoundException("Post not found"));
    }

    @CacheInvalidate(cacheName = "post-by-id")
    @CacheInvalidate(cacheName = "post-by-slug")
    public void invalidateCaches(UUID id, String slug) {
        // não precisa implementar nada
    }
}
