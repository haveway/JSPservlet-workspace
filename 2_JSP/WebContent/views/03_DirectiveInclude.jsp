<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>include 지시어</title>
</head>
<body>
	
	<h1>include지시어</h1>
	<h2>01_ScriptingElement.jsp파일 include하기</h2>
	<div style="border: 1px solid red">
	
		<%@ include file="01_ScriptingElement.jsp" %>
	</div>

	<hr>
	<h4>1부터 100까지의 총 합계 : <%= sum %></h4>
	특징 : 포함한 JSP상에 있는 모든 화면 구성요소들이 뜸 <br>
		 포함한 JSP상에 있는 모든 변수들을 가져다 쓸 수 있음<br>
		 
	<br>
	그러면 sum이라는 변수를 선언할 수 있을까?
	<%
		//int sum = 0; 중복된 변수명 사용불가!!
	%>
	
	
	<h2>오늘날짜</h2>
	<%@ include file="datePrint.jsp" %>

</body>
</html>