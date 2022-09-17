package com.kh.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONObject;

/**
 * Servlet implementation class JqAjaxController2
 */
@WebServlet("/jqAjax2.do")
public class JqAjaxController2 extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JqAjaxController2() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// POST => 인코딩
		request.setCharacterEncoding("UTF-8");
		
		System.out.println("ㅎㅇㅎㅇ");
		
		// 값 뽑기
		String name = request.getParameter("name");
		int age = Integer.parseInt(request.getParameter("age"));
		
		// VO 가공 = >  Service호출
		
		// 결과에 따른 응답
		/*
		// 한글이 있을 경우를 대비해서 인코딩 설정(필수!!!!!!!!!!!!!!!!!!!!!!!!!!)
		response.setContentType("text/html; charset=UTF-8");
		
		// 넘기기
		response.getWriter().print(name, age);
		// => ajax는 결과를 오로지 한개만 응답할 수 있음!!
		*/
		
		// 한개가지고 응답할 때!
		/*
		 * 버전 1) 문자열 하나로 이어서 보내기
		 * 이름 : XXX, 나이 : XX
		 */
		/*
		String responseData = "이름 : " + name + ", 나이 : " + age;
		response.setContentType("text/html; charset=UTF-8"); 
		response.getWriter().print(responseData);
		*/
		
		// 버전 2) ajax로 실제값을 여러개 보내고 싶을 때 => 정석
		// => JSON(JavaScript Object Notation : 자바스크립트 객체 표기법)
		
		// ajax 통신 시 데이터 전송에 이용되는 포맷 형식 중 하나
		// [value, value, value] => 자바스크립트에서의 배열 객체 => JSONArray
		// {key:value, key:value} => 자바스크립트에서의 일반 객체 => JSONObject
		
		/*
		 * JSON으로 처리 시 사용되는 클래스
		 * => 자바에서 기본적으로 제공 X(라이브러리 .jar)
		 * https://code.google.com/archive/p/json-simple/downloads 접속 후 다운로드
		 * json-simple-1.1.1.jar 다운로드 후 WEB-INF/lib에 붙여넣기
		 * 
		 * 1. JSONArray [값1, 값2, ...] => 배열 형태로 넘기기 가능
		 * 2. JSONObject {키1:밸류1, 키2:밸류2, ..} => 객체 형태로 넘기기 가능
		 */
		/*
		JSONArray jArr = new JSONArray(); // [] 빈 배열(자바스크립트)이 만들어짐
		// 배열에 값 담기 => add
		jArr.add(name); // ["홍길동"]
		jArr.add(age); // ["홍길동", 30]
		
		// 인코딩
		//response.setContentType("text/html; charset=UTF-8");
		response.setContentType("application/json; charset=UTF-8");
		// => 응답할 데이터의 컨텐트타입을 지정해야 문자열 형식으로 넘어가지 않음
		// text/html로 보내게되면 문자열로 넘어간다.
		
		// 보내기
		response.getWriter().print(jArr);
		*/
		
		// 마찬가지로 JSONObject로도 넘기기 가능
		JSONObject jObj = new JSONObject(); // {} 빈객체(자바스크립트)가 만들어짐
		// 객체에 값 담기 => put메소드
		jObj.put("name", name); // {name : "홍길동"}
		jObj.put("age", age); // {name : "홍길동", age : 34}
		
		// json 타입의 값이라고 컨텐트타입 지정
		response.setContentType("application/json; charset=UTF-8");
		
		// 보내기
		response.getWriter().print(jObj);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
