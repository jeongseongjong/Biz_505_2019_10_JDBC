package com.biz.service;

import java.util.Scanner;

import com.biz.persistence.AddrDTO;
import com.biz.persistence.dao.AddrDao;
import com.biz.persistence.dao.AddrDaoImp;

public class AddrCUDServiceV1 {

	private AddrDao addrDao = null;
	private Scanner scan = null;

	public AddrCUDServiceV1() {
		scan = new Scanner(System.in);
		addrDao = new AddrDaoImp();
	}

	public void inputAddr() {
		while (true) {
			System.out.println("=============================");
			System.out.println("이름등록");
			System.out.println("=============================");

			String strName = null;
			while (true) {

				System.out.print("1.이름(-Q:quit) >> ");
				strName = scan.nextLine();
				if (strName.equals("-Q"))
					break;
				if (strName.isEmpty()) {
					System.out.println("이름을 입력하라");
					continue;
				}
				break;
			}
			if (strName.equals("-Q"))
				break;

			System.out.print("2.전화번호(-Q:quit) >> ");
			String strTel = scan.nextLine();
			if (strTel.equals("-Q"))
				break;

			System.out.print("3.주소(-Q:quit) >> ");
			String strAddr = scan.nextLine();
			if (strAddr.equals("-Q"))
				break;

			System.out.println("4.관계(-Q:quit) >> ");
			String strChain = scan.nextLine();
			if (strChain.equals("-Q"))
				break;

			AddrDTO addrDTO = AddrDTO.builder().A_name(strName).A_tel(strTel).A_addr(strAddr).A_chain(strChain).build();

			int ret = addrDao.insert(addrDTO);
			if (ret > 0)
				System.out.println("주소 저장 완료");
			else
				System.out.println("주소 저장 실패");
		}
	}

	public void deleteAddr() {
		while (true) {
			System.out.println("======================================");
			System.out.print("삭제할 ID : ");
			String strID = scan.nextLine();
			if (strID.equals("0"))
				break;
			long longID = Long.valueOf(strID);

			AddrDTO dto = AddrDao.findById(longID);
			if (dto == null) {
				System.out.println("ID 없음");
				continue;
			}
			AddrDao.delete(longID);
		}
	}
}
