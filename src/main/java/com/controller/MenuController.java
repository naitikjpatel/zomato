package com.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.entity.MenuEntity;
import com.repository.MenuRepository;



@RestController
public class MenuController {

	@Autowired
	MenuRepository menuRepository;
	
	@PostMapping("/addmenu")
	public MenuEntity addmenu(@RequestBody MenuEntity menuEntity) {
		menuRepository.save(menuEntity);
		return menuEntity;
	}
	
	@GetMapping("/getmenubyid/{id}")
	public MenuEntity getMenuById(@PathVariable("id") Integer id) {
	Optional<MenuEntity>op=	menuRepository.findById(id);
	if(op.isEmpty()) {
		return null;
	}
	else
	{
		return op.get();
	}
	}
	
	@DeleteMapping("/deletebyid/{id}")
	public String deleteById(@PathVariable("id") Integer id) {
		menuRepository.deleteById(id);
		return "SuccessFully Deleted Record...";
	}
	
	
	//update the menu
	@PutMapping("/updatemenu")
	public MenuEntity updateMenu(@RequestBody MenuEntity menuEntity) {
		menuRepository.save(menuEntity);
		return menuEntity;
	}
}
