package com.biz.iolist.config.service;

import org.apache.ibatis.session.SqlSession;

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

	}

	/*
	 * 상품코드를 입력받아서 insert 수행 상품코드를 입력받아서 있으면 다시 입력하도록 없으면 다음단계 진행
	 */
	public void insertProduct() {
		while (true) {
			System.out.print("상품코드(-Q:중단) >> ");
			String strCode = scan.nextLine();
			if (strCode.equals("-Q"))
				break;
			if (strCode.trim().length() < 1) {
				System.out.println("코드를 입력하라");
				continue;
			}
			System.out.print("상품이름(-Q:중단) >> ");
			String strName = scan.nextLine();
			if (strName.equals("-Q"))
				break;
			if (strName.trim().length() < 1) {
				System.out.println("이름를 입력하라");
				continue;
			}
			System.out.print("매입(-Q:중단) >> ");
			String strIprice = scan.nextLine();
			if (strIprice.equals("-Q"))
				break;
			if (strIprice.trim().length() < 1) {
				System.out.println("매입단가를 입력하라");
				continue;
			}
			System.out.print("매출(-Q:중단) >> ");
			String strOprice = scan.nextLine();
			if (strOprice.equals("-Q"))
				break;
			if (strOprice.trim().length() < 1) {
				System.out.println("매출단가를 입력하라");
				continue;
			}
			ProductDTO proDTO = ProductDTO.builder()
					.p_code(strCode)
					.p_name(strName)
					.p_iprice(Integer.valueOf(strIprice))
					.p_oprice(Integer.valueOf(strOprice))
					.build();
			ProductDao proDao = sqlSession.getMapper(ProductDao.class);
			int ret = proDao.insert(proDTO);
			if(ret > 0)

		}

	}

	/*
	 * 상품코드를 입력받아서 delete 수행
	 */
	public void deleteProduct() {

	}
}
