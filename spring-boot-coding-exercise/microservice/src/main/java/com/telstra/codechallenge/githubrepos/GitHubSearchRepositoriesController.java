package com.telstra.codechallenge.githubrepos;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telstra.codechallenge.githubrepos.exception.GithubRepoSearchException;

@RestController
@RequestMapping("/search")
public class GitHubSearchRepositoriesController {
	private final GitHubRepoSearchService gitHubRepoSearchService;

	public GitHubSearchRepositoriesController(GitHubRepoSearchService gitHubRepoSearchService) {
		this.gitHubRepoSearchService = gitHubRepoSearchService;
	}

	@GetMapping(path = "/hottestRepos/{noOfRepos}")
	public List<GitHubRepository> hottestRepos(@PathVariable Integer noOfRepos) throws Exception {

		if (noOfRepos <= 0)
			throw new GithubRepoSearchException("Invalid number of repositories requested", null);

		return gitHubRepoSearchService.getHottestRepositories(noOfRepos);
	}
}