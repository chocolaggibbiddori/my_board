<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List, java.util.ArrayList, com.bestteam.dto.PostListDto" %>
<%
List<PostListDto> postList = (List<PostListDto>) request.getAttribute("postList");
int pageTotal = (int) request.getAttribute("pageTotal");
int pageNum = 1;
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Board</title>
</head>
<body>
	<hr>
	<div align="right">
		<form action="board" method="post">
			<input type="hidden" name="pageNum" value=<%= pageNum %> />
			<select name="searchCondition">
				<option value="ALL">전체</option>
				<option value="SUBJECT">제목</option>
				<option value="WRITER">작성자</option>
				<option value="CONTENTS">내용</option>
			</select>
			<input type="text" name="searchContent" />
			<input type="submit" value="검색" />
		</form>
	</div>
	<br>
	<table border="1" width="1500" align="center">
		<caption>게시판 목록</caption>
		<tr>
			<th style="width: 10%;">번호</th>
			<th style="width: 40%;">제목</th>
			<th style="width: 15%;">작성자</th>
			<th style="width: 25%;">등록 일시</th>
			<th style="width: 10%;">조회수</th>
		</tr>
		<% if (postList.size() == 0) { %>
			<tr>
				<td align="center" colspan="5">등록된 게시글이 없습니다.</td>
			</tr>
		<% } else {
				for (PostListDto dto : postList) { %>
					<tr>
						<td align="center"><%= dto.getNum() %></td>
						<td align="center"><a href="detail?num=<%= dto.getNum() %>"><%= dto.getSubject() %></a></td>
						<td align="center"><%= dto.getWriter() %></td>
						<td align="center"><%= dto.getReg_date() %></td>
						<td align="center"><%= dto.getHit() %></td>
					</tr>
		<% 		}
			} %>
		<tr>
			<td colspan="5" align="center">
				<% for (int i = 1; i <= pageTotal; i++) { %>
					<a href="board?pageNum=<%= i %>"><%= i %></a>
				<% } %>
			</td>
		</tr>
	</table>
	<hr>
	<div align="right">
		<a href="board?pageNum=1"><button>목록</button></a>
		<a href="write"><button>글쓰기</button></a>
	</div>
</body>
</html>
