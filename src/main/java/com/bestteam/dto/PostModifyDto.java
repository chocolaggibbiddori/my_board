package com.bestteam.dto;

public class PostModifyDto {
	
	private final int num;
	private final String subject;
	private final String writer;
	private final String contents;
	
	public PostModifyDto(int num, String subject, String writer, String contents) {
		this.num = num;
		this.subject = subject;
		this.writer = writer;
		this.contents = contents;
	}
	
	public int getNum() {
		return num;
	}
	public String getSubject() {
		return subject;
	}
	public String getWriter() {
		return writer;
	}
	public String getContents() {
		return contents;
	}
}
