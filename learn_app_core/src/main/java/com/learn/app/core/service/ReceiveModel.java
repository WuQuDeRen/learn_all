package com.learn.app.core.service;


import lombok.Data;


@Data
public class ReceiveModel {

	private String name;
	private String propertyId;

	public ReceiveModel() {
	}

	public ReceiveModel(String name, String propertyId) {
		this.name = name;
		this.propertyId = propertyId;
	}
}
