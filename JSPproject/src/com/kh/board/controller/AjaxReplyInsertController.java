package com.kh.board.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Reply;
import com.kh.member.model.vo.Member;

/**
 * Servlet implementation class AjaxReplyInsertController
 */
@WebServlet("/rinsert.bo")
public class AjaxReplyInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxReplyInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// POST => 인코딩
		request.setCharacterEncoding("UTF-8");
	
		// request로부터 값 뽑기
		String replyContent = request.getParameter("content");
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		// 로그인한 회원정보
		int userNo = ((Member)request.getSession().getAttribute("loginUser")).getUserNo();
		
		// VO 가공 => Reply
		Reply r = new Reply();
		r.setReplyContent(replyContent);
		r.setRefBno(boardNo);
		r.setReplyWriter(String.valueOf(userNo));
		
		// Service단 호출
		int result = new BoardService().insertReply(r);
	
		// Gson, Json => 넘겨야할 값이 여러개일때 묶을때
		
		// result 1개뿐임 그냥 넘기기
		response.setContentType("text/html; charset=UTF-8");
	
		response.getWriter().print(result);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
