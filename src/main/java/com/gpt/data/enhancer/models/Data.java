package com.gpt.data.enhancer.models;

import com.fasterxml.jackson.annotation.JsonProperty;

public class Data {

	private String prompt;

	@JsonProperty("ai_service_name")
	private String aiServiceName;

	@JsonProperty("data_structure")
	private String dataStructure;

	public String getPrompt() {
		return prompt;
	}
	public void setPrompt(String prompt) {
		this.prompt = prompt;
	}
	public String getAiServiceName() {
		return aiServiceName;
	}
	public void setAiServiceName(String aiServiceName) {
		this.aiServiceName = aiServiceName;
	}
	public String getDataStructure() {
		return dataStructure;
	}
	public void setDataStructure(String dataStructure) {
		this.dataStructure = dataStructure;
	}

}
