package com.biz.iolist.service.pro;

import org.apache.ibatis.session.SqlSession;

import com.biz.iolist.dao.IolistDao;
import com.biz.iolist.dao.ProductDao;
import com.biz.iolist.persistence.ProductDTO;

public class ProductServiceV4 extends ProductServiceV3 {

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

	protected void viewPDetail(ProductDTO proDTO) {

		System.out.println("==========================================");
		System.out.println("동일상품 있음");
		System.out.println("------------------------------------------");
		System.out.println("상품코드 : " + proDTO.getP_name());
		System.out.println("품목 : ");
		System.out.println("주매입처: ");
		System.out.println("매입단가 : " + proDTO.getP_iprice());
		System.out.println("매출단가 : " + proDTO.getP_oprice());
		System.out.println("------------------------------------------");

	}

	/*
	 * 상품코드를 입력받아서 insert 수행 상품코드를 입력받아서 있으면 다시 입력하도록 없으면 다음단계 진행
	 */
	public void insertProduct() {
		System.out.println("======================================");
		String strPCode;
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

		if (strPCode.equals("-q"))
			return;

		String strPName;
		while (true) {

			System.out.print("상품이름(-Q:quit) >> ");
			strPName = scan.nextLine();
			if (strPName.equals("Q"))
				break;
			if (strPName.trim().isEmpty()) {
				System.out.println("상품이름을 입력해야한다.");
				continue;
			}

			ProductDTO proDTO = proDao.findBySName(strPName);
			if (proDTO != null) {

				this.viewPDetail(proDTO);

				System.out.print("사용할라우?(Enter:사용, NO:다시입력)");
				String yesNo = scan.nextLine();
				if (yesNo.trim().isEmpty())
					break;

				continue;
			}
			break;
		} // 상품이름 입력 끝

		String strVAT = "1";
		int intIPrice = 0;
		while (true) {
			System.out.print("과세구분(1:과세, 0:면세, -Q:quit)");
			strVAT = scan.nextLine();
			
			if(strVAT.equals("-Q")) break;
			
			int intVAT = 1;
			try {
				intVAT = Integer.valueOf(strVAT);
				if (intVAT < 0 || intVAT < 1) {
					System.out.println("과세구분값은 0또는 1만!!");
					continue;

				}
			} catch (Exception e) {
				System.out.println("0또는 1만 가능 !!");
				
			
			}
			System.out.print("매입단가 >>");
			String strIPrice = scan.nextLine();
			
			try {
				intIPrice = 
							(int)(intVAT == 1 
							? Integer.valueOf(strIPrice) / 1.1
							: Integer.valueOf(strIPrice));
						
			} catch (Exception e) {

				System.out.println("숫자만 가능");
				continue;
			
			}
			break; 
		} // 매입단가 입력 끝
		
		if(strVAT.equals("Q")) return;
		String strOPrice ;
		int intOPrice = 0;
		while(true) {
			System.out.print("매출단가(-Q:quit) >>");
			
			strOPrice = scan.nextLine();
			if(strOPrice.equals("-Q")) break;
			
			try {
				intOPrice = Integer.valueOf(strOPrice);
			} catch (Exception e) {
				System.out.println("매출단가는 숫자만 가능");
				continue;
			}
			break;
			
		} // 매출단가 입력 끝
		
		if(strOPrice.equals("-Q")) return;
		
		ProductDTO proDTO = ProductDTO.builder()
				.p_code(strPCode)
				.p_name(strPName)
				.p_vat(strVAT)
				.p_iprice(intIPrice)
				.p_oprice(intOPrice)
				.build();
		
		int ret = proDao.insert(proDTO);
		if(ret > 0 ) System.out.println("상품등록 성공 !");
		else System.out.println("상품등록실패");
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
