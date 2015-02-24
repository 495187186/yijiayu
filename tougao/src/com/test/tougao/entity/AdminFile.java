package com.test.tougao.entity;

public class AdminFile {
	private String FileId;
	private String FileName;
	private String FileType;
	private String FileUploader;
	private String Uploader;//上传作者姓名
	private String FileTime;
	private String FileStatus;
	private String FileCounter;
	private String FileReward;//审稿费
	private String PersonNum;//指派人数
	private String IsReward;//是否确定稿费
	private String IsAllot;//是否被指派
	private String IsChoose;//是否开放
	public void setFileId(String fileId) {
		FileId = fileId;
	}
	public String getFileId() {
		return FileId;
	}
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
	public void setFileTime(String fileTime) {
		FileTime = fileTime;
	}
	public String getFileTime() {
		return FileTime;
	}
	public void setFileStatus(String fileStatus) {
		FileStatus = fileStatus;
	}
	public String getFileStatus() {
		return FileStatus;
	}
	public void setFileCounter(String fileCounter) {
		FileCounter = fileCounter;
	}
	public String getFileCounter() {
		return FileCounter;
	}
	public AdminFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setUploader(String uploader) {
		Uploader = uploader;
	}
	public String getUploader() {
		return Uploader;
	}
	public void setFileReward(String fileReward) {
		FileReward = fileReward;
	}
	public String getFileReward() {
		return FileReward;
	}
	public void setPersonNum(String personNum) {
		PersonNum = personNum;
	}
	public String getPersonNum() {
		return PersonNum;
	}
	public void setIsReward(String isReward) {
		IsReward = isReward;
	}
	public String getIsReward() {
		return IsReward;
	}
	public void setIsAllot(String isAllot) {
		IsAllot = isAllot;
	}
	public String getIsAllot() {
		return IsAllot;
	}
	public void setIsChoose(String isChoose) {
		IsChoose = isChoose;
	}
	public String getIsChoose() {
		return IsChoose;
	}
	

}
