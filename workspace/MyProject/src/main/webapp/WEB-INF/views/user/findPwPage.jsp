<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>비밀번호 찾기</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			fn_verify_num();
		});
		
		function fn_findPw() {
			if ($('#id').val() == '') {
				$('#id_result').text('아이디를 입력하세요.').css('color', 'red');
				return false;
			}else if ($('#email').val() == '') {
				$('#email_result').text('이메일을 입력하세요.').css('color', 'red');
				return false;
			}
			
		}
		
		function fn_verify_num(){
			$('#verify_num_btn').click(function(){
				fn_findPw();
				$.ajax({
					url: 'findPw.do',
					type: 'get',
					data: 'id=' + $('#id').val() + '&email=' + $('#email').val(),
					dataType: 'json',
					success: function(resultMap) {
						if(resultMap.status == 200) {
							alert('인증코드가 발송되었습니다.');
							$('#email_result').text('인증코드가 발송되었습니다.').css('color', 'blue');
							fn_verify(resultMap.authCode);
						}else {
							alert(resultMap.status);
						}
					}
				})
			});
		}
		function fn_verify(authCode) {
			$('#verify_btn').click(function(){
				if (authCode == $('#authCode').val()) {
					alert('인증되었습니다! 비밀번호 변경 페이지로 이동합니다.');
					$('#f').attr('action', 'changePwPage.do');
					$('#f').attr('method', 'post');
					$('#f').submit();
				}else {
					$('#authCode_result').text('인증이 실패했습니다.').css('color', 'red');
				}
			})
		}
		
		
	</script>
</head>
<body>
	<h1>비밀번호 찾기</h1>
	
	<form id="f">
		아이디<br>
		<input type="text" name="id" id="id"><br>
		<span id="id_result"></span><br>
		이메일<br>
		<input type="text" name="email" id="email">
		<input type="button" value="인증번호받기" id="verify_num_btn"><br>
		<span id="email_result"></span><br>
		인증번호입력<br>
		<input type="text" name="authCode" id="authCode">
		<input type="button" value="인증하기" id="verify_btn"><br>
		<span id="authCode_result"></span><br>
		<input type="button" value="홈으로가기" onclick="location.href='index.do'">
	</form>
</body>
</html>