package com.biz.book.books.service;

import java.util.List;
import java.util.Scanner;

import com.biz.book.config.DBConnection;
import com.biz.book.dao.UsersDao;
import com.biz.book.persistence.BooksDTO;
import com.biz.book.persistence.UsersDTO;

public class UsersServiceV1 {

	protected UsersDao uDao;
	Scanner scan;

	public UsersServiceV1() {
		scan = new Scanner(System.in);
		this.uDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(UsersDao.class);
	}

	public void viewAllList() {
		List<UsersDTO> uList = uDao.selectAll();

		if (uList == null || uList.size() < 1) {
			System.out.println("리스트 없음");

		} else {
			this.viewUserList(uList);
		}
	}

	public void SearchUName() {
		System.out.println("=====================================");
		System.out.print("검색할 회원 >> ");
		String strUName = scan.nextLine();

		List<UsersDTO> uList = null;
		if (strUName.trim().isEmpty()) {
			uList = uDao.selectAll();
		} else {
			uList = uDao.findByName(strUName);
		}
		this.viewUserList(uList);
	}

	protected void viewList(UsersDTO usersDTO) {
		System.out.print(usersDTO.getU_code() + "\t");
		System.out.print(usersDTO.getU_name() + "\t");
		System.out.print(usersDTO.getU_tel() + "\t");
		System.out.print(usersDTO.getU_addr() + "\n");
	}

	private void viewList() {

		System.out.println("===============================");
		System.out.println("회원 리스트");
		System.out.println("================================");
		System.out.println("코드\t이름\t전화번호\t주소");
		System.out.println("---------------------------------");
		List<UsersDTO> uList = uDao.selectAll();
		this.viewUserList(uList);
		System.out.println("=====================================");
	}

	public void viewUserList(List<UsersDTO> uList) {
		for (UsersDTO dto : uList) {
			this.viewList(dto);
		}
	}

	protected UsersDTO viewUDetail(String strUCode) {

		UsersDTO usersDTO = uDao.findById(strUCode);

		if (usersDTO == null)
			return null;

		System.out.println("================================");
		System.out.printf("회원코드 : %s\t", usersDTO.getU_code());
		System.out.printf("회원명 : %s\t", usersDTO.getU_name());
		System.out.printf("전화번호 : %s\t", usersDTO.getU_tel());
		System.out.printf("주소 : %s\n", usersDTO.getU_addr());
		System.out.println("================================");
		return usersDTO;
	}

	public void updateUName() {

		System.out.println("====================");
		System.out.print("수정할 회원 코드 >> ");
		String strUCode = scan.nextLine();

		if (strUCode.trim().isEmpty()) {
			this.viewAllList();
			
		}
		strUCode = strUCode.toUpperCase();
		UsersDTO usersDTO = this.viewUDetail(strUCode);
		
		System.out.println("1");

		System.out.printf("변경할 회원명 : [%s]  >> ", usersDTO.getU_name());
		System.out.println("2");
		String strUName = scan.nextLine();

		if (!strUName.trim().isEmpty()) {
			usersDTO.setU_name(strUName);
		}

		while (true) {
			System.out.printf("변경할 전화번호 : (%s) >> ", usersDTO.getU_tel());
			String strUTel = scan.nextLine();

			if (strUTel.trim().isEmpty()) {
				this.viewAllList();
				continue;
			} else {
				usersDTO.setU_tel(strUTel);
			}
			break;
		}
		while (true) {
			System.out.printf("변경할 주소 : %s\n >> ", usersDTO.getU_addr());
			String strUAddr = scan.nextLine();
			if (strUAddr.equals("0"))
				return;
			if (strUAddr.trim().isEmpty()) {
				this.viewAllList();
				continue;
			} else {
				usersDTO.setU_addr(strUAddr);
			}
			break;
		}

		int ret = uDao.update(usersDTO);
		if (ret > 0) {
			System.out.println("변경완료");
		} else {
			System.out.println("변경실패");
		}
	}
}
