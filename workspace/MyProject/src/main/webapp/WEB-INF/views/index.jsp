<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>로그인</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		//페이지 로드
		$(document).ready(function(){
			fn_login();
			fn_leave();
			
		});	
		
		// 함수
		function fn_login(){
			$('#f').submit(function(event){
				if ($('#id').val() == '' || $('#pw').val == '') {
					alert('아이디와 비밀번호는 필수입니다.');
					event.preventDefault();
					return false;
				}
			})
		}
		function fn_leave() {
			$('#leave_link').click(function(event){
				if(!confirm('탈퇴 하시겠습니까?')) {
					event.preventDefault();
					return false;
				}
				alert('탈퇴 되었습니다.');
			});
		}
		
		
	</script>
	
</head>
<body>
	<h1>홈페이지</h1>
	<c:if test="${loginUser.status == 'on'}">
		${loginUser.name}님 어서오세요. <br>
		상태: ${loginUser.status}
		<a href="logout.do">로그아웃</a><br>
		<a href="leave.do?id=${loginUser.id}" id="leave_link">회원탈퇴</a><br>
	</c:if>

	<c:if test="${loginUser.status == 'off'}">
		<%session.invalidate();%>
		<script>
			alert('탈퇴한 회원입니다.');
			location.href="index.do";
		</script>
	</c:if>
	
	<c:if test="${loginUser == null}">
		
		<form action="login.do" method="post" id="f">
			아이디<br>
			<input type="text" name="id" id="id"><br><br>
			비밀번호<br>
			<input type="password" name="pw" id="pw"><br><br>
			<button>로그인</button>
		</form>
		<br>
		<a href="joinPage.do">회원가입</a>&nbsp;&nbsp;&nbsp;
		<a href="findIdPage.do">아이디찾기</a>&nbsp;&nbsp;&nbsp;
		<a href="findPwPage.do">비밀번호찾기</a>
	</c:if>
	<br><br>
	
	<a href="galleryBoard.do">갤러리 게시판</a><br>	
	<a href="freeBoard.do">자유 게시판</a>
	
</body>
</html>