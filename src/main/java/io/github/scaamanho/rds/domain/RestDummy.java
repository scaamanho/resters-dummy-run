package io.github.scaamanho.rds.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="REST_DUMMY")
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

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}
}
