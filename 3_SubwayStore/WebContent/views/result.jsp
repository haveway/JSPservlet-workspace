<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%> <!-- page 지시어 -->
<%
	// 스크립틀릿 : 자바코드를 그대로 작성 (세미콜론을 포함한 완전한 형태로 작성)
	
	// 수하물 내리기 => request의 Attribute영역에서 내리기
	// request.getAttribute("키값"); => Object타입(모든 형태의 부모)
	
	String sandwich = (String)request.getAttribute("sandwich");
	String[] toppings = (String[])request.getAttribute("toppings");
	String[] cookies = (String[])request.getAttribute("cookies");
	String payment = (String)request.getAttribute("payment");
	int price = (int)request.getAttribute("price");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문내역</title>
</head>
<body>
	<h1>주문 내역</h1>
	
	<!-- = : 출력식,표현식 => jsp화면에 뿌려주는 역할 => 내가 출력할 변수명, 메소드명(호출) => 세미콜론은 X -->
	샌드위치 : <%= sandwich %><br>
	
	토핑:
	<% if(toppings == null) { %>
		선택 안함
	<% } else { %>
		<!-- String.join(연결자, 배열명) -->
		<%= String.join(",", toppings) %>
	<% } %>
	
	쿠키 :
	<% if(cookies == null) { %>
		선택 안함
	<%} else { %>
		<%= String.join(",", cookies) %>
	<% } %>
	
	결제 방식 : <%= payment %> <br><br>

	위와 같이 주문하시겠습니까? <br>

	총 가격 : <%= price %> 원

</body>
</html>