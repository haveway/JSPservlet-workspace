package com.kh.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.kh.model.vo.Member;

/**
 * Servlet implementation class JqAjaxController3
 */
@WebServlet("/jqAjax3.do")
public class JqAjaxController3 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController3() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// GET방식
	
		// request 값 뽑기
		int memberNo = Integer.parseInt(request.getParameter("no"));
	
		// DB로부터 데이터를 조회했다는 가정하에 Member객체에 값을 담기
		Member m = new Member(memberNo, "홍길동", 50, "남성");
		
		// m을 응답
		// 형식, 인코딩
		// response.setContentType("text/html; charset=UTF-8");
		// response.getWriter().print(m);
		// 내부적으로 toString()호출되어 문자열 형태로 값이 넘어감
	
		
		// JSON 이용
		// 자바 객체 => 자바스크립트 객체로 변환(JSONObject)
		// JSONObject 객체 생성
		/*
		JSONObject jObj = new JSONObject(); // {}
		jObj.put("memberNo", m.getMemberNo()); // {memberNo : 1 }
		jObj.put("memberName", m.getMemberName()); // {memberNo : 1 , memberName : "홍길동" }
		jObj.put("age", m.getAge()); //{memberNo : 1 , memberName : "홍길동" , age : 50}
		jObj.put("gender", m.getGender()); //{memberNo : 1 , memberName : "홍길동", age : 50, gender:"남성"}
	
		// 응답으로 넘기기
		response.setContentType("application/json; charset=UTF-8");
		response.getWriter().print(jObj);
		*/
		
		// ===== JSON은 정석 방법 =====
		
		// GSON : Google JSON 라이브러리
		// GSON라이브러리를 연동해야만 사용가능
		
		// 형식, 인코딩 지정 => application / json;
		response.setContentType("application/json; charset=UTF-8");
		
		// Gson객체 생성
		//Gson gson = new Gson();
		
		// [표현법] toJson(응답할 객체, 응답할 스트림)
		//gson.toJson(m, response.getWriter());
		// => response.getWriter()라는 통로로 m이라는 객체를 응답하겠다.
		// 명시적으로 키값을 제시 안하면, 키값은 자동으로 필드명이 된다.
		
		//new Gson().toJson(m, response.getWriter());
		
		// VO객체 하나만 응답 시  JSONObject{} 형태로 만들어져서 응답
		
		// 여러개의 객체가 들어있는 ArrayList넘기기
		
		// ArrayList<Member>
		ArrayList<Member> list = new ArrayList();
		list.add(new Member(1, "김길동", 40, "남성"));
		list.add(new Member(2, "홍길동", 30, "여성"));
		list.add(new Member(3, "이승철", 34, "남성"));
		
		/*
		JSONArray jArr = new JSONArray(); // []
		for(Member me : list) {
			JSONObject jObj = new JSONObject();
			jObj.put("userNo", me.getMemberNo());
			jObj.put("userName", me.getMemberName());
			jObj.put("age", me.getAge());
			jObj.put("gender", me.getGender());
			
			jArr.add(jObj);
		}
		*/
		
		// ArrayList응답 시 JSONArray[] 형태로 만들어짐
		
		// Gson 객체 생성 후 응답 보내기
		new Gson().toJson(list, response.getWriter());
	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
