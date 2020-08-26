package io.github.scaamanho.sdr.service;

import io.github.scaamanho.sdr.model.RestDummy;
import io.github.scaamanho.sdr.repository.RestDummyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SdrApiService {
	@Autowired
	RestDummyRepository repository;

	public List<RestDummy> getAllRestDummy()
	{
		List<RestDummy> list = repository.findAll();
		if(!list.isEmpty()) {
			return list;
		} else {
			return new ArrayList<>();
		}
	}

	public RestDummy getRestDummyById(Long id) throws Exception
	{
		Optional<RestDummy> employee = repository.findById(id);
		if(employee.isPresent()) {
			return employee.get();
		} else {
			throw new Exception("No RestDummy record exist for given id");
		}
	}

	public RestDummy createOrUpdateRestDummy(RestDummy entity) throws Exception
	{
		Optional<RestDummy> restDummy = repository.findById(entity.getId());

		if(restDummy.isPresent())
		{
			RestDummy newEntity = restDummy.get();
			newEntity.setContent(entity.getContent());
			newEntity.setDescription(entity.getDescription());
			//TODO Remove whitespaces in json y validate json
			newEntity.setContent(entity.getContent());
			newEntity = repository.save(newEntity);
			return newEntity;
		} else {
			entity = repository.save(entity);
			return entity;
		}
	}

	public void deleteRestDummyById(Long id) throws Exception
	{
		Optional<RestDummy> employee = repository.findById(id);

		if(employee.isPresent())
		{
			repository.deleteById(id);
		} else {
			throw new Exception("No RestDummy record exist for given id");
		}
	}
}
