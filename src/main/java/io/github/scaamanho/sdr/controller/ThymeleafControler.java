package io.github.scaamanho.sdr.controller;

import io.github.scaamanho.sdr.domain.RestDummy;
import io.github.scaamanho.sdr.service.RdrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class ThymeleafControler {

	@Autowired
	RdrService service;

	@GetMapping("/")
	public String main(Model model) {
		return getMainPage(model);
	}

	@GetMapping("/hmi/edit")
	public String edit(@RequestParam(name = "id", required = true, defaultValue = "-1") String id, Model model) throws Exception {
		RestDummy api = service.getRestDummyById(id);
		model.addAttribute("mode", "update");
		model.addAttribute("api", api);
		return "edit";
	}

	@GetMapping("/hmi/delete")
	public String delete(@RequestParam(name = "id", required = true, defaultValue = "-1") String id, Model model) throws Exception {
		service.deleteRestDummyById(id);
		return getMainPage(model);
	}

	@PostMapping("/hmi/search")
	public String search(@RequestParam(name = "search", required = false, defaultValue = "") String search, Model model) {
		List<RestDummy> apis = service.searchRestDummy(search);
		model.addAttribute("apis", apis);
		return "main";
	}

	@PostMapping("/hmi/save")
	public String save(Model model) {
		//TODO implementar busqueda
		return getMainPage(model);
	}

	@PostMapping("/hmi/update")
	public String update(RestDummy restDummy, Model model) throws Exception {
		service.createOrUpdateRestDummy(restDummy);
		List<RestDummy> apis = service.getAllRestDummy();
		model.addAttribute("apis", apis);
		return "main"; //view
	}

	private String getMainPage(Model model) {
		List<RestDummy> apis = service.getAllRestDummy();
		model.addAttribute("apis", apis);
		return "main"; //view
	}
}
