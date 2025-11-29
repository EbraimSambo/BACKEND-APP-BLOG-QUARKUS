package org.acme.features.post.application.ports.in;

import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Optional;
import java.util.UUID;

import org.acme.features.post.domain.entity.Post;
import org.acme.features.post.domain.ports.PostRepository;
import org.acme.root.domain.pagination.DataPagination;
import org.acme.root.domain.pagination.Pagination;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;

@QuarkusTest
public class PostServiceImplTest {

    @InjectMock
    PostRepository postRepository;

    @Test
    void testFindById() {
        var id = UUID.randomUUID();
        var post = new Post();
        post.setId(id);
        when(postRepository.findById(id)).thenReturn(Optional.of(post));
        var service = new PostServiceImpl(postRepository);
        var result = service.findById(id);
        assertEquals(id, result.getId());
    }

    @Test
    void testFindBySlug() {
        var slug = "my-post";
        var post = new Post();
        post.setSlug(slug);
        when(postRepository.findBySlug(slug)).thenReturn(Optional.of(post));
        var service = new PostServiceImpl(postRepository);
        var result = service.findBySlug(slug);
        assertEquals(slug, result.getSlug());
    }

}
