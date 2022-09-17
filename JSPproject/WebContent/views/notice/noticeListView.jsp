<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "java.util.ArrayList, com.kh.notice.model.vo.Notice" %>
<%
	ArrayList<Notice> list = (ArrayList<Notice>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>나는 공지사항이야 ~ </title>
<style>
	.outer{
        background-color: rgb(28, 141, 127);
        color: white;
        width: 1000px;
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

	<%@ include file="../common/menubar.jsp" %>
	
	
	<div class="outer">

        <br>
        <h2 align="center">공지사항!</h2>
        <br>


        <!--관리자만 글작성 버튼이 보이게끔-->
        <% if(loginUser != null && loginUser.getUserId().equals("admin")) {%>
        <!-- 로그인이 되어있고 그리고 관리자일 경우 -->
        
	        <div align="right" style="width:850px;">
	        
	        	<!-- 
	            <button onclick="location.href='이동할페이지';">글작성</button>
	            	버튼에 href속성이 없기 때문에 버튼을 눌러서 페이지를 이동시키려면 onclick="location.href='요청url'"
	             -->
	             
	             <!-- a태그를 쓰고도 버튼 모양을 만들고 싶다면? : 부트스트랩 활용 -->
	             <a href="<%=contextPath %>/enrollForm.no" class="btn btn-info btn-sm">글작성</a>
	            <br><br>
	            
	        </div>
        
        <% } %>

        <table class="list-area" align="center">
            <thead>
                <tr>
                    <th>글번호</th>
                    <th width="400">글제목</th>
                    <th width="100">작성자</th>
                    <th>조회수</th>
                    <th width="100">작성일</th>
                </tr>
            </thead>
            <tbody>
                <!-- 리스트가 비어있는가 아닌가 -->

                <% if(list.isEmpty()) { %>
                    <!-- 공지사항이 존재하지 않을 경우-->
                    <tr>
                        <td colspan="5">공지사항이 존재하지 않습니다.</td>
                    </tr>
                <% } else { %>
                    <!-- 공지사항이 존재할 경우-->
                    <% for(Notice n : list) { %>
                        <tr>
                            <td><%= n.getNoticeNo() %></td>
                            <td><%= n.getNoticeTitle() %></td>
                            <td><%= n.getNoticeWriter() %></td>
                            <td><%= n.getCount() %></td>
                            <td><%= n.getCreateDate() %></td>
                        </tr>
                    <% } %>
                <% } %>
            </tbody>
        </table>


        <br><br><br>

    </div>

    <script>

        $(function(){

            $(".list-area>tbody>tr").click(function(){

                //console.log("클릭~");

                // 클릭했을 때 해당 공지사항의 번호를 넘겨야함
                // 해당 tr태그의 자손 중에서도 첫번째 td태그의 값만 필요함
                var nno = $(this).children().eq(0).text();

                //console.log(nno);

                // 글번호를 이용한 요청
                // => 대놓고 요청 => url에 키와 밸류를 대놓고 작성해서 요청을 보내버리겠다.
                // => GET방식 : 요청할url?키=밸류&키&밸류&키=밸류....
                // "쿼리 스트링" : ? 뒤의 내용들, 직접 쿼리스트링을 만들어서 요청
                // localhost:8001/jsp/detail.no?nno=글번호

                location.href="<%= contextPath %>/detail.no?nno=" + nno;
            })

        })

    </script>
</body>
</html>