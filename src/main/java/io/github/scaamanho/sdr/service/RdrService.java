package io.github.scaamanho.sdr.service;

import io.github.scaamanho.sdr.domain.RestDummy;
import io.github.scaamanho.sdr.repository.RestDummyRepository;
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
		List<RestDummy> list = repository.findByNameOrDescriptionContaining(filter, filter);
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
	public RestDummy createOrUpdateRestDummy(RestDummy entity) throws Exception
	{
		Optional<RestDummy> restDummy = repository.findById(entity.getId());

		if(restDummy.isPresent()) {
			//TODO usar Mappers
			RestDummy newEntity = restDummy.get();
			newEntity.setContent(entity.getContent());
			newEntity.setName(entity.getName());
			newEntity.setDescription(entity.getDescription());
			//TODO Remove whitespaces in json y validate json
			newEntity.setContent(entity.getContent());
			newEntity = repository.save(newEntity);
			return newEntity;
		} else {
			if (StringUtils.isEmpty(entity.getId()))
				entity.setId(entity.hashCode() + "");
			entity = repository.save(entity);
			return entity;
		}
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
