package com.biz.book.books.service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.book.config.DBConnection;
import com.biz.book.dao.BooksDao;
import com.biz.book.dao.RentBookDao;
import com.biz.book.dao.UsersDao;
import com.biz.book.persistence.BooksDTO;
import com.biz.book.persistence.RentBookDTO;
import com.biz.book.persistence.UsersDTO;

public class RentBookServiceV1 {
	protected BooksDao bDao;
	protected UsersDao uDao;
	protected RentBookDao rDao;

	BooksServiceV2 bs = new BooksServiceV2();
	UsersServiceV2 us = new UsersServiceV2();
	
	Scanner scan = new Scanner(System.in);

	public RentBookServiceV1() {
		SqlSession sqlSession = DBConnection.getSqlSessionFactory().openSession(true);

		this.bDao = sqlSession.getMapper(BooksDao.class);
		this.uDao = sqlSession.getMapper(UsersDao.class);
		this.rDao = sqlSession.getMapper(RentBookDao.class);
	}

	public void bookListMenu() {
		while (true) {
			System.out.println("===================================");
			System.out.println("도서대여 관리");
			System.out.println("===================================");
			System.out.println("1.검색 2.대여 3.반납 0.종료");
			System.out.println("-----------------------------------");
			System.out.print("선택 >>");
			String strMenu = scan.nextLine();

			int intMenu = 0;
			try {
				intMenu = Integer.valueOf(strMenu);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (intMenu == 0)
				break;
			if (intMenu == 1)
				this.searchRent();
			if (intMenu == 2)
				this.rent();
			if (intMenu == 3)
				this.returnRent();
		}
		System.out.println("선택 종료");
		return;
	}

	public void searchRent() {

		System.out.println("===============================================");
		System.out.println("도서 대여 검색");
		System.out.println("-----------------------------------------------");
		System.out.println("1.대여목록 2.도서검색 3.회원검색 0.돌아가");
		System.out.println("===============================================");
		System.out.print("선택 >> ");
		String strMenu = scan.nextLine();
		int intMenu = Integer.valueOf(strMenu);
		if (intMenu == 0)
			return;
		if (intMenu == 1) {
			this.selectAll();
		} else if (intMenu == 2) {
			this.searchBName();
		} else if (intMenu == 3) {
			this.searchUName();
		}
		return;

	}

	

	private void selectAll() {

		RentBookDTO dto;
		List<RentBookDTO> rList = rDao.selectAll();
		if (rList == null || rList.size() < 1) {
			System.out.println("대여 리스트 없음");
		} else {
			this.viewList(rList);
		}
		
	}

	public void viewList(RentBookDTO rentDTO) {
		System.out.print(rentDTO.getRent_seq() + "\t");
		System.out.print(rentDTO.getRent_date() + "\t");
		System.out.print(rentDTO.getRent_return_date() + "\t");
		System.out.print(rentDTO.getRent_bcode() + "\t");
		System.out.print(rentDTO.getRent_ucode() + "\t");
		System.out.print(rentDTO.getRent_return_yn() + "\t");
		System.out.print(rentDTO.getRent_point() + "\n");
	}
	public void viewList(List<RentBookDTO> rList) {
		System.out.println("========================================");
		System.out.println("도서 리스트");
		System.out.println("----------------------------------------");
		System.out.println("코드\t대여일\t반납일\t도서코드\t회원코드\t대여여부\t포인트");
		System.out.println("========================================");
		for(RentBookDTO rDTO : rList) {
			this.viewList(rDTO);
		}
		System.out.println("========================================");
	}
	
	protected void viewRDetail(RentBookDTO rentDTO) {
		
		System.out.println("=========================================");
		System.out.print("코드 : " + rentDTO.getRent_seq() + "\t");
		System.out.print("대여일 : " + rentDTO.getRent_date() + "\t");
		System.out.print("반납일 : " + rentDTO.getRent_return_date() + "\t");
		System.out.print("도서코드 : " + rentDTO.getRent_bcode() + "\t");
		System.out.print("회원코드 : " + rentDTO.getRent_ucode() + "\t");
		System.out.print("대여여부 : " + rentDTO.getRent_return_yn() + "\t");
		System.out.print("포인트 : " + rentDTO.getRent_point() + "\n");
		System.out.println("=========================================");
	}

	protected void searchBName() {
		System.out.print("검색할 도서코드 >> ");
		String strCode = scan.nextLine();
		if(strCode.trim().isEmpty()) {
			bs.viewAllList();
		}
		RentBookDTO rentDTO = rDao.findByBCode(strCode);
		if(rentDTO == null) {
			System.out.println("검색결과 ㄴㄴ");
			return;
		}
		this.viewRDetail(rentDTO);
	}
	protected void searchUName() {

		System.out.print("검색할 회원코드 >>");
		String strCode = scan.nextLine();
		RentBookDTO rentDTO = rDao.findByUCode(strCode);
		if(rentDTO == null) {
			System.out.println("검색결과 ㄴㄴ");
			return;
		}
		this.viewRDetail(rentDTO);
	}
	
	protected void rent() {
		System.out.print("대여할 도서 코드 >> ");
		String strBook = scan.nextLine();
		BooksDTO booksDTO = bDao.findById(strBook);
		if(booksDTO == null) {
			System.out.println("검색 ㄴㄴ");
			return;
		}else {
			System.out.println("11");
		}
		
		System.out.print("대여하고싶은 회원 코드");
		String strUser = scan.nextLine();
		if(strUser.trim().isEmpty()) {
			us.viewAllList();
		}
		UsersDTO usersDTO = uDao.findById(strUser);
		if(usersDTO == null) {
			System.out.println("검색 ㄴㄴ");
			return;
		}else {
			System.out.println("22");
		}
		LocalDate localDate = LocalDate.now();
		String lDate = localDate.toString();
		System.out.println("33");
		
		Calendar cal = Calendar.getInstance();
		Date date = new Date(System.currentTimeMillis());
		cal.setTime(date);
		cal.add(Calendar.DATE, 14);
		SimpleDateFormat sd = new SimpleDateFormat("yyyy-MM-dd");
		String rentDate = sd.format(date);
		String returnDate = sd.format(cal.getTime());
		
		String rent_return_yn = "y";
		int point = 0;
		
		RentBookDTO rentDTO = rDao.findByUCode(strUser);
		if(rentDTO != null ) {
			point = rentDTO.getRent_point();
		}
				rentDTO = RentBookDTO.builder()
				.rent_date(rentDate)
				.rent_return_date(returnDate)
				.rent_bcode(strBook)
				.rent_ucode(strUser)
				.rent_return_yn(rent_return_yn)
				.rent_point(point)
				.build();
		
			int ret = rDao.insert(rentDTO);
			if(ret > 0 ) {
				System.out.println("대여 완료");
			}else {
				System.out.println("대여 실패");
			}
			return;
		
	}

	protected void returnRent() {

		System.out.print("반납코드 >>");
		String strCode = scan.nextLine();
		BooksDTO booksDTO = bDao.findById(strCode);
		if(booksDTO == null) {
			System.out.println("대여정보 없음");
		}else {
			System.out.println("반납완료");
		}
		RentBookDTO rentDTO = rDao.findByBCode(strCode);
		if(rentDTO == null || !rentDTO.getRent_return_yn().equalsIgnoreCase("y")) {
			System.out.println("대여 중 ㄴㄴ");
			return;
		}
		rentDTO.setRent_return_yn(null);
		
		LocalDate local = LocalDate.now();
		if(rentDTO.getRent_return_date().compareTo(local.toString()) >= 0) {
			rentDTO.setRent_point(rentDTO.getRent_point() + 5);
		}
		rDao.update(rentDTO);
		System.out.println("도서반납 끝");
		
	}
	
}
