<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.board.model.vo.Board" %>
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나는 사진게시판이야 ~ </title>
<style>
	img{
		width: 200px;
		height: 150px;
		padding: 10px;
	}
	.outer{
		background-color: rgb(33, 155, 114);
		color:white;
		width: 1000px;
		height: 1000px;
		margin:auto;
		margin-top : 50px;
	}
	.list-area{
		width: 760px;
		margin: auto;
	}
	.thumbnail {
		border: 1px solid white;
		width: 200px;
		display: inline-block;
		margin: 14px;
		background-color: rgb(103, 173, 152);
	}
	.thumbnail:hover{
		cursor:pointer;
		opacity: 0.8;
	}

</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
	
		<br>
		<h2 align="center">사진게시판</h2>
		<br>

		<% if(loginUser != null) { %>
			<div align="right" style="width:800px">
				<a href="<%= contextPath %>/enrollForm.th" class="btn btn-sm btn-info">글작성</a>
				<br>
			</div>
		<% } %>

		<div class="list-area">

			<% if(list != null) { %>
				<% for(Board b : list) { %>
					<div class="thumbnail" align="center">
						<input type="hidden" value="<%= b.getBoardNo()%>">
						<img src="<%= b.getTitleImg() %>">
						<p>
							No. <%= b.getBoardNo() %> / <%= b.getBoardTitle() %> <br>
							조회수 : <%= b.getCount() %>
						</p>
					</div>
				<% } %>
			<% } else {%>
				등록된 게시글이 없습니다.
			<% } %>

		</div>
	
		<script>
			$(function(){

				$(".thumbnail").click(function(){

					// 클릭될때마다 url요청 => location.href
					var bno = $(this).children().eq(0).val();

					location.href = "<%= contextPath %>/detail.th?bno=" + bno;
					// /jsp/detail.th?bno=?

				});
			});
		</script>

	
	</div>



</body>
</html>