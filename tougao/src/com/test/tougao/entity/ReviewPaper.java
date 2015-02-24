package com.test.tougao.entity;

public class ReviewPaper {
	private String ReviewPaperId;
	private String ExpertNo;
	private String PaperName;
	private String PaperType;
	private String UploadDate;
	private String AllotDate;
	private String PaperDeadline;
	private String PaperReward;
	private String IsReviewed;
	private String PaperFlag;
	private String IsAgreed;
	public void setReviewPaperId(String reviewPaperId) {
		ReviewPaperId = reviewPaperId;
	}
	public String getReviewPaperId() {
		return ReviewPaperId;
	}
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
	public void setAllotDate(String allotDate) {
		AllotDate = allotDate;
	}
	public String getAllotDate() {
		return AllotDate;
	}
	public void setPaperDeadline(String paperDeadline) {
		PaperDeadline = paperDeadline;
	}
	public String getPaperDeadline() {
		return PaperDeadline;
	}
	public void setPaperReward(String paperReward) {
		PaperReward = paperReward;
	}
	public String getPaperReward() {
		return PaperReward;
	}
	public ReviewPaper() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setUploadDate(String uploadDate) {
		UploadDate = uploadDate;
	}
	public String getUploadDate() {
		return UploadDate;
	}
	public void setIsReviewed(String isReviewed) {
		IsReviewed = isReviewed;
	}
	public String getIsReviewed() {
		return IsReviewed;
	}
	public void setPaperFlag(String paperFlag) {
		PaperFlag = paperFlag;
	}
	public String getPaperFlag() {
		return PaperFlag;
	}
	public void setIsAgreed(String isAgreed) {
		IsAgreed = isAgreed;
	}
	public String getIsAgreed() {
		return IsAgreed;
	}
    
}
