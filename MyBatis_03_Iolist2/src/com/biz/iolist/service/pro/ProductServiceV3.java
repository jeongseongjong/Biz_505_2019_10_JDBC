package com.biz.iolist.service.pro;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.dao.IolistDao;
import com.biz.iolist.dao.ProductDao;
import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV3 extends ProductServiceV2 {

	SqlSession sqlSession = null;

	public void menuProduct() {
		System.out.println("===============================");
		System.out.println("대한쇼핑몰 상품관리 시스템 v3");
		System.out.println("===============================");
		System.out.println("1.등록 2.수정 3.삭제 4.검색 0.종료");
		System.out.print("업무선택 >> ");
		String strMenu = scan.nextLine();
		int intMenu = Integer.valueOf(strMenu);

		if (intMenu == 1) {
			this.insertProduct();
		} else if (intMenu == 2) {
			this.searchPName();
			this.proUpdate();
		} else if (intMenu == 3) {
			this.searchPName();
			this.deleteProduct();
		} else if (intMenu == 4) {
			this.searchPName();
		} else if (intMenu == 0) {

		}

	}

	/*
	 * 상품코드를 입력받아서 insert 수행 상품코드를 입력받아서 있으면 다시 입력하도록 없으면 다음단계 진행
	 */
	public void insertProduct() {
		System.out.println("======================================");
		String strPCode ;
		while (true) {
			
			System.out.print("상품코드(Enter : 자동생성, Q:quit) >> ");
			strPCode = scan.nextLine();
			if (strPCode.equals("0"))
				break;

			if (strPCode.trim().isEmpty()) {
				// 코드 자동생성
				String strTMPCode = proDao.getMaxPCode();
				int intPCode = Integer.valueOf(strTMPCode.substring(1));
				intPCode++;
				strPCode = strTMPCode.substring(0, 1);
				strPCode += String.format("%04d", intPCode);

				System.out.println("생성된 코드 : " + strPCode);
				System.out.println("사용하시겠습니까 ?(Enter:Yes)");
				String strYesNo = scan.nextLine();
				if (strYesNo.trim().isEmpty())
					break;
				else
					continue;
			}
			if (strPCode.length() != 5) {
				System.out.println("코드길이가 맞지 않다.");
				continue;

			}
			strPCode = strPCode.toUpperCase();
			if (strPCode.substring(0, 1).equalsIgnoreCase("P")) {
				System.out.println("상품코드는 첫글자가 P로 시작하라");
				continue;
			}
			try {
				Integer.valueOf(strPCode.substring(1));
			} catch (Exception e) {
				System.out.println("상품코드 2번째부터 숫자만 기입");
				continue;
			}
			if (proDao.findById(strPCode) != null) {
				System.out.println("이미 등록된 코드");
				continue;
			}

			break;

		} // Pcode 입력끝

		if(strPCode.equals("-q")) return;
		
		String strPName ;
		while(true) {
			
			System.out.print("상품이름(-Q:quit) >> ");
			strPName = scan.nextLine();
			if(strPName.equals("Q")) break;
			if(strPName.trim().isEmpty()) {
				System.out.println("상품이름을 입력해야한다.");
				continue;
			}
			
			
			ProductDTO proDTO = proDao.findBySName(strPName);
			
			
			if(proDTO != null) {
				System.out.println("==========================================");
				System.out.println("동일상품 있음");
				System.out.println("------------------------------------------");
				System.out.println("상품코드 : " + proDTO.getP_name());
				System.out.println("품목 : " );
				System.out.println("주매입처: ");
				System.out.println("매입단가 : " + proDTO.getP_iprice());
				System.out.println("매출단가 : " + proDTO.getP_oprice());
				System.out.println("------------------------------------------");
				System.out.print("사용할라우?(Enter:사용, NO:다시입력)");
				String yesNo = scan.nextLine();
				if(yesNo.trim().isEmpty()) break;
				
				continue;
			}
		}
	}

	/*
	 * 상품코드를 입력받아서 delete 수행
	 */
	public void deleteProduct() {

		System.out.println("========================");
		System.out.print("삭제할 ID(Q:quit) >> ");
		String strPCode = scan.nextLine();

		ProductDTO proDTO = this.viewPDetail(strPCode);

		if (proDTO == null) {
			System.out.println("상품코드가 없다.");
			return;
		}

		int ret = proDao.delete(strPCode);
		if (ret > 0) {
			System.out.println("삭제완료");

		} else {
			System.out.println("삭제실패");
		}

	}
}
