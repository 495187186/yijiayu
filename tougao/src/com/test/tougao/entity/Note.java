package com.test.tougao.entity;

public class Note {
	private String NoteId;
	private String AdminNo;
	private String AdminName;
	private String NoteName;
	private String NoteDate;
	private String NoteContent;
	private String DiskName;
	private String FileName;
	private String FileSize;
	private String IsAttach;
	public void setNoteId(String noteId) {
		NoteId = noteId;
	}
	public String getNoteId() {
		return NoteId;
	}
	public void setAdminNo(String adminNo) {
		AdminNo = adminNo;
	}
	public String getAdminNo() {
		return AdminNo;
	}
	public void setAdminName(String adminName) {
		AdminName = adminName;
	}
	public String getAdminName() {
		return AdminName;
	}
	public void setNoteName(String noteName) {
		NoteName = noteName;
	}
	public String getNoteName() {
		return NoteName;
	}
	public void setNoteDate(String noteDate) {
		NoteDate = noteDate;
	}
	public String getNoteDate() {
		return NoteDate;
	}
	public void setNoteContent(String noteContent) {
		NoteContent = noteContent;
	}
	public String getNoteContent() {
		return NoteContent;
	}
	public void setDiskName(String diskName) {
		DiskName = diskName;
	}
	public String getDiskName() {
		return DiskName;
	}
	public void setFileName(String fileName) {
		FileName = fileName;
	}
	public String getFileName() {
		return FileName;
	}
	public void setFileSize(String fileSize) {
		FileSize = fileSize;
	}
	public String getFileSize() {
		return FileSize;
	}
	public void setIsAttach(String isAttach) {
		IsAttach = isAttach;
	}
	public String getIsAttach() {
		return IsAttach;
	}
	public Note() {
		super();
		// TODO Auto-generated constructor stub
	}
	

}
