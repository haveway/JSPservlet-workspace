<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, com.kh.board.model.vo.Board, com.kh.common.model.vo.PageInfo" %>
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");

	// 페이징바 만들 때 필요한 변수 미리 셋팅
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>일반 게시판</title>
<style>
	.outer{
        background-color: rgb(28, 141, 127);
        color: white;
        width: 1000px;
		height: 600px;
        margin: auto;
        margin-top: 30px;
    }

    .list-area{
        border : 1px solid white;
        text-align: center;
    }

    .list-area>tbody>tr:hover{
        cursor: pointer;
        background-color: rgb(13, 66, 60);
    }
</style>
</head>
<body>

	<%@include file="../common/menubar.jsp" %>
	
	<div class="outer">
	
		<br>
		<h2 align="center">일반게시판</h2>
		<br>	

		<div align="right" style="width:850px;">
			<!-- 로그인한 회원만 보여지는 버튼 : loginUser가 null인지 아닌지 판단 -->
			<% if(loginUser != null) { %>
				<a href="<%= contextPath %>/enrollForm.bo" class="btn btn-info btn-sm">글작성</a>
				<br><br>
			<% } %>
		</div>

		<table class="list-area" align="center">
			<thead>
				<tr>
					<th width="70">글번호</th>
					<th width="70">카테고리</th>
					<th width="300">제목</th>
					<th width="100">작성자</th>
					<th width="50">조회수</th>
					<th width="100">작성일</th>
				</tr>
			</thead>
			<tbody>
				
				<!-- 게시글 출력 : 게시글이 있는지 없는지 => isEmpty() 이용해서 없는 경우 먼저 조건 부여 -->
				
				<% if(list.isEmpty()) { %> <!-- 조회글 없음 -->
					<tr>
						<td colspan="6">조회된 게시글이 없습니다.</td>					
					</tr>
				<% } else {%>
					<!-- 반복 : list에 있는 값을 순차적으로 접근해서 뽑아오기 -->
					<% for(Board b : list) { %>
						<tr>
							<td><%= b.getBoardNo() %></td>						
							<td><%= b.getCategory() %></td>						
							<td><%= b.getBoardTitle() %></td>						
							<td><%= b.getBoardWriter() %></td>						
							<td><%= b.getCount() %></td>						
							<td><%= b.getCreateDate() %></td>						
						</tr>
					<% } %>
				<% } %>
			</tbody>
		</table>

		<script>

			$(function(){
	
				$(".list-area>tbody>tr").click(function(){

					var bno = $(this).children().eq(0).text();

					location.href="<%= contextPath %>/detail.bo?bno=" + bno;
				})
	
			})
	
		</script>

		<br><br>

		<div align="center" class="paging-area">

			<% if(currentPage != 1) {%> <!-- 페이징바에서 <를 담당 -->
				<button class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/list.bo?cpage=<%= currentPage - 1 %>'"> &lt; </button>
			<% } %>

			<% for(int i = startPage; i <= endPage; i++) { %>
				<% if(i != currentPage) {%>
					<button class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/list.bo?cpage=<%= i %>'"><%= i %></button>
				<% } else { %>
					<button class="btn btn-sm btn-primary" disabled><%= i %></button>
				<% } %>
			<% } %>

			<% if(currentPage != maxPage) {%>
				<button class="btn btn-sm btn-info" onclick="location.href='<%= contextPath %>/list.bo?cpage=<%= currentPage + 1 %>'"> &gt; </button>
			<% } %>
			
		</div>



	</div>

</body>
</html>