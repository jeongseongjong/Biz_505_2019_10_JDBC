package com.biz.book.dao;

import java.util.List;

import com.biz.book.persistence.UsersDTO;

public interface UsersDao {

	public List<UsersDTO> selectAll();

	public List<UsersDTO> findByName(String u_name);

	public List<UsersDTO> findByTel(String u_tel);
	public UsersDTO findById(String u_code);

	public int insert(UsersDTO uDTO);

	public int update(UsersDTO uDTO);

	public int delete(String u_code);
	public String getMaxUCode();

}
