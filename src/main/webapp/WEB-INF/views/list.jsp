<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<style type="text/css">
	table{
	margin: 200px auto 0 auto;
	width:300px;
	text-align:center;
	}
</style>
</head>
<body>
	<table border="1">
		<tr>
			<td>번호</td>
			<td>제목</td>
		</tr>
		<c:forEach var="list" items="${list}">
			
			<tr>
				<td><c:out value="${list.no}"/></td>
				<td><a href="/content.do?no=<c:out value="${list.no}"/>">${list.title}</a></td>
			</tr>
			<%-- <tr >
				<td>${list.content}</td>
			</tr> --%>
		</c:forEach>
		<tr>
			<td colspan="2">
				<button type="button" onclick="location='/write.do';">글작성</button>
			</td>
		</tr>
	</table>
</body>
</html>
