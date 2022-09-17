<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글작성</title>
<style>
	.outer{
		background-color: rgb(28, 141, 127);
		color: white;
		width: 1000px;
		height: 1000px;
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
		<h2 align="center">사진게시판 작성하기</h2>
		<br>

		<form id="enroll-form" enctype="multipart/form-data" action="<%= contextPath %>/insert.th" method="post">
		
		
			<input type="hidden" name="userNo" value="<%= loginUser.getUserNo() %>">

			<table align="center" border="1">
				<tr>
					<th width="100">제목</th>
					<td colspan="3"><input type="text" name="title" required></td>
				</tr>
				<tr>
					<th>내용</th>
					<td colspan="3">
						<textarea name="content" rows="10" required style="resize:none;"></textarea>
					</td>
				</tr>
				<!-- 미리보기 영역 -->
				<tr>
					<th>대표이미지</th>
					<td colspan="3" align="center">
						<img id="titleImg" width="250" height="170">
					</td>
				</tr>
				<tr>
					<th>상세이미지</th>
					<td><img width="150" height="120" id="contentImg1"></td>
					<td><img width="150" height="120" id="contentImg2"></td>
					<td><img width="150" height="120" id="contentImg3"></td>
				</tr>
			</table>
			<br>

			<div id="file-area">
				<input type="file" name="file1" id="file1" required onchange="loadImg(this, 1);"> <!-- 대표 이미지 : 필수 입력 -->
				<input type="file" name="file2" id="file2" onchange="loadImg(this, 2);">
				<input type="file" name="file3" id="file3" onchange="loadImg(this, 3);">
				<input type="file" name="file4" id="file4" onchange="loadImg(this, 4);">
				<!-- onchange : input태그의 내용물이 변경되었을 때 발생하는 이벤트속성 -->
				<!-- loadImg() => 우리가 만들 함수-->
			</div>

			<script>

				$(function(){
					$("#file-area").hide();

					$("#titleImg").click(function(){
						$("#file1").click();
					});

					$("#contentImg1").click(function(){
						$("#file2").click();
					});
					$("#contentImg2").click(function(){
						$("#file3").click();
					});
					$("#contentImg3").click(function(){
						$("#file4").click();
					});
				});

				function loadImg(inputFile, num){
					

					// inputFile : 현재 변화가 생긴 <input type="file"> 요소객체
					// num : 몇번째 input요소 인지 확인 후 그 후 해당영역에 미리보기를 하기위해서 받아주는 변수

					//console.log(inputFile.files.length);
					// inputFile.files.length : 파일 선택 1, 파일 선택 취소 시 0
					// => 파일의 존재 유무를 알 수 있다. inputFile.files[0]에 선택된 파일이 담겨있음

					//files속성 : 업로드된 파일의 정보들을 배열형식으로 여러개 묶어서 반환 length는 배열의 크기

					if(inputFile.files.length == 1){ // 파일이 있냐??

						// 선택된 파일을 읽여들여서 그 영역에 맞는 미리보기

						// 파일을 읽어들일 FileReader 객체 생성
						var reader = new FileReader();

						// FileReader객체로부터 파일을 읽어들이는 메소드를 호출
						// 어느 파일을 읽을건데?? 인자값으러 전달해줌
						reader.readAsDataURL(inputFile.files[0]);
						// 해당 파일읅 읽어들이는 순간 그 파일만의 고유한 겁나긴 url이 부여됨
						// => 해당 url을 src속성으로 부여할것(attr)

						// 파읽 읽기가 완료되었을때 실행할 함수를 정의
						reader.onload = function(e){
							// e의 target => e.target => 이벤트 당한놈

							// e의 target.result에 각 파일의 url이 담긴.

							// 각 영역에 맞춰서 이미지 미리보기
							switch(num){
								case 1 : $("#titleImg").attr("src", e.target.result); break;
								case 2 : $("#contentImg1").attr("src", e.target.result); break;
								case 3 : $("#contentImg2").attr("src", e.target.result); break;
								case 4 : $("#contentImg3").attr("src", e.target.result); break;
							};
						};

					}
					else{
						switch(num){
								case 1 : $("#titleImg").attr("src", "https://cnucc.jbnu.ac.kr/korean/system/concert/image/noimage02.gif"); break;
								case 2 : $("#contentImg1").attr("src", "https://cnucc.jbnu.ac.kr/korean/system/concert/image/noimage02.gif"); break;
								case 3 : $("#contentImg2").attr("src", "https://cnucc.jbnu.ac.kr/korean/system/concert/image/noimage02.gif"); break;
								case 4 : $("#contentImg3").attr("src", "https://cnucc.jbnu.ac.kr/korean/system/concert/image/noimage02.gif"); break;
							};
					}



				}
			</script>










			<br>
			<div align="center">
				<button type="submit">작성하기</button>
				<button type="reset">취소하기</button>
			</div>
		</form>
	
	</div>

</body>
</html>