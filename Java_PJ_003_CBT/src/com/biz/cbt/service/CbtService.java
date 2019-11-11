package com.biz.cbt.service;

import java.util.ArrayList;
import java.util.Collections;
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
		this.cDao = DBConnection
				.getSqlSessionFactory()
				.openSession(true)
				.getMapper(CbtDao.class);
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
				this.quiz();
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
		CbtDTO cbtDTO = new CbtDTO();
		while (true) {
			System.out.println("등록할 문제 (종료 : 0) >> ");
			strPlo = scan.nextLine();
			if (strPlo.equals("0"))
				break;
			cbtDTO.setCb_plo(strPlo);
			break;
			
		}
		while (true) {
			System.out.print("등록할 보기1번 >> ");
			strEx1 = scan.nextLine();
			if(!strEx1.trim().isEmpty()) {
				System.out.println("보기1번 등록 완료");	
			}
			cbtDTO.setCb_ex1(strEx1);
			break;
		}
		while (true) {
			System.out.print("등록할 보기2번 >> ");
			strEx2 = scan.nextLine();
			if(!strEx2.trim().isEmpty()) {
				System.out.println("보기2번 등록 완료");	
			}
			cbtDTO.setCb_ex2(strEx2);
			break;
		}
		while (true) {
			System.out.print("등록할 보기3번 >> ");
			strEx3 = scan.nextLine();
			if(!strEx3.trim().isEmpty()) {
				System.out.println("보기3번 등록 완료");	
			}
			cbtDTO.setCb_ex3(strEx3);
			break;
		}
		while (true) {
			System.out.print("등록할 보기4번 >> ");
			strEx4 = scan.nextLine();
			if(!strEx4.trim().isEmpty()) {
				System.out.println("보기4번 등록 완료");	
			}
			cbtDTO.setCb_ex4(strEx4);
			break;
		}
		while (true) {
			System.out.print("등록할 답 >> ");
			strAns = scan.nextLine();
			int intanswer = 0;
			try {
				intanswer = Integer.valueOf(strAns);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if(intanswer==1) {
				cbtDTO.setCb_answer(cbtDTO.getCb_ex1());
			}else if(intanswer==2){
				cbtDTO.setCb_answer(cbtDTO.getCb_ex2());
			}else if(intanswer==3) {
				cbtDTO.setCb_answer(cbtDTO.getCb_ex3());
			}else if(intanswer==4) {
				cbtDTO.setCb_answer(cbtDTO.getCb_ex4());
			}else {
				System.out.println("다시 등록해주세요");
				continue;
			}
			break;
		}
		
		
		List<CbtDTO> cList = new ArrayList<CbtDTO>();
		cList.add(cbtDTO);
		
		int ret = cDao.insert(cbtDTO);
		if(ret > 0) {
			System.out.println("등록 완료");
		}else {
			System.out.println("등록 실패");
		}
		return;
	}

	public void update() {
		int intAns = 0;
		CbtDTO cbtDTO = new CbtDTO();
		String strEx1 = "";
		String strEx2 = "";
		String strEx3 = "";
		String strEx4 = "";
		while (true) {
		System.out.println("===============================");
		System.out.print("수정할 문제 번호 >>");
		String strSeq = scan.nextLine();
		cbtDTO = cDao.findById(Long.valueOf(strSeq));
		if(!strSeq.trim().isEmpty()) {
			cbtDTO.setCb_seq(Long.valueOf(strSeq));
		}
		System.out.println("수정할 문제 >>");
		String strPlo = scan.nextLine();
		if(!strPlo.trim().isEmpty()) {
			cbtDTO.setCb_plo(strPlo);
		}
			System.out.print("수정할 보기1 >> ");
			strEx1 = scan.nextLine();
			if (!strEx1.trim().isEmpty()) {
				cbtDTO.setCb_ex1(strEx1);
			}
			System.out.print("수정할 보기2 >> ");
			strEx2 = scan.nextLine();
			if (!strEx2.trim().isEmpty()) {
				cbtDTO.setCb_ex2(strEx2);
			}
			System.out.print("수정할 보기3 >> ");
			strEx3 = scan.nextLine();
			if (!strEx3.trim().isEmpty()) {
				cbtDTO.setCb_ex3(strEx3);
			}
			System.out.print("수정할 보기4 >> ");
			strEx4 = scan.nextLine();
			if (!strEx4.trim().isEmpty()) {
				cbtDTO.setCb_ex4(strEx4);
			}
			System.out.print("수정할 답 >> ");
			String strAns = scan.nextLine();
			try {
				intAns = Integer.valueOf(strAns);
			} catch (Exception e) {
				// TODO: handle exception
			}
			if (intAns == 1) {
				cbtDTO.setCb_answer(strEx1);
			}else if (intAns == 2){
				cbtDTO.setCb_answer(strEx2);
			}else if (intAns == 3) {
				cbtDTO.setCb_answer(strEx3);
			}else if (intAns == 4) {
				cbtDTO.setCb_answer(strEx4);
			}else {
				System.out.println("11");
				break;
			}
			
			List<CbtDTO> cList = new ArrayList<CbtDTO>();
			cList.add(cbtDTO);
			
			int ret = cDao.update(cbtDTO);
			if(ret > 0) {
				System.out.println("수정완료");
			}else {
				System.out.println("수정실패");
			}
			
		}
		

	}

	public void quiz() {
		
		List<CbtDTO> cList = cDao.selectAll();
		this.title(cList);
			
		
	}
	
	public void delete() {
		// TODO Auto-generated method stub

	}

	public void selectAll() {

		List<CbtDTO> cList = cDao.selectAll();

		if (cList == null) {
			System.out.println("리스트 없음");
		} else {
			this.cDao.selectAll();
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

		System.out.print(cbtDTO.getCb_seq() + ". ");
		System.out.println(cbtDTO.getCb_plo() + "\t");
		System.out.println("1). " + cbtDTO.getCb_ex1() + "\t");
		System.out.println("2). " + cbtDTO.getCb_ex2() + "\t");
		System.out.println("3). " + cbtDTO.getCb_ex3() + "\t");
		System.out.println("4). " + cbtDTO.getCb_ex4() + "\n");
		
	}

	public void answer(long strSeq) {
		CbtDTO cbtDTO = cDao.findById(strSeq);

		System.out.printf("답 : %d ", cbtDTO.getCb_answer() + "\n");
		System.out.printf("맞은 문제 : %s", cbtDTO.getCb_Oplo() + "\n");
		System.out.printf("틀린 문제 : %s", cbtDTO.getCb_Xplo() + "\n");
		System.out.printf("맞은 개수  : %d", cbtDTO.getCb_Ocount() + "\t");
		System.out.printf("틀린 개수 : %d", cbtDTO.getCb_Xcount() + "\t");

	}

}
