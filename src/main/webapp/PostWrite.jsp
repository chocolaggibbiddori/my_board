<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Write Post</title>
</head>
<body>
	<form action="save" method="post">
		<table border="1" width="730">
			<caption>게시판 등록 폼</caption>
			<tr>
				<td align="center">제목</td>
				<td><input type="text" name="subject" required /></td>
			</tr>
			<tr>
				<td align="center">작성자</td>
				<td><input type="text" name="writer" required /></td>
			</tr>
			<tr>
				<td colspan="2"><textarea rows="10" cols="100" name="contents" required></textarea></td>
			</tr>
		</table>
		<button type="submit">글쓰기</button>
	</form>
		<a href="board?pageNum=1"><button>목록</button></a>
</body>
</html>
