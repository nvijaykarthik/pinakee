package org.pinakee.domain;

import java.util.HashMap;
import java.util.Map;

public class TranformerInput {

	@Override
	public String toString() {
		return "TranformerInput [xqueryName=" + xqueryName + ", xml=**large Size to print**]";
	}
	public String getXqueryName() {
		return xqueryName;
	}
	public void setXqueryName(String xqueryName) {
		this.xqueryName = xqueryName;
	}
	public String getXml() {
		return xml;
	}
	public void setXml(String xml) {
		this.xml = xml;
	}
	
	private String xqueryName;
	private String xml;
	private Map<String,String> parameters=new HashMap<>();
	
	public Map<String, String> getParameters() {
		return parameters;
	}
	public void setParameters(Map<String, String> parameters) {
		this.parameters = parameters;
	}
	
}
