<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
table {
	margin: auto;
	margin-top: 100px;
	border-collapse: collapse;
	border: 1px solid black;
	width: 400px;
}
th, td {
	border: 1px solid black;
	text-align: center;
}
th{
	width: 40px;
}

textarea {
	padding:20px;
	resize: none;
	height: auto;
}
</style>

</head>
<body>
	<table>
		<tr>
			<th colspan="2">${list.no}</th>
		</tr>
		<tr>
			<th>제목</th>
			<td>${list.title }</td>
		</tr>
		<tr>
			<th>내용</th>
			<td>${list.content }</td>
		</tr>
		<tr>
			<td colspan="2">
			<button type="button" onclick="location='list.do';">목록</button>
			<button type="button" onclick="location='edit.do?no=${list.no}';">수정</button>
			<button type="button" onclick="location='del.do';">삭제</button>
			</td>
		</tr>
	</table>
</body>
</html>