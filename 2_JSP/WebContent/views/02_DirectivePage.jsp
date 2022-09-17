<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.ArrayList, java.util.Date" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>page 지시어</title>
</head>
<body>
	<h1>page 지시어</h1>
	
	<%
		// ArrayList 사용
		
		ArrayList<String> list = new ArrayList<>();
	
		list.add("Servlet");
		list.add("JSP");
	
		Date today = new Date();
	%>
	
	<p>
		리스트 길이 : <%= list.size() %> <br>
		리스트가 텅 비었나?? : <%= list.isEmpty() %> <br>
		0번 인덱스에 들어있는 요소 : <%= list.get(0) %> <br>
		1번 인덱스에 들어있는 요소 : <%= list.get(1) %> <br>
		
		<br>
		
		현재 날짜 및 시간 : <%= today %>
		<%-- <%= list.get(10) %> --%>
	</p>

</body>
</html>