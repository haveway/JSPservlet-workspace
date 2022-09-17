package com.kh.notice.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeInsertController
 */
@WebServlet("/insert.no")
public class NoticeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// POST방식
		// NOTICE 테이블에 INSERT => 추가적으로 필요한 데이터? == 관리자의 USERNO
		
		// 1) 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2) 값 뽑기
		String userNo = request.getParameter("userNo"); // "1"
		String noticeTitle = request.getParameter("title"); // "XXXX"
		String noticeContent = request.getParameter("content"); // "XXXX"
		
		// 3) VO 객체로 가공
		Notice n = new Notice();
		n.setNoticeWriter(userNo);
		n.setNoticeTitle(noticeTitle);
		n.setNoticeContent(noticeContent);
		
		// 4) Service단으로 토스 ~ 
		int result = new NoticeService().insertNotice(n);
		
		// 5) 결과에 따른 응답페이지 지정
		if(result > 0) { // 성공 => 공지사항 리스트가 보이게끔 넘겨주자 => localhost:8001     /jsp/list.no
			request.getSession().setAttribute("alertMsg", "공지사항이 등록되었습니다!");
			response.sendRedirect(request.getContextPath() + "/list.no");
			
		} else { // 실패 => 에러페이지로 보내자
			request.setAttribute("errorMsg", "공지사항 등록 실패!");
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
