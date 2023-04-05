package dev.connor.Carsharingplatform.common.base;

import lombok.*;

import static java.util.stream.Collectors.toMap;

@Getter
@Builder
@RequiredArgsConstructor
public class ErrorResponse {
    private final int status;
    private final String error;
    private final String message;
}