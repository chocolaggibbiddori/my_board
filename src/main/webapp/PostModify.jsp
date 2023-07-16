<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%
int num = (int) request.getAttribute("num");
String subject = (String) request.getAttribute("subject");
String writer = (String) request.getAttribute("writer");
String contents = (String) request.getAttribute("contents");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Modify Post</title>
</head>
<body>
	<form action="modify" method="post">
		<input type="hidden" name="num" value="<%= num %>" />
		<table border="1" width="730">
			<caption>게시판 수정 폼</caption>
			<tr>
				<td align="center">제목</td>
				<td><input type="text" name="subject" value="<%= subject %>" required /></td>
			</tr>
			<tr>
				<td align="center">작성자</td>
				<td><input type="text" name="writer" value="<%= writer %>" readOnly /></td>
			</tr>
			<tr>
				<td colspan="2"><textarea rows="10" cols="100" name="contents" required><%= contents %></textarea></td>
			</tr>
		</table>
		<button type="submit">수정</button>
	</form>
		<a href="board?pageNum=1"><button>목록</button></a>
</body>
</html>
