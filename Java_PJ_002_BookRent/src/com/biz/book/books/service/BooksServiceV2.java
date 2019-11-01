package com.biz.book.books.service;

import java.util.List;

import org.apache.ibatis.session.SqlSession;

import com.biz.book.dao.BooksDao;
import com.biz.book.persistence.BooksDTO;

public class BooksServiceV2 extends BooksServiceV1 {

	SqlSession sqlSession = null;

	public void menuBooks() {
		System.out.println("==================================");
		System.out.println("도서대여 관리 시스템 v1");
		System.out.println("==================================");
		System.out.println("1.검색 2.등록 3.수정 4.삭제 0.종료 ");
		System.out.print("업무선택 >> ");
		String strMenu = scan.nextLine();
		int intMenu = Integer.valueOf(strMenu);

		if (intMenu == 1)
			this.SearchBName();
		if (intMenu == 2)
			this.insertBName();
		if (intMenu == 3)
			this.updateBName();
		if (intMenu == 4)
			this.deleteBName();
		if (intMenu == 0)
			;
	}

	public void insertBName() {
		System.out.println("=========================================");
		String strBCode ;
		while (true) {

			System.out.print("상품코드(Enter : 자동생성, Q:quit) >> ");
			strBCode = scan.nextLine();
			if (strBCode.equals("0"))
				break;

			if (strBCode.trim().isEmpty()) {
				// 코드 자동생성
				String strTMPCode = bDao.getMaxBCode();
				int intBCode = Integer.valueOf(strTMPCode.substring(2));
				intBCode++;
				strBCode = strTMPCode.substring(0, 2);
				strBCode += String.format("%04d", intBCode);

				System.out.println("생성된 코드 : " + strBCode);
				System.out.println("사용하시겠습니까 ?(Enter:Yes)");
				String strYesNo = scan.nextLine();
				if (strYesNo.trim().isEmpty()) {
					break;
				} else {
					continue;
				}
			}
			if (strBCode.length() != 6) {
				System.out.println("코드길이가 맞지 않다.");
				continue;

			}
			strBCode = strBCode.toUpperCase();
			if (strBCode.substring(0, 1).equalsIgnoreCase("B")) {
				System.out.println("상품코드는 첫글자가 B로 시작하라");
				continue;
			}
			try {
				Integer.valueOf(strBCode.substring(1));
			} catch (Exception e) {
				System.out.println("상품코드 2번째부터 숫자만 기입");
				continue;
			}
			if (bDao.findById(strBCode) != null) {
				System.out.println("이미 등록된 코드");
				continue;
			}

			break;

		}// Pcode 입력끝
		if(strBCode.equals("0"))
			return;

		System.out.println("=================================");
		while (true) {
			System.out.print("도서명 >> ");
			String strBName = scan.nextLine();
			if (strBName.equals("0"))
				break;

			if (strBName.trim().isEmpty()) {
				this.viewAllList();
				System.out.println("도서이름을 입력하라");
				continue;
			}
			System.out.println("도서입력완료. 리스트불러오기 전");
			List<BooksDTO> bList = bDao.findByName(strBName);
			System.out.println("도서입력완료. 리스트불러오기 후");
			if (bList.size() > 0) {
				System.out.println("도서명 중복");
				System.out.print("다시입력하라(Enter) >>");
				String yesNo = scan.nextLine();
				if (yesNo.trim().isEmpty()) {
					break;
				} else {
					continue;
				}

			}

			while (true) {
				System.out.println("등록할 저자 >> ");
				String strAuth = scan.nextLine();
				if (strAuth.trim().isEmpty()) {
					System.out.println("회원전화번호를 입력하라");
					continue;
				}
				System.out.println("등록할 출판사 >> ");
				String strComp = scan.nextLine();
				if (strComp.trim().isEmpty()) {
					System.out.println("회원전화번호를 입력하라");
					continue;
				}
				System.out.println("등록할 출판연도 >> ");
				String strYear = scan.nextLine();
				if (strYear.trim().isEmpty()) {
					System.out.println("회원전화번호를 입력하라");
					continue;
				}
				System.out.println("등록할 가격 >> ");
				String strPrice = scan.nextLine();
				if (strPrice.trim().isEmpty()) {
					System.out.println("회원전화번호를 입력하라");
					continue;
				}
				System.out.println("등록할 대여가격 >> ");
				String strRPrice = scan.nextLine();
				if (strRPrice.trim().isEmpty()) {
					System.out.println("회원전화번호를 입력하라");
					continue;
				}
				BooksDTO booksDTO = BooksDTO.builder()
						.b_code(strBCode)
						.b_name(strBName)
						.b_auther(strAuth)
						.b_comp(strComp)
						.b_year(Integer.valueOf(strYear))
						.b_iprice(Integer.valueOf(strPrice))
						.b_rprice(Integer.valueOf(strRPrice))
						.build();
				
				int ret = bDao.insert(booksDTO);
				if(ret > 0) {
					System.out.println("등록 성공");
				}else {
					System.out.println("등록 실패");
				}
				break;
			}
		}

	}

	public void deleteBName() {
		System.out.println("================================");
		System.out.println("삭제할 코드");
		String strBName = scan.nextLine();

		BooksDTO booksDTO = this.viewBDetail(strBName);

		if (booksDTO == null) {
			System.out.println("책이 음서여");
			return;
		}
		int ret = bDao.delete(strBName);
		if (ret > 0) {
			System.out.println("삭제완료");
		} else {
			System.out.println("삭제실패");
		}
	}
}
