package com.cheng.test.dto;

import java.util.ArrayList;

public class RequestData {

	public ArrayList<String> environmentList;
	public ArrayList<String> circleList;
	public ArrayList<String> modelList;
	public ArrayList<String> interfaceList;

	public ArrayList<String> getEnvironmentList() {
		return environmentList;
	}

	public void setEnvironmentList(ArrayList<String> environmentList) {
		this.environmentList = environmentList;
	}

	public ArrayList<String> getCircleList() {
		return circleList;
	}

	public void setCircleList(ArrayList<String> circleList) {
		this.circleList = circleList;
	}

	public ArrayList<String> getModelList() {
		return modelList;
	}

	public void setModelList(ArrayList<String> modelList) {
		this.modelList = modelList;
	}

	public ArrayList<String> getInterfaceList() {
		return interfaceList;
	}

	public void setInterfaceList(ArrayList<String> interfaceList) {
		this.interfaceList = interfaceList;
	}

	@Override
	public String toString() {
		return "RequestData [environmentList=" + environmentList + ", circleList=" + circleList + ", modelList="
				+ modelList + ", interfaceList=" + interfaceList + "]";
	}

}
