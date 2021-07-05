<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>아이디 찾기 결과</title>	
</head>
<body>
	
	<h1>아이디 찾기 결과</h1>
	
	<c:if test="${findId == null}">
		일치하는 회원 정보가 없습니다. <br><br>
		<a href="findIdPage.do">아이디 찾기</a>
		<input type="button" value="로그인하러 가기" onclick="location.href='index.do'">
	</c:if>
	
	<c:if test="${findId.status == 'off'}">
		<script>
			alert('탈퇴한 회원입니다.')
			location.href = 'index.do';
		</script>
	</c:if>
	
	<c:if test="${findId.status == 'on'}">
		아이디 : ${findId.id}<br>
		가입이메일 : ${findId.email}<br>
		상태: ${findId.status}<br>
		<input type="button" value="로그인하러 가기" onclick="location.href='index.do'">
	</c:if>
		
	
	
</body>
</html>