package io.github.scaamanho.rds.command;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

@JsonSerialize
public class SearchCmd {
	String search;

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}
}
