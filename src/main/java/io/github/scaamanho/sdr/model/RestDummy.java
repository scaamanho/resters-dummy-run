package io.github.scaamanho.sdr.model;

import javax.persistence.*;

@Entity
@Table(name="REST_DUMMY")
public class RestDummy {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name="name", nullable=false, length=64)
	private String name;

	@Column(name="description", nullable=true, length=255)
	private String description;

	@Column(name="content", nullable=false, length=2048)
	private String content;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
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
