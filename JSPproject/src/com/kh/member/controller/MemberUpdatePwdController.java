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
 * Servlet implementation class MemberUpdatePwdController
 */
@WebServlet("/updatePwd.me")
public class MemberUpdatePwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MemberUpdatePwdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

	
		// 1) POST방식이므로 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2) request로부터 값 뽑기
		String userId = request.getParameter("userId");
		String userPwd = request.getParameter("userPwd");
		String updatePwd = request.getParameter("updatePwd");
		
		// 3) VO 객체에 담아서 가공 => 담을 값이 너무 적을때는 패싱 가능
		
		
		// 4) Service단으로 토스
		Member updateMem = new MemberService().updatePwdMember(userId, userPwd, updatePwd);
	
		// 5) 결과값을 통해 성공 실패여부에 따른 응답화면 지정
		
		HttpSession session = request.getSession();
		
		if(updateMem == null) { // 실패
			session.setAttribute("alertMsg", "비밀번호 변경 실패!");
		} else { // 성공
			session.setAttribute("alertMsg", "비밀번호 변경 성공 !");
			session.setAttribute("loginUser", updateMem);
		}
		
		// 성공이든 실패든 myPage.me를 요청
		// localhost:8001/jsp/myPage.me
		response.sendRedirect(request.getContextPath()+ "/myPage.me");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
