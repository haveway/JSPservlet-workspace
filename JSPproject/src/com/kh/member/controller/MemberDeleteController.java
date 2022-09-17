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
 * Servlet implementation class MemberDeleteController
 */
@WebServlet("/delete.me")
public class MemberDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) 인 코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2) 값 뽑기
		String userPwd = request.getParameter("userPwd");
		
		// 현재 로그인한 회원의 정보를 얻는 방법
		// 방법 1. input type="hidden"으로 애초에 요청시 숨겨서 전달해버린다.
		// 방법 2. session 영역에 담겨있는 회원객체로부터 뽑아온다.
		
		// 세션에 담겨있는 기존 로그인된 사용자의 정보를 얻어와보자!
		HttpSession session = request.getSession();
		String userId = ((Member)session.getAttribute("loginUser")).getUserId();
		
		// 3) VO가공 => 패스 ~
		
		// 4) Service단으로 토스 ~ 
		int result = new MemberService().deleteMember(userId, userPwd);
		
		// 5) 결과에따른 응답페이지 지정
		
		
		if(result > 0) {// 성공 => 탈퇴가 된것 => 로그인이 유지되어야하나요? => NO
			
			// invalidate() 세션을 만료시킴
			// alert못씀
			// removeAttribute()요거 써서 로그아웃 시키자
			session.removeAttribute("loginUser");
			
			// 로그아웃이 되었으므로 마이페이지가 보이면 안됨
			// 메인페이지로 보내버리자 => localhost:8001/jsp
			
			response.sendRedirect(request.getContextPath());
			
		} else { // 실패 => 탈퇴가 아직 안된것 => 오류페이지로 보낼것이다!
			request.setAttribute("errorMsg", "회원 탈퇴에 실패했습니다.");
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
