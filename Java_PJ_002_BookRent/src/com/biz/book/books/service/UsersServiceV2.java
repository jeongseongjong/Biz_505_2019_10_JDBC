package com.biz.book.books.service;

import org.apache.ibatis.session.SqlSession;

import com.biz.book.persistence.UsersDTO;

public class UsersServiceV2 extends UsersServiceV1 {

	SqlSession sqlSession = null;

	public void menuUsers() {
		while (true) {
			System.out.println("================================");
			System.out.println("회원 관리 시스템 v1");
			System.out.println("================================");
			System.out.println("1.검색 2.등록 3.수정 4.삭제 0.종료");
			System.out.print("업무선택 >> ");
			String strMenu = scan.nextLine();
			int intMenu = 0;
			try {
				intMenu = Integer.valueOf(strMenu);
			} catch (Exception e) {
				// TODO: handle exception
			}

			if (intMenu == 1)
				this.SearchUName();
			if (intMenu == 2)
				this.insertUName();
			if (intMenu == 3)
				this.updateUName();
			if (intMenu == 4)
				this.deleteUName();
			if (intMenu == 0)
				break;
		}

	}

	public void insertUName() {
		System.out.println("===================================");
		String strUCode;

		while (true) {
			System.out.println("회원 코드 생성(자동생성 : Enter) >>");
			strUCode = scan.nextLine();
			if (strUCode.equals("0"))
				break;
			if (strUCode.trim().isEmpty()) {
				String strTMPCode = uDao.getMaxUCode();
				int intUCode = Integer.valueOf(strTMPCode.substring(1));
				intUCode++;
				strUCode = strTMPCode.substring(0, 1);
				strUCode = String.format("S%05d", intUCode);

				System.out.println("생성된 코드 : " + strUCode);
				System.out.println("사용하려면 Enter");
				String strCode = scan.nextLine();
				if (strCode.trim().isEmpty()) {
					break;
				} else {
					continue;
				}
			}
			if(strUCode.length() != 6) {
				System.out.println("회원코드 오류");
				continue;

		}
			strUCode = strUCode.toUpperCase();
			if(strUCode.substring(0, 1).equalsIgnoreCase("S")) {
				System.out.println("도서코드는 S");
				continue;
			}
			try {
				Integer.valueOf(strUCode.substring(1));
			} catch (Exception e) {
				// TODO: handle exception
				System.out.println("두번째자리는 숫자이다");
				continue;
			}
			if(uDao.findById(strUCode) != null) {
				System.out.println("코드중복");
				continue;
			}
			break;

	}
		if(strUCode.equals("0"))
			return;
		
		while(true) {
			System.out.println("등록할 회원 명 >> ");
			String strName = scan.nextLine();
			if(strName.trim().isEmpty()) {
				System.out.println("회원이름을 입력하라");
				continue;
			}
			System.out.println("등록할 회원 전화번호 >> ");
			String strTel = scan.nextLine();
			if(strTel.trim().isEmpty()) {
				System.out.println("회원전화번호를 입력하라");
				continue;
			}
			System.out.println("등록할 회원 주소 >> ");
			String strAddr = scan.nextLine();
			if(strAddr.trim().isEmpty()) {
				System.out.println("회원주소를 입력하라");
				continue;
			}
			UsersDTO usersDTO = UsersDTO.builder()
					.u_code(strUCode)
					.u_name(strName)
					.u_tel(strTel)
					.u_addr(strAddr)
					.build();
			int ret = uDao.insert(usersDTO);
			if(ret > 0) {
				System.out.println("등록 완료");
			}else {
				System.out.println("등록 실패");
			}
			break;
			
		}

	}

	public void deleteUName() {

		System.out.println("====================");
		System.out.print("삭제할 회원 >> ");
		String strUName = scan.nextLine();

		UsersDTO usersDTO = this.viewUDetail(strUName);

		if (usersDTO == null) {
			System.out.println("회원이 없다");
			return;
		}
		int ret = uDao.delete(strUName);
		if (ret > 0) {
			System.out.println("삭제 완료");
		} else {
			System.out.println("삭제 실패");
		}
	}

}
