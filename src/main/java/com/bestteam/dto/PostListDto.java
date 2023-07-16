package com.bestteam.dto;

public class PostListDto {

	private final int num;
	private final String subject;
	private final String writer;
	private final String reg_date;
	private final int hit;
	
	public PostListDto(int num, String subject, String writer, String reg_date, int hit) {
		this.num = num;
		this.subject = subject;
		this.writer = writer;
		this.reg_date = reg_date;
		this.hit = hit;
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
	public String getReg_date() {
		return reg_date;
	}
	public int getHit() {
		return hit;
	}
}
