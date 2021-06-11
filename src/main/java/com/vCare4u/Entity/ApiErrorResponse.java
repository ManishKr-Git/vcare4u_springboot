package com.vCare4u.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Value;

import org.springframework.http.HttpStatus;

import java.time.Instant;
@Value
public class ApiErrorResponse
{
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", timezone = "UTC")
    private final Instant timestamp;
    private final String message;
    private final String error;
    private final HttpStatus status;
    private final String path;
}

