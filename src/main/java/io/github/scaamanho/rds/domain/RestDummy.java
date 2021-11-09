package io.github.scaamanho.rds.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REST_DUMMY")
@Setter
@Getter
public class RestDummy {

	@Id
	//@GeneratedValue(strategy = GenerationType.IDENTITY)
	private String id;

	@Column(name = "name", nullable = false, length = 255)
	private String name;

	@Column(name = "description", nullable = true, length = 255)
	private String description;

	@Column(name = "content", nullable = false, length = 102400)
	private String content;
}
