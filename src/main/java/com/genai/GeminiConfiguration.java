package com.genai;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.support.RestClientAdapter;
import org.springframework.web.service.invoker.HttpServiceProxyFactory;

import com.google.cloud.vertexai.VertexAI;
import com.google.cloud.vertexai.generativeai.GenerativeModel;
import com.genai.gemini.GeminiInterface;

@Configuration (proxyBeanMethods = false)
public class GeminiConfiguration {
	
	@Bean
	public VertexAI vertexAI() {
		return new VertexAI("my-ai-project-414415", "asia-southeast1");
		
	}
	
	@Bean
	public GenerativeModel generativeModel(VertexAI vertexAI) {
		return new GenerativeModel("gemini-1.0-pro-vision", vertexAI);
		
	}
	
	   @Bean
	    public RestClient geminiRestClient(@Value("${gemini.baseurl}") String baseUrl,
	                                       @Value("${googleai.api.key}") String apiKey) {
	        return RestClient.builder()
	                .baseUrl(baseUrl)
	                .defaultHeader("x-goog-api-key", apiKey)
	                .defaultHeader("Content-Type", "application/json")
	                .defaultHeader("Accept", "application/json")
	                .build();
	    }

	    @Bean
	    public GeminiInterface geminiInterface(@Qualifier("geminiRestClient") RestClient client) {
	        RestClientAdapter adapter = RestClientAdapter.create(client);
	        HttpServiceProxyFactory factory = HttpServiceProxyFactory.builderFor(adapter).build();
	        return factory.createClient(GeminiInterface.class);
	    }
	

}
