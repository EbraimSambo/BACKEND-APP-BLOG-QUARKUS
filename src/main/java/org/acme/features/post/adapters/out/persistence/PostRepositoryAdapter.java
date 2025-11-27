package org.acme.features.post.adapters.out.persistence;

import java.util.Optional;
import java.util.UUID;

import org.acme.features.post.adapters.out.mappers.PostMapper;
import org.acme.features.post.application.helpers.PostUtil;
import org.acme.features.post.domain.entity.Post;
import org.acme.features.post.domain.ports.PostRepository;
import org.acme.root.domain.pagination.DataPagination;
import org.acme.root.domain.pagination.Pagination;
import io.quarkus.hibernate.orm.panache.PanacheQuery;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.transaction.Transactional;

@ApplicationScoped
public class PostRepositoryAdapter implements PostRepository {

    @Override
    public Optional<Post> findById(UUID id) {
        return PostEntity.find("id", id)
                .firstResultOptional()
                .map(entity -> PostMapper.toDomain((PostEntity) entity));
    }

    @Override
    @Transactional
    public Post save(Post post) {
        String baseSlug = PostUtil.generateSlug(post.getTitle());
        String finalSlug = baseSlug;
        int attempt = 0;

        while (this.findBySlug(finalSlug).isPresent()) {
            attempt++;
            finalSlug = PostUtil.generateUniqueSlug(baseSlug, attempt);
        }

        post.setSlug(finalSlug);
        return PostMapper.toSavePost(post);
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
                posts);
    }

    @Override
    public Optional<Post> findBySlug(String slug) {
        return PostEntity.find("slug", slug)
                .firstResultOptional()
                .map(entity -> PostMapper.toDomain((PostEntity) entity));
    }

}
