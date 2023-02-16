package com.Spring.users;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import org.springframework.stereotype.Repository;

import com.Spring.common.JDBCUtil;

@Repository("userDAO")  //DAO에 객체를 자동으로 Spring framework에 생성 : Bean 생성
public class UserDAO {
	//JDBC 관련 변수 선언 (Connection, PreparedStatement(Statement), ResultSet)
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//SQL 쿼리를 담은 상수를 선언 : (insert, update, delete, getboardList : 생략)
	private final String USER_GET = "select * from users where id = ? and password = ?";

	//회원 정보를 가지고 오는 메소드 : getUser(dto);
	public UserDTO getUser(UserDTO dto) {
		//객체 선언 : DB에서 select 한 레코드를 user 에 담아서 리턴 
		UserDTO user = null;
		
//		System.out.println("DAO - " + dto.getId());
//		System.out.println("DAO - " + dto.getPassword());
		
		try {
			System.out.println("==> JDBC로 getUser() 시작");
			conn = JDBCUtil.getConnection();
			pstmt = conn.prepareStatement(USER_GET);
			
			//pstmt에 할당된 쿼리에 ? , ? 에 변수값을 할당 후 실행 : dto에 변수값으로 할당.
			pstmt.setString(1, dto.getId());
			pstmt.setString(2, dto.getPassword());
			
			rs = pstmt.executeQuery();	//select 문으로 executeQuery()를 실행한 다음 rs로 리턴
			//rs의 담긴 값을 가져와서 DTO (user)에 저장 후 리턴 돌려줌
			
			//DB의 ID와 Pass가 모두 일치할 경우 if문이 작동됨
			if (rs.next()) {	//레코드의 값이 존재할 때 커서를 해당 레코드로 이동
				
				user = new UserDTO();
				
				System.out.println("DB에서 값이 잘 select 되었습니다.");
				
				user.setId(rs.getString("ID"));
				user.setPassword(rs.getString("PASSWORD"));
				user.setName(rs.getString("NAME"));
				user.setRole(rs.getString("ROLE"));
				
				System.out.print("JDBC로 DB를 잘 쿼리해서 DTO로 잘 전송");
			}
		} catch (Exception e) {
			e.printStackTrace(); //개발이 완료된 이후 주석 처리
			System.out.println("JDBC로 쿼리 실행중 오류 발생");
		} finally {
			//모두 사용한 객체를 제거 : conn, pstmt, rs
			JDBCUtil.close(rs, pstmt, conn);
		}
		return user;
	}
}
