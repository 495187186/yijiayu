package com.test.tougao.entity;

public class ReviewedFile {
	private String ReviewedFileId;
	private String ExpertNo;
	private String DocName;
	private String DocType;
	private String AllotTime;
	private String ReviewTime;
	private String DocReward;
	private String IsAgreed;
	public void setReviewedFileId(String reviewedFileId) {
		ReviewedFileId = reviewedFileId;
	}
	public String getReviewedFileId() {
		return ReviewedFileId;
	}
	public void setDocName(String docName) {
		DocName = docName;
	}
	public String getDocName() {
		return DocName;
	}
	public void setExpertNo(String expertNo) {
		ExpertNo = expertNo;
	}
	public String getExpertNo() {
		return ExpertNo;
	}
	public void setDocType(String docType) {
		DocType = docType;
	}
	public String getDocType() {
		return DocType;
	}
	public void setAllotTime(String allotTime) {
		AllotTime = allotTime;
	}
	public String getAllotTime() {
		return AllotTime;
	}
	public void setReviewTime(String reviewTime) {
		ReviewTime = reviewTime;
	}
	public String getReviewTime() {
		return ReviewTime;
	}
	public void setDocReward(String docReward) {
		DocReward = docReward;
	}
	public String getDocReward() {
		return DocReward;
	}
	public void setIsAgreed(String isAgreed) {
		IsAgreed = isAgreed;
	}
	public String getIsAgreed() {
		return IsAgreed;
	}
	public ReviewedFile() {
		super();
		// TODO Auto-generated constructor stub
	}

}
