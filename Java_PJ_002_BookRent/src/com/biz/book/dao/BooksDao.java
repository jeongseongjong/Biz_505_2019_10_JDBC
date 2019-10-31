package com.biz.book.dao;

import java.util.List;

import com.biz.book.persistence.BooksDTO;

public interface BooksDao {

	public List<BooksDTO> selectAll();
	
	public List<BooksDTO> findByName(String b_name);
	public BooksDTO findById(String b_code);
	public List<BooksDTO> findByAuther(String b_auther);
	
	public int insert(BooksDTO bDTO);
	public int update(BooksDTO bDTO);
	public int delete(String b_code);
	
	public String getMaxBCode();

	
}
