package com.app.vple.config.jwt;

public interface JwtProperties {
    String SECRET = "{}";
    int EXPIRATION_TIME =  86400;
    String TOKEN_PREFIX = "Bearer ";
    String HEADER_STRING = "Authorization";
}
