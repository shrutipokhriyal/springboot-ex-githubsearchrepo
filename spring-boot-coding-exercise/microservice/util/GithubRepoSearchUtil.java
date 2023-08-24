package com.telstra.codechallenge.githubrepos.util;

import static com.telstra.codechallenge.githubrepos.util.GithubRepoSearchConstants.CREATED_AFTER;
import static com.telstra.codechallenge.githubrepos.util.GithubRepoSearchConstants.DATE_FORMAT;
import static com.telstra.codechallenge.githubrepos.util.GithubRepoSearchConstants.DESC;
import static com.telstra.codechallenge.githubrepos.util.GithubRepoSearchConstants.ORDER;
import static com.telstra.codechallenge.githubrepos.util.GithubRepoSearchConstants.PER_PAGE;
import static com.telstra.codechallenge.githubrepos.util.GithubRepoSearchConstants.QUERY;
import static com.telstra.codechallenge.githubrepos.util.GithubRepoSearchConstants.REPOSITORIES;
import static com.telstra.codechallenge.githubrepos.util.GithubRepoSearchConstants.SORT;
import static com.telstra.codechallenge.githubrepos.util.GithubRepoSearchConstants.STARS;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.springframework.web.util.UriComponentsBuilder;

public class GithubRepoSearchUtil {

	/**
	 * 
	 * @param gitHubReposBaseUrl
	 * @param noOfRepos
	 * @return URL string
	 */
	public static String buildUrl(String gitHubReposBaseUrl, int noOfRepos, int days) {

		return UriComponentsBuilder.fromHttpUrl(gitHubReposBaseUrl + REPOSITORIES)
				.queryParam(QUERY, CREATED_AFTER + getDate(days))
				.queryParam(SORT, STARS)
				.queryParam(ORDER, DESC)
				.queryParam(PER_PAGE, noOfRepos)
				.build()
				.toUriString();
	}

	/**
	 * 
	 * @param days
	 * @return formatted date
	 */
	public static String getDate(int days) {

		LocalDate lastDate = LocalDate.now().minusDays(days);
		return lastDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));

	}

}
