<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script src="${pageContext.request.contextPath}/ckeditor/ckeditor.js"></script>

</head>
<body>
	<form action="save.do" method="post">
		<div class="mb-3" style="width: 50%; margin: 0 auto;">
			<div>제목&nbsp;<input type="text" name="title"></div><br>
			<textarea class="form-control " name="content" id="editor4" rows="6"></textarea>
		</div>
		<button type="submit" >저장ㄱ</button>
	</form>
	<script>
		CKEDITOR.replace('editor4',{
			filebrowserUploadUrl:"/main/fileuploads.do"
			});
	</script>
</body>
</html>