package com.gpt.data.enhancer.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gpt.data.enhancer.models.Data;

@Service
public class DataServices {

	@Autowired
	private FlowService flowService;

	@Autowired
	private OpenAIService openAIService;

	public String sendPrompt(Data data) throws Exception {
		String serviceName = data.getAiServiceName().toLowerCase();
		switch (serviceName) {
		case "flow":
			return flowService.sendPrompt(data);
		case "openai":
			return openAIService.sendPrompt(data);
		default:
			throw new IllegalArgumentException("Unknown service name: " + serviceName);
		}
	}
}