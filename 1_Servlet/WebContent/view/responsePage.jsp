<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<% 
	// 이 구문을 스크립틀릿(scriptlet)이라고 해서 HTML내에 자바 코드를 쓸 수 있는 영역
	// 현재 이 jsp파일에서 필요로 하는 데이터들 => request객체의  attribute영역에 담겨있음
	// request.getAttribute("키값") : Object
	
	String name = (String)request.getAttribute("username");
	int age = (int)request.getAttribute("age");
	String gender = (String)request.getAttribute("gender");
	String city = (String)request.getAttribute("city");
	double height = (double)request.getAttribute("height");
	String[] foods = (String[])request.getAttribute("foods");
	
	String msg = (String)request.getAttribute("msg");

%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>개인정보 응답화면</title>
<style>
    h2 { color : rgb(184, 207, 255);}
    span{font-weight: bold;}
    #name{color:gold;}
    #age{color:rgb(250, 58, 58);}
    #city{color:aqua;}
    #height{color:tomato;}
    #gender{color:aquamarine;}
    li{color:indianred;}
</style>
</head>
<body>

    <h2>개인정보 응답화면</h2>

    <span id="name"> <%= name %></span> 님은
    <span id="age"> <%= age %></span> 살이며
    <span id="city"> <%= city %></span> 에 삽니다.
        키는 <span id="height"> <%= height %></span> cm이고<br>

	성별은
	<% if(gender == null) { %> <!-- 성별을 선택안했을 경우-->
	    <span id="gender">선택을 안했습니다.</span><br>
    <% } else { %>
		<% if(gender.equals("M")) { %> <!-- 남자일 경우 -->
    		<span id="gender">남자</span>입니다.<br>
		<% } else { %> <!-- 여자일 경우-->
    		<span id="gender">여자</span>입니다.<br>
    	<% } %>
	<% } %>

    좋아하는 음식은
	<% if(foods == null) {%><!-- 체크를 안했을 경우-->
		없습니다. <br>
    <% } else { %> <!-- 체크를 했을 경우-->
    	<ul>
			<% for(int i = 0; i < foods.length; i++) {%>
				<li><%= foods[i] %></li>
			<% } %>    	
    	</ul>
	<% } %>
	
	<div>요청 결과 : <%= msg %></div>
	
	dsjifosdfjsidofdisof

</body>
</html>