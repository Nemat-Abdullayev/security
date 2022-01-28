package az.security.exception;

import az.security.dto.ApiError;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

@Slf4j
@RestControllerAdvice
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RestExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<?> handleAuthenticationException(AuthenticationException ex, WebRequest request) {
        log.error("authentication exception {} ", ex.getMessage());
        String uri = request.getDescription(false);
        ApiError apiError = new ApiError(HttpStatus.FORBIDDEN, uri);
        apiError.setMessage("request is unauthorized");
        apiError.setDebugMessage(ex.getLocalizedMessage());
        return new ResponseEntity<>(apiError, HttpStatus.FORBIDDEN);
    }
}
