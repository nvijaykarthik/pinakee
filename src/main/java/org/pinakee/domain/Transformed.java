package org.pinakee.domain;

public class Transformed {
	@Override
	public String toString() {
		return "Transformed [status=" + status + ", content=**large Size to print**]";
	}
	private Integer status;

	private String content;
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
}
