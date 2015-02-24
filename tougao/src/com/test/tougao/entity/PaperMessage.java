package com.test.tougao.entity;

public class PaperMessage {
	private String PaperMessageId;
	private String ExpertNo;
	private String ExpertName;
	private String StudentNo;
	private String PaperName;
	private String PaperWriter;
	private String MessageName;
	private String UploadTime;
	private String MessageDate;
	private String IsRead;
	public void setPaperMessageId(String paperMessageId) {
		PaperMessageId = paperMessageId;
	}
	public String getPaperMessageId() {
		return PaperMessageId;
	}
	public void setExpertNo(String expertNo) {
		ExpertNo = expertNo;
	}
	public String getExpertNo() {
		return ExpertNo;
	}
	public void setExpertName(String expertName) {
		ExpertName = expertName;
	}
	public String getExpertName() {
		return ExpertName;
	}
	public void setPaperName(String paperName) {
		PaperName = paperName;
	}
	public String getPaperName() {
		return PaperName;
	}
	public void setMessageName(String messageName) {
		MessageName = messageName;
	}
	public String getMessageName() {
		return MessageName;
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
	public PaperMessage() {
		super();
		// TODO Auto-generated constructor stub
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
	public void setStudentNo(String studentNo) {
		StudentNo = studentNo;
	}
	public String getStudentNo() {
		return StudentNo;
	}
	

}
