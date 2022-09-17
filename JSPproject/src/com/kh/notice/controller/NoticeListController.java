package com.kh.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeListController
 */
@WebServlet("/list.no")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 화면을 띄우기 전에 notice테이블에 들어있는 값을 뽑아서 응답페이지에 전달할것을 먼저 생각해야함
		
		// 1) 인코딩
		
		// 2) 값뽑기
		
		// 3) 가공 
		
		// 4) Service단에 SELECT요청
		// 공지사항 전체 리스트 => 가져올 행의 갯수 : 최소 0개 ~ 여러개일 가능성 => ArrayList
		ArrayList<Notice> list = new NoticeService().selectNoticeList();
		
		// 4.5) 
		request.setAttribute("list", list);
		// 화면 띄우기
		request.getRequestDispatcher("/views/notice/noticeListView.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
