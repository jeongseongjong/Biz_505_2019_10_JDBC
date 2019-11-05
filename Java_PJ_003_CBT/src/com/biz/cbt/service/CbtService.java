package com.biz.cbt.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.biz.cbt.config.DBConnection;
import com.biz.cbt.dao.CbtDao;
import com.biz.cbt.persistence.CbtDTO;

public class CbtService {

	public CbtDao cDao;
	Scanner scan;

	public CbtService() {

		scan = new Scanner(System.in);
		this.cDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(CbtDao.class);
	}

	public void ploblemMenu() {
		while (true) {
			System.out.println("==================================");
			System.out.println("1.문제입력 2.문제풀이 0.종료");
			System.out.println("----------------------------------");
			System.out.print("선택 >> ");
			String strMenu = scan.nextLine();
			int intMenu = Integer.valueOf(strMenu);
			if (intMenu == 0)
				break;
			if (intMenu == 1) {
				this.write();
			} else if (intMenu == 2) {
				this.solve();
			} else {
				System.out.println("메뉴를 입력하시오");
				continue;
			}
		}
	}

	

	public void write() {
		while (true) {
			System.out.println("====================================");
			System.out.println("1.문제등록 2.문제수정 3.문제삭제 0.종료");
			System.out.println("------------------------------------");
			System.out.print("선택 >> ");
			String strMenu = scan.nextLine();
			int intMenu = Integer.valueOf(strMenu);
			if (intMenu == 0)
				break;
			if (intMenu == 1) {
				this.insert();
			} else if (intMenu == 2) {
				this.update();
			} else if (intMenu == 3) {
				this.delete();
			} else {
				System.out.println("메뉴를 고르세용");
				continue;
			}
		}
	}

	public void insert() {

		System.out.println("====================================");
		String strPlo;
		String strEx1;
		String strEx2;
		String strEx3;
		String strEx4;
		String strAns;
		String strNum;
		while(true) {
			System.out.println("등록할 코드");
			strNum = scan.nextLine();
			if(strNum.trim().isEmpty()) {
				String strTMPNum = cDao.getMaxNum();
				int intNum = Integer.valueOf(strTMPNum.substring(0));
				intNum++;
				strNum = strTMPNum.substring(0);
				
				System.out.println("생성된 넘버 : " + strNum);
				System.out.println("사용할 거냐 ?");
				String strYN = scan.nextLine();
				if(strYN.trim().isEmpty()) {
					break;
				}else {
					continue;
				}
			}
			CbtDTO cbtDTO = new CbtDTO();
			cbtDTO.setCbt_code(strNum);
			break;
		}
		while (true) {
			System.out.println("등록할 문제 (종료 : 0) >> ");
			strPlo = scan.nextLine();
			if (strPlo.equals("0"))
				break;

			CbtDTO cList = cDao.findByPlo(strPlo);
			System.out.println("문제등록 완료");
			
			CbtDTO cbtDTO = new CbtDTO();
			cbtDTO.setCbt_plo(strPlo);
			break;
			
		}
		while (true) {
			System.out.print("등록할 보기1번 >> ");
			strEx1 = scan.nextLine();
			CbtDTO cList = cDao.findByPlo(strEx1);
			System.out.println("보기1번 등록 완료");
			
			CbtDTO cbtDTO = new CbtDTO();
			cbtDTO.setCbt_ex1(strEx1);
			break;
		}
		while (true) {
			System.out.print("등록할 보기2번 >> ");
			strEx2 = scan.nextLine();
			CbtDTO cList = cDao.findByPlo(strEx2);
			
			CbtDTO cbtDTO = new CbtDTO();
			cbtDTO.setCbt_ex2(strEx2);
			break;
		}
		while (true) {
			System.out.print("등록할 보기3번 >> ");
			strEx3 = scan.nextLine();
			CbtDTO cList = cDao.findByPlo(strEx3);
			
			CbtDTO cbtDTO = new CbtDTO();
			cbtDTO.setCbt_ex3(strEx3);
			break;
		}
		while (true) {
			System.out.print("등록할 보기4번 >> ");
			strEx4 = scan.nextLine();
			CbtDTO cList = cDao.findByPlo(strEx4);
			
			CbtDTO cbtDTO = new CbtDTO();
			cbtDTO.setCbt_ex4(strEx4);
			break;
		}
		while (true) {
			System.out.print("등록할 답 >> ");
			strAns = scan.nextLine();
			CbtDTO cList = cDao.findByPlo(strAns);
			
			CbtDTO cbtDTO = new CbtDTO();
			cbtDTO.setCbt_answer(Integer.valueOf(strAns));
			break;
		}
		
		CbtDTO cbtDTO = new CbtDTO();
		List<CbtDTO> cList = new ArrayList<CbtDTO>();
		cList.add(cbtDTO);
		
		System.out.println("등록완료");
		System.out.println(cDao.selectAll());
		
		int ret = cDao.insert(cbtDTO);
		if(ret > 0) {
			System.out.println("등록 완료");
		}else {
			System.out.println("등록 실패");
		}

	}

	public void update() {

		System.out.println("===============================");
		System.out.print("수정할 문제 >>");
		String strPlo = scan.nextLine();
		if (strPlo.trim().isEmpty()) {
			this.selectAll();
		}

		CbtDTO cbtDTO = cDao.findByPlo(strPlo);
		while (true) {
			System.out.print("수정할 보기1 >> ");
			String strEx1 = scan.nextLine();
			if (!strEx1.trim().isEmpty()) {
				cbtDTO.setCbt_ex1(strEx1);
			}
			System.out.print("수정할 보기2 >> ");
			String strEx2 = scan.nextLine();
			if (!strEx2.trim().isEmpty()) {
				cbtDTO.setCbt_ex1(strEx2);
			}
			System.out.print("수정할 보기3 >> ");
			String strEx3 = scan.nextLine();
			if (!strEx3.trim().isEmpty()) {
				cbtDTO.setCbt_ex1(strEx3);
			}
			System.out.print("수정할 보기4 >> ");
			String strEx4 = scan.nextLine();
			if (!strEx4.trim().isEmpty()) {
				cbtDTO.setCbt_ex1(strEx4);
			}
			int ret = cDao.update(cbtDTO);
			if(ret > 0) {
				System.out.println("수정완료");
			}else {
				System.out.println("수정실패");
			}
			
			System.out.println(cDao.findByPlo(strPlo).toString());
		}
		

	}

	private void solve() {
		// TODO Auto-generated method stub
		
	}
	
	public void delete() {
		// TODO Auto-generated method stub

	}

	public void selectAll() {

		List<CbtDTO> cList = cDao.selectAll();

		if (cList == null) {
			System.out.println("리스트 없음");
		} else {
			this.title(cList);
		}

	}

	public void title(List<CbtDTO> cList) {

		System.out.println("=========================================");
		System.out.println("정보처리기사 문제은행");
		System.out.println("=========================================");
		System.out.println("문제");
		System.out.println("-----------------------------------------");
		for (CbtDTO cbtDTO : cList) {
			this.body(cbtDTO);
		}

	}

	public void body(CbtDTO cbtDTO) {

		System.out.println(cbtDTO.getCbt_code() + "\t");
		System.out.println(cbtDTO.getCbt_plo() + "\t");
		System.out.println(cbtDTO.getCbt_ex1() + "\t");
		System.out.println(cbtDTO.getCbt_ex2() + "\t");
		System.out.println(cbtDTO.getCbt_ex3() + "\t");
		System.out.println(cbtDTO.getCbt_ex4() + "\t");

	}

	public void cbtDetail(String strNum) {
		CbtDTO cbtDTO = cDao.findByPlo(strNum);
		if (cbtDTO == null)

			System.out.printf("답 : %d ", cbtDTO.getCbt_answer() + "\n");
		System.out.printf("맞은 문제 : %s", cbtDTO.getCbt_yes() + "\n");
		System.out.printf("틀린 문제 : %s", cbtDTO.getCbt_no() + "\n");
		System.out.printf("맞은 개수  : %d", cbtDTO.getCbt_right() + "\t");
		System.out.printf("틀린 개수 : %d", cbtDTO.getCbt_wrong() + "\t");

	}

}
