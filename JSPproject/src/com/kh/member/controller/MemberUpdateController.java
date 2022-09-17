package com.kh.member.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.member.model.service.MemberService;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class MemberUpdateController
 */
@WebServlet("/update.me")
public class MemberUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) POST방식 => 인코딩설정
		request.setCharacterEncoding("UTF-8");
		
		// 2) request로부터 요청 시 전달한 값을 뽑기
		String userId = request.getParameter("userId");
		String userName = request.getParameter("userName");
		String phone = request.getParameter("phone");
		String email = request.getParameter("email");
		String address = request.getParameter("address");
		String[] interestArr = request.getParameterValues("interest"); // null
		
		String interest = "";
		if(interestArr != null) {
			interest = String.join(",", interestArr);
		}
	
		// 3) VO객체에 담기
		Member m = new Member(userId, userName, phone, email, address, interest);
		
		// 4) Service단으로 토스
		Member updateMem = new MemberService().updateMember(m);
		
		// 5) 결과물에 따라서 응답화면 지정 => 업데이트된 내용물을 같이 화면에 뿌려주기
		
		if(updateMem != null) { // 마이페이지 화면 그대로 보여주기
			
			HttpSession session = request.getSession();
			session.setAttribute("alertMsg", "성공적으로 회원정보를 수정했습니다.");
			session.setAttribute("loginUser", updateMem);
			// sendRedirect형식으로 보여주기
			// localhost:8001/jsp/myPage.me
			response.sendRedirect(request.getContextPath()+ "/myPage.me");
			
		} else {
			request.setAttribute("errorMsg", "회원정보 수정에 실패했습니다.");
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
