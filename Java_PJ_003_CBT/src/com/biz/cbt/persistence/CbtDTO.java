package com.biz.cbt.persistence;

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
public class CbtDTO {

	private String cbt_code; //	VARCHAR2(5)
	private String cbt_plo; //		nVARCHAR2(125)
	private String cbt_ex1; //		nVARCHAR2(64)
	private String cbt_ex2; //		nVARCHAR2(64)
	private String cbt_ex3; //		nVARCHAR2(64)
	private String cbt_ex4; //		nVARCHAR2(64)
	private int cbt_answer; //	NUMBER(2)
	private String cbt_yes; //		nVARCHAR2(125)
	private String cbt_no; //		nVARCHAR2(125)
	private int cbt_right; //	VARCHAR2(5)
	private int cbt_wrong; //	VARCHAR2(5)


}
