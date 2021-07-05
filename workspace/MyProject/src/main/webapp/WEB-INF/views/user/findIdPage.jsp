<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
	<meta charset="UTF-8">
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
	
		$(document).ready(function(){
			fn_findId();
		})
		
		function fn_findId(){
			$('#find_id_btn').click(function(){
				if ($('#name').val() == '') {
					alert('이름을 입력하세요.');
					$('#name').focus();
					return false;
				}else if ($('#email').val() == '') {
					alert('이메일을 입력하세요.');
					$('#email').focus();
					return false;
				}else {
					$('#f').attr('action', 'findId.do');
					$('#f').submit();
				}
			});
		}
	</script>
<title>아이디 찾기</title>
</head>
<body>
	<h1>아이디 찾기</h1>
	<form id="f" method="post">
		이름<br>
		<input type="text" name="name" id="name"><br><br>
		이메일<br>
		<input type="text" name="email" id="email"><br><br>
		<input type="button" value="아이디 찾기" id="find_id_btn">
		<input type="button" value="로그인 하러 가기" onclick="location.href='index.do'">
	</form>
</body>
</html>