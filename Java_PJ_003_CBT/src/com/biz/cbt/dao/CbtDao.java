package com.biz.cbt.dao;

import java.util.List;

import com.biz.cbt.persistence.CbtDTO;

public interface CbtDao {

	public CbtDTO findByPlo(String cb_plo);
	
	public List<CbtDTO> selectAll();
	
	public int insert(CbtDTO cbtDTO);
	public int update(CbtDTO cbtDTO);
	public int delete(String cb_plo);
	
	public String getMaxNum();
}
