package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberInsertController
 */
@WebServlet("/insert.me")
public class MemberInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// POST
		// 1) 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 2) request객체로부터 요청 시 전달값 뽑기
		String userId = request.getParameter("userId"); // 필수입력
		String userPwd = request.getParameter("userPwd"); //필수입력
		String userName = request.getParameter("userName"); // 필수입력
		String phone = request.getParameter("phone"); // 빈 문자열이 들어갈 수 있음
		String email = request.getParameter("email"); // 빈 문자열이 들어갈 수 있음
		String address = request.getParameter("address"); // 빈 문자열이 들어갈 수 있음
		String[] interestArr = request.getParameterValues("interest"); // ["운동", "여행", ...] / null
	
		// 운동, 여행...
		// String.join(구분자, 배열명);
		String interest = "";
		
		if(interestArr != null) {
			interest = String.join(",", interestArr);
		}
		
		// 3) 매개변수 생성자를 이용해서 Member객체에 담기
		Member m = new Member(userId, userPwd, userName, phone, email, address, interest);
		
		// 4) 요청 처리 (Service단으로 토스~)
		int result = new MemberService().insertMember(m);
		
		// 5) 처리결과를 가지고 사용자가 보게 될 응답화면 지정
		if(result > 0) { // 성공 => /jsp => url 재요청방식(sendRedirect방식)
			request.getSession().setAttribute("alertMsg", "회원가입에 성공했습니다."); // 회원 가입 성공시 alertMsg에 추가
			response.sendRedirect(request.getContextPath());
		} else { // 실패 => 에러페이지
			request.setAttribute("errorMsg", "회원가입에 실패했습니다.");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
