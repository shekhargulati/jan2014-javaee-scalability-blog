package com.todo.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class Todo {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@NotNull
	@Size(min = 10, max = 40)
	private String todo;
	
	@ElementCollection(fetch=FetchType.EAGER)
	@CollectionTable(name = "Tags", joinColumns = @JoinColumn(name = "todo_id"))
	@Column(name = "tag")
	@NotNull
	private List<String> tags;

	@NotNull
	private Date createdOn = new Date();

	public Todo(String todo) {
		this.todo = todo;
	}

	public Todo() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTodo() {
		return todo;
	}

	public void setTodo(String todo) {
		this.todo = todo;
	}

	public Date getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(Date createdOn) {
		this.createdOn = createdOn;
	}
	
	
	public void setTags(List<String> tags) {
		this.tags = tags;
	}
	
	public List<String> getTags() {
		return tags;
	}

	@Override
	public String toString() {
		return "Todo [id=" + id + ", todo=" + todo + ", tags=" + tags
				+ ", createdOn=" + createdOn + "]";
	}


	
}
