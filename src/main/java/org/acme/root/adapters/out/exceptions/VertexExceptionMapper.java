package org.acme.root.adapters.out.exceptions;

import java.time.Instant;

import org.acme.root.domain.response.ErrorResponse;

import io.vertx.core.impl.NoStackTraceException;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class VertexExceptionMapper implements ExceptionMapper<NoStackTraceException> {
    @Override
    public Response toResponse(NoStackTraceException exception) {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                .entity(new ErrorResponse("VERTX_ERROR", exception.getMessage(), Instant.now()))
                .build();
    }
}
