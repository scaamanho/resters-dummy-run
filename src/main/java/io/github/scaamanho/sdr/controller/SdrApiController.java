package io.github.scaamanho.sdr.controller;

import io.github.scaamanho.sdr.model.RestDummy;
import io.github.scaamanho.sdr.service.SdrApiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/sdr")
public class SdrApiController {
	@Autowired
	SdrApiService service;

	@GetMapping
	public ResponseEntity<List<RestDummy>> getAllRestDummy() {
		List<RestDummy> list = service.getAllRestDummy();
		return new ResponseEntity<>(list, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<RestDummy> getRestDummyById(@PathVariable("id") Long id)
			throws Exception {
		RestDummy entity = service.getRestDummyById(id);
		return new ResponseEntity<>(entity, new HttpHeaders(), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<RestDummy> createOrUpdateRestDummy(RestDummy restDummy)
			throws Exception {
		RestDummy updated= service.createOrUpdateRestDummy(restDummy);
		return new ResponseEntity<>(updated, new HttpHeaders(), HttpStatus.OK);
	}

	@DeleteMapping("/{id}")
	public HttpStatus deleteRestDummyById(@PathVariable("id") Long id)
			throws Exception {
		service.deleteRestDummyById(id);
		return HttpStatus.FORBIDDEN;
	}
}


