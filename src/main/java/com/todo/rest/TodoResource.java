package com.todo.rest;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;

import com.todo.domain.Todo;
import com.todo.service.TodoService;

@Path("/todos")
public class TodoResource {

	@Inject
	private TodoService todoService;

	@POST
	@Consumes("application/json")
	public Response create(Todo entity) {
		todoService.create(entity);
		return Response.created(
				UriBuilder.fromResource(TodoResource.class)
						.path(String.valueOf(entity.getId())).build()).build();
	}

	@GET
	@Path("/{id:[0-9][0-9]*}")
	@Produces(MediaType.APPLICATION_JSON)
	public Todo lookupTodoById(@PathParam("id") long id) {
		Todo todo = todoService.find(id);
		if (todo == null) {
			throw new WebApplicationException(Response.Status.NOT_FOUND);
		}
		return todo;
	}

}
