package com.biz.iolist.persistence;

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
public class ProductDTO {

	private String p_code; //	varchar2(5 byte)
	private String p_name; //	nvarchar2(50 char)
	private int p_iprice; //	number
	private int p_oprice; //	number
	private String p_vat; //	varchar2(1 byte)
}
