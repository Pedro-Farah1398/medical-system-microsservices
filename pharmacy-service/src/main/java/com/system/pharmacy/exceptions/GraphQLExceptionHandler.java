package com.system.pharmacy.exceptions;

import graphql.schema.DataFetchingEnvironment;
import org.springframework.graphql.execution.DataFetcherExceptionResolverAdapter;
import org.springframework.stereotype.Component;
import graphql.GraphQLError;
import graphql.GraphqlErrorBuilder;

@Component
public class GraphQLExceptionHandler extends DataFetcherExceptionResolverAdapter {

    @Override
    protected GraphQLError resolveToSingleError(Throwable ex, DataFetchingEnvironment env) {
        if (ex instanceof MedicineSoldOutException || ex instanceof MedicineNotFoundException) {
            return GraphqlErrorBuilder.newError(env)
                    .message(ex.getMessage())
                    .build();
        }
        return null;
    }
}

