package org.acme.features.post.adapters.in.rest;

import io.smallrye.common.constraint.NotNull;

public record PostRequest(
        @NotNull String title,
        @NotNull String content,
        @NotNull String bannerPath) {

}
