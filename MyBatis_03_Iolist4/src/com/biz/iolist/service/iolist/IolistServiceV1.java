package com.biz.iolist.service.iolist;

import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.dao.DeptDao;
import com.biz.iolist.dao.IolistDao;
import com.biz.iolist.dao.IolistViewDao;
import com.biz.iolist.dao.ProductDao;
import com.biz.iolist.service.iolist.view.IolistViewServiceV1;

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

	public void iolistMenu() {

		while (true) {

			System.out.println("============================================================");
			System.out.println("새나라 마트 매입매출 관리 v1");
			System.out.println("============================================================");
			System.out.println("1.등록 2.수정 3.삭제 4.검색 0.종료");
			System.out.println("-----------------------------------------------------------");
			System.out.print("업무선택 >> ");
			String strMenu = scan.nextLine();

			int intMenu = -1;
			try {
				intMenu = Integer.valueOf(strMenu);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (intMenu == 0)break;
			if(intMenu == 1) this.insert();
			if(intMenu == 2) this.update();
			if(intMenu == 3) this.delete();
			if(intMenu == 4) this.search();
		}
		System.out.println("업무종료");
	}

	protected void search() {
		// TODO Auto-generated method stub
		
	}

	protected void delete() {
		// TODO Auto-generated method stub
		
	}

	protected void update() {
		// TODO Auto-generated method stub
		
	}

	protected void insert() {
		// TODO 매입매출 등록 method
		
	}
}
