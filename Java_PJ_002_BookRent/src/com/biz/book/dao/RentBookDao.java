package com.biz.book.dao;

import java.util.List;

import com.biz.book.persistence.RentBookDTO;

public interface RentBookDao {

	public RentBookDTO rentYN();
	
	public List<RentBookDTO> selectAll();

	public RentBookDTO findById(String rent_seq);
	public RentBookDTO findByBCode(String rent_bcode);
	public RentBookDTO findByUCode(String rent_ucode);
	
	public int insert(RentBookDTO rDTO);
	
	public int update(RentBookDTO rDTO);
	
}
