package com.biz.book.dao;

import java.util.List;

import com.biz.book.persistence.RentBookDTO;

public interface RentBookDao {

	public List<RentBookDTO> selectAll();

	
}
