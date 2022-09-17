<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList, com.kh.model.vo.Order" %>
<%
	//.getAttribute("키값");  => : OBJECT
	String alertMsg = (String)request.getAttribute("alertMsg");
	ArrayList<Order> list = (ArrayList<Order>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>주문내역뽑기</title>
</head>
<body>
	
	<h3>샌드위치 주문내역 확인</h3>
	
	
	<table border="1">
		<tr>
			<th>주문자명</th>
			<th>핸드폰번호</th>
			<th>주소</th>
			<th>요청사항</th>
			<th>샌드위치</th>
			<th>토핑</th>
			<th>쿠키</th>
			<th>결제수단</th>
			<th>총금액</th>
			<th>주문일자</th>
		</tr>
	
		
		<% if(list.isEmpty()) { %>
		<!-- 조회결과가 없을 때 -->
			<tr>
				<td colspan="10"><%= alertMsg %></td>
			</tr>
		<% } else { %>
		<!-- 조회결과가 있을 때 -->
			<!-- 리스트를 반복을 돌리면서 요소들에 순차적으로 접근해서 표에 찍어주기 -->
			
			<% for(Order o : list) { %>
				<tr>
					<td><%= o.getUserName() %></td>
					<td><%= o.getPhone() %></td>
					<td><%= o.getAddress() %></td>
					<td><%= o.getMessage() %></td>
					<td><%= o.getSandwich() %></td>
					<td><%= o.getTopping() %></td>
					<td><%= o.getCookie() %></td>
					<td><%= o.getPayment() %></td>
					<td><%= o.getPrice() %></td>
					<td><%= o.getOrderDate() %></td>
				</tr>
			<% } %>
		<% } %>
		
	
	</table>


</body>
</html>