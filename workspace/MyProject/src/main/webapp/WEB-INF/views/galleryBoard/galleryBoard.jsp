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
			fn_paging();
			fn_home();
		});
		
		function fn_home() {
			$('#home_btn').click(function(){
				$('#f').attr('action', 'index.do');
				$('#f').submit();
			});
		}
		
		
		function fn_init(){
			$('#init_btn').click(function(){
				fn_selectAll();
			});
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
		
		var page = 1;
		function fn_selectAll(){
			var obj = {
				page: page
			};
			$.ajax({
				url: 'selectAll.do',
				type: 'post',
				contentType: 'application/json',
				data: JSON.stringify(obj),
				dataType: 'json',
				success: function(resultMap){
					
					$('#list').empty();
					if (resultMap.status == 200) {
						$.each(resultMap.list, function(i, board) {
							$('<tr>')
							.append( $('<td>').text(board.no) )
							.append( $('<td>').append( $('<a href="selectByNo.do?no=' + board.no + '">').text(board.title) ) )
							.append( $('<td>').text(board.id) )
							.append( $('<td>').text(board.ip) )
							.append( $('<td>').text(board.hit) )
							.append( $('<td>').text(board.regdate) )
							.appendTo('#list');
						});
					}else if (resultMap.status == 500) {
						$('<tr>')
						.append( $('<td colspan="6">').text('검색 결과 없음') )
						.appendTo('#list');
					}
					
					var paging = resultMap.paging;
					
					$('#paging').empty();
					
					// 2) 이전('◀')
					if (paging.beginPage <= paging.pagePerBlock) {  // 이전('◀')이 없음(1블록)
						// class
						// 1. disable : color silver
						$('<div>').addClass('disable').text('◀').appendTo('#paging');
					} else {  // 이전('◀')이 있음
						// class
						// 1. previous_block : click 이벤트에서 활용
						// 2. link : cursor pointer
						$('<div>')
						.addClass('previous_block').addClass('link')
						.attr('data-page', paging.beginPage - 1)
						.text('◀')
						.appendTo('#paging');
					}
					// 3) 1 2 3 4 5
					for (let p = paging.beginPage; p <= paging.endPage; p++) {
						if (p == paging.page) {  // 표시된 페이지가 현재 페이지(링크 없음)
							// class
							// 1. now_page : color limegreen
							$('<div>')
							.addClass('now_page')
							.text(p)
							.appendTo('#paging');
						} else {
							// class
							// 1. go_page : click 이벤트에서 활용
							// 2. link : cursor pointer
							$('<div>')
							.addClass('go_page').addClass('link')
							.attr('data-page', p)
							.text(p)
							.appendTo('#paging');
						}
					}
					// 4) 다음('▶')
					if (paging.endPage == paging.totalPage) {  // 다음('▶')이 없음(마지막 블록)
						// class
						// 1. disable : color silver
						$('<div>')
						.addClass('disable')
						.text('▶')
						.appendTo('#paging');
					} else {  // 다음('▶')이 있음
						// class
						// 1. next_block : click 이벤트에서 활용
						// 2. link : cursor pointer
						$('<div>')
						.addClass('next_block').addClass('link')
						.attr('data-page', paging.endPage + 1)
						.text('▶')
						.appendTo('#paging');
					}
				}
			});
		}
		
		// 2. 회원 목록 페이징(페이징 링크 처리)
		function fn_paging() {
			$('body').on('click', '.previous_block', function(){
				page = $(this).attr('data-page');
				fn_selectAll();
			});
			$('body').on('click', '.go_page', function(){
				page = $(this).attr('data-page');
				fn_selectAll();
			});
			$('body').on('click', '.next_block', function(){
				page = $(this).attr('data-page');
				fn_selectAll();
			});
		}
	</script>
	
	<style>
		#paging {
			width: 50%;
			margin: 0 auto;
			display: flex;
			justify-content: space-between;
			text-align: center;
		}
		#paging div {
			width: 40px;
			height: 20px;
		}
		.disable {
			color: silver;
		}
		.link {
			cursor: pointer;
		}
		.now_page {
			color: limegreen;
		}
	</style>
</head>
<body>
	<h1>갤러리 게시판</h1>
	<form id="f">
	<input type="hidden" name="id" id="id" value="${loginUser.id}">		
	<input type="button" value="작성하기" id="insert_btn">
	<input type="button" value="초기화" id="init_btn">
	<input type="button" value="홈으로" id="home_btn">
	</form>
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
		<tfoot>
			<tr>
				<td colspan="6">
					<div id="paging"></div>
				</td>
			</tr>
		</tfoot>
	</table>
	
</body>
</html>