package com.biz.iolist.service;

import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.dao.DeptDao;
import com.biz.iolist.dao.IolistDao;
import com.biz.iolist.dao.IolistViewDao;
import com.biz.iolist.dao.ProductDao;

public class IolistServiceV1 {

	Scanner scan = new Scanner(System.in);
	
	protected ProductDao proDao;
	protected IolistDao iolistDao;
	protected DeptDao deptDao;
	protected IolistViewDao viewDao;
	
	protected IolistViewServiceV1 ioView;
	
	public IolistServiceV1() {
		
		SqlSession sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		
		this.proDao = sqlSession.getMapper(ProductDao.class);
		this.iolistDao = sqlSession.getMapper(IolistDao.class);
		this.deptDao = sqlSession.getMapper(DeptDao.class);
		this.viewDao = sqlSession.getMapper(IolistViewDao.class);
			
		ioView = new IolistViewServiceV1();
		
	}
	
}
