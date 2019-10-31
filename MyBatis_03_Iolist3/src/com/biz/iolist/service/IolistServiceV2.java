package com.biz.iolist.service;

import java.util.List;

import com.biz.iolist.dao.IolistViewDao;
import com.biz.iolist.persistence.DeptDTO;
import com.biz.iolist.persistence.IolistVO;

public class IolistServiceV2 extends IolistServiceV1 {

	/*
	 * 매입매출 등록 현재날짜 자동등록 거래처검색 거래처코드입력 입력한 코드 검증
	 * 
	 * 상품검색 상품코드입력 입력한 코드 검증
	 * 
	 * 매입매출구분입력
	 * 
	 * 매입,매출에 따라서 매입단가, 매출단가 가져오기(세팅)
	 * 
	 * 매입합계 또는 매출합계 계산
	 * 
	 * insert
	 * 
	 * 추가된 데이터 보여주기
	 */
	public void iolistInsert() {

	}

	private void viewAllList(List<DeptDTO> deptList) {
		// TODO Auto-generated method stub

	}

	public void searchDCode() {
		// this.viewAllList();
		System.out.println("======================================");
		String strDCode;
		while (true) {
			System.out.print("거래처 코드 검색 >>");
			strDCode = scan.nextLine();

			List<IolistVO> vList = null;

			vList = viewDao.findByDCode(strDCode);
			for(IolistVO vo : vList) {
				System.out.println(vo.toString());
			}
		}
	}
}
