package io.github.scaamanho.rds.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ArrayNode;
import io.github.scaamanho.rds.domain.RestDummy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Objects;

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
	 * @param elementId number of element in api
	 * @return API element
	 * @throws Exception e
	 */
	public JsonNode getObjectFromList(String id, String elementId) throws Exception {
		JsonNode node = getAllObjects(id);
		if (node.isArray())
			node = node.get(getElementIndex(node, elementId));
		return node;
	}

	/**
	 * Add a new element to API list
	 * @param id API id
	 * @param content content of element to add
	 * @return Element added
	 * @throws Exception e
	 */
	public JsonNode addElementToList(String id, JsonNode content) throws Exception {
		RestDummy entity = service.getRestDummyById(id);
		JsonNode node = getJSonObjectsFromString(entity.getContent());
		if (node.isArray())
			((ArrayNode) node).add(content);
		else
			node = content;
		entity.setContent(node.toString());
		service.createOrUpdateRestDummy(entity);
		return content;
	}


	/**
	 * Replace an element in API List
	 * @param id api name
	 * @param content content to replace
	 * @return content replaced
	 * @throws Exception e
	 */
	public JsonNode replaceElementInList(String id, JsonNode content) throws Exception {
		RestDummy entity = service.getRestDummyById(id);
		JsonNode node = getJSonObjectsFromString(entity.getContent());
		if (node.isArray())
		{
			int index = getElementIndex(node, content.get("id").toString());
			ArrayNode nodes = (ArrayNode)node;
			if(index != -1)
				nodes.remove(index);
			nodes.add(content);
		}
		else
			node = content;
		entity.setContent(node.toString());
		service.createOrUpdateRestDummy(entity);
		return content;
	}

	/**
	 * Revome an element from API
	 * @param id API id
	 * @param elementId number of element in api list
	 * @return element removed
	 * @throws Exception e
	 */
	public JsonNode removeElementFromList(String id, String elementId) throws Exception {
		RestDummy entity = service.getRestDummyById(id);
		JsonNode node = getJSonObjectsFromString(entity.getContent());

		if (node.isArray())
		{
			((ArrayNode) node).remove(getElementIndex(node,elementId));
		}
		else
			node = getJSonObjectsFromString("{}");

		entity.setContent(node.toString());
		service.createOrUpdateRestDummy(entity);
		return node;
	}

	public JsonNode getElementById(JsonNode nodeParent, String id){
		JsonNode node=null;
		if(!nodeParent.isArray())
			return nodeParent;

		Iterator<JsonNode> iterator = nodeParent.elements();
		JsonNode nodeTemp;
		while (iterator.hasNext())
		{
			nodeTemp = iterator.next();
			//Recuperamos objeto por su id
			if(Objects.nonNull(nodeTemp.get("id")) &&
					(nodeTemp.get("id").toString().equals(id) ||
					nodeTemp.get("id").asLong() == Long.parseLong(id)))
			{
				node = nodeTemp;
				break;
			}
		}
		return node;
	}

	public int getElementIndex(JsonNode nodeParent, String id)
	{
		int index = 0;
		if(!nodeParent.isArray())
			return index;
		Iterator<JsonNode> iterator = nodeParent.elements();
		JsonNode nodeTemp;
		boolean found =false;
		while (iterator.hasNext())
		{
			nodeTemp = iterator.next();
			//Recuperamos objeto por su id
			if( Objects.nonNull(nodeTemp.get("id")) &&
					(nodeTemp.get("id").toString().equals(id) ||
							nodeTemp.get("id").asLong() == Long.parseLong(id))) {
				found =true;
				break;
			}
			index ++;
		}
		return found ? index : -1;
	}
}
