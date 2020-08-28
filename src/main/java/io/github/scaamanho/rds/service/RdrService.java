package io.github.scaamanho.rds.service;

import io.github.scaamanho.rds.domain.RestDummy;
import io.github.scaamanho.rds.repository.RestDummyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RdrService {
	@Autowired
	RestDummyRepository repository;

	public List<RestDummy> getAllRestDummy() {
		List<RestDummy> list = repository.findAll();
		if (!list.isEmpty()) {
			return list;
		} else {
			return new ArrayList<>();
		}
	}

	public List<RestDummy> searchRestDummy(String filter) {
		List<RestDummy> list = repository.findByIdContainingIgnoreCaseOrNameContainingIgnoreCaseOrDescriptionContainingIgnoreCase(filter, filter, filter);
		if (!list.isEmpty()) {
			return list;
		} else {
			return new ArrayList<>();
		}
	}

	public RestDummy getRestDummyById(String id) throws Exception {
		Optional<RestDummy> employee = repository.findById(id);
		if (employee.isPresent()) {
			return employee.get();
		} else {
			return new RestDummy();
		}
	}

	//TODO SEparar update y create
	public RestDummy createOrUpdateRestDummy(RestDummy entity) throws Exception {
		//Set default id
		if (StringUtils.isEmpty(entity.getId()))
			entity.setId(entity.hashCode() + "");
		//Fix id non valid chars
		entity.setId(entity.getId().replaceAll("/", "_"));
		//Set defaultName
		if (StringUtils.isEmpty(entity.getName()))
			entity.setName(entity.getId());
		entity = repository.save(entity);
		return entity;

		/**Optional<RestDummy> restDummy = repository.findById(entity.getId());
		 if(restDummy.isPresent()) {
		 entity = repository.save(entity);
		 } else {
		 entity = repository.save(entity);
		 }
		 return entity;*/
	}

	public void deleteRestDummyById(String id) throws Exception {
		Optional<RestDummy> employee = repository.findById(id);

		if (employee.isPresent()) {
			repository.deleteById(id);
		} else {
			throw new Exception("No RestDummy record exist for given id");
		}
	}
}
