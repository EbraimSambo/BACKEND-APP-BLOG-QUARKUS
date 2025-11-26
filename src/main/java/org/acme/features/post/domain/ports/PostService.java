package org.acme.features.post.domain.ports;

import java.util.UUID;

import org.acme.features.post.domain.entity.Post;

public interface PostService{
    Post findById(UUID id);
}
