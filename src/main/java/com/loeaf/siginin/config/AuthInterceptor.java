package com.loeaf.siginin.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.client.ClientHttpRequestExecution;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.http.client.ClientHttpResponse;
import org.springframework.util.StringUtils;

import java.io.IOException;
@Configuration
public class AuthInterceptor implements ClientHttpRequestInterceptor {

    @Override
    public ClientHttpResponse intercept(HttpRequest request, byte[] body, ClientHttpRequestExecution execution) throws IOException {
        // Retrieve the token from the request headers
        String token = request.getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

        // If the token is present, you can use it to authenticate the request
        if (StringUtils.hasText(token)) {
            // Add the token to the request headers, or use it to authenticate the request in some other way
            // For example, you can add an "Authorization" header with the value "Bearer <token>"
            request.getHeaders().add(HttpHeaders.AUTHORIZATION, "Bearer " + token);
        }

        // Execute the request and get the response
        ClientHttpResponse response = execution.execute(request, body);

        // Check the response status code and handle errors if necessary
        if (response.getStatusCode() == HttpStatus.UNAUTHORIZED) {
            // Handle the unauthorized error
        }

        // Return the response
        return response;
    }
}