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

    @ExceptionHandler(value = { ClasseNotFoundException.class })
    protected ResponseEntity<Object> handleClasseNotFoundException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResponseBody(
                        400,
                        "Classe inexistente no sistema.",
                        "classe"
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(value = { ItemNotFoundException.class })
    protected ResponseEntity<Object> handleItemNotFoundException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResponseBody(
                        400,
                        "Item inexistente no sistema.",
                        "item"
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(value = { ClienteNotFoundException.class })
    protected ResponseEntity<Object> handleClienteNotFoundException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResponseBody(
                        400,
                        "Cliente inexistente no sistema.",
                        "cliente"
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(value = { LocacaoNotFoundException.class })
    protected ResponseEntity<Object> handleLocacaoNotFoundException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResponseBody(
                        400,
                        "Locacao inexistente no sistema.",
                        "locacao"
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(value = { ClienteNotActiveException.class })
    protected ResponseEntity<Object> handleClienteNotActiveException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResponseBody(
                        400,
                        "Cliente inativo no sistema.",
                        "cliente"
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(value = { ItemLocadoException.class })
    protected ResponseEntity<Object> handleItemLocadoException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResponseBody(
                        400,
                        ex.getMessage(),
                        "cliente"
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(value = { AtorInTituloException.class })
    protected ResponseEntity<Object> handleAtorInTituloException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResponseBody(
                        400,
                        "Ator vinculado a um titulo",
                        "ator"
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(value = { ClasseInTituloException.class })
    protected ResponseEntity<Object> handleClasseInTituloException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResponseBody(
                        400,
                        "Classe vinculada a um titulo",
                        "classe"
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }

    @ExceptionHandler(value = { DiretorInTituloException.class })
    protected ResponseEntity<Object> handleDiretorInTituloException(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(
                ex,
                new ErrorResponseBody(
                        400,
                        "Diretor vinculado a um titulo",
                        "diretor"
                ),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }


}