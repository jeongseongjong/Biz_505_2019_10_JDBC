package com.biz.book.books.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biz.book.persistence.UsersDTO;

public class UsersServiceV2 extends UsersServiceV1 {

	SqlSession sqlSession = null;

	public void menuUsers() {
		System.out.println("================================");
		System.out.println("회원 관리 시스템 v1");
		System.out.println("================================");
		System.out.println("1.검색 2.등록 3.수정 4.삭제 0.종료");
		System.out.print("업무선택 >> ");
		String strMenu = scan.nextLine();
		int intMenu = Integer.valueOf(strMenu);

		if (intMenu == 1)
			this.SearchUName();
		if (intMenu == 2)
			this.insertUName();
		if (intMenu == 3)
			this.updateUName();
		if (intMenu == 4)
			this.deleteUName();
		if (intMenu == 0);
			
			

	}

	public void insertUName() {

		System.out.println("===================================");
		String strUCode;
		String strUName;
		String strUTel;
		String strUAddr;
		while (true) {

			System.out.println("회원코드 추가(Enter : 자동생성) :  ");
			strUCode = scan.nextLine();
			if (strUCode.equals("0"))
				break;

			if (strUCode.trim().isEmpty()) {
				String strTMPCode = uDao.getMaxUCode();
				int intUCode = Integer.valueOf(strTMPCode.substring(2));
				intUCode++;
				strUCode = strTMPCode.substring(0, 2);
				strUCode += String.format("%04d", intUCode);
				System.out.println("생성된 코드 : " + strUCode);
				System.out.println("사용할거냐 ? (Enter:Yes)");
				String strYesNo = scan.nextLine();
				if (strYesNo.trim().isEmpty())
					break;
				else
					continue;
			}
			if(strUCode.length() != 6) {
				System.out.println("코드길이 오류");
				continue;
			}
			strUCode = strUCode.toUpperCase();
			if (strUCode.substring(0, 1).equalsIgnoreCase("U")) {
				System.out.println("상품코드는 첫글자가 U로 시작하라");
				continue;
			}
			try {
				Integer.valueOf(strUCode.substring(1));
			} catch (Exception e) {
				System.out.println("상품코드 2번째부터 숫자만 기입");
				continue;
			}
			if (uDao.findById(strUCode) != null) {
				System.out.println("이미 등록된 코드");
				continue;
			}

			break;

		} // UCODE 끝

		if(strUCode.equals("0"))
			return;
		
		System.out.println("===================================");
		while(true) {
			System.out.print("회원명 >> ");
			strUName = scan.nextLine();
			if(strUName.equals("0"))break;
			
			if(strUName.trim().isEmpty()) {
				this.viewAllList();
				System.out.println("회원이름을 입력하라");
				continue;
			}
			
			System.out.println("회원입력 완료.리스트 전");
			List<UsersDTO> uList = uDao.findByName(strUName);
			System.out.println("회원입력 완료 리스트 후");
			if(uList.size() > 0) {
				System.out.println("회원명 중복");
				System.out.print("다시 입력해 (Enter) >> ");
				String yesNo = scan.nextLine();
				if(yesNo.trim().isEmpty())
					break;
				
				continue;
			}
			
			while(true) {
				System.out.print("회원 전화번호 >>");
				String strTel = scan.nextLine();
				if(strTel.equals("0"))break;
				
				if(strTel.trim().isEmpty()) {
					this.viewAllList();
					System.out.println("전화번호를 입력하라");
					continue;
				}
				break;
			}
			while(true) {
				System.out.print("회원 주소 등록 >> ");
				String strAddr = scan.nextLine();
				if(strAddr.equals("0")) break;
				
				if(strAddr.trim().isEmpty()) {
					this.viewAllList();
					System.out.println("주소를 입력하라");
					continue;
				}
				break;
			}
			break;
		}
		
	}
	public void deleteUName() {
		System.out.println("====================");
		System.out.print("삭제할 회원 >> ");
		String strUName = scan.nextLine();
		
		UsersDTO usersDTO = this.viewUDetail(strUName);
		
		if(usersDTO == null) {
			System.out.println("회원이 없다");
			return;
		}
		int ret = uDao.delete(strUName);
		if(ret > 0) {
			System.out.println("삭제 완료");
		}else {
			System.out.println("삭제 실패");
		}
		 
	}

}


































