package com.telstra.codechallenge.githubrepos;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GitHubRepositoryResponse {
	
	private List<GitHubRepository> items;
	
}
