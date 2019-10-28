package com.biz.exec;

import com.biz.iolist.service.pro.ProductServiceV2;

public class ProEx_02 {

	public static void main(String[] args) {
		
		ProductServiceV2 pd = new ProductServiceV2();
		pd.searchPName();
		pd.proUpdate();
		
	}
}
