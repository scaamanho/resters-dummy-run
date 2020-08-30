package io.github.scaamanho.rds.controller;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.scaamanho.rds.service.RdrApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ApiController {


	@Autowired
	RdrApiService service;

	@GetMapping("/{path}")
	public ResponseEntity<JsonNode> getRestDummyContent(@PathVariable("path") String id) throws Exception {
		JsonNode node = service.getAllObjects(id);
		return new ResponseEntity<>(node, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{path}/{id}")
	public ResponseEntity<JsonNode> getRestDummyContentById(@PathVariable("path") String path, @PathVariable("id") int id)
			throws Exception {
		JsonNode entity = service.getObjectFromList(path, id);
		return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/{path}")
	public ResponseEntity<JsonNode> createOrUpdateRestDummy(@PathVariable("path") String path, @RequestBody String node)
			throws Exception {
		JsonNode entity = service.addElementToList(path, node);
		return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{path}/{id}")
	public HttpStatus deleteRestDummyById(@PathVariable("path") String path, @PathVariable("id") Integer id)
			throws Exception {
		service.removeElementFromList(path, id);
		return HttpStatus.OK;
	}
}
