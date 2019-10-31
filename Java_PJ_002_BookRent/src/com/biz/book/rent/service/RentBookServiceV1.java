package com.biz.book.rent.service;

import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.book.config.DBConnection;
import com.biz.book.dao.BooksDao;
import com.biz.book.dao.UsersDao;

public class RentBookServiceV1 {
	protected BooksDao bDao;
	protected UsersDao uDao;
	
	Scanner scan = new Scanner(System.in);
	
	public RentBookServiceV1() {
		SqlSession sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		
		this.bDao = sqlSession.getMapper(BooksDao.class);
		this.uDao = sqlSession.getMapper(UsersDao.class);
	}
	protected void bookListMenu() {
		while(true) {
		System.out.println("===================================");
		System.out.println("도서대여 관리");
		System.out.println("===================================");
		System.out.println("1.검색 2.등록 3.수정 4.삭제 0.종료");
		System.out.println("-----------------------------------");
		System.out.print("선택 >>");
		String strMenu = scan.nextLine();
		
		int intMenu = 0;
		try {
			intMenu = Integer.valueOf(strMenu);
		} catch (Exception e) {
			// TODO: handle exception
		}
		if(intMenu == 0) break;
		if(intMenu == 1)
			this.search();
		if(intMenu == 2)
			this.insert();
		if(intMenu == 3)
			this.update();
		if(intMenu == 4)
			this.delete();
		}
		System.out.println("선택 종료");
	}

	protected void search() {
		// TODO Auto-generated method stub
		
	}

	protected void insert() {
		// TODO Auto-generated method stub
		
	}

	protected void update() {
		// TODO Auto-generated method stub
		
	}

	protected void delete() {
		// TODO Auto-generated method stub
		
	}
}
