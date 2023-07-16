<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
int num = (int) request.getAttribute("num");
String subject = (String) request.getAttribute("subject");
String writer = (String) request.getAttribute("writer");
int hit = (int) request.getAttribute("hit");
String reg_date = (String) request.getAttribute("reg_date");
String contents = (String) request.getAttribute("contents");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Detail Post</title>
</head>
<body>
	<table border="1" width="730">
		<caption>게시판 상세 조회</caption>
		<tr>
			<td align="center" width="100">제목</td>
			<td><%= subject %></td>
		</tr>
		<tr>
			<td align="center" width="100">작성자/조회수</td>
			<td><%= writer + "/" + hit %></td>
		</tr>
		<tr>
			<td align="center" width="100">등록 일시</td>
			<td><%= reg_date %></td>
		</tr>
		<tr>
			<td colspan="2"><%= contents %></td>
		</tr>
	</table>
	<br>
	<div align="right">
		<a href="board?pageNum=1"><button>목록</button></a>
		<a href="modify?num=<%= num %>"><button>수정</button></a>
		<a href="delete?num=<%= num %>"><button>삭제</button></a>
	</div>
</body>
</html>
