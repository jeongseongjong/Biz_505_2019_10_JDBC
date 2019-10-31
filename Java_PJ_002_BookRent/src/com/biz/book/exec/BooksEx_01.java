package com.biz.book.exec;

import com.biz.book.books.service.BooksServiceV1;

public class BooksEx_01 {

	public static void main(String[] args) {
		BooksServiceV1 bs = new BooksServiceV1();
		bs.viewAllList();
		bs.SearchBName();
	}
	
	
}
