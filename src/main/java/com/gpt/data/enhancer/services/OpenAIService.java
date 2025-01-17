package com.gpt.data.enhancer.services;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import com.gpt.data.enhancer.helpers.PromptBuilder;
import com.gpt.data.enhancer.interfaces.AIServiceBase;
import com.gpt.data.enhancer.models.Data;

@Service
public class OpenAIService extends AIServiceBase{

    @Override
    protected String getApiUrl() {
        return System.getenv("OPENAI_API_URL");
	}

    @Override
    protected String getApiKey() {
        return System.getenv("OPENAI_API_KEY");
    }

    @Override
    protected JSONObject buildRequestBody(Data data) {
        String prompt = data.getPrompt();
        String structure = data.getDataStructure();

        // Build the request body (JSON)
        JSONObject json = new JSONObject();
        json.put("stream", false);
        json.put("max_tokens", 4096);
        json.put("temperature", 0.7);
        json.put("model", "gpt-4o-mini");

        JSONObject userMessage = new JSONObject();
        userMessage.put("role", "user");

        String messagePrompt = PromptBuilder.buildPrompt(prompt, structure);
        userMessage.put("content", messagePrompt);

        JSONArray messages = new JSONArray();
        messages.put(userMessage);

        json.put("messages", messages);

        return json;
    }
}
