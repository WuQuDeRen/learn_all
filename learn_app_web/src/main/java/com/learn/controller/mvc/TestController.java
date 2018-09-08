package com.learn.controller.mvc;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

	@RequestMapping(value = "/b")
	public String getJsonB() {
		return "{name: b}";
	}
	
	@RequestMapping(value = "/a")
	public String getJsonA() {
		return "{name: a}";
	}
}
