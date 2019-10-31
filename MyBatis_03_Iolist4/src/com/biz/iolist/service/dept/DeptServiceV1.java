package com.biz.iolist.service.dept;

import java.util.List;
import java.util.Scanner;

import com.biz.iolist.config.DBConnection;
import com.biz.iolist.dao.DeptDao;
import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV1 {

	protected DeptDao deptDao;
	Scanner scan;

	public DeptServiceV1() {
		deptDao = DBConnection.getSqlSessionFactory().openSession(true).getMapper(DeptDao.class);
		scan = new Scanner(System.in);
	}

	// deptDao.selectAll()을 호출하여 전체리스트를
	// 보여주는 method

	public void viewAllList() {

		List<DeptDTO> deptList = deptDao.selectAll();

		if (deptList == null || deptList.size() < 1) {
			System.out.println("리스트 없음");
		} else {
			this.viewList(deptList);

		}
	}

	public void viewNameList() {
		System.out.println("=============================");
		System.out.println("거래처 이름검색");
		System.out.println("=============================");
		System.out.print("거래처명 >>");
		String strDName = scan.nextLine();

		List<DeptDTO> deptList = null;
		if (strDName.trim().isEmpty()) {
			deptList = deptDao.selectAll();
		} else {
			deptList = deptDao.findByName(strDName);
		}
		this.viewList(deptList);
	}

	public void viewNameAndCEOList() {
		System.out.println("=============================");
		System.out.println("거래처 이름검색");
		System.out.println("=============================");
		
		System.out.print("거래처명 >>");
		String strDName = scan.nextLine();

		System.out.print("대표자명 >>");
		String strDCEO = scan.nextLine();
		
		List<DeptDTO> deptList = null;
		
		if (strDName.trim().isEmpty() && strDCEO.trim().isEmpty()) {
			deptList = deptDao.selectAll();
			
		} else if(strDName.trim().isEmpty()){
			deptList = deptDao.findByCEO(strDCEO);
			
		} else if(strDCEO.trim().isEmpty()){
			deptList = deptDao.findByName(strDName);
			
		}else {
			deptList = deptDao.findByNameAndCEO(strDName, strDCEO);
		}
		this.viewList(deptList);
	}

	// 각 view에서 List를 출력할때 사용할 method
	// List를 반복하면서 deptDTO를 매개변수로 전달
	protected void viewList(DeptDTO deptDTO) {
		System.out.print(deptDTO.getD_code() + "\t");
		System.out.print(deptDTO.getD_name() + "\t");
		System.out.print(deptDTO.getD_ceo() + "\t");
		System.out.print(deptDTO.getD_tel() + "\t");
		System.out.print(deptDTO.getD_addr() + "\n");
	}

	// List를 받아서 출력할 때 사용할 method
	protected void viewList(List<DeptDTO> deptList) {
		System.out.println("==============================");
		System.out.println("거래처리스트");
		System.out.println("==============================");
		System.out.println("코드\t상호\t대표\t전화\t주소");
		System.out.println("------------------------------");
		for (DeptDTO deptDTO : deptList) {
			this.viewList(deptDTO);
		}
		System.out.println("===============================");
	}
	
	protected void viewDetail(DeptDTO deptDTO) {
		System.out.println("=======================================");
		System.out.println("상호 : " + deptDTO.getD_name());
		System.out.println("대표 : " + deptDTO.getD_ceo());
		System.out.println("전화 : " + deptDTO.getD_tel());
		System.out.println("주소 : " + deptDTO.getD_addr());
		System.out.println("업종 : 슈퍼마켓");
		System.out.println("업태 : 도소매");
		System.out.println("사업자번호 : 409-01-00001");
		System.out.println("담당자 : 홍길동");
		System.out.println("담당자 직통전화 : 010-123-1234");
		System.out.println("========================================");
	}
}
