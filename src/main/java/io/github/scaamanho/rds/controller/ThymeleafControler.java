package io.github.scaamanho.rds.controller;

import io.github.scaamanho.rds.command.SearchCmd;
import io.github.scaamanho.rds.domain.RestDummy;
import io.github.scaamanho.rds.service.RdrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Objects;

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
		model.addAttribute("searchForm", new SearchCmd());
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
	public String search(SearchCmd search, Model model) {
		List<RestDummy> apis = service.searchRestDummy(search.getSearch());
		return getMainPage(apis, search, model);
	}

	@PostMapping("/hmi/save")
	public String save(Model model) {
		return getMainPage(model);
	}

	@PostMapping("/hmi/update")
	public String update(RestDummy restDummy, Model model) throws Exception {
		service.createOrUpdateRestDummy(restDummy);
		return getMainPage(model);
	}

	private String getMainPage(Model model) {
		return getMainPage(null, null, model);
	}

	private String getMainPage(List<RestDummy> apis, SearchCmd search, Model model) {
		if (Objects.isNull(apis))
			apis = service.getAllRestDummy();
		if (Objects.isNull(search))
			search = new SearchCmd();
		model.addAttribute("searchForm", search);
		model.addAttribute("apis", apis);
		return "main"; //view
	}
}
