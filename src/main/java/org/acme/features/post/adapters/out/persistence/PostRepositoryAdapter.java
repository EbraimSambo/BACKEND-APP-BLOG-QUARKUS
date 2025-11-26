package org.acme.features.post.adapters.out.persistence;

import java.util.Optional;
import java.util.UUID;

import org.acme.features.post.adapters.out.mappers.PostMapper;
import org.acme.features.post.domain.entity.Post;
import org.acme.features.post.domain.ports.PostRepository;

import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostRepositoryAdapter implements PostRepository{


    @Override
    public Optional<Post> findById(UUID id) {
        return PostEntity.find("id", id)
                .firstResultOptional()
                 .map(entity -> PostMapper.toDomain((PostEntity) entity));
    }

    @Override
    public Post save(Post post) {
        var entity = new PostEntity();
        entity.setTitle(post.getTitle());
        entity.setContent(post.getContent());
        entity.setBannerPath(post.getBannerPath());
        entity.persist();
        return PostMapper.toDomain(entity);
    }



}
