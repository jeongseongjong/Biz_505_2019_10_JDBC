package com.biz.dbms.exec;

import com.biz.dbms.service.BBsServiceV4;

public class BBsEx_04 {

	public static void main(String[] args) {
		
		BBsServiceV4 bs = new BBsServiceV4();
		bs.viewBBsList();
		bs.bbsMenu();
	}
}
