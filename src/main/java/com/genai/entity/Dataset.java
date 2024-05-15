package com.genai.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Dataset {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private  String inputText;
    private  String outputText;
	
    
    public Dataset(long id, String inputText, String outputText) {
		super();
		this.id = id;
		this.inputText = inputText;
		this.outputText = outputText;
	}
    
    public Dataset() {
    	super();
    }
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getInputText() {
		return inputText;
	}
	public void setInputText(String inputText) {
		this.inputText = inputText;
	}
	public String getOutputText() {
		return outputText;
	}
	public void setOutputText(String outputText) {
		this.outputText = outputText;
	}
	
    
    
	
    
    
    
    // standard constructors / setters / getters / toString
    
    
}
