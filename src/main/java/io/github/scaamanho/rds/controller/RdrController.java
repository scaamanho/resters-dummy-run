package io.github.scaamanho.rds.controller;

import io.github.scaamanho.rds.domain.RestDummy;
import io.github.scaamanho.rds.service.RdrService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rdr")
@CrossOrigin(origins = "*", methods= {RequestMethod.GET,RequestMethod.POST,RequestMethod.DELETE})
public class RdrController {
	@Autowired
	RdrService service;

	@GetMapping
	@Operation(tags= {"Inrternal"})
	public ResponseEntity<List<RestDummy>> getAllRestDummy() {
		List<RestDummy> list = service.getAllRestDummy();
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	@Operation(tags= {"Inrternal"})
	public ResponseEntity<RestDummy> getRestDummyById(@PathVariable("id") String id)
			throws Exception {
		RestDummy entity = service.getRestDummyById(id);
		return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	@Operation(tags= {"Inrternal"})
	public ResponseEntity<RestDummy> createOrUpdateRestDummy(RestDummy restDummy)
			throws Exception {
		RestDummy updated= service.createOrUpdateRestDummy(restDummy);
		return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.CREATED);
	}

	@DeleteMapping("/{id}")
	@Operation(tags= {"Inrternal"})
	public HttpStatus deleteRestDummyById(@PathVariable("id") String id)
			throws Exception {
		service.deleteRestDummyById(id);
		return HttpStatus.NO_CONTENT;
	}
}


