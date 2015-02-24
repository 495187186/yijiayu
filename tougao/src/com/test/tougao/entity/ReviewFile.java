package com.test.tougao.entity;

public class ReviewFile {
	private String ReviewFileId;
	private String ExpertNo;
	private String DocName;
	private String DocType;
	private String UploadDate;
	private String AllotDate;
	private String DocDeadline;
	private String DocReward;
	private String IsReviewed;
	private String DocFlag;
	private String IsAgreed;
	public void setReviewFileId(String reviewFileId) {
		ReviewFileId = reviewFileId;
	}
	public String getReviewFileId() {
		return ReviewFileId;
	}
	public void setExpertNo(String expertNo) {
		ExpertNo = expertNo;
	}
	public String getExpertNo() {
		return ExpertNo;
	}
	public void setDocName(String docName) {
		DocName = docName;
	}
	public String getDocName() {
		return DocName;
	}
	public void setDocType(String docType) {
		DocType = docType;
	}
	public String getDocType() {
		return DocType;
	}
	public void setUploadDate(String uploadDate) {
		UploadDate = uploadDate;
	}
	public String getUploadDate() {
		return UploadDate;
	}
	public void setAllotDate(String allotDate) {
		AllotDate = allotDate;
	}
	public String getAllotDate() {
		return AllotDate;
	}
	public void setDocDeadline(String docDeadline) {
		DocDeadline = docDeadline;
	}
	public String getDocDeadline() {
		return DocDeadline;
	}
	public void setDocReward(String docReward) {
		DocReward = docReward;
	}
	public String getDocReward() {
		return DocReward;
	}
	public void setIsReviewed(String isReviewed) {
		IsReviewed = isReviewed;
	}
	public String getIsReviewed() {
		return IsReviewed;
	}
	public ReviewFile() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setDocFlag(String docFlag) {
		DocFlag = docFlag;
	}
	public String getDocFlag() {
		return DocFlag;
	}
	public void setIsAgreed(String isAgreed) {
		IsAgreed = isAgreed;
	}
	public String getIsAgreed() {
		return IsAgreed;
	}
	

}
