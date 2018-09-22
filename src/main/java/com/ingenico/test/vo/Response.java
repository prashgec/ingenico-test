package com.ingenico.test.vo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Response {
    private ResponseStatus status;
    private String message;
}
