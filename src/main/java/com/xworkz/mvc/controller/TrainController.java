package com.xworkz.mvc.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Component
@RequestMapping("/")
public class TrainController {
	private Map<String, String> map;

	public TrainController() {
		System.out.println("created\t" + this.getClass().getSimpleName());
	}

	@PostConstruct
	public void mapInit() {

		System.out.println("invoked mapInit method");
		map = new HashMap<String, String>();
		map.put("MYS", "SHEXP");
		map.put("GOA", "GOAN");
		map.put("DEL", "EXP");
	}

	@RequestMapping(value = "/search.do", method = RequestMethod.POST)
	public String doSend(@RequestParam String name, @RequestParam String address, @RequestParam String date,
			@RequestParam String from, @RequestParam String to, Model model) {
		System.out.println("invoked doSend method");
		System.out.println("Name: " + name);
		model.addAttribute("message", "Regiatration successfull " + name);

		return "index";

	}

	@RequestMapping(value = "/submit.do")
	public String doFetch(@RequestParam String location, Model model) {
		System.out.println("invoked doFetch method");

		if (map.containsKey(location)) {
			System.out.println(location + ":" + map.get(location));
			model.addAttribute("message", "Result Found: " + map.get(location));
			return "Search";
		} else {
			model.addAttribute("message", "Result not found");
			return "Search";

		}

	}

}
