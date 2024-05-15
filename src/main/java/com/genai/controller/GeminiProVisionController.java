package com.genai.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;

import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.genai.gemini.GeminiService;
import com.genai.gemini.JsonStructure.Content;
import com.genai.gemini.JsonStructure.GeminiRequest;
import com.genai.gemini.JsonStructure.GeminiResponse;
import com.genai.gemini.JsonStructure.Part;
import com.genai.gemini.JsonStructure.TextPart;
import com.genai.entity.Dataset;
import com.genai.repossitory.DatasetRepository;
import com.google.cloud.vertexai.generativeai.GenerativeModel;

@RestController
@RequestMapping("/gemini-pro")

public class GeminiProVisionController {
	
	@Autowired
	private GenerativeModel generativeModel;
	
	@Autowired
    private GeminiService service;
	
	private final DatasetRepository datasetRepository;
	
	public GeminiProVisionController(DatasetRepository datasetRepository) {
		super();
		this.datasetRepository = datasetRepository;
	}
	
	@PostMapping ("/text-bison")
	public GeminiResponse questionAnswer2(@RequestParam(value = "question") String question) throws IOException {
		List<Part> part = new ArrayList();
		List<Dataset> datasetList = (List<Dataset>) datasetRepository.findAll();
		for(Dataset dataset: datasetList){
			part.add(new TextPart("input: "+dataset.getInputText()));
			part.add(new TextPart("output: "+dataset.getOutputText()));
		}
		part.add(new TextPart("input: "+question));
		GeminiRequest request = new GeminiRequest(
                List.of(new Content(part)));
		GeminiResponse response = service.getCompletion(request);
	      return response;
		
	}

	@GetMapping
	public ResponseEntity<String> appName() {

		return new ResponseEntity<String>("Hello! App working", HttpStatus.OK);

	}
	
	@GetMapping("/datasets")
    public List<Dataset> getUsers() throws Exception{
        return (List<Dataset>) datasetRepository.findAll();
    }

    @PostMapping("/datasets")
    void addUser(@RequestBody Dataset dataset) {
        datasetRepository.save(dataset);
    }

}
