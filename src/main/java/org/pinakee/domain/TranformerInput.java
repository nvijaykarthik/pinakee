package org.pinakee.domain;

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
	
}
