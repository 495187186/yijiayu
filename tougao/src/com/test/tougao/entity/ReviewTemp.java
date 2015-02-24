package com.test.tougao.entity;

public class ReviewTemp {
	private String ReviewTempId;
	private String ExpertNo;
	private String PaperName;
	private String PaperType;
	private String IsAgreed;
	private String PaperLevel;
	private String AllotTime;
	private String SaveTime;
	private String MessageName;
	private String PaperMessage;
	
	public void setExpertNo(String expertNo) {
		ExpertNo = expertNo;
	}
	public String getExpertNo() {
		return ExpertNo;
	}
	public void setPaperName(String paperName) {
		PaperName = paperName;
	}
	public String getPaperName() {
		return PaperName;
	}
	public void setPaperType(String paperType) {
		PaperType = paperType;
	}
	public String getPaperType() {
		return PaperType;
	}
	public void setIsAgreed(String isAgreed) {
		IsAgreed = isAgreed;
	}
	public String getIsAgreed() {
		return IsAgreed;
	}
	public void setPaperLevel(String paperLevel) {
		PaperLevel = paperLevel;
	}
	public String getPaperLevel() {
		return PaperLevel;
	}
	public void setSaveTime(String saveTime) {
		SaveTime = saveTime;
	}
	public String getSaveTime() {
		return SaveTime;
	}
	public void setPaperMessage(String paperMessage) {
		PaperMessage = paperMessage;
	}
	public String getPaperMessage() {
		return PaperMessage;
	}
	public ReviewTemp() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setReviewTempId(String reviewTempId) {
		ReviewTempId = reviewTempId;
	}
	public String getReviewTempId() {
		return ReviewTempId;
	}
	public void setAllotTime(String allotTime) {
		AllotTime = allotTime;
	}
	public String getAllotTime() {
		return AllotTime;
	}
	public void setMessageName(String messageName) {
		MessageName = messageName;
	}
	public String getMessageName() {
		return MessageName;
	}
	

}
