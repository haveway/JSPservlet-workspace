<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>회원가입을 해랏 ~ ~ !</title>
<style>
	.outer{

		background-color: rgb(28, 141, 127);
		color: white;
		width: 1000px;
		margin: auto;
		margin-top: 30px;
	}

	#enroll-form table {margin : auto;}
	#enroll-form input {margin : 5px;}

</style>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	
	<div class="outer">
		<br>
		<form id="enroll-form" action="<%= contextPath %>/insert.me" method="post">

			<!-- 아이디, 비밀번호, 이름, 전화번호, 이메일주소, 주소, 취미-->
			<table>
				<tr>
					<td>* 아이디</td>
					<td><input type="text" required maxlength="12" name="userId"></td>
					<td><button type="button" onclick="idCheck();">중복확인</button></td>
					<!-- Ajax기술을 배울때 구현할 것-->
				</tr>
				<tr>
					<td>* 비밀번호</td>
					<td><input type="password" name="userPwd" maxlength="15" required></td>
					<td></td>
				</tr>
				<tr>
					<td>* 비밀번호확인</td>
					<td><input type="password" maxlength="15" required></td>
					<td></td>
				</tr>
				<tr>
					<td>* 이름</td>
					<td><input type="text" name="userName" maxlength="6" required></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;전화번호</td>
					<td><input type="text" name="phone" placeholder="- 포함해서 입력"></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;이메일</td>
					<td><input type="email" name="email"></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;주소</td>
					<td><input type="text" name="address"></td>
					<td></td>
				</tr>
				<tr>
					<td>&nbsp;&nbsp;관심분야</td>
					<td colspan="2">
						<input type="checkbox" name="interest" value="운동" id="sports"><label for="sports">운동</label>
						<input type="checkbox" name="interest" value="노래" id="song"><label for="song">노래</label>
						<input type="checkbox" name="interest" value="여행" id="travel"><label for="travel">여행</label>
						<br>
						<input type="checkbox" name="interest" value="게임" id="game"><label for="game">게임</label>
						<input type="checkbox" name="interest" value="영화" id="movie"><label for="movie">영화</label>
						<input type="checkbox" name="interest" value="악기" id="mi"><label for="mi">악기</label>
					</td>
				</tr>
			</table>

			<br><br>

			<div align="center">
				<button type="submit" disabled>회원가입</button>
				<button type="reset">초기화</button>
			</div>

		</form>
	</div>
	<script>
	
		function idCheck(){
			
			// 아이디 인풋 태그로부터 값을 뽑아와야함 => 인풋요소 자체를 먼저 뽑자
			var $userId = $("#enroll-form input[name=userId]");
			// name이 userId인 요소가 menubar.jsp에도 있기 때문에 조금 더 디테일하게 선택해야함
			
			// ajax로 컨트롤러로 요청하기
			$.ajax({
				url : "idCheck.me",
				data : {checkId : $userId.val()},
				success : function(result){
					// result 경우의 수 : "NNNNN", "NNNNY"
					// 문자열 동등비교로 따지기
					if(result == "NNNNN"){ // 중복된 아이디 == 사용불가
						alert("이미 존재하거나 탈퇴한 회원의 아이디입니다.")
						// 재입력
						$userId.focus();
					}
					else{ // 중복 X == 사용가능
						
						// 알림창 => window.confirm()
						if(confirm("사용가능한 아이디입니다. 사용하시겠습니까?")){ // 사용하겠다는 대답을 받은 경우
							
							// 중복확인전에 막아두었던 submit버튼을 해제해주자
							$("#enroll-form button[type=submit]").removeAttr("disabled");
							// 해당 구문이 실행되면 submit버튼이 활성화
							
							// 아이디 값은 이후에 변경이 불가능하도록 => readonly
							$userId.attr("readonly", true);
						} 
						else{ // 사용 안함 선택 => 다시 입력
							$userId.focus();
						}
						
					}
				},
				error : function(){
					console.log("아이디 중복체크용 비동기요청 실패");					
				}
			});
			
		}
	
	
	
	</script>

</body>
</html>