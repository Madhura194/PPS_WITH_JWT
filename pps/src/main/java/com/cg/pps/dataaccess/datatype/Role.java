package com.cg.pps.dataaccess.datatype;

import com.fasterxml.jackson.annotation.JsonFormat;

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Role {
	
    CANDIDATE("CANDIDATE","candidate"),
    ADMIN("ADMIN","admin");
    
    String key;
	String value;
	
	private Role(String key, String value) {
		
		this.key = key;
		this.value = value;
	}
	
	
	
	
	
    
	
	
}
