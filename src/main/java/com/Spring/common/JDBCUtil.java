package com.Spring.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


public class JDBCUtil {
		//DB�� ���� �����ϴ� Ŭ����
	//1. DB�� �����ϴ� �޼ҵ�
		//static : ��ü ���� ���� Ŭ���� �̸����� �ٷ� ȣ��
	public static Connection getConnection() {
		String driver = "oracle.jdbc.driver.OracleDriver";
		//string driver = "org.h2.Driver";
		String url = "jdbc:oracle:thin:@localhost:1522:XE";
		//String url = "jdbc:h2:tcp://localhost/~/test";
		
		Connection conn = null;
		
		try {
			Class.forName(driver);
			conn =  DriverManager.getConnection(url, "C##HR", "1234");
			//conn = DriverManager.getconnection(url, "sa", "");
			
			System.out.println("DB�� ���� �Ǿ����ϴ�.");	//Ȯ�� �� �ּ� ó��
			return conn;
			
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("DB�� ������ �����߽��ϴ�.");
		}
		return null;
	}
	
	
	
	//2. DB������ �����ϴ� �޼ҵ� : Connection, Statement ��ü�� ����
		//Insert, Update, Delete 
	public static void close (PreparedStatement pstmt, Connection conn) {
		if (pstmt != null) {
			try {
				if (!pstmt.isClosed()) {	//pstmt ��ü�� ���� �ȵ� ���¶�� 
					pstmt.close();
					System.out.println("pstmt ��ü close()");
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				pstmt = null;
			}
		}
		
		if (conn != null) {
			try {
				if (!conn.isClosed()) {
					conn.close();
					System.out.println("conn ��ü close()");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
	
	
	//3. DB������ �����ϴ� �޼ҵ� : Connection, PreparedStatement, ResultSet ��ü�� ����
		//Select
	public static void close (ResultSet rs, PreparedStatement pstmt, Connection conn) {
		if (rs != null) {
			try {
				if (!rs.isClosed()) {
					rs.close();
					System.out.println("rs ��ü close()");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				rs = null;
			}
		}
		
		if (pstmt != null) {
			try {
				if (!pstmt.isClosed()) {	//pstmt ��ü�� ���� �ȵ� ���¶�� 
					pstmt.close();
					System.out.println("pstmt ��ü close()");
				}
				
			} catch(Exception e) {
				e.printStackTrace();
			} finally {
				pstmt = null;
			}
		}
		
		if (conn != null) {
			try {
				if (!conn.isClosed()) {
					conn.close();
					System.out.println("conn ��ü close()");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				conn = null;
			}
		}
	}
}
