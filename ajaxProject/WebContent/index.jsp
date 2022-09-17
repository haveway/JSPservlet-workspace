<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>AJAX ~</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
</head>
<body>

	<h1>AJAX개요</h1>
	
	
	<p>
		Asynchronous Javascript And XML의 약자<br>
		서버로부터 데이터를 가져와 전체 페이지를 새로고침하지 않고 일부만 로드해 내용물만 바꿀 수 있는 기법 <br><br>
		
		우리가 그동안 기존에 a태그를이용해서 요청 또는 form태그를 이요한 요청 방식은 동기식 요청이었음 <br>
		=> 응답페이지가 돌아와야 그 결과를 볼 수 있었다(== 페이지가 한번 깜빡거린다) <br><br>
		
		비동기식 요청을 보내기 위해서는 AJAX라는 기술이 필요함 <br><br>
		
		
		* 동기식 / 비동기식 요청 차이 <br>
		- 동기식 : 요청 처리 후 그에 해당하는 응답 페이지가 돌아와야만 그 다음 작업이 가능<br>
		        만약 서버에서 응답페이지를 돌려주는 시간이 지연되면 무작정 기다려야함<br>
		     전체 페이지가 리로드됨(새로고침, 즉, 페이지가 기본적으로 한번 깜빡거리면서 넘어감) <br><br>
		
		- 비동기식 : 현재 페이지는 그대로 유지하면서 중간중간마다 추가적인 요청을 보내줄 수 있고 <br>
		          응답을 받는다고해서 다른 페이지로 넘어가지는 않음(현재 페이지 그대로 유지)<br>
		          요청을 보내놓고 그에 해당하는 응답이 돌아올때 까지 현재 페이지에서 다른 작업을 할 수 있음(페이지가 깜빡거리는 시간 X)<br> 
		예 ) NAVER아이디 중복체크 기능, 댓글, 실시간 검색, 검색어 자동완성 <br><br>
		
		* 비동기식 단점<br>
		- 현재 페이지에 지속적으로 리소스가 쌓임 => 페이지가 현저히 느려질 수 있음<br>
		- 페이지 내 복잡도가 기하급수적으로 증가 => 에러 발생 시 디버깅이 어려움<br>
		- 요청 후 응답데이터를 가지고 현재 페이지내에서 새로운 요소를 동적으로 만들어서 뿌려줘야함
		=> DOM요소를 새롭게 만드는 구문을 잘 익혀둬야함<br><br>
		
		* AJAX 구현방식 : JavaScript방식 / jQuery방식 <br>
		=> jQuery가 코드가 간결하고 사용하기 쉽다.<br><br>
	</p>
	
	<pre>
		* jQuery에서의 AJAX통신
		
		[ 표현법 ]
		$.ajax({
			속성명 : 속성값,	
			속성명 : 속성값,
			속성명 : 속성값,
			....
		});
		
		=> AJAX기술이 가능하게 하는 객체를 인자값으로 넘긴다고 생각하자.
		
		
		* 주요 속성
		- url : 요청할 url(필수로 작성) => form태그의 action속성
		- type/method : 요청 전송 방식(get /post 생략 시 기본값은 get) => form태그의 method속성
		- data : 요청 시 전달할 값 (*{키:밸류, 키:밸류, ...}*) => form태그 안에 input태그의 value값
		- success : ajax통신 성공 시 실행할 함수를 의미
		- error : ajax통신 실패 시 실행할 함수를 의미
		- complete : ajax통신 성공이든 실패든간에 무조건 끝나면 실행할 함수
	
		* 부가적인 속성
		- async : 서버와의 비동기 처리 방식 설정 여부(기본값 true)
		- contentType : request의 데이터 인코딩 방식 정의(보내는 측의 데이터 인코딩)
		- dataType : 서버의 response로 오는 데이터의 데이터 타입 설정, 값이 없으면 지가 알아서 판단함
					xml, json, script, html, text
		- accept : 파라미터의 타입을 설정
		- beforeSend : ajax요청을 하기 전 실행되는 이벤트
		- cache : 요청 및 결과값을 캐시에 저장하는것
		- context : ajax메소드 내 모든 영역에서 파싱을 어떻게 할 것인가
		- timeout : 서버 요청 시 응답 대기 시간
	</pre>
	
	<hr>
	
	
	<h1>jQuery방식을 이용한 AJAX테스트</h1>
	
	<h3>1. 버튼 클릭 시 get방식으로 서버에 데이터 전송 및 응답</h3>
	
	입력 : <input type="text" id="input1">
	<button id="btn1">전송</button>
	
	<br>
	
	응답 : <label id="output1">현재 응답 없음</label>
	
	<script>
		$(function(){
			
			$("#btn1").click(function(){

				// 동기식 요청 : location.href = "요청url?쿼리스트링"
						
				// 비동기식 요청
				$.ajax({
					url : "jqAjax1.do",				
					data : {input : $("#input1").val()},
					type : "get",
					success : function(result){
						$("#output1").text(result);
					},
					error : function(){
						alert("ajax통신실패!");						
					}, 
					complete : function(){
						alert("성공했는지 실패했는지 나는 몰라 ~ ");
					}
				});
				
			})
			
		})
	</script>
	
	<hr>
	
	<h3>2. 버튼 클릭 시 Post방식으로 서버에 데이터 전송 및 응답</h3>
	
	이름 : <input type="text" id="input2_1"><br>
	나이 : <input type="number" id="input2_2"><br>
	<button onclick="test2();">전송</button>
	
	<br>
	
	응답 : <label id = "output2">현재 응답 없음</label>
	
	<script>
		function test2(){
			
			$.ajax({
				url : "jqAjax2.do",
				data : {
					name : $("#input2_1").val(),
					age : $("#input2_2").val()
				},
				type : "post",
				success : function(result){
					//$("#output2").text(result);
					
					// JSONArray로 넘겼을경우
					//$("#output2").text("이름 : " + result[0] + ", 나이 : " + result[1]);
					//console.log(result);
					// 배열형태로 넘겼을 때 가공된것을 눈에 보여지게하는것 뷰단의 역할(언빡싱)
					
					// JSONObject로 넘겼을 경우
					// 자바스크립트 객체에 속성값에 접근하는 방법 두가지
					// 객체명.속성명, 객체명["속성명"]
					$("#output2").text("이름 : " + result.name + ", 나이 : " + result.age);
					console.log(result);
					
					// + 위의 인풋태그 초기화시키기
					$("#input2_1").val("");
					$("#input2_2").val("");
					
				},
				error : function(){
					alert("야 실패했어 ~ ~ ");
				}
			});			
			
		}
	</script>
	
	
	<!-- 1. String 2. ArrayList 3. VO -->
	
	<hr>
	
	<h3>3. 서버로 데이터 전송 후, 조회된 객체를 응답데이터로 받기</h3>
	
	회원번호 입력 : <input type="number" id="input3">
	
	<button onclick="test3();">조회</button>
	
	<div id="output3"></div>
	
	<table id="output4" border="1">
		<thead>
			<tr>
				<th>번호</th>
				<th>이름</th>
				<th>나이</th>
				<th>성별</th>
			</tr>
		</thead>
		<tbody>
		
		</tbody>
	</table>
	
	<script>
		function test3(){
			
			$.ajax({
				url : "jqAjax3.do",				
				data : {no : $("#input3").val()},
				success : function(result){
					
					/* VO객체 하나만 보낸 케이스
					var resultStr = "회원번호 : " + result.memberNo + "<br>"
					              + "이름 : " + result.memberName + "<br>"
								  + "나이 : " + result.age + "<br>"
								  + "성별 : " + result.gender;
					$("#output3").html(resultStr);
					*/
					
					// ArrayList로 VO객체 여러개가 묶여서 보내져왔따
					// => 반복문으로 문자열을 연이어서 만들기(누적)
					
					var resultStr2 = "";
					
					for(var i = 0; i < result.length; i++){
						resultStr2 += "<tr>"
									+ "<td>" + result[i].memberNo + "</td>"
									+ "<td>" + result[i].memberName + "</td>"
									+ "<td>" + result[i].age + "</td>"
									+ "<td>" + result[i].gender + "</td>"
									+ "</tr>";
					}
					$("#output4 tbody").html(resultStr2);
					
				},
				error : function(){
					alert("실패 ~ ");					
				}
			});			
			
		}
	</script>
	


<br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>




</body>
</html>