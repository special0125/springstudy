<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html">
<html>
<head>
	<meta charset="UTF-8">
	<title>Insert title here</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
			
	function fn2() {
		$('#btn2').click(function(){
			$.ajax({
				url: 'v02',
				type: 'get',
				data: $('#f').serialize(),  // form의 모든 요소를 한 번에 보냄
				dataType: 'json',
				success: function(responseData) {
					console.log(responseData);
				},
				error: function(xhr, text, error) {
					console.log(text + ", " + error);
				}
			
			})
		})
	}
	</script>
</head>
<body>
	<form id="f">
	</form>
</body>
</html>