package com.biz.book.books.service;

import java.util.List;
import java.util.Scanner;

import com.biz.book.config.DBConnection;
import com.biz.book.dao.UsersDao;
import com.biz.book.persistence.UsersDTO;

public class UsersServiceV1 {

	protected UsersDao uDao ;
	Scanner scan;
	
	public UsersServiceV1() {
		scan = new Scanner(System.in);
		this.uDao = DBConnection
				.getSqlSessionFactory()
				.openSession(true)
				.getMapper(UsersDao.class);
	}
	public void viewAllList() {
		List<UsersDTO> uList = uDao.selectAll();
		
		if(uList == null || uList.size() < 1) {
			System.out.println("리스트 없음");
			
		}else {
			this.viewList(uList);
		}
	}
	
	public void SearchUName() {
		System.out.println("=====================================");
		System.out.print("검색할 회원 >> ");
		String strUName = scan.nextLine();
		
		List<UsersDTO> uList = null;
		if(strUName.trim().isEmpty()) {
			uList = uDao.selectAll();
		}else {
			uList = uDao.findByName(strUName);
		}
		this.viewList(uList);
	}
	
	protected void viewList(UsersDTO usersDTO) {
		System.out.print(usersDTO.getU_code() + "\t");
		System.out.print(usersDTO.getU_name() + "\t");
		System.out.print(usersDTO.getU_tel() + "\t");
		System.out.print(usersDTO.getU_addr() + "\n");
	}
	
	private void viewList(List<UsersDTO> uList) {

		System.out.println("===============================");
		System.out.println("회원 리스트");
		System.out.println("================================");
		System.out.println("코드\t이름\t전화번호\t주소");
		System.out.println("---------------------------------");
		for(UsersDTO usersDTO : uList) {
			this.viewList(usersDTO);
		}
		System.out.println("=====================================");
	}
	
	protected UsersDTO viewUDetail(String strUCode) {
		UsersDTO usersDTO = uDao.findById(strUCode);
		
		System.out.println("1");
		if(usersDTO == null) return null;
		System.out.println("2");
		
		System.out.println("================================");
		System.out.printf("회원코드 : %s\t", usersDTO.getU_code());
		System.out.printf("회원명 : %s\t", usersDTO.getU_name());
		System.out.printf("전화번호 : %s\t", usersDTO.getU_tel());
		System.out.printf("주소 : %s\n", usersDTO.getU_addr());
		return usersDTO;
	}
	public void updateUName() {
		System.out.println("====================");
		System.out.println("수정할 회원 코드");
		String strUCode = scan.nextLine();
		if(strUCode.trim().isEmpty()) {
			this.viewAllList();
		}
		
		strUCode = strUCode.toUpperCase();
		
		UsersDTO usersDTO = this.viewUDetail(strUCode);
		
		System.out.printf("변경할 회원명 : %s\n >> " , usersDTO.getU_name());
		String strUName = scan.nextLine();
		
		if(!strUName.trim().isEmpty()) {
			usersDTO.setU_name(strUName);
		}
		int ret = uDao.update(usersDTO);
		if(ret > 0 ) {
			System.out.println("변경완료");
		}else {
			System.out.println("변경실패");
		}
		System.out.println(uDao.findById(strUCode).toString());
	}
	
	
	
	
	
	
	
	
}


















