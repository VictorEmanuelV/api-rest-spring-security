package dev.victoremanuelvieira0.gmail.com.api_rest_spring_security.exception.handler;

import lombok.*;

import java.time.Instant;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class StandardError {
    private Instant timestamp;
    private Integer Status;
    private String message;
    private String error;
    private String path;

}
