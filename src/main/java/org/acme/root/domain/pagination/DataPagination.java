package org.acme.root.domain.pagination;

import java.util.Optional;

public record DataPagination(
        int page,
        int size,
        Optional<String> query

) {


}
