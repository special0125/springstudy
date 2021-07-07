<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>게시판 내용 보기</title>
	<script src="https://code.jquery.com/jquery-3.6.0.min.js" integrity="sha256-/xUj+3OJU5yExlq6GSYGSHk7tPXikynS7ogEvDej/m4=" crossorigin="anonymous"></script>
	<script>
		$(document).ready(function(){
			fn_update();
			fn_delete();
			fn_replyList();
			fn_paging();
		});
		function fn_update(){
			$('#update_btn').click(function(){
				if(confirm('수정하시겠습니까?')) {
					$('#f').attr('action', 'updateBoard.do');
					$('#f').submit();
				}
			});
		}
		function fn_delete(){
			$('#delete_btn').click(function(){
				if (confirm('삭제할까요?')) {
					$('#f').attr('action', 'deleteBoard.do');
					$('#f').submit();
				}
			});
		}
		
		var page = 1;
		function fn_replyList(){
			var obj = {
				page: page
			};
			$.ajax({
				url: 'replyList.do',
				type: 'post',
				contentType: 'application/json',
				data: JSON.stringify(obj),
				dataType: 'json',
				success: function(resultMap){
					
					$('#list').empty();
					if (resultMap.status == 200) {
						$.each(resultMap.list, function(i, reply) {
							$('<tr>')
							.append( $('<td>').text(reply.no) )
							.append( $('<td>').text(board.id) )
							.append( $('<td>').text(board.content) )
							.append( $('<td>').text(board.ip) )
							.append( $('<td>').text(board.regdate) )
							.appendTo('#list');
						});
					}else if (resultMap.status == 500) {
						$('<tr>')
						.append( $('<td colspan="5">').text('검색 결과 없음') )
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
	</style>
</head>
<body>
		<h1>게시글 보기</h1>
		
		<c:if test="${loginUser.id == galleryBoard.id}">  <!-- 로그인유저 == 작성자만 수정, 삭제 가능 -->
			<form id="f" method="post" enctype="multipart/form-data">
				<input type="button" value="수정하기" id="update_btn">
				<input type="button" value="삭제하기" id="delete_btn"><br><br>
				
				<input type="hidden" name="no" value="${galleryBoard.no}">
				<input type="hidden" name="filename1" value="${filename}">  <!-- 서버에 첨부된 첨부파일명 -->
				
				작성자: ${galleryBoard.id}<br><br>
				
				제목:
				<input type="text" name="title" value="${galleryBoard.title}"><br><br>
				
				내용:
				<input type="text" name="content" value="${galleryBoard.content}"><br><br>
				
				첨부 이미지:<br>
					<c:if test="${filename != ''}">
					<img alt="${filename}" src="resources/archive/${filename}" style="width: 300px;"><br><br>
					</c:if>
				
				첨부 이미지:<br>
				<input type="file" name="filename2">
			</form>
		</c:if>
		
		<c:if test="${loginUser.id != galleryBoard.id}">
			<form>
				<input type="button" value="뒤로가기" onclick="location.href='galleryBoard.do'"><br><br>
				작성자: ${galleryBoard.id}<br><br>
		
				제목: ${galleryBoard.title}<br><br>
				
				내용: ${galleryBoard.content}<br><br>
				
				첨부 이미지:<br>
					<img alt="${filename}" src="resources/archive/${filename}" style="width: 300px;"><br><br>
				
			</form>
		</c:if>
		<br><br>
		<br>
		
		<table border="1">
		<thead>
			<tr>
				<td>번호</td>
				<td>작성자</td>
				<td>내용</td>
				<td>IP</td>
				<td>작성일</td>
			</tr>
		</thead>
		<tbody id="list">
		</tbody>
		<tfoot>
			<tr>
				<td colspan="5">
					<div id="paging"></div>
				</td>
			</tr>
		</tfoot>
		</table>
		
		
		
		
		
		
		
		<c:if test="${loginUser != null}">
			<form>
				<input type="text" name="reply" id="reply">
				<input type="button" value="댓글 작성" id="reply_btn">
			</form>
		</c:if>		
		
		
	
</body>
</html>