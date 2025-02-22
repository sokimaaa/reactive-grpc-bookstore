package com.sokima.reactive.grpc.bookstore.usecase.get.out;

import org.immutables.value.Value;

@Value.Immutable
public interface GetBookFlowResult {
    String checksum();

    String title();

    String author();

    String edition();

    String description();

    Boolean isAvailable();
}
