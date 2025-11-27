package org.acme.root.adapters.out.cors;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;
import jakarta.ws.rs.ext.Provider;

@Provider
@ApplicationScoped
public class CorsFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext responseContext) {
        responseContext.getHeaders().add("Access-Control-Allow-Origin", "*"); // ou
        // "http://localhost:3000"
        // responseContext.getHeaders().add("Access-Control-Allow-Headers", "origin,
        // content-type, accept, authorization");
        // responseContext.getHeaders().add("Access-Control-Allow-Credentials", "true");
        // responseContext.getHeaders().add("Access-Control-Allow-Methods", "GET, POST,
        // PUT, DELETE, OPTIONS, HEAD");
        // responseContext.getHeaders().add("Access-Control-Max-Age", "1209600");
    }
}
