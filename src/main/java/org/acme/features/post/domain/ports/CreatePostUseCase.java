package org.acme.features.post.domain.ports;

import java.util.Optional;

import org.acme.features.post.domain.entity.Post;

public interface CreatePostUseCase {
    Post execute(CreatePostCommand command);

    record CreatePostCommand(String title, String content, String bannerPath, Optional<String> excerpt) {
    }
}
