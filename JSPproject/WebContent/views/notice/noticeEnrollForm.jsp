<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 작성</title>
<style>
		.outer{
        background-color: rgb(28, 141, 127);
        color: white;
        width: 1000px;
		height: 800px;
        margin: auto;
        margin-top: 50px;
    }
	#enroll-form>table{ border: 1px solid white;}
	#enroll-form input, #enroll-form textarea{
		width: 100%;
		box-sizing: border-box;
	}



</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
	
		<br>
		<h2 align="center">공지사항 작성하기!!</h2>
		<br><br>
	
		<form action="<%= contextPath %>/insert.no" id="enroll-form" method="post">
		
		
			<input type="hidden" name="userNo" value="<%= loginUser.getUserNo() %>">

			<table align="center">
				<tr>
					<th width="50">제목</th>
					<td width="380"><input type="text" required name="title"></td>
				</tr>
				<tr>
					<th>내용</th>
					<td></td>
				</tr>
				<tr>
					<td colspan="2">
						<textarea name="content" rows="10" style="resize:none" required></textarea>
					</td>
				</tr>
			</table>
			<br><br>

			
			<div align="center">
				<button type="submit" class="btn btn-sm btn-primary">등록하기</button>
				<button type="button" onclick="history.back();" class="btn btn-sm btn-secondary">뒤로가기</button>
				<!-- history.back() 이전페이지로 돌아가게 해주는 함수 -->
			</div>


		</form>
	
	</div>

</body>
</html>