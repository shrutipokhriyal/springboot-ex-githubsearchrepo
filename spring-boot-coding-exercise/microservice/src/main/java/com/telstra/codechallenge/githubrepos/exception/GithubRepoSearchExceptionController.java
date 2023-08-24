package com.telstra.codechallenge.githubrepos.exception;

import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpClientErrorException;

import lombok.extern.slf4j.Slf4j;

@RestControllerAdvice
@Slf4j
public class GithubRepoSearchExceptionController {

	@ExceptionHandler({ TypeMismatchException.class, GithubRepoSearchException.class })
	public ResponseEntity<GithubRepoSearchError> handleTypeMismatchException(Exception ex) {

		log.error("Exception occured: ", ex);

		return new ResponseEntity<>(
				new GithubRepoSearchError(HttpStatus.BAD_REQUEST, HttpStatus.BAD_REQUEST.value(), ex.getMessage()),
				HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler({ Exception.class, HttpClientErrorException.class })
	public ResponseEntity<GithubRepoSearchError> handleException(Exception ex) {

		log.error("Exception occured: ", ex);

		return new ResponseEntity<>(new GithubRepoSearchError(HttpStatus.INTERNAL_SERVER_ERROR,
				HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage()), HttpStatus.INTERNAL_SERVER_ERROR);
	}

}
