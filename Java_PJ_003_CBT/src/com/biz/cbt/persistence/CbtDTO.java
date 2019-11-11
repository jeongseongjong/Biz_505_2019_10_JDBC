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

	private long cb_seq; //	VARCHAR2(5)
	private String cb_plo; //		nVARCHAR2(125)
	private String cb_ex1; //		nVARCHAR2(64)
	private String cb_ex2; //		nVARCHAR2(64)
	private String cb_ex3; //		nVARCHAR2(64)
	private String cb_ex4; //		nVARCHAR2(64)
	private String cb_answer; //	NUMBER(2)
	private String cb_Oplo; //		nVARCHAR2(125)
	private String cb_Xplo; //		nVARCHAR2(125)
	private int cb_Ocount; //	VARCHAR2(5)
	private int cb_Xcount; //	VARCHAR2(5)


}
