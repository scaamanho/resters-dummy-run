package io.github.scaamanho.rds.command;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.Setter;

@JsonSerialize
@Setter
@Getter
public class SearchCmd {
	String search;
}
