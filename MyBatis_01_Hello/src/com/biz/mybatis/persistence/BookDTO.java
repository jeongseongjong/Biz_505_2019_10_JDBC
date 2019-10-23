package com.biz.mybatis.persistence;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class BookDTO {

	
	private String b_code; 		//	nvarchar2(5 char)
	private String b_name; 		//	nvarchar2(50 char)
	private String b_comp; 		//	nvarchar2(50 char)
	private String b_writer;	//	nvarchar2(20 char)
	private int b_price;	    //  number
}
