package com.hbwh.xj.myblog.controller;

import java.util.List;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbwh.xj.myblog.bean.Person;
import com.hbwh.xj.myblog.service.PersonService;

@Controller
//@RestController
@RequestMapping("/persons")
@Api("swaggerDemoController相关的api")
public class PersonController {

	private PersonService personService;
	
	public PersonService getPersonService() {
		return personService;
	}

	@Autowired
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	@GetMapping
	@ApiOperation(value = "显示用户列表", notes = "翻页显示")
	public String personList(ModelMap map){
		PageHelper.startPage(8, 1);
		List<Person> persons = personService.getAll();
		map.put("list", persons);
		PageInfo<Person> pi = new PageInfo<>(persons);
		map.put("pageInfo", pi);
		return "person/list";
	}
	
	
//	public List<Person> personList(ModelMap map){
//		List<Person> results = personService.getAll();
//		return results;
//	}
	
}
