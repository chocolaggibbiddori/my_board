package com.bestteam.dto;

public class PostSaveDto {
	
	private final String subject;
	private final String writer;
	private final String contents;
	private final String ip;
	
	public PostSaveDto(String subject, String writer, String contents, String ip) {
		this.subject = subject;
		this.writer = writer;
		this.contents = contents;
		this.ip = ip;
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
	
	public String getIp() {
		return ip;
	}
}
