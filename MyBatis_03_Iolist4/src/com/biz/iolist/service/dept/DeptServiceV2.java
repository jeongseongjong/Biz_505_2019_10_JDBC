package com.biz.iolist.service.dept;

import com.biz.iolist.persistence.DeptDTO;

public class DeptServiceV2 extends DeptServiceV1 {

	public void deptMenu() {
		System.out.println("===================================");
		System.out.println("거래처 정보 관리 ");
		System.out.println("===================================");
		System.out.println("1.등록 2.수정 3.삭제 4.검색 0.종료");
		System.out.println("-----------------------------------");
		System.out.print("업무선택 >> ");
		String strMenu = scan.nextLine();

		try {
			int intMenu = Integer.valueOf(strMenu);
			if (intMenu == 0)
				return;
			else if (intMenu == 1) {
				this.viewNameList();
				this.deptInsert();
			
			} else if (intMenu == 2) {
				this.viewNameList();
				this.deptUpdate();
			} else if (intMenu == 3) {
				// 상호검색을 해서 리스트 출력
				this.viewNameList();

				// 출력된 리스트 중 거래서 코드 입력받아 삭제 수행
				this.deptDelete();
			} else if (intMenu == 4) {
				this.viewNameAndCEOList();
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}
	
	protected DeptDTO viewDetail(String strDCode) {
		DeptDTO deptDTO = deptDao.findById(strDCode);
		
		if(deptDTO == null) return null;
		
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
		
		return deptDTO;
	}

	public void deptDelete() {
		
		while(true) {
			System.out.print("삭제할 거래처 코드(-Q:quit) >>");
			String strDCode = scan.nextLine();
			
			if(strDCode.equals("-Q")) break;
			
			DeptDTO deptDTO = deptDao.findById(strDCode);
			if(deptDTO == null)	{
				System.out.println("삭제할 거래처 코드가 없음");
				continue;
			}
			this.viewDetail(deptDTO);
			
			System.out.print("레알 삭제 할라우 ? Enter : 실행");
			String strYesNo = scan.nextLine();
			if(strYesNo.trim().isEmpty()) {
				int ret = deptDao.delete(strDCode);
				if(ret > 0) { 
					System.out.println("삭제완료");
					break;
				}else 
					System.out.println("삭제실패");
			}
			
		}
		
	} // end delete
	
	public void deptUpdate() {
		System.out.println("===============================");
		System.out.print("수정할 거래처 코드 >>");
		String strDCode = scan.nextLine();
		
		strDCode = strDCode.toUpperCase();
		
		DeptDTO deptDTO = this.viewDetail(strDCode);
		
		System.out.printf("변경할 상호명(%s) >>", deptDTO.getD_name());
		String strName = scan.nextLine();
		
		if( !strName.trim().isEmpty()) {
			deptDTO.setD_name(strName);
		}
		
		System.out.printf("변경할 대표명(%s) >> ", deptDTO.getD_ceo());
		String strCeo = scan.nextLine();
		
		if( !strCeo.trim().isEmpty()) {
			deptDTO.setD_ceo(strCeo);
		}
		System.out.printf("변경할 전화명(%s) >> ", deptDTO.getD_tel());
		String strTel = scan.nextLine();
		
		if( !strTel.trim().isEmpty()) {
			deptDTO.setD_tel(strTel);
		}
		System.out.printf("변경할 주소명(%s) >> ", deptDTO.getD_addr());
		String strAddr = scan.nextLine();
		
		if( !strAddr.trim().isEmpty()) {
			deptDTO.setD_addr(strAddr);
			
		}
		int ret = deptDao.update(deptDTO);
		if(ret > 0) {
			System.out.println("변경 완료");
			
		}else {
			System.out.println("변경 실패");
		}
		System.out.println(deptDao.findById(strDCode).toString());
	}
	
	public void deptInsert() {
		
	}

}





























