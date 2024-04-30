package com.sokima.reactive.grpc.bookstore.infrastructure.adapter.test;


import com.sokima.reactive.grpc.bookstore.infrastructure.context.common.TransformerContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Import(TransformerContext.class)
@ComponentScan("com.sokima.reactive.grpc.bookstore.infrastructure.adapter.persistent.transformer")
public @interface PersistentTestContext {
}
