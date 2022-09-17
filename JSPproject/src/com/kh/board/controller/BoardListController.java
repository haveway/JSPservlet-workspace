package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 쿼리스트링으로 요청 /jsp/list.bo?cpage = 1 => GET방식 인코딩 X
		
		// 2) request로 부터 값 뽑기
		
		// ------------ 페이징 처리 ------------
		// 필요한 변수들
		int listCount; // 현재 일반게시판의 게시글 총 갯수 => BOARD로 부터 조회 COUNT(*)활용(STATUS = 'Y')
		int currentPage; // 현재 페이지(즉, 사용자가 요청한 페이지) => request.getParameter("cpage");
		int pageLimit; // 페이지하단에 보여질 페이징바의 최대 갯수 => 10개로 고정
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 갯수 => 10개로 고정
		
		int maxPage; // 가장 마지막 페이지가 몇번 페이지 인지(총 페이지의 갯수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작 수
		int endPage; // 페이지 하단에 보여질 페이징바의 끝 수
	
		// * listCount : 총 게시글 갯수
		listCount = new BoardService().selectListCount();
		
		// * currentPage : 현재페이지(== 사용자가 요청한 페이지)
		currentPage = Integer.parseInt(request.getParameter("cpage"));
		
		//System.out.println(listCount); //  107
		//System.out.println(currentPage); //  1
		
		// * pageLimit : 페이징바의 페이지 최대 갯수
		pageLimit = 10;
		
		// * boardLimit : 한 페이지에 보여질 게시글의 최대 갯수
		boardLimit = 10;
		
		// * maxPage : 가장 마지막 페이지가 몇 번 페이지인지(총 페이지 갯수)
		/*
		 * listCount, boardLimit에 영향을 받음
		 * 
		 * - 공식 구하기
		 *   단, boardLimit이 10이라는 가정하에 규칙을 찾아보자!
		 *   
		 *   총 갯수(listCount)			boardLimit(10개)					maxPage(마지막페이지)
		 *   100 개				/			10개							=  10.0 10번 페이지
		 * 	 101 개				/			10개							=  10.1 11번 페이지
		 * 	 105 개				/			10개							=  10.5 11번 페이지
		 * 	 109 개				/			10개							=  10.9 11번 페이지
		 * 	 110 개				/			10개							=  11.0 11번 페이지
		 *   111 개				/			10개							=  11.1 12번 페이지
		 * => 나눗셈결과(listCount / boardLimit)를 올림처리 할 경우 maxPage가 된다.
		 * 
		 * 
		 * 스텝
		 * 1) listCount를 double로 형변환
		 * 2) listCount / boardLimit
		 * 3) Math.ceil() => 결과를 올림 처리 
		 * 4) 결과값을 int로 형변환 
		 */
				
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
				
		//System.out.println(maxPage);
		
		// *startPage : 페이지 하단에 보여질 페이징바의 시작수
		/*
		 * pageLimit, currentPage에 영향을 받음
		 * 
		 * - 공식 구하기
		 *   단, pageLimit 10이라는 가정하에 규칙을 구해보자
		 *   
		 * startPage : 1, 11, 21, 31, 41 .... => n * 10 + 1 => 10의 배수 + 1
		 * 
		 * 만약에 pageLimit이 5였다면?
		 * 
		 * startPage : 1, 6, 11, 16 .... => n * 5 + 1 => 5의 배수 + 1
		 * 
		 * 즉, startPage = n * pageLimit + 1
		 * 
		 * currentPage					startPage
		 * 		1							1
		 * 		5							1
		 * 		10							1
		 * 		11							11
		 * 		13							11
		 * 		20							11
		 * 
		 * 
		 * => 1 ~ 10 :  n * 10 + 1 = 1   => n = 0
		 * => 11 ~ 20 : n * 10 + 1 = 11  => n = 1
		 * => 21 ~ 30 : n * 10 + 1 = 21  => n = 2
		 * ....
		 * 
		 * 		1 ~ 10 / 10 => n = 0 / 1
		 *     11 ~ 20 / 10 => n = 1 / 2
		 *     21 ~ 30 / 10 => n = 2 / 3    1씩 모자르네....
		 *     
		 *     n을 구하는 공식을 도출해보자
		 *     
		 *     0 ~ 9 / 10 = 0
		 *     10 ~ 19 / 10 = 1
		 *     20 ~ 29 / 10 = 2
		 *     
		 *     n = (currentPage - 1) / pageLimit
		 * 								n * pageLimit + 1
		 * 	startPage = (currentPage - 1) / pageLimit * pageLimit + 1
		 * 
		 */
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		// * endPage : 페이지 하단에 보여질 페이징바의 끝수
		/*
		 * startPage, pageLimit에 영향을 받음(단, maxPage도 마지막 페이징 바에 대해선 영향을 준다)
		 * 
		 * - 공식 구하기
		 * 단, pageLimit이 10이라는 가정
		 * 
		 * startPage :  1 => endPage : 10
		 * startPage : 11 => endPage : 20
		 * startPage : 21 => endPage : 30
		 * ....
		 * 
		 * => endPage = startPage + pageLimit - 1;
		 * 
		 * 
		 */
		endPage = startPage + pageLimit - 1;
				
		// startPage가 11이어서 endPage에는 20이 들어갔는데
		// maxPage가 13이라면????
		// => endPage값을 maxPage값으로 변경
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 여기까지 총 7개의 변수를 만들었음!
		// 7개의 변수를 가지고 해당되는 범위에 따른 게시글 10개씩만 SELECT
		// Service단으로 토스 => 7개나있다 => VO 클래스에 만들어서 가공해서 넘기자
		
		
		// 3) VO로 가공
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit,
									maxPage, startPage, endPage);
				
		// 4) Service로 토스
		ArrayList<Board> list = new BoardService().selectList(pi);
		
		// 5) 응답뷰 지정
		request.setAttribute("list", list); // 우리가 실제로 조회한 페이지에 보일 10개의 게시글
		request.setAttribute("pi", pi); // 페이징바를 만들 때 필요한 변수
	
		// views/board/boardDetailView.jsp 화면으로 응답 => 포워딩
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
