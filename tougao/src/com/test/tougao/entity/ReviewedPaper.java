package com.test.tougao.entity;

public class ReviewedPaper {
	private String ReviewedId;
	private String ExpertNo;
	private String PaperName;
	private String PaperType;
	private String AllotTime;
	private String ReviewTime;
	private String PaperReward;
	private String IsAgreed;
	public void setReviewedId(String reviewedId) {
		ReviewedId = reviewedId;
	}
	public String getReviewedId() {
		return ReviewedId;
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
	public void setPaperReward(String paperReward) {
		PaperReward = paperReward;
	}
	public String getPaperReward() {
		return PaperReward;
	}
	public void setIsAgreed(String isAgreed) {
		IsAgreed = isAgreed;
	}
	public String getIsAgreed() {
		return IsAgreed;
	}
	public ReviewedPaper() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
