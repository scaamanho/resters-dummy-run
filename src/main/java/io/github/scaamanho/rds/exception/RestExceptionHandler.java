package io.github.scaamanho.rds.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletResponse;

@Slf4j
@Order(Ordered.HIGHEST_PRECEDENCE)
@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	/**
	 * Captura las excepciones cuando se produce una denegacion de servicio
	 * @param e the exception
	 * @param response the current response
	 * @return a {@code ResponseEntity} instance
	 */
	@ExceptionHandler(HttpClientErrorException.Unauthorized.class)
	public ResponseEntity<Void> handleUnauthorizedException(HttpClientErrorException.Unauthorized e, HttpServletResponse response) {
		log.error(e.getMessage(),e);
		return new ResponseEntity<Void>(HttpStatus.UNAUTHORIZED);
	}

	/**
	 * Captura las excepciones internas
	 * @param e the exception
	 * @param response the current response
	 * @return a {@code ResponseEntity} instance
	 */
	@ExceptionHandler(RdrException.class)
	public ResponseEntity<Void> handleInairportException(RdrException e, HttpServletResponse response) {
		log.error(e.getMessage(),e);
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * Customize the response for MethodArgumentNotValidException.
	 * <p>This method delegates to {@link #handleExceptionInternal}.
	 * @param e the exception
	 * @param headers the headers to be written to the response
	 * @param status the selected response status
	 * @param request the current request
	 * @return a {@code ResponseEntity} instance
	 */
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException e, HttpHeaders headers,
																																HttpStatus status, WebRequest request) {
		log.error(e.getMessage(),e);
		return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
	}

	/**
	 * Captura las excepciones en los servicios
	 * @param e the exception
	 * @param response the current response
	 * @return a {@code ResponseEntity} instance
	 */
	@ExceptionHandler(ServletException.class)
	public ResponseEntity<Void> handleServletException(ServletException  e, HttpServletResponse response) {
		log.error(e.getMessage(),e);
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

	/**
	 * Captura las excepciones de tipo general
	 * @param e the exception
	 * @param response the current response
	 * @return a {@code ResponseEntity} instance
	 */
	@ExceptionHandler(Exception.class)
	public ResponseEntity<Void> handleRuntimeException(Exception  e, HttpServletResponse response) {
		log.error(e.getMessage(),e);
		return new ResponseEntity<Void>(HttpStatus.BAD_REQUEST);
	}

}
