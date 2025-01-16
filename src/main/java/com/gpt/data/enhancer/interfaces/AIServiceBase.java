package com.gpt.data.enhancer.interfaces;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.json.JSONObject;

import com.gpt.data.enhancer.models.Data;

public abstract class AIServiceBase {

    private Logger logger = Logger.getLogger(AIServiceBase.class.getName());

    protected abstract String getApiUrl();
    protected abstract String getApiKey();
    protected abstract JSONObject buildRequestBody(Data data);

    public String sendPrompt(Data data) throws Exception {
        validateApiConfig();

        // Build headers.
        Map<String, String> headers = buildHeaders(getApiKey());

        // Build request body.
        JSONObject requestBody = buildRequestBody(data);

        // Execute POST request.
        String response = executeHttpRequest(getApiUrl(), headers, requestBody);

        // Parse response.
        return parseResponse(response);
    }

    private void validateApiConfig() {
        if (getApiUrl() == null || getApiUrl().isBlank()) {
            throw new IllegalArgumentException("API URL is not set or is empty.");
        }
        if (getApiKey() == null || getApiKey().isBlank()) {
            throw new IllegalArgumentException("API Key is not set or is empty.");
        }
    }

    protected Map<String, String> buildHeaders(String apiKey) {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Accept", "application/json");
        headers.put("Authorization", "Bearer " + apiKey);
        return headers;
    }

    private String executeHttpRequest(String apiUrl, Map<String, String> headers, JSONObject requestBody) throws Exception {
      try (CloseableHttpClient client = HttpClients.createDefault()) {
          HttpPost post = new HttpPost(apiUrl);

          // Set headers
          headers.forEach(post::setHeader);

          // Set request body
          StringEntity entity = new StringEntity(requestBody.toString(), "UTF-8");
          post.setEntity(entity);

          // Execute the request
          try (CloseableHttpResponse response = client.execute(post)) {
              // Read response content
              StringBuilder responseContent = new StringBuilder();
              try (BufferedReader reader = new BufferedReader(
                      new InputStreamReader(response.getEntity().getContent(), "UTF-8"))) {
                  String line;
                  while ((line = reader.readLine()) != null) {
                      responseContent.append(line);
                  }
              }

              // Log and handle non-200 status codes
              int statusCode = response.getStatusLine().getStatusCode();
              if (statusCode != 200) {
                  logger.warning("HTTP Status Code: " + statusCode);
                  logger.warning("Error Response: " + responseContent.toString());
                  return responseContent.toString();
              }

              logger.info("API Response: " + responseContent.toString());
              return responseContent.toString();
          }
      }
  }

  private String parseResponse(String response) {
      try {
          JSONObject jsonResponse = new JSONObject(response);
          if (jsonResponse.has("choices")) {
              return jsonResponse.getJSONArray("choices")
                      .getJSONObject(0)
                      .getJSONObject("message")
                      .getString("content");
          } else if (jsonResponse.has("error")) {
              // Extract and return error message
              return jsonResponse.getJSONObject("error").getString("message");
          }
      } catch (Exception e) {
          logger.warning("Failed to parse response as JSON: " + response);
      }
      return response;
  }
}
