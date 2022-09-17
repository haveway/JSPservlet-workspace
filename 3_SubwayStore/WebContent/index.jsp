<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>안녕하세요 서브웨이입니다 ~ </title>
<style>
	body {
		margin : 0px;
		padding : 0px;
		background-color: lightgreen;
		color: yellow;
	}



</style>
</head>
<body>
	<h1>고르세요 ~ </h1>
	
	<br><br>
	
	<!-- 
		서버로 요청 할 때 : 항상 form태그로 넘겼음(submit 버튼)	
		form 태그에 항상 있어야 하는것 : action 속성(어느 서블릿으로 보낼건지)
								method 속성 (get/post)
	 -->
	 
	 <form action="/subway/order.do" method="get">

		<fieldset>
			<legend>주문자 정보</legend>

			<table>
				<!-- 4행(tr) 2열(th, td)-->
				<tr>
					<th>이름</th>
					<td><input type="text" name="userName" required></td>
				</tr>
				<tr>
					<th>전화번호</th>
					<td><input type="text" name="phone" required></td>
				</tr>
				<tr>
					<th>주소</th>
					<td><input type="text" name="address" required></td>
				</tr>
				<tr>
					<th>요청사항</th>
					<td><textarea name="message" cols="30" rows="15" style="resize:none"></textarea></td>
				</tr>
			</table>
		</fieldset>

		<br>

		<fieldset>
			<legend>주문 정보</legend>

			<table>
				<tr>
					<th>샌드위치</th>
					<td>
						<select name="sandwich">
							<option>참치</option>
							<option>에그마요</option>
							<option>이탈리안비엠티</option>
							<option>터키베이컨아보카도</option>
							<option>로티세리바베큐치킨</option>
							<option>치즈N스테이크</option>
						</select>
					</td>
				</tr>
				<tr>
					<th>토핑</th>
					<td>
						<input type="checkbox" name="topping" value="오이" checked>오이
						<input type="checkbox" name="topping" value="양배추" checked>양배추
						<input type="checkbox" name="topping" value="올리브" checked>올리브
						<input type="checkbox" name="topping" value="할라피뇨" checked>할라피뇨
						<input type="checkbox" name="topping" value="적양파" checked>적양파
						<input type="checkbox" name="topping" value="피망" checked>피망
						<input type="checkbox" name="topping" value="토마토" checked>토마토
						<input type="checkbox" name="topping" value="렌치">렌치
						<input type="checkbox" name="topping" value="후추">후추
						<input type="checkbox" name="topping" value="핫칠리">핫칠리
						<input type="checkbox" name="topping" value="마요네즈">마요네즈
						<input type="checkbox" name="topping" value="바베큐">바베큐
					</td>
				</tr>
				<tr>
					<th>꽈자</th>
					<td>
						<input type="checkbox" name="cookie" value="라즈베리치즈케잌">라즈베리치즈케잌
						<input type="checkbox" name="cookie" value="더블초코칩쿠키">더블초코칩쿠키
						<input type="checkbox" name="cookie" value="스모어초코어쩌고" checked>스모어초코어쩌고
					</td>
				</tr>
				<tr>
					<th>결제방식</th>
					<td>
						<input type="radio" name="payment" value="card" checked> 카드결제
						<input type="radio" name="payment" value="cash"> 현금결제
					</td>
				</tr>
			</table>	
		</fieldset>

		<br>
		<input type="submit" value="주문">
	 </form>

	<hr>
	<br>
	<a href='/subway/orderList.sandwich'>주문내역보기</a>



	 <br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br><br>
</body>
</html>