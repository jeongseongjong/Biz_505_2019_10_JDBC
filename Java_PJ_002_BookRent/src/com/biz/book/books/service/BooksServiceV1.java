package com.biz.book.books.service;

import java.util.List;
import java.util.Scanner;

import com.biz.book.config.DBConnection;
import com.biz.book.dao.BooksDao;
import com.biz.book.persistence.BooksDTO;

public class BooksServiceV1 {

	protected BooksDao bDao;
	Scanner scan;
	
	public BooksServiceV1() {
		scan = new Scanner(System.in);
		this.bDao = DBConnection
				.getSqlSessionFactory()
				.openSession(true)
				.getMapper(BooksDao.class);
	}
	
	public void viewAllList() {
		List<BooksDTO> bList = bDao.selectAll();
		
		if(bList == null || bList.size() < 1) {
			System.out.println("리스트 없음");
		}else {
			this.viewList(bList);
		}
		
	}
	
	
	public void SearchBName() {
		
		System.out.println("================================");
		System.out.println("검색할 도서 이름 >> ");
		String strBName = scan.nextLine();
		
		List<BooksDTO> bList = null;
		if(strBName.trim().isEmpty()) {
			bList = bDao.selectAll();
		}else {
			bList = bDao.findByName(strBName);
		}
		this.viewList(bList);
		
	}
	protected void viewList(BooksDTO bDTO) {
		System.out.print(bDTO.getB_code() + "\t");
		System.out.print(bDTO.getB_name() + "\t");
		System.out.print(bDTO.getB_auther() + "\t");
		System.out.print(bDTO.getB_comp() + "\t");
		System.out.print(bDTO.getB_year() + "\t");
		System.out.print(bDTO.getB_iprice() + "\t");
		System.out.print(bDTO.getB_rprice() + "\n");
	}
	
	protected void viewList(List<BooksDTO> bList) {
		System.out.println("==============================");
		System.out.println("도서리스트");
		System.out.println("==============================");
		System.out.println("코드\t도서명\t저자\t출판사\t출판연도\t구입가격\t대여가격");
		System.out.println("------------------------------");
		for(BooksDTO bDTO : bList) {
			this.viewList(bDTO);
		}
		System.out.println("================================");
	}
	
	protected BooksDTO viewBDetail(String strBCode) {
		BooksDTO booksDTO = bDao.findById(strBCode);
		
		if(booksDTO == null) return null;
		
		System.out.println("====================================");
		System.out.printf("도서코드 : %s\n", booksDTO.getB_code());
		System.out.printf("도서명 : %s\n", booksDTO.getB_name());
		System.out.printf("저자 : %s\n", booksDTO.getB_auther());
		System.out.printf("출판사 : %s\n", booksDTO.getB_comp());
		System.out.printf("출판연도 : %s\n", booksDTO.getB_year());
		System.out.printf("구입가격 : %d\n", booksDTO.getB_iprice());
		System.out.printf("대여가격 : %d\n", booksDTO.getB_rprice());
		return booksDTO;
	}
	
	public void updateBName() {
		System.out.println("=======================");
		System.out.println("수정할 도서코드");
		String strBCode = scan.nextLine();
		if(strBCode.trim().isEmpty()) {
			this.viewAllList();	
		}
		
		strBCode = strBCode.toUpperCase();
		
		BooksDTO booksDTO = this.viewBDetail(strBCode);
		
		System.out.printf("변경할 도서명 : %s\n >> ", booksDTO.getB_name());
		String strName = scan.nextLine();
		
		if(!strName.trim().isEmpty()) {
			booksDTO.setB_name(strName);
		}
		int ret = bDao.update(booksDTO);
		if(ret > 0) {
			System.out.println("변경완료");
		}else {
			System.out.println("변경실패");
		}
		System.out.println(bDao.findById(strBCode).toString());
	}
}






























