package com.sokima.reactive.grpc.bookstore.infrastructure.adapter.persistent.transformer;

import com.sokima.reactive.grpc.bookstore.domain.BookIdentity;
import com.sokima.reactive.grpc.bookstore.domain.generator.ChecksumGenerator;
import com.sokima.reactive.grpc.bookstore.domain.helper.BookIdentityMapper;
import com.sokima.reactive.grpc.bookstore.infrastructure.adapter.persistent.entity.BookIdentityEntity;
import com.sokima.reactive.grpc.bookstore.infrastructure.adapter.persistent.repository.out.BookWithBookIdentityProjection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class BookIdentityEntityTransformer extends BookIdentityMapper {

    private static final Logger log = LoggerFactory.getLogger(BookIdentityEntityTransformer.class);

    private static final String CHECKSUM = "checksum";
    private static final String AUTHOR = "author";
    private static final String TITLE = "title";
    private static final String EDITION = "edition";
    private static final String DESCRIPTION = "description";

    public BookIdentity mapToBookIdentity(final BookIdentityEntity entity) {
        log.trace("Transforming to book identity: {}", entity);
        return bookIdentity(
                entity.getTitle(),
                entity.getAuthor(),
                entity.getEdition(),
                entity.getDescription()
        );
    }

    public BookIdentity mapToPartialBookIdentity(final BookIdentityEntity entity) {
        log.trace("Transforming to partial book identity: {}", entity);
        return partialBookIdentity(
                entity.getTitle(),
                entity.getAuthor(),
                entity.getEdition()
        );
    }

    public BookIdentity mapToPartialBookIdentity(final BookWithBookIdentityProjection projection) {
        log.trace("Transforming to partial book identity: {}", projection);
        return partialBookIdentity(
                projection.getTitle(),
                projection.getAuthor(),
                projection.getEdition()
        );
    }

    public BookIdentityEntity mapToBookIdentityEntity(final BookIdentity bookIdentity) {
        log.trace("Transforming to book identity entity: {}", bookIdentity);
        final var bookIdentityEntity = new BookIdentityEntity();
        bookIdentityEntity.setIsNew(Boolean.TRUE);
        bookIdentityEntity.setChecksum(ChecksumGenerator.generateBookChecksum(bookIdentity));
        bookIdentityEntity.setAuthor(bookIdentity.author());
        bookIdentityEntity.setTitle(bookIdentity.title());
        bookIdentityEntity.setEdition(bookIdentity.edition());
        return bookIdentityEntity;
    }

    public BookIdentityEntity mapRow(final Map<String, Object> row) {
        log.trace("Transforming to book identity entity: {}", row);
        final var entity = new BookIdentityEntity();
        entity.setChecksum((String) row.get(CHECKSUM));
        entity.setAuthor((String) row.get(AUTHOR));
        entity.setTitle((String) row.get(TITLE));
        entity.setEdition((String) row.get(EDITION));
        entity.setDescription((String) row.get(DESCRIPTION));
        return entity;
    }
}
