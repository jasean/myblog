package com.hbwh.xj.myblog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hbwh.xj.myblog.po.Person;
import com.hbwh.xj.myblog.dao.PersonMapper;
import com.hbwh.xj.myblog.service.PersonService;

@Service("personService")
public class PersonServiceImpl implements PersonService {

	private PersonMapper personMapper;
	
	
	public PersonMapper getPersonMapper() {
		return personMapper;
	}

	@Autowired
	public void setPersonMapper(PersonMapper personMapper) {
		this.personMapper = personMapper;
	}


	@Override
	public List<Person> getAll() {
		List<Person> persons = personMapper.selectAll();
		return persons;
	}

}
