<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.kh.notice.model.vo.Notice" %>
<%
	Notice n = (Notice)request.getAttribute("n");
%>
<!DOCTYPE html>
<html>
<head>
	<meta charset="UTF-8">
	<title>나는 공지사항 수정이야</title>
	<style>
		.outer{
			background-color: rgb(28, 141, 127);
			color: white;
			width: 1000px;
			height: 800px;
			margin: auto;
			margin-top: 50px;
		}
		#update-form>table{ border: 1px solid white;}
		#update-form input, #update-form textarea{
			width: 100%;
			box-sizing: border-box;
		}

	</style>
</head>
<body>

	<%@include file="../common/menubar.jsp" %>
	
    <div class="outer">
	
		<br>
		<h2 align="center">공지사항 상세보기</h2>
		<br><br>
		<form action="<%=contextPath%>/update.no" method="post" id="update-form">
		
			<input type="hidden" name="nno" value="<%= n.getNoticeNo() %>">
	
			<table align="center">
				<tr>
					<th width="50">제목</th>
					<td width="380"> <input type="text" value="<%= n.getNoticeTitle() %>" name="title" required></td>
				</tr>
				<tr>
					<th>내용</th>
					<td></td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea name="content" rows="10" required><%= n.getNoticeContent() %></textarea>
					</td>
				</tr>
			</table>
			<br><br>
			
			<div align="center">
				<button type="submit">수정하기</button>
				<button type="button" onclick="history.back();">뒤로가기</button>
			</div>
		</form>
	
	</div>

</body>
</html>