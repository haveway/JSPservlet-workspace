<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>얼굴피자</title>
</head>
<body>

    <h1>★얼굴피자에 오신걸 환영합니다☆</h1>

    <h2>오늘의 날짜</h2>
    <%@ include file="datePrint.jsp" %>

    <br>

    <form action="/2_JSP/confirmPizza.do" method="get">

        <fieldset>
            <legend>주문자 정보</legend>
                <table>
                    <tr>
                        <th>이름</th>
                        <td><input type="text" name="userName" required></td>
                    </tr>
                    <tr>
                        <th>전화번호</th>
                        <td><input type="phone" name="phone" required></td>
                    </tr>
                    <tr>
                        <th>주소</th>
                        <td><input type="text" name="address" required></td>
                    </tr>
                    <tr>
                        <th>요청사항</th>
                        <td><textarea name="message" style="resize:none"></textarea></td>
                    </tr>
                </table>
        </fieldset>

        <br>

        <fieldset>
            <legend>주문정보</legend>

            <table>
                <tr>
                    <th>피자</th>
                    <td>
                        <select name="pizza">
                            <option>페퍼로니 피자</option>
                            <option>고구마 피자</option>
                            <option>마르게리따 피자</option>
                            <option>콤비네이션 피자</option>
                            <option>하와이안 피자</option>
                            <option>민트초코 피자</option>
                        </select>
                    </td>
                </tr>
                <tr>
                    <th>토핑</th>
                    <td>
                        <input type="checkbox" name="topping" value="치즈">치즈
                        <input type="checkbox" name="topping" value="올리브">올리브
                        <input type="checkbox" name="topping" value="할라피뇨">할라피뇨
                        <input type="checkbox" name="topping" value="피망">피망
                        <input type="checkbox" name="topping" value="시금치">시금치
                        <input type="checkbox" name="topping" value="살라미">살라미
                        <input type="checkbox" name="topping" value="파인애플">파인애플
                    </td>
                </tr>
                <tr>
                    <th>사이드</th>
                    <td>
                        <input type="checkbox" name="side" value="치킨">치킨
                        <input type="checkbox" name="side" value="오븐스파게티">오븐스파게티
                        <input type="checkbox" name="side" value="치즈스파게티">치즈스파게티
                        <input type="checkbox" name="side" value="치즈볼">치즈볼
                        <input type="checkbox" name="side" value="피클">피클
                        <input type="checkbox" name="side" value="떡볶이">떡볶이
                        <input type="checkbox" name="side" value="파인애플샤베트">파인애플샤베트
                    </td>
                </tr>
                <tr>
                    <th>결제방식</th>
                    <td>
                        <input type="radio" name="payment" value="cash">만나서현금결제
                        <input type="radio" name="payment" value="card" checked>카드결제
                    </td>
                </tr>
            </table>
        </fieldset>

        <input type="submit" value="주문하기">
        <input type="reset" value="다시고르기">
    </form>

</body>
</html>