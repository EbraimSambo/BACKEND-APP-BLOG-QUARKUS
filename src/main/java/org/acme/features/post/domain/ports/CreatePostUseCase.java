package org.acme.features.post.domain.ports;

import org.acme.features.post.domain.entity.Post;

public interface CreatePostUseCase {
    Post createPost(CreatePostCommand command);
    record CreatePostCommand(String title, String content, String bannerPath) {}
}
