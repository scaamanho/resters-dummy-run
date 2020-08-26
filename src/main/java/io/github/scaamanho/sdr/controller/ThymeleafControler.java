package io.github.scaamanho.sdr.controller;

import io.github.scaamanho.sdr.domain.RestDummy;
import io.github.scaamanho.sdr.service.SdrApiService;
import io.github.scaamanho.sdr.service.SdrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ThymeleafControler {

	@Autowired
	SdrApiService service;

	@GetMapping("/")
	public String main(Model model) {
		List<String> tasks = new ArrayList<>();
		tasks.add("tasks1");
		tasks.add("tasks2");
		List<RestDummy> apis= service.getAllRestDummy();
		model.addAttribute("message", "Hola mundo");
		model.addAttribute("tasks", tasks);
		model.addAttribute("apis", apis);
		return "main"; //view
	}

	@GetMapping("/hmi/edit")
	public String edit(@RequestParam(name = "id", required = true, defaultValue = "-1")	Long id, Model model) throws Exception{
		RestDummy api = service.getRestDummyById(id);
		model.addAttribute("mode","update");
		model.addAttribute("api", api);
		return "edit"; //view
	}

	@GetMapping("/hmi/delete")
	public String delete (@RequestParam(name = "id", required = true, defaultValue = "-1")	Long id, Model model) throws Exception
	{
		service.deleteRestDummyById(id);
		List<RestDummy> apis= service.getAllRestDummy();
		model.addAttribute("apis", apis);
		return "main"; //view
	}

	@PostMapping("/hmi/save")
	public String save(Model model) {
		List<RestDummy> apis= service.getAllRestDummy();
		model.addAttribute("apis", apis);
		return "main"; //view
	}

	@PostMapping("/hmi/update")
	public String update(RestDummy restDummy, Model model) throws Exception{
		service.createOrUpdateRestDummy(restDummy);
		List<RestDummy> apis= service.getAllRestDummy();
		model.addAttribute("apis", apis);
		return "main"; //view
	}
}
