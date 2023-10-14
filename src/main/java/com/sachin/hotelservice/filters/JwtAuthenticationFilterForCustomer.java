package com.sachin.hotelservice.filters;


import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.ClientRequest;
import org.springframework.web.reactive.function.client.WebClient;

import java.io.IOException;


public class JwtAuthenticationFilterForCustomer extends OncePerRequestFilter {
    @Value("${security.customerApiUrl}")
    private String customerApiUrl;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        System.out.println("customer  filter start");
        String authorizationHeader = request.getHeader("Authorization");
        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {
            String jwtToken = authorizationHeader.substring(7);
            ResponseEntity<String> hotelResponse = makeAuthenticatedRequest(customerApiUrl, jwtToken);
            if (hotelResponse.getStatusCode() == HttpStatus.OK) {
                System.out.println("ok");
                filterChain.doFilter(request, response);
            } else {
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            }

        } else {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        }
        System.out.println("hotel customer filter end");
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);

    }

    private ResponseEntity<String> makeAuthenticatedRequest(String apiUrl, String bearerToken) {
        try {
            WebClient webClient = WebClient.builder()
                    .baseUrl(apiUrl)
                    .filter((request, next) -> {
                        ClientRequest filteredRequest = ClientRequest.from(request)
                                .headers(headers -> headers.setBearerAuth(bearerToken))
                                .build();
                        return next.exchange(filteredRequest);
                    })
                    .build();

            String responseBody = webClient.post()
                    .contentType(MediaType.APPLICATION_JSON)
                    .accept(MediaType.APPLICATION_JSON)
                    .body(BodyInserters.empty())
                    .retrieve()
                    .bodyToMono(String.class)
                    .block();
            return new ResponseEntity<>(responseBody, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.FORBIDDEN);
        }
    }
}