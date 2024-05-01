package com.sokima.reactive.grpc.bookstore.infrastructure.adapter.grpc.get;

import com.sokima.reactive.grpc.bookstore.infrastructure.adapter.grpc.common.transformer.Java2ProtoTransformer;
import com.sokima.reactive.grpc.bookstore.infrastructure.adapter.grpc.common.transformer.Proto2JavaTransformer;
import com.sokima.reactive.grpc.bookstore.infrastructure.adapter.grpc.common.validator.FieldValidator;
import com.sokima.reactive.grpc.bookstore.infrastructure.adapter.grpc.Workflow;
import com.sokima.reactive.grpc.bookstore.infrastructure.adapter.grpc.WorkflowTemplate;
import com.sokima.reactive.grpc.bookstore.proto.GetBookRequest;
import com.sokima.reactive.grpc.bookstore.proto.GetBookResponse;
import com.sokima.reactive.grpc.bookstore.usecase.Flow;
import com.sokima.reactive.grpc.bookstore.usecase.get.in.SearchOption;
import com.sokima.reactive.grpc.bookstore.usecase.get.out.GetBookFlowResult;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class GetBookWorkflow implements Workflow<GetBookRequest, GetBookResponse> {

    private final WorkflowTemplate<GetBookRequest, SearchOption<?>, GetBookFlowResult, GetBookResponse> delegate;

    public GetBookWorkflow(
            final FieldValidator<GetBookRequest> requestFieldValidator,
            final Proto2JavaTransformer<GetBookRequest, SearchOption<?>> proto2JavaTransformer,
            final Flow<SearchOption<?>, GetBookFlowResult> flow,
            final Java2ProtoTransformer<GetBookResponse, GetBookFlowResult> java2ProtoTransformer,
            final FieldValidator<GetBookResponse> responseFieldValidator
    ) {
        this.delegate = new WorkflowTemplate<>(
                requestFieldValidator,
                proto2JavaTransformer,
                flow,
                java2ProtoTransformer,
                responseFieldValidator
        );
    }

    @Override
    public Flux<GetBookResponse> doWorkflow(final GetBookRequest getBookRequest) {
        return delegate.doWorkflow(getBookRequest);
    }
}
