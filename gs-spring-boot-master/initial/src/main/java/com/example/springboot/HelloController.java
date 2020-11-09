package com.example.springboot;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
public class HelloController {

	@RequestMapping("/")
	public String ind() {
		return "Greetings from Spring Boot!";
	}

	@RequestMapping("/test")
	public String test(@RequestParam(name="name",required = false,defaultValue = "Stacc") String name, Model model) {
		if (name==null)
			name = "Not null";
		model.addAttribute("name",name);
		return "test";
	}
}
