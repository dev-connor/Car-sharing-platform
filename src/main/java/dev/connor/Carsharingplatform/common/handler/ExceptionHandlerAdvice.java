package dev.connor.Carsharingplatform.common.handler;

import dev.connor.Carsharingplatform.common.base.ErrorResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RequiredArgsConstructor
@RestControllerAdvice
public class ExceptionHandlerAdvice {

    @ExceptionHandler(AccessDeniedException.class)
    protected ResponseEntity<ErrorResponse> exception(AccessDeniedException e) {
        var httpStatus = HttpStatus.FORBIDDEN;
        var error = ErrorResponse.builder()
                .status(httpStatus.value())
                .error(e.getMessage())
                .message("해당 유저의 접근 권한이 없습니다.")
                .build();

        log.error(e.getMessage(), e);
        return ResponseEntity.status(httpStatus).body(error);
    }

    @ExceptionHandler(AuthenticationException.class)
    protected ResponseEntity<ErrorResponse> exception(AuthenticationException e) {
        var httpStatus = HttpStatus.UNAUTHORIZED;
        var error = ErrorResponse.builder()
                .status(httpStatus.value())
                .error(e.getMessage())
                .message("아이디가 없거나 비밀번호가 틀렸습니다.")
                .build();

        log.error(e.getMessage(), e);
        return ResponseEntity.status(httpStatus).body(error);
    }

    @ExceptionHandler(Exception.class)
    protected ResponseEntity<ErrorResponse> exception(Exception e) {
        var httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
        var error = ErrorResponse.builder()
                .status(httpStatus.value())
                .error(e.getMessage())
                .message("예상치 못한 예외가 발생했습니다.")
                .build();

        log.error(e.getMessage(), e);
        return ResponseEntity.status(httpStatus).body(error);
    }
}