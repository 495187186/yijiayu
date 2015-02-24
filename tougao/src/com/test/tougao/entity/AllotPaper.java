package com.test.tougao.entity;

public class AllotPaper {
	private String AllotPaperId;
	private String PaperName;
	private String PaperUploader;//上传作者姓名
	private String PaperWriter;
	private String ExpertName;
	private String UploadTime;
	private String AllotTime;
	private String PaperReward;
	private String PaperFlag;//是否同意标志
	private String IsReviewed;
	private String IsAgreed;
	public void setAllotPaperId(String allotPaperId) {
		AllotPaperId = allotPaperId;
	}
	public String getAllotPaperId() {
		return AllotPaperId;
	}
	public void setPaperName(String paperName) {
		PaperName = paperName;
	}
	public String getPaperName() {
		return PaperName;
	}
	public void setPaperUploader(String paperUploader) {
		PaperUploader = paperUploader;
	}
	public String getPaperUploader() {
		return PaperUploader;
	}
	public void setPaperWriter(String paperWriter) {
		PaperWriter = paperWriter;
	}
	public String getPaperWriter() {
		return PaperWriter;
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
	public void setPaperReward(String paperReward) {
		PaperReward = paperReward;
	}
	public String getPaperReward() {
		return PaperReward;
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
	public AllotPaper() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setPaperFlag(String paperFlag) {
		PaperFlag = paperFlag;
	}
	public String getPaperFlag() {
		return PaperFlag;
	}
	

}
