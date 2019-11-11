package com.biz.cbt.dao;

import java.util.List;

import com.biz.cbt.persistence.CbtDTO;

public interface CbtDao {

	public CbtDTO findById(long cb_seq);
	
	public List<CbtDTO> selectAll();
	
	public CbtDTO findByEx1(String cb_ex1);
	public CbtDTO findByEx2(String cb_ex2);
	public CbtDTO findByEx3(String cb_ex3);
	public CbtDTO findByEx4(String cb_ex4);
	
	public int insert(CbtDTO cbtDTO);
	public int update(CbtDTO cbtDTO);
	public long delete(long cb_seq);
	
	public String getMaxNum();
}
