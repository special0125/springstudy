<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>비밀번호 변경</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			fn_pwCheck();
			fn_pwCheck2();
			fn_changePw();
		});
		// 비밀번호 검증 함수
		var pwPass = false;
		function fn_pwCheck(){
			$('#pw').keyup(function(){  // keyup : 비밀번호를 입력할때마다
				var regPW = /^[0-9]{1,4}$/;  // 나중에 수정해서 사용
				if (regPW.test($('#pw').val())){
					$('#pw_result').text('사용 가능한 비밀번호입니다.').css('color', 'blue');
					pwPass = true;
				} else {
					$('#pw_result').text('비밀번호는 ~~~입니다.').css('color', 'red');
					pwPass = false;
				}
				if ($('#pw').val() == '') {
					$('#pw_result').text('');
					$('#pw2_result').text('');
				}else if ($('#pw').val() == $('#pw2').val()){
					$('#pw2_result').text('비밀번호 일치').css('color', 'blue');
					pwPass2 = true;
				}
			});
		}
		// 비밀번호 입력 확인 함수
		var pwPass2 = false;
		function fn_pwCheck2(){
			$('#pw2').keyup(function(){  // keyup : 비밀번호를 입력할때마다
				if($('#pw2').val() == '') {
					$('#pw2_result').text('');
				}else if ($('#pw').val() == $('#pw2').val()){
					$('#pw2_result').text('비밀번호 일치').css('color', 'blue');
					pwPass2 = true;
				}else {
					$('#pw2_result').text('비밀번호를 확인하세요.').css('color', 'red');
					pwPass2 = false;
				} 
			});
		}
		
		function fn_changePw() {
			$('#change_pw_btn').click(function(){
				var regPW = /^[0-9]{1,4}$/;
				if ( !regPW.test($('#pw').val()) ) {
					alert('비밀번호는 ~~~입니다.');
					$('#pw').focus();
					return false;
				} else if ($('#pw').val() != $('#pw2').val()) {
					alert('비밀번호를 확인하세요.');
					return false;
				} else {
					alert('비밀번호가 변경되었습니다! 홈으로 이동합니다.');
					$('#f').attr('action', 'changePw.do');
					$('#f').submit();
				}
			});
		}
		
		
	</script>
	
</head>
<body>
	
	<h1>비밀번호 변경</h1>
	
	<form id="f" method="post">
		<input type="hidden" name="id" value="${id}">
		새 비밀번호<br>
		<input type="password" name="pw" id="pw"><br>
		<span id=pw_result></span><br>
		비밀번호 확인<br>
		<input type="password" name="pw2" id="pw2"><br>
		<span id=pw2_result></span><br>
		<input type="button" value="비밀번호 변경" id="change_pw_btn">
		<input type="button" value="돌아가기" onclick="location.href='index.do'">
	</form>

</body>
</html>