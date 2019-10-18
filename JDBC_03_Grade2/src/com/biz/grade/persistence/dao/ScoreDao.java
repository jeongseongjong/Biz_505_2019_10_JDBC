package com.biz.grade.persistence.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

import com.biz.grade.config.DBConnection;
import com.biz.grade.config.DBContract;
import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.persistence.domain.ScoreVO;
import com.biz.grade.persistence.domain.StudentDTO;
/*
 * Service 클래스
 * main() 호출하여 다양한 연산을 수행하는 용도
 * file읽기, 쓰기, 읽은후 연산처리
 * JDBC연결하여 데이터 연동
 * 
 * Dao 클래스
 *    Data Access Object (데이터베이스 접근 객체)
 * Service 클래스의 연산기능 중에서
 * 순수하게 JDBC와 연동하여 직접 
 * DB를 읽고(SELECT) 
 * DB를 UPDATE(INSERT, UPDATE, DELETE) 수행하는 기능을
 * Service로 부터 분리
 * 
 * 이제부터 Service 클래스는 비즈니스 로직만 담당하는 역할 수행
 * 비즈니스 로직이란?
 * 	사용자로부터 어떤 데이터를 입력받고,
 * 	결과를 보여주는 용도
 * 	main()과 Dao 클래스 사이에서 연산을 주도적으로 수행
 * 	mian() 입력된 데이터 -> Service에서 가공, 검증
 * 		-> Dao에서 UPDATE 수행
 *  
 *  main() 명령 실행하면
 *  Dao에서 SELECT한 data 
 *  	-> Service에서 다양한 방법으로 가공하고 View를 수행
 *  
 *  
 */
public abstract class ScoreDao {

	protected Connection dbConn = null ;
	
	// ScoreServiceV2 생성자
	public ScoreDao() {

		this.dbConn = DBConnection.getDBConnection();
	
	}
	
	public abstract List<ScoreVO> selectAll();
	public abstract ScoreVO findById(long id);
	
	// 학생이름으로 검색
	public abstract List<ScoreVO> findByStName(String stName);
	// 학번으로 조회하여 성적 리스트 가져오기
	public abstract List<ScoreVO> findByStNum(String stNum);
	public abstract List<ScoreVO> findBySubject(String stSubject);
	
	public abstract int insert (ScoreDTO scoreDTO);
	public abstract int update (ScoreDTO scoreDTO);
	public abstract int delete (long id);
	
	
}




















