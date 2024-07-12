package hr.ericsson.sample.trello.controllers.advice;

import hr.ericsson.sample.trello.exceptions.NotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.util.ObjectUtils;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.HandlerMethodValidationException;

import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Slf4j
@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler({MethodArgumentNotValidException.class, HandlerMethodValidationException.class})
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ProblemDetail handleValidationException(ErrorResponse errorResponse) {
        var detailMessageArguments = errorResponse.getDetailMessageArguments();
        var errorResponseMessage = Objects.isNull(detailMessageArguments) ?
                "validation exception" : Stream.of(detailMessageArguments)
                .filter(Predicate.not(ObjectUtils::isEmpty))
                .map(Object::toString)
                .collect(Collectors.joining(" ."));
        log.info("Validation error : {}", errorResponseMessage);
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, errorResponseMessage);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    public ProblemDetail handleNotFoundException(NotFoundException ex) {
        log.info("Not found exception : {}", ex.getMessage());
        return ProblemDetail.forStatusAndDetail(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(code = HttpStatus.INTERNAL_SERVER_ERROR)
    public ProblemDetail handleException(Exception ex) {
        log.error("Internal Server error : ", ex);
        return ProblemDetail.forStatusAndDetail(HttpStatus.INTERNAL_SERVER_ERROR, ex.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(code = HttpStatus.BAD_REQUEST)
    public ProblemDetail handleIllegalArgumentException(IllegalArgumentException ex){
        log.info("Error : {}", ex.getMessage());
        return ProblemDetail.forStatusAndDetail(HttpStatus.BAD_REQUEST, ex.getMessage());
    }
}
