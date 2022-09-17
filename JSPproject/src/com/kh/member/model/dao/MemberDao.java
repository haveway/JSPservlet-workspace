package com.kh.member.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.member.model.vo.Member;

public class MemberDao {
	
	private Properties prop = new Properties();
	
	public MemberDao() {
		
		String file = MemberDao.class.getResource("/sql/member/member-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(file));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public Member loginMember(Connection conn, String userId, String userPwd) {
		
		// SELECT 문 => ResultSet객체(unique key조건에 의해 한 행만 조회됨) => Member
		
		// 필요한 변수
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginMember");
		
		
		try {
			// pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 구멍 메꾸기
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			// 쿼리문 실행 후 결과 받기
			// 쿼리문 실행 메소드
			// pstmt.executeQuery(); => SELECT
			// pstmt.executeUpdate(); => INSERT/UPDATE/DELETE
			rset = pstmt.executeQuery();
			
			// rset으로부터 각각의 컬럼값을 뽑아서 Member객체 담는다.
			// rset.next()
			// 조회 결과가 여러 행일 때 => while(rset.next())
			// 조회 결과가 한 행일 때 => if(rset.next())
			
			if(rset.next()) {
				
				// 각 컬럼값 뽑기
				// rset.getInt/getString/getDate(뽑아올 컬럼명 또는 컬럼의 순번)
				
				m = new Member(rset.getInt("USER_NO"),
							   rset.getString("USER_ID"),
							   rset.getString("USER_PWD"),
							   rset.getString("USER_NAME"),
						       rset.getString("PHONE"),
							   rset.getString("EMAIL"),
							   rset.getString("ADDRESS"),
							   rset.getString("INTEREST"),
							   rset.getDate("ENROLL_DATE"),
							   rset.getDate("MODIFY_DATE"),
							   rset.getString("STATUS"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원반납 => 생성된 순서의 역순
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		// Service에 결과(Member) 넘기기
		return m;
	}
	
	
	public int insertMember(Connection conn, Member m) {
		
		// INSERT문 => 처리된 행의 갯수
		
		// 필수한 변수들 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertMember");
		
		try {
			// pstmt 객체 생성(sql미리 넘기기)
			pstmt = conn.prepareStatement(sql);
			
			// 빵꾸 매꾸기
			pstmt.setString(1, m.getUserId());
			pstmt.setString(2, m.getUserPwd());
			pstmt.setString(3, m.getUserName());
			pstmt.setString(4, m.getPhone());
			pstmt.setString(5, m.getEmail());
			pstmt.setString(6, m.getAddress());
			pstmt.setString(7, m.getInterest());
			
			// SQL 실행 및 결과 받기
			// insert/update/delete -> pstmt.executeUpdate();
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원반납
			JDBCTemplate.close(pstmt);
		}
		
		// 결과 리턴
		return result;
	}
	
	public int updateMember(Connection conn, Member m) {
		
		// UPDATE문 => 처리된 행의 갯수
		
		// 필요한 변수
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, m.getUserName());
			pstmt.setString(2, m.getPhone());
			pstmt.setString(3, m.getEmail());
			pstmt.setString(4, m.getAddress());
			pstmt.setString(5, m.getInterest());
			pstmt.setString(6, m.getUserId());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;
	}
	
	public Member selectMember(Connection conn, String userId) {
		
		// SELECT 문 => ResultSet형태로 결과 반환 => Member
		
		// 필요한 변수
		Member m = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectMember");
		
		try {
			
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, userId);
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				m = new Member(rset.getInt("USER_NO"),
							   rset.getString("USER_ID"),
							   rset.getString("USER_PWD"),
							   rset.getString("USER_NAME"),
							   rset.getString("PHONE"),
							   rset.getString("EMAIL"),
							   rset.getString("ADDRESS"),
							   rset.getString("INTEREST"),
							   rset.getDate("ENROLL_DATE"),
							   rset.getDate("MODIFY_DATE"),
							   rset.getString("STATUS"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return m;
	}
	
	public int updatePwdMember(Connection conn, String userId, String userPwd, String updatePwd) {
		
		// UPDATE 문 => 처리된 행의 갯수
		// 필요한 변수
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updatePwdMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, updatePwd);
			pstmt.setString(2, userPwd);
			pstmt.setString(3, userId);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int deleteMember(Connection conn, String userId, String userPwd) {
		// DELETE 보다 복구의 여지가 있는 UPDATE문을 쓰는 것이 더 좋다!
		// STATUS = 'Y' => STATUS ='N'
		
		// UPDATE문 => 처리된 행의 갯수
		// 필요한 변수
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteMember");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, userId);
			pstmt.setString(2, userPwd);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
		
	}
	
	
	
	public int idCheck(Connection conn, String checkId) {
		
		// SELECT 문 => ResultSet => COUNT함수 이용(숫자 한개)
		
		int count = 0;
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("idCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, checkId);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("COUNT(*)");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return count;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
