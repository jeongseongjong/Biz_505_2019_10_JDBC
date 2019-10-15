package com.biz.jdbc.exec;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JdbcEx_01 {

	public static void main(String[] args) {
	
		System.out.println("Hello 오라클");
		String jdbcDriver = "oracle.jdbc.driver.OracleDriver";
		String url = "jdbc:oracle:thin:@localhost:1521:xe";
		String userName = "grade";
		String password = "grade";
		
		try {
			/*
			 * jdbdDriver(oracle.jdbc.driver.OracleDrivcer.class 파일)을
			 * 사용할 수 있도록 JVM에 등록(loading)하라는 지시
			 */
			Class.forName(jdbcDriver);
			
			/*
			 * url : 연결주소
			 * userName : DB사용자
			 * password : 사용자의 비밀번호
			 * 를 사용하여 DBMS와 SQL을 주고받을 통로를 설정
			 */
			Connection dbConn = null;
			dbConn = 
					DriverManager.getConnection(url,userName,password);
			System.out.println("Connection OK");

			// DBMS에게 SQL을 전송절차 시작
			// JDBC와 우리의 App같이 데이터 교환이 이루어지는 영역
			PreparedStatement pStr = null;
			
			// 연결된 통로를 통해서 SQL문을 전달하고
			// 그 결과를 pStr 변수에 받는다.
			String sql = "SELECT * FROM tbl_score";
			pStr = dbConn.prepareStatement(sql);
			
			// sql문이 실행하도록 지시
			// SELECT 된 결과를
			ResultSet rst = pStr.executeQuery();
			/*
			 * 이제 rst 객체에는 DB로 읽어온 리스트가
			 * ResultSet 이라는 데이터 type으로 저장되어 있을 것이다.
			 * 이 ResultSet 일종의 Iterator처럼 취급할 수 있다.
			 */
			/*
			 * rst.next() method가 한번 실행 될 때마다
			 * 데이터 리스트의 앞쪽부터 한개씩 꺼내서 읽을 수 있도록
			 * 준비를 한다.
			 * 또, rst.next() method가 실행되면
			 * 현재 위치의 다음번 리스트 요소를 읽을 수 있도록 준비
			 *  마지막 리스틀르 모든
			 */
			while(rst.next()) {
				// 읽을 준비가 된 리스트 요소의 1번칼럼의 값을 꺼내라
				System.out.print(rst.getString(1) + "\t");
				System.out.print(rst.getString(2) + "\t");
				System.out.print(rst.getString(3) + "\n");
			}
			rst.close();
			dbConn.close();
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
