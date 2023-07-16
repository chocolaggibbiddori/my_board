package com.bestteam.service;

import java.util.ArrayList;
import java.util.List;

import com.bestteam.dto.PostDetailDto;
import com.bestteam.dto.PostListDto;
import com.bestteam.dto.PostModifyDto;
import com.bestteam.dto.PostSaveDto;
import com.bestteam.repository.Repository;

public class Service {

	private final Repository repository = new Repository();

	public List<PostListDto> extractTen(int pageNum) {
		return repository.extractTen(pageNum);
	}

	public List<PostListDto> extractTen(int pageNum, SearchCondition condition, String searchContent) {
		switch (condition) {
		case ALL:
			return repository.extractTenByAll(pageNum, searchContent);
		case SUBJECT:
			return repository.extractTenBySubject(pageNum, searchContent);
		case WRITER:
			return repository.extractTenByWriter(pageNum, searchContent);
		case CONTENTS:
			return repository.extractTenByContents(pageNum, searchContent);
		default:
			return new ArrayList<>();
		}
	}

	public PostDetailDto findById(int num) {
		return repository.findById(num);
	}

	public int save(PostSaveDto postSaveDto) {
		repository.save(postSaveDto);
		return repository.getMaxNum();
	}

	public void delete(int num) {
		repository.delete(num);
	}

	public void modify(PostModifyDto postModifyDto) {
		repository.modify(postModifyDto);
	}

	public void addHit(int num) {
		repository.addHit(num);
	}

	public int getBoardSize() {
		return repository.size();
	}

	public int getBoardSize(SearchCondition searchCondition, String searchContent) {
		switch (searchCondition) {
			case ALL:
				return repository.size("subject like ('%', ?, '%') or writer like ('%', ?, '%') or contents like ('%', ?, '%')", searchContent);
			case SUBJECT:
			case WRITER:
			case CONTENTS:
				return repository.size(searchCondition.toString(), searchContent);
			default:
				return 0;
		}
	}
}
