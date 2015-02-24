package com.test.tougao.entity;

public class FileMessage {
	private String FileMessageId;
	private String ExpertNo;
	private String ExpertName;
	private String AdminNo;
	private String FileName;
	private String MessageName;
	private String UploadTime;
	private String MessageDate;
	private String IsRead;
	public void setFileMessageId(String fileMessageId) {
		FileMessageId = fileMessageId;
	}
	public String getFileMessageId() {
		return FileMessageId;
	}
	public void setExpertName(String expertName) {
		ExpertName = expertName;
	}
	public String getExpertName() {
		return ExpertName;
	}
	public void setExpertNo(String expertNo) {
		ExpertNo = expertNo;
	}
	public String getExpertNo() {
		return ExpertNo;
	}
	public void setAdminNo(String adminNo) {
		AdminNo = adminNo;
	}
	public String getAdminNo() {
		return AdminNo;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public String getFileName() {
		return FileName;
	}
	public void setMessageName(String messageName) {
		MessageName = messageName;
	}
	public String getMessageName() {
		return MessageName;
	}
	public void setUploadTime(String uploadTime) {
		UploadTime = uploadTime;
	}
	public String getUploadTime() {
		return UploadTime;
	}
	public void setMessageDate(String messageDate) {
		MessageDate = messageDate;
	}
	public String getMessageDate() {
		return MessageDate;
	}
	public void setIsRead(String isRead) {
		IsRead = isRead;
	}
	public String getIsRead() {
		return IsRead;
	}
	public FileMessage() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
