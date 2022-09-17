package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;

/**
 * Servlet implementation class ThumbnailDetailController
 */
@WebServlet("/detail.th")
public class ThumbnailDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ThumbnailDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 우선적으로 글번호 뽑아오기
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		// 조회
		// 1. 조회수 증가시키는 쿼리문 요청
		int result = new BoardService().increaseCount(boardNo);
	
		// 2. 1번이 성공했을 경우 => Board에서 조회요청, Attachment에서도 조회 요청
		if(result > 0) { // 성공
			
			// BOARD테이블에 조회 요청
			// 기존에 우리가 만들어놨떤 selectBoard메소드를 호출해서 재활용하려고하니까
			// 문제는 일반게시판의 경우 카테고리가 있어서 조인을 했을때 카테고리가 null인애가 없었다!!
			// 사진게시글경우 카테고리가 전부 null이기 때문이 innerjoin으로는 조회가 불가능하다
			// => 카테고리 컬럼을 기준으로 일치하는 컬럼도, 일치하지 않는 컬럼도 전부 가져오려면
			// 기존 조인에서 outerjoin으로 바꿔야한다(왼쪽 테이블을 기준으로)
			Board b = new BoardService().selectBoard(boardNo);
			
			// Attachment에도 조회요청 => ArrayList<Attachment>
			ArrayList<Attachment> list = new BoardService().selectAttachmentList(boardNo);
			
			// 요청결과 request에 담기
			request.setAttribute("b", b);
			request.setAttribute("list", list);
			
			// 응답 뷰 지정
			// views/board/thumbnailDetailView.jsp
			request.getRequestDispatcher("views/board/thumbnailDetailView.jsp").forward(request, response);
			
			
		} else { // 실패
			
			
			
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
