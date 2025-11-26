package org.acme.features.post.domain.ports;

import java.util.UUID;

import org.acme.features.post.domain.entity.Post;
import org.acme.root.domain.pagination.DataPagination;
import org.acme.root.domain.pagination.Pagination;

public interface PostService{
    Post findById(UUID id);
    Pagination<Post> findAll(DataPagination dataPagination);
}
