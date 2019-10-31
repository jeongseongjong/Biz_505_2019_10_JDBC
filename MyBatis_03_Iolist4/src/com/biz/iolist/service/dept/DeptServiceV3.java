package com.biz.iolist.service.dept;

import java.util.List;

import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV3 extends DeptServiceV2 {

	/*
	 * 키보드에서 정보를 입력받아 DB에 추가 1. 거래코드 : 자동생성 (기존 코드가 있으면 추가 금지) 2. 상호는 같은 상호명이 있더라도
	 * 대표자 명이다르면 입력가능
	 */
	public void deptInsert() {

		System.out.println("=======================================");
		String strDCode;
		while (true) {

			System.out.println("거래처 코드 (Enter : 자동생성) >> ");
			strDCode = scan.nextLine();
			if (strDCode.equals("0"))
				break;

			if (strDCode.trim().isEmpty()) {

				String strTMPCode = deptDao.getMaxDCode();
				int intDCode = Integer.valueOf(strTMPCode.substring(1));
				intDCode++;
				strDCode = strTMPCode.substring(0, 1);
				strDCode += String.format("%04d", intDCode);

				System.out.println("생성된 코드 : " + strDCode);
				System.out.print("사용할라우 ? (Enter:Yes)");
				String strYesNo = scan.nextLine();
				if (strYesNo.trim().isEmpty())
					break;
				else
					continue;

			}
			if (strDCode.length() != 5) {
				System.out.println("코드길이가 안맞아유");
				continue;
			}
			strDCode = strDCode.toUpperCase();
			if (strDCode.substring(0, 1).equalsIgnoreCase("D")) {
				System.out.println("상품코드 첫글자가 D");
				continue;
			}
			try {
				Integer.valueOf(strDCode.substring(1));
			} catch (Exception e) {
				System.out.println("상품코드 2번재부터 숫자만기입");
				continue;

			}
			if (deptDao.findById(strDCode) != null) {
				System.out.println("이미등록된 코드");
				continue;
			}
			break;
		}
		if (strDCode.equals("-Q"))
			return;

		String strDName;
		String strDCEO;
		String strTel;
		String strAddr;

		while (true) {
			System.out.println("상호명(-Q:quit) >> ");
			strDName = scan.nextLine();
			if (strDName.equals("-Q"))
				break;
			if (strDName.trim().isEmpty()) {
				System.out.println("상호명을 입력");
				continue;
			}
			DeptDTO deptDTO = deptDao.findByDName(strDName);
			if (deptDTO != null) {
				this.viewDetail(deptDTO);
				System.out.print("사용하실 ? >> ");
				String YesNo = scan.nextLine();
				if (YesNo.trim().isEmpty())
					break;
				continue;
			}
			break;
		}
		
		while (true) {
			System.out.println("대표자명(-Q:quit) >> ");
			strDCEO = scan.nextLine();
			if (strDCEO.equals("-Q"))
				break;
			if (strDCEO.trim().isEmpty()) {
				System.out.println("대표자명을 입력");
				continue;
			}
			DeptDTO deptDTO = deptDao.findByDCEO(strDCEO);
			if (deptDTO != null) {
				this.viewDetail(deptDTO);
				System.out.print("사용하실 ? >> ");
				String YesNo = scan.nextLine();
				if (YesNo.trim().isEmpty())
					break;
				continue;
			}
			break;
		}

		while (true) {

			System.out.println("전화번호(-Q:quit) >> ");
			strTel = scan.nextLine();
			if (strTel.equals("-Q"))
				break;
			if (strTel.trim().isEmpty()) {
				System.out.println("전화번호를 입력");
				continue;
			}
			System.out.println("주소(-Q:quit) >> ");
			strAddr = scan.nextLine();
			if (strAddr.equals("-Q"))
				break;
			if (strAddr.trim().isEmpty()) {
				System.out.println("주소를 입력");
				continue;
			}
			DeptDTO deptDTO = DeptDTO.builder()
					.d_code(strDCode)
					.d_name(strDName)
					.d_ceo(strDCEO)
					.d_tel(strTel)
					.d_addr(strAddr)
					.build();
				int ret = deptDao.insert(deptDTO);
				if (ret > 0) {
					System.out.println("변경완료");

				} else {
					System.out.println("변경실패");
				}
				break;
			}
		}

	}

