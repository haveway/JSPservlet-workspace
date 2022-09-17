package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;

/**
 * Servlet implementation class NoticeDeleteController
 */
@WebServlet("/delete.no")
public class NoticeDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// get 방식 => 인코딩 X => 값을 뽑을때 쿼리스트링에 키 : nno
		
		// 2) 값 뽑기
		int noticeNo = Integer.parseInt(request.getParameter("nno"));
		
		// 3) 가공 ~ 패스
		
		// 4) Service단으로 토스
		int result = new NoticeService().deleteNotice(noticeNo);
		
		// 5) 결과에 따른 응답뷰 지정
		if(result > 0) {
			response.sendRedirect(request.getContextPath() + "/list.no");
			
		} else { // 에러페이지 
			
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
