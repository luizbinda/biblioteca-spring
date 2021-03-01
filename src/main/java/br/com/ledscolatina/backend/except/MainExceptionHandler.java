package br.com.ledscolatina.backend.except;

import br.com.ledscolatina.backend.except.custom.*;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.stream.Collectors;

@RestControllerAdvice
public class MainExceptionHandler extends ResponseEntityExceptionHandler {

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request
    ) {
        return handleExceptionInternal(
                ex,
                new ErrorResponseBody(
                        400,
                        ex.getBindingResult().getFieldErrors().stream()
                                .map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.toList()),
                        ex.getBindingResult().getFieldErrors().stream()
                                .map(FieldError::getField).collect(Collectors.toList())
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(value = { CadernoNotFoundException.class })
    protected ResponseEntity<Object> handleCadernoNotFoundException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResponseBody(
                        400,
                        "Caderno inexistente no sistema.",
                        "caderno"
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(value = { AtorNotFoundException.class })
    protected ResponseEntity<Object> handleAtorNotFoundException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResponseBody(
                        400,
                        "Ator inexistente no sistema.",
                        "ator"
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(value = { DiretorNotFoundException.class })
    protected ResponseEntity<Object> handleDiretorNotFoundException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResponseBody(
                        400,
                        "Diretor inexistente no sistema.",
                        "diretor"
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(value = { TituloNotFoundException.class })
    protected ResponseEntity<Object> handleTituloNotFoundException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResponseBody(
                        400,
                        "Titulo inexistente no sistema.",
                        "titulo"
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }



    @ExceptionHandler(value = { NotaNotFoundException.class })
    protected ResponseEntity<Object> handleNotaNotFoundException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResponseBody(
                        400,
                        "Nota inexistente no sistema.",
                        "nota"
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(value = { UsuarioNotFoundException.class })
    protected ResponseEntity<Object> handleUsuarioNotFoundException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResponseBody(
                        400,
                        "Usuário inexistente no sistema.",
                        "usuario"
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

}