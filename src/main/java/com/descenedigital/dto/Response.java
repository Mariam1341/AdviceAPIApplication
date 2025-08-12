package com.descenedigital.dto;

import lombok.*;

@Data
@Builder
public class Response<T> {
    private boolean success;
    private String message;
    private T data;
}
