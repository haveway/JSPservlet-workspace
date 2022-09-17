<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import = "com.kh.board.model.vo.*" %>
<%
	Board b = (Board)request.getAttribute("b");
	// 게시글번호, 카테고리명, 제목, 내용, 작성자 아이디, 작성일

	Attachment at = (Attachment)request.getAttribute("at");
	// 파일번호, 원본명, 수정명, 저장경로
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글 상세보기</title>
<style>
    .outer{
        background-color: rgb(28, 141, 127);
        color: white;
        width: 1000px;
		height: 800px;
        margin: auto;
        margin-top: 50px;
    }
    #detail-area{
        border : 1px solid white;
    }

</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<h2 align="center">일반게시판 상세보기</h2>
		<br>
	
		<table id="detail-area" align="center" border="1">

			<tr>
				<th width="70">카테고리</th>
				<td width="70"><%= b.getCategory() %></td>
				<th width="70">제목</th>
				<td width="370"><%= b.getBoardTitle() %></td>
			</tr>
			<tr>
				<th>작성자</th>
				<td><%= b.getBoardWriter() %></td>
				<th>작성일</th>
				<td><%= b.getCreateDate() %></td>
			</tr>
			<tr>
				<th>내용</th>
				<td colspan="3">
					<p style="height: 200px;"><%= b.getBoardContent() %></p>
				</td>
			</tr>
			<tr>
				<th>첨부파일</th>
				<td colspan="3">
					<!-- 첨부파일이 없을수도있다.-->
					<% if(at == null) { %>
						첨부파일이 없습니다.
					<% } else { %>
					<!-- 첨부파일이 있을수도있다.-->
						<a download="<%= at.getOriginName() %>" href="<%= contextPath %>/<%= at.getFilePath()%>/<%=at.getChangeName()%>"><%= at.getOriginName() %></a>
					<% } %>
				</td>
			</tr>
		</table>

		<br>


		<div align="center">
			<a href="<%= contextPath %>/list.bo?cpage=1" class="btn btn-sm btn-secondary">목록가기</a>

			<!-- 로그인한 사용자고 그리고 현재 이 게시글의 작성자일 경우에만 수정하기 삭제하기 보여지게끔해보자-->
			<% if(loginUser != null && b.getBoardWriter().equals(loginUser.getUserId())) { %>
				<a href="<%= contextPath %>/updateForm.bo?bno=<%= b.getBoardNo() %>" class="btn btn-sm btn-warning">수정하기</a>
				<!--<a href="<%= contextPath %>/delete.bo" class="btn btn-sm btn-danger">삭제하기</a>-->
				<button data-toggle="modal" data-target="#myModal" class="btn btn-sm btn-danger">삭제하기</button>
			<% } %>
		</div>

		<br>

		<!-- 댓글창 !! 일단화면만-->
		<div id="reply-area">
			<table border="1" align="center">
				<thead>
					<% if(loginUser != null) { %>
						<!--로그인이 O-->
						<tr>
							<th>댓글작성</th>
							<td>
								<textarea id="replyContent" cols="50" rows="3" style="resize:none;"></textarea>
							</td>
							<td><button onclick="insertReply();">댓글등록</button></td>
						</tr>
					<% } else { %>
						<!-- 로그인이 X-->
						<tr>
							<th>댓글작성</th>
							<td>
								<textarea rows="3" cols="50" style="resize:none;" readonly>로그인 후 이용가능한 서비스입니다.</textarea>
							</td>
							<td><button>댓글등록</button></td>
						</tr>
					<% } %>
				</thead>
				<tbody>
					
				</tbody>
			</table>
			<br>

		</div>
		<script>

			function selectReplyList(){

				$.ajax({
					url : "rlist.bo",
					data : {bno : <%= b.getBoardNo() %>},
					success : function(list){
						
						// 댓글갯수만큼 반복 => 누적(문자열)
						var result = "";
						for(var i in list){
							result += "<tr>"
									+ "<td>" + list[i].replyWriter + "</td>"
									+ "<td>" + list[i].replyContent + "</td>"
									+ "<td>" + list[i].createDate + "</td>"
									+ "</tr>";
						}
						
						$("#reply-area tbody").html(result);

					},
					error : function(){
						console.log("댓글 읽어오기 실패  ~");
					}
				});

			}
			// 댓글은 화면이 로딩되었을 때 곧바로 뿌려줘야함 => window.onload => $(function(){})
			$(function(){
				selectReplyList();
				
				setInterval(selectReplyList, 1000);
			});
			
			
			function insertReply(){
				
				$.ajax({
					url : "rinsert.bo",
					data : {
						bno : <%= b.getBoardNo() %>,
						content : $("#replyContent").val()
					},
					type : "post",
					success : function(result){
						
						// result값에 따라서 성공했으면 성공메시지 띄우기
						if(result > 0){
							
							alert("댓글작성에 성공하셨습니다!");
							
							selectReplyList();
							
							$('#replyContent').val("");
						}
						
					},
					error : function(){
						console.log("댓글 작성 실패!");
					}
				});
				
			}

		</script>
	</div>

	


	<!-- The Modal -->
	<div class="modal" id="myModal">
		<div class="modal-dialog">
			<div class="modal-content">

			<!-- Modal Header -->
			<div class="modal-header">
				<h4 class="modal-title">삭제하시겠습니까?</h4>
				<button type="button" class="close" data-dismiss="modal">&times;</button>
			</div>

			<!-- Modal footer -->
			<div class="modal-footer">
				<a href="<%= contextPath %>/delete.bo?bno=<%=b.getBoardNo()%>" class="btn btn-sm btn-danger">삭제하기</a>
				<button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">취소</button>
			</div>

			</div>
		</div>
	</div>


	
</body>
</html>