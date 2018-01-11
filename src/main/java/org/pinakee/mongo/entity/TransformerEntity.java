package org.pinakee.mongo.entity;

import java.util.Date;

import org.springframework.data.annotation.Id;

import com.fasterxml.jackson.annotation.JsonFormat;

public class TransformerEntity {

	@Override
	public String toString() {
		return "TransformerEntity [id=" + id + ", xqueryName=" + xqueryName + ", xqueryContent=**large Size to print**"
				+ ", active=" + active + ", createdDate=" + createdDate + ", modifiedDate=" + modifiedDate
				+ ", createdBy=" + createdBy + ", modifiedBy=" + modifiedBy + "]";
	}
	@Id
	private String id;
	
	private String xqueryName;
	private String xqueryContent;
	private boolean active;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private Date createdDate;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss") 
	private Date modifiedDate;
	private String createdBy;
	private String modifiedBy;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getXqueryName() {
		return xqueryName;
	}
	public void setXqueryName(String xqueryName) {
		this.xqueryName = xqueryName;
	}
	public String getXqueryContent() {
		return xqueryContent;
	}
	public void setXqueryContent(String xqueryContent) {
		this.xqueryContent = xqueryContent;
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getModifiedDate() {
		return modifiedDate;
	}
	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
}
