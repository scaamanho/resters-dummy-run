package io.github.scaamanho.rds.controller;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.scaamanho.rds.service.RdrApiService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,
		RequestMethod.DELETE, RequestMethod.PUT, RequestMethod.PATCH})
public class ApiController {


	@Autowired
	RdrApiService service;

	@GetMapping(value = "/{path}", produces = MediaType.APPLICATION_JSON_VALUE )
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = JsonNode.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = Void.class)))
	})
	@Operation(summary = "Get elements from API", tags= {"API"})
	public ResponseEntity<JsonNode> getRestDummyContent(@PathVariable("path") String id) throws Exception {
		return ResponseEntity.ok(service.getAllObjects(id));
	}

	@GetMapping(value="/{path}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", content = @Content(schema = @Schema(implementation = JsonNode.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = Void.class)))
	})

	@Operation(summary = "Get element from API", tags= {"API"})
	public ResponseEntity<JsonNode> getRestDummyContentById(@PathVariable("path") String path, @PathVariable("id") String id)
			throws Exception {
		return ResponseEntity.ok(service.getObjectFromList(path, id));
	}

	@PostMapping(value="/{path}"
			, produces = MediaType.APPLICATION_JSON_VALUE
			,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", content = @Content(schema = @Schema(implementation = JsonNode.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "422", description = "Validation Error", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = Void.class)))
	})
	@Operation(summary = "Insert element in API", tags= {"API"})
	public ResponseEntity<JsonNode> createRestDummy(@PathVariable("path") String path, @RequestBody JsonNode node)
			throws Exception {
		return new ResponseEntity<JsonNode>(service.addElementToList(path, node), HttpStatus.CREATED);
	}

	@PutMapping(value="/{path}"
			, produces = MediaType.APPLICATION_JSON_VALUE
			,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202", content = @Content(schema = @Schema(implementation = JsonNode.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "422", description = "Validation Error", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = Void.class)))
	})
	@Operation(summary = "Update element in API", tags= {"API"})
	public ResponseEntity<JsonNode> uppdateRestDummy(@PathVariable("path") String path, @RequestBody JsonNode node)
			throws Exception {
		return new ResponseEntity<>(service.replaceElementInList(path, node),HttpStatus.ACCEPTED);
	}

	@PatchMapping(value="/{path}"
			, produces = MediaType.APPLICATION_JSON_VALUE
			,consumes = MediaType.APPLICATION_JSON_VALUE)
	@ApiResponses(value = {
			@ApiResponse(responseCode = "202", content = @Content(schema = @Schema(implementation = JsonNode.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "422", description = "Validation Error", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = Void.class)))
	})
	@Operation(summary = "Patch element in API", tags= {"API"})
	public ResponseEntity<JsonNode> patchRestDummyById(@PathVariable("path") String path, @RequestBody JsonNode node) throws Exception {
		return new ResponseEntity<>(service.replaceElementInList(path, node),HttpStatus.ACCEPTED);
	}

	@DeleteMapping(value="/{path}/{id}")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "400", description = "Bad Request", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "401", description = "Unauthorized", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "403", description = "Forbidden", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "404", description = "Not Found", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "422", description = "Validation Error", content = @Content(schema = @Schema(implementation = Void.class))),
			@ApiResponse(responseCode = "500", description = "Internal Server Error", content = @Content(schema = @Schema(implementation = Void.class)))
	})
	@Operation(summary = "Delete element from API", tags= {"API"})
	public HttpStatus deleteRestDummyById(@PathVariable("path") String path, @PathVariable("id") String id)
			throws Exception {
		service.removeElementFromList(path, id);
		return HttpStatus.NO_CONTENT;
	}
}
