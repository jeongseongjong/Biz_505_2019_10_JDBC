package com.biz.iolist.service.iolist;

import com.biz.iolist.persistence.IolistDTO;

public class IolistServiceV4 extends IolistServiceV3 {

	public void delete() {

		while (true) {
			System.out.println("===================================");
			System.out.print("삭제할 SEQ 입력(-Q:quit) >> ");
			String strSEQ = scan.nextLine();
			
			if(strSEQ.equals("-Q")) break;
			long longSEQ = Integer.valueOf(strSEQ);
			IolistDTO iolistDTO = iolistDao.findById(longSEQ);
			if(iolistDTO == null) {
				System.out.println("삭제할 SEQ 없음");
				continue;
			}
			System.out.print("삭제하실 ? (Enter:삭제) >>");
			String strDel = scan.nextLine();
			if(strDel.trim().isEmpty()) {
				int ret = iolistDao.delete(longSEQ);
				if(ret > 0) {
					System.out.println("삭제완료");
					break;
				}else{
					System.out.println("삭제실패");
				}
			
			}	break;
		}

	}
}
