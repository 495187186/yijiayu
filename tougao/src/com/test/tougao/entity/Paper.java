package com.test.tougao.entity;

public class Paper {
	private String PaperId;
	private String CollegeName;
	private String PaperName;
	private String PaperType;
	private String PaperUploader;//上传学生学号
	private String Uploader;//上传学生姓名
	private String PaperWriter;//通讯作者姓名
	private String PaperTime;
	private String PaperStatus;
	private String PaperCounter;
	private String PaperReward;//审稿费
	private String PersonNum;//指派人数
	private String IsReward;//是否确定稿费
	private String IsAllot;//是否被指派
	private String IsChoose;//是否开放
	public void setPaperId(String paperId) {
		PaperId = paperId;
	}
	public String getPaperId() {
		return PaperId;
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
	public void setPaperTime(String paperTime) {
		PaperTime = paperTime;
	}
	public String getPaperTime() {
		return PaperTime;
	}
	public void setPaperStatus(String paperStatus) {
		PaperStatus = paperStatus;
	}
	public String getPaperStatus() {
		return PaperStatus;
	}
	public void setPaperCounter(String paperCounter) {
		PaperCounter = paperCounter;
	}
	public String getPaperCounter() {
		return PaperCounter;
	}
	public Paper() {
		super();
		// TODO Auto-generated constructor stub
	}
	public void setCollegeName(String collegeName) {
		CollegeName = collegeName;
	}
	public String getCollegeName() {
		return CollegeName;
	}
	public void setIsReward(String isReward) {
		IsReward = isReward;
	}
	public String getIsReward() {
		return IsReward;
	}
	public void setIsChoose(String isChoose) {
		IsChoose = isChoose;
	}
	public String getIsChoose() {
		return IsChoose;
	}
	public void setUploader(String uploader) {
		Uploader = uploader;
	}
	public String getUploader() {
		return Uploader;
	}
	public void setIsAllot(String isAllot) {
		IsAllot = isAllot;
	}
	public String getIsAllot() {
		return IsAllot;
	}
	public void setPaperReward(String paperReward) {
		PaperReward = paperReward;
	}
	public String getPaperReward() {
		return PaperReward;
	}
	public void setPersonNum(String personNum) {
		PersonNum = personNum;
	}
	public String getPersonNum() {
		return PersonNum;
	}
	

}
