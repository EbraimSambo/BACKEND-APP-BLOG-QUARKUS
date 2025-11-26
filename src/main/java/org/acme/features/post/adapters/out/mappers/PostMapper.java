package org.acme.features.post.adapters.out.mappers;

import java.security.PublicKey;

import org.acme.features.post.adapters.out.persistence.PostEntity;
import org.acme.features.post.domain.entity.Post;

public class PostMapper {

    public static Post toDomain(PostEntity entity) {
        Post post = new Post();
        post.setId(entity.getId());
        post.setInternalId(entity.getInternalId());
        post.setTitle(entity.getTitle());
        post.setContent(entity.getContent());
        post.setBannerPath(entity.getBannerPath());
        post.setCreatedAt(entity.getCreatedAt());
        post.setUpdatedAt(entity.getUpdatedAt());
        post.setDeletedAt(entity.getDeletedAt());
        return post;
    }

    public static PostEntity toEntity(Post post) {
        PostEntity entity = new PostEntity();
        entity.setId(post.getId());
        entity.setInternalId(post.getInternalId());
        entity.setTitle(post.getTitle());
        entity.setContent(post.getContent());
        entity.setBannerPath(post.getBannerPath());
        entity.setCreatedAt(post.getCreatedAt());
        entity.setUpdatedAt(post.getUpdatedAt());
        entity.setDeletedAt(post.getDeletedAt());
        return entity;
    }

    public static Post toEntityFromCommand(String title, String content, String bannerPath) {
        Post post = new Post();
        post.setTitle(title);
        post.setContent(content);
        post.setBannerPath(bannerPath);
        return post;
    }

}
