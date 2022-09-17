package com.kh.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.model.service.SubwayService;
import com.kh.model.vo.Order;

/**
 * Servlet implementation class SubwayOrderController
 */
@WebServlet("/order.do")
public class SubwayOrderController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SubwayOrderController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) POST방식일때만 => 인코딩
		
		// 2) 뽑기 => request의 Parameter 영역에서(request.getParameter("키값")/ request.getParameterValues("키값"))
		// 뽑아서 변수에 담아야함!!
		// 주문자 정보 뽑기
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String address = request.getParameter("address");
		String message = request.getParameter("message");
		
		// 주문 정보 뽑기
		String sandwich = request.getParameter("sandwich");
		// checkbox = request.getParameterValues(); => String[] => 체크된게 1도 없을 때 null
		String[] toppings = request.getParameterValues("topping");
		String[] cookies = request.getParameterValues("cookie");
		String payment = request.getParameter("payment");
		
		// 3) 가공 => VO클래스 객체 생성해서 거기 담았음
		
		// 4) Service단으로 토스 => 결과값
		
		// 5) 결과에 따른 응답뷰 지정 => 성공 뷰 / 실패 뷰
		// 5_1) 응답뷰로 보낼 수하물 붙이기 => request.setAttribute("키", 밸류);
		// 5_2) 응답화면 지정 => forwarding / sendRedirect
		
		//----------------------------------------------------------------
		
		// 요청처리 간단하게
		int price = 0; // 지역변수는 항상 초기화 !
		// 가격책정하기
		
		// 샌드위치에 따른 기본가격
		switch(sandwich) {
		case "참치" : price += 5500; break;
		case "에그마요" : price += 4300; break;
		case "이탈리안비엠티" : price += 5300; break;
		case "터키베이컨아보카도" : 
		case "로티세리바베큐치킨" : price += 6300; break;
		case "치즈N스테이크" : price += 6500; break;
		}
		
		// 쿠키 종류에 따라서 추가 금액 더해주기
		// 배열의 경우는 체크된게 없을 때 null이기 떄문에
		// 무작정 반복문을 돌리면 nullPointerException이 발생할 가능성이 있음
		// => if문으로 애초에 발생 안하게 막는게 권장사항
		if(cookies != null) {
			for(int i = 0; i < cookies.length; i++) {
				switch(cookies[i]) {
				case "라즈베리치즈케잌" : 
				case "더블초코칩쿠키" : 
				case "스모어초코어쩌고" : price += 1500; break;
				}
			}
		}
		
		// 값다뽑았다!!! => VO객체에 담기
		Order order = new Order(userName,
							    phone,
							    address,
							    message,
							    sandwich,
							    String.join(",", toppings),
							    String.join(",", cookies),
							    payment,
							    price,
							    null);
		
		
		// Service단으로 토스
		int result = new SubwayService().insertOrder(order);
		
		// 수하물 붙이기 => request의 Attribute영역에 담기
		request.setAttribute("sandwich", sandwich);
		request.setAttribute("toppings", toppings);
		request.setAttribute("cookies", cookies);
		request.setAttribute("payment", payment);
		
		request.setAttribute("price", price);
		
		// 응답 뷰 지정 => 응답화면 jsp파일로 하나만들어서 보내기
		// WebContent/views/~~~~.jsp
		// 포워딩방식을 이용해서!
		if(result > 0) {
			RequestDispatcher view = request.getRequestDispatcher("views/result.jsp");
			view.forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
