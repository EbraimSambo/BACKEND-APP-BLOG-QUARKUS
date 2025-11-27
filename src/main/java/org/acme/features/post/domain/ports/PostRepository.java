package org.acme.features.post.domain.ports;

import java.util.Optional;
import java.util.UUID;

import org.acme.features.post.domain.entity.Post;
import org.acme.root.domain.pagination.DataPagination;
import org.acme.root.domain.pagination.Pagination;

public interface PostRepository {
    Post save(Post post);
    Optional<Post> findById(UUID id);
    Optional<Post> findBySlug(String slug);
    Pagination<Post> findAll(DataPagination dataPagination);
}
