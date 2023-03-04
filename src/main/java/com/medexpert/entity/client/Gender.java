package com.medexpert.entity.client;

import lombok.Getter;

@Getter
public enum Gender {
	
	MALE("Male"),
	FEMALE("Female");
	
	private String type;
	
	private Gender(String type) {
		this.type = type;
	}

}
