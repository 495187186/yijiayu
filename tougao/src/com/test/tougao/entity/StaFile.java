package com.test.tougao.entity;

public class StaFile {
	private String StaFileId;
	private String FileName;
	private String FileType;
	private String FileUploader;
	private String UploadTime;
	private String AllotCounter;
	private String FileReward;
	private String FileStatus;
	
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileType(String fileType) {
		FileType = fileType;
	}
	public String getFileType() {
		return FileType;
	}
	public void setFileUploader(String fileUploader) {
		FileUploader = fileUploader;
	}
	public String getFileUploader() {
		return FileUploader;
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
	public void setFileStatus(String fileStatus) {
		FileStatus = fileStatus;
	}
	public String getFileStatus() {
		return FileStatus;
	}
	public void setFileReward(String fileReward) {
		FileReward = fileReward;
	}
	public String getFileReward() {
		return FileReward;
	}
	public StaFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setStaFileId(String staFileId) {
		StaFileId = staFileId;
	}
	public String getStaFileId() {
		return StaFileId;
	}
	

}
