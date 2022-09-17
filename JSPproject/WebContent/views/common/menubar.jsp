<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.member.model.vo.Member" %>
<%
	Member loginUser = (Member)session.getAttribute("loginUser");
	// 로그인 전 : menubar.jsp가 로딩될 때 null
	// 로그인 후 : menubar.jsp가 로딩될 때 로그인한 회원의 정보가 담겨있음
	
	// 성공 / 경고메시지 뽑기
	String alertMsg = (String)session.getAttribute("alertMsg");
	// 서비스 요청 전 : alertMsg = null
	// 서비스 요청 후 성공 시 : alertMsg = 메시지문구
	
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나는 메뉴바다</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/css/bootstrap.min.css">
<script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.1/dist/js/bootstrap.bundle.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
<style>
	#login-form, #user-info { float:right; }

	#user-info a{
		text-decoration : none;
		color : black;
		font-size : 12px;
	}

    .nav-area{background-color: darkcyan;}
    .menu{
        display: table-cell;
        height: 50px;
        width: 150px;
    }
    .menu a{
        text-decoration: none;
        color:white;
        font-size:20px;
        font-weight: bold;
        display: block;
        width: 100%;
        height: 100%;
        line-height: 50px;
    }
    .menu a:hover{background-color: rgb(0, 82, 82);}

</style>
</head>
<body>
	<script>
		// script태그 내에서도 스크립틀릿과 같은 jsp요소가 사용 가능
		
		var msg = "<%= alertMsg %>"; // '메세지문구' / null
		
		if(msg != "null"){
			alert(msg);
			
			// session에 들어있는 alertMsg키값에 대한 밸류를 지워줘야함!!!!
			// 왜냐? menubar.jsp가 로딩될때마다 매번 alert이 뜸 화남
			// => XX.removeAttribute("키값");
			<% session.removeAttribute("alertMsg"); %>
		}
	</script>


	<h1 align="center">자~ 드가자</h1>
	
	<div class="login-area">
		
		<% if(loginUser == null) { %>
			<!-- 로그인 전에 보여지는 로그인 form -->
													<!-- localhost:8001/jsp/login.me -->
			<form id="login-form" method="post" action="<%= contextPath %>/login.me">
				<table>
					<tr>
						<th>아이디 </th>
						<td><input type="text" name="userId" required></td>
					</tr>
					<tr>
						<th>비밀번호 </th>
						<td><input type="password" name="userPwd" required></td>
					</tr>
					<tr>
						<th colspan="2">
							<button type="submit">로그인</button>
							<button type="button" onclick="enrollPage();">회원가입</button>
						</th>
					</tr>		
				</table>
			</form>	
			
			
			<script>
				function enrollPage(){
					
					// 페이지 이동
					// location.href = "<%= contextPath %>/views/member/memberEnrollForm.jsp";
					// 웹 애플리케이션의 디렉토리 구조가 url에 노출된다 => 보안에 취약
					// localhost:8001/jsp/views/member/memberEnrollForm.jsp
					
					// 단순한 정적인 페이지 요청이라고 하더라도 반드시 Servlet을 거쳐서 화면을 띄워주자!!!
					// => url에 서블릿 맵핑값만 노출되게끔 하겠다.
					// localhost:8001/jsp/맵핑값
					
					location.href = "<%= contextPath %>/enrollForm.me";
				}			
			</script>
			
			
			
		<% } else {%>
			<!-- 로그인 성공 후 화면 -->
			<div id="user-info">
				<b><%= loginUser.getUserName() %></b>님 환영합니다.<br><br>
				<div align="center">
					<a href="<%= contextPath %>/myPage.me">마이페이지</a>
					<a href="<%= contextPath %>/logout.me">로그아웃</a>
				</div>
			</div>
		<% } %>
	
	</div>

    <br clear="both"><br>

    <div class="nav-area" align="center">
        <div class="menu"><a href="<%= contextPath %>">HOME</a></div>
        <div class="menu"><a href="<%= contextPath %>/list.no">공지사항</a></div>
        <div class="menu"><a href="<%= contextPath %>/list.bo?cpage=1">일반게시판</a></div>
        <div class="menu"><a href="<%= contextPath %>/list.th">사진게시판</a></div>
    </div>





</body>
</html>