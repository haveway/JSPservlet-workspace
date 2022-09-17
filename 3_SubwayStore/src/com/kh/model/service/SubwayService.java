package com.kh.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.model.dao.SubwayDao;
import com.kh.model.vo.Order;

public class SubwayService {
	
	public int insertOrder(Order order) {
		
		// Service단의 가장 큰 역할! : Connection 객체 만들기
		Connection conn = JDBCTemplate.getConnection();
		
		// DAO 호출
		// => Connection객체, Controller로부터 받았던 그 무언가하고 넘겨버리기
		int result = new SubwayDao().insertOrder(conn, order);
		
		// insert, update, delete를 하면
		// 테이블의 내용물이 바뀜 => 확정(COMMIT) / 돌아가기(ROBLLBACK)
		if(result > 0) {
			JDBCTemplate.commit(conn);
		} else {
			JDBCTemplate.rollback(conn);
		}
		
		// Connection 자원 반납
		JDBCTemplate.close(conn);
		
		// 결과값을 리턴
		return result;
	}
	
	public ArrayList<Order> selectOrderList() {
		
		// Service => Connection객체
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Order> list = new SubwayDao().selectOrderList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

}
