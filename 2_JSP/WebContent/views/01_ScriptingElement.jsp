<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.Arrays" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>스크립팅 요소</title>
</head>
<body>
	<h1>스크립팅 요소</h1>
	
	<%--
		<%= sum %> 
		스크립틀릿 내의 자바코드의 실행순서는 순수 자바코드와 마찬가지로 위에서 아래순서대로 흐른다.
		즉, 먼저 선언을 하고나서 출력식을 통해 출력할 수 있다.
	 --%>
	 
	<!-- HTML주석 : 개발자도구탭에 노출 -->
	<%-- JSP주석 : 개발자도구탭에 노출 안됨 --%>
	
	<%
		// 스크립틀릿 : 이 안에 일반적인 자바코드를 작성(변수 선언, 초기화, 제어문 등)
		
		// 1 ~ 100까지의 누적합 구하기
		
		int sum = 0;
	
		for(int i = 1; i <= 100; i++){
			sum += i;
		}
	
		//System.out.println("sum : " + sum);
	%>
	
	<p>
		웹 화면에서 출력하고자 한다면? <br>
		스크립틀릿으로 출력 : <% out.println(sum); %><br>
		표현식(출력식)으로 출력 : <%= sum %> <br>
	</p>
	<%
		// 배열 사용
		String[] names = {"아버지","을지문덕","세종대왕"};
	%>
	
	<h5>배열의 길이<%= names.length %></h5>
	
	<%= Arrays.toString(names) %>
	
	<h4>반복문을 통해 html요소를 반복적으로 화면에 출력!!!</h4>
	
	<ul>
		<% for(int i = 0; i < names.length; i++) { %>
			<li><%= names[i] %></li>
		<% } %>
	
		<% for(String s:names) {%>
			<li><%= s %></li>
		<%} %>
	</ul>
	
	<%! 
		public void test(){ // 현재 이 JSP에서만 사용가능
		
		}
	%>
	
	<% test(); %>


</body>
</html>