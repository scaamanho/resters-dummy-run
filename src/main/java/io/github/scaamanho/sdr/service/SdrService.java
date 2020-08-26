package io.github.scaamanho.sdr.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
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

	public Object addElementToList(String content, String element) throws Exception
	{
		//TODO
		Object listElements= getJSonObjectsFromString(content);
		return listElements;
	}
	public Object removeElementFromList(String content,int elementNumber) throws Exception
	{
		//TODO
		Object listElements= getJSonObjectsFromString(content);
		return listElements;
	}
}
