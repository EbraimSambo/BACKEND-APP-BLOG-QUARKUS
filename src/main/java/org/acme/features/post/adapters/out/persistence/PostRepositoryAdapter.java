package org.acme.features.post.adapters.out.persistence;

import java.util.Optional;
import java.util.UUID;

import org.acme.features.post.adapters.out.mappers.PostMapper;
import org.acme.features.post.domain.entity.Post;
import org.acme.features.post.domain.ports.PostRepository;
import org.acme.root.domain.pagination.DataPagination;
import org.acme.root.domain.pagination.Pagination;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PostRepositoryAdapter implements PostRepository{


    @Override
    public Optional<Post> findById(UUID id) {
        return PostEntity.find("id", id)
                .firstResultOptional()
                 .map(entity -> PostMapper.toDomain((PostEntity) entity));
    }

    @Override
    @Transactional
    public Post save(Post post) {
        var entity = new PostEntity();
        entity.setTitle(post.getTitle());
        entity.setContent(post.getContent());
        entity.setBannerPath(post.getBannerPath());
        entity.persist();
        return PostMapper.toDomain(entity);
    }

    @Override
    public Pagination<Post> findAll(DataPagination dataPagination) {
        PanacheQuery<PostEntity> query = PostEntity.findAll();
        if (dataPagination.query().isPresent()) {
            query = PostEntity.find("title LIKE ?1 OR content LIKE ?1", "%" + dataPagination.query().get() + "%");
        }
        var page = query.page(dataPagination.page(), dataPagination.size());
        var posts = page.list().stream()
                .map(PostMapper::toDomain)
                .toList();
        return new Pagination<Post>(
                dataPagination.page(),
                dataPagination.size(),
                page.count(),
                page.pageCount(),
                posts
        );
    }



}
