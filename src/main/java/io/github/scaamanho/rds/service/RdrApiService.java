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

	/**
	 * Convert string to Json
	 * @param content string
	 * @return jsonNode
	 * @throws Exception e
	 */
	public JsonNode getJSonObjectsFromString(String content) throws Exception {
		return new ObjectMapper().readTree(content);
	}

	/**
	 * Convert Json to String
	 * @param content jsonNode
	 * @return string
	 * @throws Exception e
	 */
	public String getStringFormJSonObject(Object content) throws Exception {
		return null;
	}

	/**
	 * Retrieve all elements from RestAPI and buil a Json Node in memory to work with
	 * @param id API id
	 * @return API Contet
	 * @throws Exception e
	 */
	public JsonNode getAllObjects(String id) throws Exception {
		RestDummy entity = service.getRestDummyById(id);
		return getJSonObjectsFromString(entity.getContent());
	}

	//TODO Implementar paginado y filtrado

	/**
	 * Simulate el get by ID.
	 * Retrieve the element in API list
	 * @param id API id
	 * @param elementNumber number of element in api
	 * @return API element
	 * @throws Exception e
	 */
	public JsonNode getObjectFromList(String id, int elementNumber) throws Exception {
		JsonNode node = getAllObjects(id);
		if (node.isArray())
			node = (node.size() > elementNumber) ? node.get(elementNumber) : null;
		//TODO Filtar por id?
		return node;
	}

	/**
	 * Add a new element to API list
	 * @param id API id
	 * @param content content of element to add
	 * @return Element added
	 * @throws Exception e
	 */
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

	/**
	 * Revome an element from API
	 * @param id API id
	 * @param elementNumber number of element in api list
	 * @return element removed
	 * @throws Exception e
	 */
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
