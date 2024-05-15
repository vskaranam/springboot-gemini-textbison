package com.genai.gemini;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.service.annotation.GetExchange;
import org.springframework.web.service.annotation.HttpExchange;
import org.springframework.web.service.annotation.PostExchange;

import static com.genai.gemini.JsonStructure.GeminiRequest;
import static com.genai.gemini.JsonStructure.GeminiResponse;

@HttpExchange("/v1beta/models/")
public interface GeminiInterface {
    @GetExchange
    JsonStructure.ModelList getModels();

    @PostExchange("{model}:generateContent")
    GeminiResponse getCompletion(
            @PathVariable(value = "model") String model,
            @RequestBody  GeminiRequest request);
}
