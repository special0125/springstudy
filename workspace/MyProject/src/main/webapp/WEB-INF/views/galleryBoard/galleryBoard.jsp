<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>갤러리 게시판</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			fn_init();
			fn_selectAll();
			fn_insert();
		});
		
		function fn_init(){
			$('#init_btn').click(function(){
				fn_selectAll();
			});
		}
		
		function fn_selectAll(){
			$.ajax({
				url: 'selectAll.do',
				type: 'get',
				dataType: 'json',
				success: function(resultMap){
					fn_listTable(resultMap.status, resultMap.list);					
				}
			});
		}
		
		function fn_listTable(status, list) {
			$('#list').empty();
			if (status == 200) {
				$.each(list, function(i, board) {
					$('<tr>')
					.append( $('<td>').text(board.no) )
					.append( $('<td>').append( $('<a href="selectByNo.do?no=' + board.no + '">').text(board.title) ) )
					.append( $('<td>').text(board.id) )
					.append( $('<td>').text(board.ip) )
					.append( $('<td>').text(board.hit) )
					.append( $('<td>').text(board.regdate) )
					.appendTo('#list');
				});
			}else if (status == 500) {
				$('<tr>')
				.append( $('<td colspan="6">').text('검색 결과 없음') )
				.appendTo('#list');
			}
		}
		function fn_insert() {
			$('#insert_btn').click(function(){
				if($('#id').val() != '') {
					$('#f').attr('action', 'insertPage.do');
					$('#f').submit();
				}else {
					alert('로그인해야 작성 가능 합니다.');
					return false;
				}
				
			});
		}
	</script>
</head>
<body>
	<h1>갤러리 게시판</h1>
	<form id="f">
	<input type="hidden" name="id" id="id" value="${loginUser.id}">		
	<input type="button" value="작성하기" id="insert_btn">
	</form>
	<input type="button" value="초기화" id="init_btn">
	<br><br>
	
	
	<table border="1">
		<thead>
			<tr>
				<td>번호</td>
				<td>제목</td>
				<td>작성자</td>
				<td>IP</td>
				<td>조회수</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody id="list">
		</tbody>
	</table><br>
	${loginUser.id}
	
</body>
</html>