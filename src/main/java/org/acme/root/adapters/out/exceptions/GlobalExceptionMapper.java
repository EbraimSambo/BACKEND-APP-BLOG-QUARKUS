package org.acme.root.adapters.out.exceptions;

import java.time.Instant;

import org.acme.root.domain.exceptions.ConflictException;
import org.acme.root.domain.exceptions.NotfoundException;
import org.acme.root.domain.response.ErrorResponse;

import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class GlobalExceptionMapper implements ExceptionMapper<Throwable>{
   @Override
    public Response toResponse(Throwable exception) {
        if (exception instanceof ConflictException e) {
            return build(Response.Status.CONFLICT, "CONFLICT", e.getMessage());
        }

        if (exception instanceof NotfoundException e) {
            return build(Response.Status.NOT_FOUND, "NOT_FOUND", e.getMessage());
        }

        if (exception instanceof IllegalArgumentException e){
               return build(Response.Status.BAD_REQUEST, "BAD_REQUEST", e.getMessage());
        }
        String errorMessage = exception.getMessage() != null ? exception.getMessage() : "Erro interno: " + exception.getClass().getSimpleName();
        return build(Response.Status.INTERNAL_SERVER_ERROR, "INTERNAL_ERROR", errorMessage);
    }


    private Response build(Response.Status status, String code, String message) {
        return Response.status(status)
                .entity(new ErrorResponse(code, message, Instant.now()))
                .build();
    }
}
