package com.telstra.codechallenge.githubrepos;

import static com.telstra.codechallenge.githubrepos.util.GithubRepoSearchConstants.ACCEPT;
import static com.telstra.codechallenge.githubrepos.util.GithubRepoSearchConstants.JSON;
import static com.telstra.codechallenge.githubrepos.util.GithubRepoSearchUtil.buildUrl;

import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GitHubRepoSearchService {

	@Value("${github.repos.base.url}")
	private String gitHubReposBaseUrl;

	@Value("${github.repos.search.noOfDays}")
	private int days;

	private final RestTemplate restTemplate;

	public GitHubRepoSearchService(RestTemplate restTemplate) {
		this.restTemplate = restTemplate;
	}

	/**
	 * Returns a list of hottest repositories created in the last week 
	 * Taken from <a href="https://api.github.com/search">https://api.github.com/search</a>.
	 * For API documentation see: <a href="https://docs.github.com//">https://docs.github.com/</a>.
	 * 
	 * @param noOfRepos
	 * 
	 * @return - hottest repositories list.
	 * @throws Exception 
	 */
	public List<GitHubRepository> getHottestRepositories(Integer noOfRepos) {

		log.info("Search hottest repositories service called::GitHubRepoSearchService");
		
		HttpHeaders header = new HttpHeaders();
		header.set(ACCEPT, JSON);

		ResponseEntity<GitHubRepositoryResponse> gitHubRepositoryResponse = restTemplate.exchange(
				buildUrl(gitHubReposBaseUrl, noOfRepos, days), HttpMethod.GET,
				new HttpEntity<GitHubRepositoryResponse>(header), GitHubRepositoryResponse.class);

		List<GitHubRepository> gitHubRepos = null;

		if (gitHubRepositoryResponse.getStatusCode() == HttpStatus.OK) {
			gitHubRepos = gitHubRepositoryResponse.getBody().getItems();
		}

		return gitHubRepos;
	}

}
