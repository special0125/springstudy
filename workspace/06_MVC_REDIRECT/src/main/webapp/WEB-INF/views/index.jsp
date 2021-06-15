<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
</head>
<body>

	<!-- /컨텍스트패스 : 컨텍스트패스는 슬래시로 시작해야 한다. -->
	<form action="/mvc04/v01">
		<input type="text" name="name" placeholder="이름"><br>
		<input type="text" name="age" placeholder="나이"><br><br>
		<button>전송</button>
	</form>
	
	<hr>
	
	<!-- /컨텍스트패스 : 컨텍스트패스는 슬래시가 없으면 매핑으로 인식된다. -->
	<form action="mvc04/v01">
		<input type="text" name="name" placeholder="이름"><br>
		<input type="text" name="age" placeholder="나이"><br><br>
		<button>전송</button>
	</form>
	
	<hr>
	
	<!-- /매핑 : 매핑을 컨텍스트패스로 인식한다. 즉 동작하지 않는다. -->
	<form action="/v01">
		<input type="text" name="name" placeholder="이름"><br>
		<input type="text" name="age" placeholder="나이"><br><br>
		<button>전송</button>
	</form>
	
	<hr>
	
	<!-- 매핑 : 정상적으로 매핑으로 인식한다. -->
	<form action="v01">
		<input type="text" name="name" placeholder="이름"><br>
		<input type="text" name="age" placeholder="나이"><br><br>
		<button>전송</button>
	</form>
	
	<!-- form action의 결론 -->
	<!-- 
		1. 슬래시로 시작하려면 컨텍스트 패스 부터 작성한다.
		2. 슬래시 없이 시작하려면 매핑만 작성한다.
	-->
	
	<!-- 새로운 매핑 -->
	<h2>v02 매핑</h2>
	<form action="v02">
		<input type="text" name="name" placeholder="이름"><br>
		<input type="text" name="age" placeholder="나이"><br><br>
		<button>전송</button>
	</form>
	
	<!-- 새로운 매핑 -->
	<h2>v04 매핑</h2>
	<form action="v04">
		<input type="text" name="name" placeholder="이름"><br>
		<input type="text" name="age" placeholder="나이"><br><br>
		<button>전송</button>
	</form>
	
	<!-- 새로운 매핑 -->
	<h2>v06 매핑</h2>
	<form action="v06">
		<input type="text" name="name" placeholder="이름"><br>
		<input type="text" name="age" placeholder="나이"><br><br>
		<button>전송</button>
	</form>
	
	
	
	
</body>
</html>