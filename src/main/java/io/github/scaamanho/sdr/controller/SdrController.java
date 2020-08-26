package io.github.scaamanho.sdr.controller;

import com.fasterxml.jackson.databind.JsonNode;
import io.github.scaamanho.sdr.model.RestDummy;
import io.github.scaamanho.sdr.service.SdrApiService;
import io.github.scaamanho.sdr.service.SdrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/dummy")
public class SdrController {


	@Autowired
	SdrService service;

	@GetMapping("/{path}")
	public ResponseEntity<JsonNode> getRestDummyContent(@PathVariable("path") Long id) throws Exception{
		JsonNode node = service.getAllObjects(id);
		return new ResponseEntity<JsonNode>(node, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{path}/{id}")
	public ResponseEntity<JsonNode> getRestDummyContentById(@PathVariable("path") Long path,@PathVariable("id") int id)
			throws Exception {
		JsonNode entity = service.getObjectFromList(path,id);//service.getRestDummyById(path);
		return new ResponseEntity<JsonNode>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping("/{path}")
	public ResponseEntity<JsonNode> createOrUpdateRestDummy(@PathVariable("path") Long path, Object restDummy)
			throws Exception {
		//RestDummy updated= service.createOrUpdateRestDummy(restDummy);
		return new ResponseEntity<JsonNode>(null, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{path}/{id}")
	public HttpStatus deleteRestDummyById(@PathVariable("path") Long path,@PathVariable("id") Long id)
			throws Exception {
		//service.deleteRestDummyById(id);
		return HttpStatus.FORBIDDEN;
	}
}
