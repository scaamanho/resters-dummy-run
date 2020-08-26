package io.github.scaamanho.sdr.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import io.github.scaamanho.sdr.model.RestDummy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SdrService {

	@Autowired
	SdrApiService service;

	public JsonNode getJSonObjectsFromString(String content) throws Exception
	{
		return new ObjectMapper().readTree(content);
	}




	public String getStringFormJSonObject(Object content) throws Exception
	{
		return null;
	}

	public JsonNode getAllObjects(Long id) throws Exception
	{
		RestDummy entity = service.getRestDummyById(id);
		return getJSonObjectsFromString(entity.getContent());
	}

	public JsonNode getObjectFromList(Long id, int elementNumber) throws Exception
	{
		JsonNode node = getAllObjects(id);
		if(node.isArray())
			node = (node.size()>elementNumber) ? node = node.get(elementNumber) : null;
		return node;
	}

	public JsonNode addElementToList(Long id, String content) throws Exception
	{
		RestDummy entity = service.getRestDummyById(id);
		JsonNode node = getJSonObjectsFromString(entity.getContent());
		JsonNode element = getJSonObjectsFromString(content);

		if(node.isArray())
			((ArrayNode)node).add(element);
		else
			node = element;

		entity.setContent(node.toString());
		service.createOrUpdateRestDummy(entity);
		return node;
	}
	public JsonNode removeElementFromList(Long id, int elementNumber) throws Exception
	{
		RestDummy entity = service.getRestDummyById(id);
		JsonNode node = getJSonObjectsFromString(entity.getContent());
		if(node.isArray())
			((ArrayNode)node).remove(elementNumber);
		else
			node = getJSonObjectsFromString("{}");

		entity.setContent(node.toString());
		service.createOrUpdateRestDummy(entity);
		return node;
	}
}
