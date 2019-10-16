package com.biz.grade.service;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.persistence.ScoreDTO;
import com.biz.grade.persistence.StudentDTO;
import com.biz.grade.utils.DBContract;

public abstract class StudentService {

	protected Connection dbConn = null;
	
	/*
	 * dbConn을 설정하여 DBMS에 접속할 수 있는 통로 설정 method
	 */
	protected void dbConnection() {
		
		try {
			Class.forName(DBContract.DbConn.JdbcDriver);
			dbConn = DriverManager.getConnection(
					DBContract.DbConn.URL,
					DBContract.DbConn.USER,
					DBContract.DbConn.PASSWORD);
			System.out.println("DbConnection OK!!!");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println("JDBC 드라이버를 찾지못함");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			System.out.println("DBMS 접속 오류!!");
		
		}
	}
	// CRUD
	public abstract int insert(StudentDTO studentDTO);
	
	// 모든 레코드
	// 1개 이상의 레코드
	public abstract List<StudentDTO> selectAll();
	
	// id값을 매개변수로 받아서
	// 1개의 레코드만 조회하는 method
	public abstract StudentDTO findById(String num);
	
	public abstract List<StudentDTO> findByName(String name);
	// 과목별로 점수리스트를
	public abstract List<StudentDTO> findBySubject(String subject);
	
	public abstract int update(StudentDTO dVO);
	public abstract int delete(long id);
	
}





















