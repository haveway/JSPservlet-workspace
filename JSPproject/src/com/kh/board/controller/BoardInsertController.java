package com.kh.board.controller;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) 인코딩 설정(POST)
		request.setCharacterEncoding("UTF-8");
		
		// 2) 값 뽑기 : multipart객체를 이용해보자
		
		// System.out.println(request.getParameter("userNo")); // null
		// 폼전송을 일반 방식이 아닌 multipart/form-data방식으로 전송하는 경우
		// request.getParameter로 값뽑기가 불가함
		// => mutipart라는 객체에 값을 이관시켜서 다뤄야 한다.
		
		// 스텝 0) enctype이 multipart/form-data로 잘 전송되었을 경우
		//       전반적인 내용들이 수정되도록 조건을 걸어줌
		if(ServletFileUpload.isMultipartContent(request)) {
			
			//System.out.println("성공??");
			
			// 파일 업로드를 위한 라이브러리 다운로드( http://www.servlets.com )
			// 파일 업로드를 위한 라이브러리명 : cos.jar(com.oreilly.servlet의 약자)
			
			// 스텝 1) 전송되는 파일을 처리할 작업
			//     (전송되는 파일의 용량제한, 전달된 파일을 저장할 경로)
			// 1_1. 전송파일 용량 제한
			//      (int maxSize => 10Mbyte)
			/*
			 * 단위 정리
			 * byte -> Kbyte -> Mbyte -> Gbyte -> Tbyte -> ...
			 * 
			 * 환산
			 * 1Kbyte == 1024byte(2의 10승)
			 * 1Mbyte == 1024Kbyte(2의 10승) == 1024 * 1024Byte(2의 20승)
			 * 
			 */
			int maxSize = 10 * 1024 * 1024; // 10Mbyte
			
			
			// 1_2. 전달된 파일을 저장할 서버의 폴더 경로 알아내기(String savePath)
			// getRealPath메소드를 통해 알아내기 => 인자값으로 WebContent부터 board_upfiles폴더까지의 경로를 제시
			// HttpServletRequest request 
			// HttpSession session 
			// ServletContext application
			// /가 WebContent를 나타냄
			
			/*
			HttpSession session = request.getSession();
			ServletContext application = session.getServletContext();
			String savePath = application.getRealPath("/resources/board_upfiles/");
			*/
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
			
			//System.out.println(maxSize);
			//System.out.println(savePath);
			
			// 스텝 2) 서버에 업로드 작업(파일명 수정)
			/*
			 * 
			 * - HttpServletRequest request => MultipartRequest multiRequest 객체로 변환
			 * 
			 * MultipartRequest 객체 생성 방법 : 매개변수 생성자를 이용해서 생성(cos.jar에서 제공)
			 * [ 표현법 ]
			 * MultipartRequest multiRequest
			 * 		= new MultipartRequest(request, savePath, maxSize, 인코딩, 파일명을 수정시켜주는 객체);
			 * 
			 * 위 구문 한줄 실행만으로 첨푸파일이 그대로 무조건 업로드됨!!!!
			 * 사용자가 올린 파일명은 그대로 해당 폴더에 업로드하지않는것이 일반적이기 때문에
			 * 파일명을 수정시켜주는 객체를 생성
			 * 
			 * Q) 파일명을 수정하는 이유는?
			 * A) 같은 파일명이 있을 경우를 대비해서,
			 *   파일명에 한글 / 특수문자 / 띄어쓰기가 포함된경우 서버에 따라 문제가 발생할 수도 있기 때문에
			 *   
			 *   기본적으로 파일명을 수정시켜주는 객체 => DefaultFileRenamePolicy객체(cos.jar에서 제공)
			 *    => 내부적으로 rename()호출하면서 파일명 수정
			 *    => 기본적으로 동일한 파일명이 있을경우 뒤에 카운팅된 숫자를 붙여서 만들어줌
			 *    예 ) aaa.jpg, aaa1.jpg, aaa2.jpg ...
			 *   
			 * 드릅게 성의가없음 ㅡㅡ
			 * 
			 * 우리 입맛대로 파일명을 수정해서 절대로 파일명이 안겹치게끔 rename해봅시다
			 * 
			 * 예 ) kakaotalk_yyyymmdd_hhmmsssRRR 카캌오톡
			 * 
			 * 나만의 com.kh.common.MyFileRenamePolicy라는 클래스를 만들어서 rename메소드를 정의
			 */
			
			
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 2) 값 뽑기
			// 카테고리 번호, 제목, 내용, 게시글을 작성한 멤버의 회원번호 Board객체로 가공 => INSERT
			String category = multiRequest.getParameter("category");
			String title = multiRequest.getParameter("title");
			String content = multiRequest.getParameter("content");
			String userNo = multiRequest.getParameter("userNo");
			
			// 3) VO객체로 가공 => 첫번째 INSERT문에 해당
			Board b = new Board();
			b.setCategory(category);
			b.setBoardTitle(title);
			b.setBoardContent(content);
			b.setBoardWriter(userNo);
			
			
			// 두번째 INSERT => 선택적(첨부파일이 있을 경우에만 INSERT)
			Attachment at = null;
			
			// 4) Service단으로 토스 => 선택적으로 첨부파일 객체를 생성 후 토스(첨부파일 유무)
			
			// 첨부파일의 유무를 가려내는 메소드(원본파일명 리턴)
			// multiRequest.getOriginalFileName("키값");
			// => 첨부파일이 있으면 "원본파일명" / 첨부파일이 없으면 null 리턴
			if(multiRequest.getOriginalFileName("upfile") != null) {
				
				// 첨부파일이 있다 => VO 객체로 가공
				at = new Attachment();
				
				at.setOriginName(multiRequest.getOriginalFileName("upfile")); // 원본명
				
				// 수정파일명
				// multiRequest.getFilesystemName("키값");
				at.setChangeName(multiRequest.getFilesystemName("upfile"));
				
				// 파일경로
				at.setFilePath("resources/board_upfiles");
			}
			
			// 4. 서비스 요청
			int result = new BoardService().insertBoard(b, at);
			
			if(result > 0) { // 성공 => list.bo?cpage=1 요청
				
				request.getSession().setAttribute("alertMsg", "게시글 작성 성공");
				response.sendRedirect(request.getContextPath() + "/list.bo?cpage=1");
				
			} else { // 실패
				
				// 첨부파일이 있었는데 실패했다면 이미 업로드된 첨부파일을 굳이 서버에 보관할 필요는 없다(용량만차지)
				
				if(at != null) {
					// delete()호출 => 삭제시키고자 하는 파일 객체 생성
					new File(savePath + at.getChangeName()).delete();
				}
				
				request.setAttribute("errorMsg", "게시글 작성 실패");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
			
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
