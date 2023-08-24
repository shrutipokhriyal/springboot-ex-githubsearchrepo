package com.telstra.codechallenge.githubrepos.exception;

public class GithubRepoSearchException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public GithubRepoSearchException(String errorMessage, Throwable err) {
		super(errorMessage, err);
	}

}
