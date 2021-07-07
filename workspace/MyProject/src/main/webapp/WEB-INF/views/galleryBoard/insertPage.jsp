<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시물 작성 페이지</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
	
		$(document).ready(function(){
			
			$('#insert_btn').click(function(){
				if(confirm('작성하시겠습니까?')) {
					$('#f').submit();
				}
			});
			
			
		});
	</script>
</head>
<body>
	<form id="f" action="insertBoard.do" method="post" enctype="multipart/form-data">
		아이디: ${loginUser.id}<br><br>
		<input type="hidden" name="id" value="${loginUser.id}">
		제목: <input type="text" name="title"><br><br>
		내용: <input type="text" name="content"><br><br>
		첨부파일: <input type="file" name="filename" ><br><br>
		<input type="button" value="작성하기" id="insert_btn">
	</form>
	<br><br>
	<input type="button" value="뒤로가기" onclick="location.href='galleryBoard.do'">


	
</body>
</html>