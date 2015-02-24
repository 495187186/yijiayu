package com.test.tougao.entity;

public class AllotFile {
	private String AllotFileId;
	private String FileName;
	private String FileUploader;//上传作者姓名
	private String ExpertName;
	private String UploadTime;
	private String AllotTime;
	private String FileReward;
	private String FileFlag;//是否同意标志
	private String IsReviewed;
	private String IsAgreed;
	public void setAllotFileId(String allotFileId) {
		AllotFileId = allotFileId;
	}
	public String getAllotFileId() {
		return AllotFileId;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileUploader(String fileUploader) {
		FileUploader = fileUploader;
	}
	public String getFileUploader() {
		return FileUploader;
	}
	public void setExpertName(String expertName) {
		ExpertName = expertName;
	}
	public String getExpertName() {
		return ExpertName;
	}
	public void setUploadTime(String uploadTime) {
		UploadTime = uploadTime;
	}
	public String getUploadTime() {
		return UploadTime;
	}
	public void setAllotTime(String allotTime) {
		AllotTime = allotTime;
	}
	public String getAllotTime() {
		return AllotTime;
	}
	public void setFileReward(String fileReward) {
		FileReward = fileReward;
	}
	public String getFileReward() {
		return FileReward;
	}
	public void setFileFlag(String fileFlag) {
		FileFlag = fileFlag;
	}
	public String getFileFlag() {
		return FileFlag;
	}
	public void setIsReviewed(String isReviewed) {
		IsReviewed = isReviewed;
	}
	public String getIsReviewed() {
		return IsReviewed;
	}
	public void setIsAgreed(String isAgreed) {
		IsAgreed = isAgreed;
	}
	public String getIsAgreed() {
		return IsAgreed;
	}
	public AllotFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
