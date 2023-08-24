package com.telstra.codechallenge.githubrepos;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonSetter;
import com.fasterxml.jackson.annotation.Nulls;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class GitHubRepository {

	@JsonProperty("html_url")
	private String htmlUrl;

	@JsonProperty("watchers_count")
	private String watchersCount;

	@JsonProperty("language")
	@JsonSetter(nulls=Nulls.AS_EMPTY)
	private String language;

	@JsonProperty("description")
	@JsonSetter(nulls=Nulls.AS_EMPTY)
	private String description;

	@JsonProperty("name")
	private String name;
}
