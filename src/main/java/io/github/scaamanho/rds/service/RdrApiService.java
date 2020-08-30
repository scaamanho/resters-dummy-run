package io.github.scaamanho.rds.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.github.scaamanho.rds.domain.RestDummy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RdrApiService {

	@Autowired
	RdrService service;

	public JsonNode getJSonObjectsFromString(String content) throws Exception {
		return new ObjectMapper().readTree(content);
	}

	public String getStringFormJSonObject(Object content) throws Exception {
		return null;
	}

	public JsonNode getAllObjects(String id) throws Exception {
		RestDummy entity = service.getRestDummyById(id);
		return getJSonObjectsFromString(entity.getContent());
	}

	public JsonNode getObjectFromList(String id, int elementNumber) throws Exception {
		JsonNode node = getAllObjects(id);
		if (node.isArray())
			node = (node.size() > elementNumber) ? node.get(elementNumber) : null;
		return node;
	}

	public JsonNode addElementToList(String id, String content) throws Exception {
		RestDummy entity = service.getRestDummyById(id);
		JsonNode node = getJSonObjectsFromString(entity.getContent());
		JsonNode element = getJSonObjectsFromString(content.toString());
		if (node.isArray())
			((ArrayNode) node).add(element);
		else
			node = element;
		entity.setContent(node.toString());
		service.createOrUpdateRestDummy(entity);
		return node;
	}

	public JsonNode removeElementFromList(String id, int elementNumber) throws Exception {
		RestDummy entity = service.getRestDummyById(id);
		JsonNode node = getJSonObjectsFromString(entity.getContent());
		if (node.isArray())
			((ArrayNode) node).remove(elementNumber);
		else
			node = getJSonObjectsFromString("{}");
		entity.setContent(node.toString());
		service.createOrUpdateRestDummy(entity);
		return node;
	}
}
