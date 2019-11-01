package com.biz.book.exec;

import com.biz.book.books.service.RentBookServiceV1;

public class RentEx_01 {

	public static void main(String[] args) {
		
		RentBookServiceV1 rs = new RentBookServiceV1();
		rs.bookListMenu();
	}
}
