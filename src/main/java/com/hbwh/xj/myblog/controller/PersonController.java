package com.hbwh.xj.myblog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.hbwh.xj.myblog.bean.Person;
import com.hbwh.xj.myblog.service.PersonService;

@Controller
//@RestController
@RequestMapping("/person")
public class PersonController {

	private PersonService personService;
	
	public PersonService getPersonService() {
		return personService;
	}

	@Autowired
	public void setPersonService(PersonService personService) {
		this.personService = personService;
	}

	@RequestMapping("/list")
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
