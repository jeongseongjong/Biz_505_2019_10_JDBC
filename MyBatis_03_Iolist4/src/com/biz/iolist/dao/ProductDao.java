package com.biz.iolist.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.biz.iolist.persistence.ProductDTO;

public interface ProductDao {

	// tbl_product 테이블의 p_code의 최대값을 가져오기
	public String getMaxPCode();
	
	public List<ProductDTO> selectAll();
	public ProductDTO findById(String p_code);
	
	public List<ProductDTO> findByName(String p_name);
	
	// 상품정보를 입력할 때
	// 완전히 일치하는 이름을 가진 상품이 있는지 검사
	public ProductDTO findBySName(String p_name);
	
	public int insert(ProductDTO productDTO);
	public int update(ProductDTO productDTO);
	public int delete(String p_code);
	
	public List<ProductDTO> findByIPrice(
			@Param("sprice") int sprice,
			@Param("eprice") int eprice);
		
}
