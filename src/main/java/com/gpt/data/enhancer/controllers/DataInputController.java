package com.gpt.data.enhancer.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gpt.data.enhancer.models.Data;
import com.gpt.data.enhancer.services.DataServices;

@RestController
@RequestMapping("/api/data/v1")
public class DataInputController {

	@Autowired
	private DataServices service;

	@PostMapping
	public String inpuData(
	    @RequestBody Data data
	) throws Exception {
	    return service.sendPrompt(data);
	}
}
