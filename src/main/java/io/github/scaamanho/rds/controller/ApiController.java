package io.github.scaamanho.rds.controller;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.scaamanho.rds.service.RdrApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {


	@Autowired
	RdrApiService service;

	@GetMapping(value = "/{path}", produces = MediaType.APPLICATION_JSON_VALUE )
	public ResponseEntity<JsonNode> getRestDummyContent(@PathVariable("path") String id) throws Exception {
		return ResponseEntity.ok(service.getAllObjects(id));
	}

	@GetMapping(value="/{path}/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonNode> getRestDummyContentById(@PathVariable("path") String path, @PathVariable("id") int id)
			throws Exception {
		return ResponseEntity.ok(service.getObjectFromList(path, id));
	}

	@PostMapping(value="/{path}"
			, produces = MediaType.APPLICATION_JSON_VALUE
			,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonNode> createRestDummy(@PathVariable("path") String path, @RequestBody String node)
			throws Exception {
		return ResponseEntity.ok(service.addElementToList(path, node));
	}

	@PutMapping(value="/{path}"
			, produces = MediaType.APPLICATION_JSON_VALUE
			,consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<JsonNode> uppdateRestDummy(@PathVariable("path") String path, @RequestBody String node)
			throws Exception {
		return createRestDummy(path, node);
	}

	@DeleteMapping(value="/{path}/{id}",
			produces = MediaType.APPLICATION_JSON_VALUE
			,consumes = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus deleteRestDummyById(@PathVariable("path") String path, @PathVariable("id") Integer id)
			throws Exception {
		service.removeElementFromList(path, id);
		return HttpStatus.OK;
	}

	@PatchMapping(value="/{path}")
	public HttpStatus deleteRestDummyById(@PathVariable("path") String path) throws Exception {
		//TODO
		return HttpStatus.OK;
	}
}
