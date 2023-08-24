package com.telstra.codechallenge.githubrepos.exception;

import org.springframework.http.HttpStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class GithubRepoSearchError {
	
	private HttpStatus status;
	private Integer code;
	private String errorMessage;

}
