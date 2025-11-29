package org.acme.features.post.adapters.in.rest;

import java.util.Optional;

import io.smallrye.common.constraint.NotNull;
import io.smallrye.common.constraint.Nullable;

public record PostRequest(
                @NotNull String title,
                @NotNull String content,
                @NotNull String bannerPath,
                @Nullable Optional<String> excerpt) {

}
