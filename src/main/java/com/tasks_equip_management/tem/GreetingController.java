package com.tasks_equip_management.tem;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class GreetingController {

	@GetMapping
	public String greetingForm(Model model) {
		return "redirect:/events/all";
	}
//
//	@PostMapping("/greeting")
//	public String greetingSubmit(@ModelAttribute Greeting greeting, Model model) {
//		System.out.println(greeting);
//		model.addAttribute("greeting", greeting);
//		return "result";
//	}
//
}
