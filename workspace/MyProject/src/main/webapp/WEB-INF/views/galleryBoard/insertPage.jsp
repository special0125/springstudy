<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시물 작성 페이지</title>
</head>
<body>

	<form action="insertBoard.do" method="post" enctype="multipart/form-data">
		
	아이디: ${loginUser.id}<br><br>
	<input type="hidden" name="id" value="${loginUser.id}">
	제목: <input type="text" name="title"><br><br>
	내용: <input type="text" name="content"><br><br>
	첨부파일: <input type="file" name="files" ><br><br>
	
	<button>작성하기</button>
	
	</form>


	
</body>
</html>