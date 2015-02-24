package com.test.tougao.entity;

public class StaPaper {
	private String StaPaperId;
	private String PaperName;
	private String PaperType;
	private String PaperUploader;
	private String PaperWriter;
	private String UploadTime;
	private String AllotCounter;
	private String PaperReward;
	private String PaperStatus;
	public void setStaPaperId(String staPaperId) {
		StaPaperId = staPaperId;
	}
	public String getStaPaperId() {
		return StaPaperId;
	}
	public void setPaperName(String paperName) {
		PaperName = paperName;
	}
	public String getPaperName() {
		return PaperName;
	}
	public void setPaperUploader(String paperUploader) {
		PaperUploader = paperUploader;
	}
	public String getPaperUploader() {
		return PaperUploader;
	}
	public void setPaperWriter(String paperWriter) {
		PaperWriter = paperWriter;
	}
	public String getPaperWriter() {
		return PaperWriter;
	}
	public void setUploadTime(String uploadTime) {
		UploadTime = uploadTime;
	}
	public String getUploadTime() {
		return UploadTime;
	}
	public void setAllotCounter(String allotCounter) {
		AllotCounter = allotCounter;
	}
	public String getAllotCounter() {
		return AllotCounter;
	}
	public void setPaperReward(String paperReward) {
		PaperReward = paperReward;
	}
	public String getPaperReward() {
		return PaperReward;
	}
	public StaPaper() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setPaperType(String paperType) {
		PaperType = paperType;
	}
	public String getPaperType() {
		return PaperType;
	}
	public void setPaperStatus(String paperStatus) {
		PaperStatus = paperStatus;
	}
	public String getPaperStatus() {
		return PaperStatus;
	}
	

}
