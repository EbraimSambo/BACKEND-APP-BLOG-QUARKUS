package org.acme.features.post.domain.ports;

import java.util.Optional;
import java.util.UUID;

import org.acme.features.post.domain.entity.Post;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(UUID id);
}
