package motion.programming.users.config;

import motion.programming.users.exception.UserNotFoundException;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.reactive.result.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    protected ProblemDetail handleNotFound(RuntimeException ex, HandlerMethod handler) {
        final var pd = ProblemDetail.forStatusAndDetail(NOT_FOUND, ex.getMessage());
        pd.setType(URI.create(handler.getBeanType().getTypeName()));
        pd.setTitle(NOT_FOUND.getReasonPhrase());

        return pd;
    }

    @ExceptionHandler
    protected ProblemDetail handleBadRequest(RuntimeException ex, HandlerMethod handler) {
        final var pd = ProblemDetail.forStatusAndDetail(BAD_REQUEST, ex.getMessage());
        pd.setType(URI.create(handler.getBeanType().getTypeName()));
        pd.setTitle(BAD_REQUEST.getReasonPhrase());

        return pd;
    }
}
