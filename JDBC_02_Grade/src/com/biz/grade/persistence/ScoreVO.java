package com.biz.grade.persistence;
/*
 * DB와 관련 용어
 * Table과 연관 class들의 묶음(package)
 * vo(value object)
 * domain
 * command
 * entity
 * persistence : 
 * 		영속성, 지속성
 * 		메모리가 아닌 물리적 공간에 저장되는 데이터
 */
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
/*
 * DBMS Table과 관련된 Class
 * VO(Value Object), DTO(Data Transfer Object)
 * - 공통기능
 * 		Table과 연관되어서 CRUD를 수행할 때
 * 		데이터를 담아서 method간에 이동할 때 사용
 * - DTO
 * 		물리적 Table과 연관(매핑)되어 완전한 CRUD를 수행할 때
 * 
 * - VO
 * 		VIEW Table, JOIN된 SQL과 연관되어
 * 		주로 READ( = Retrieve 인출)용으로 사용되는 클래스
 */


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class ScoreVO {

	private String s_std; //	varchar2(5)
	private String st_name; //	nvarchar2(50)
	private int st_grade; //	number(1)
	private String st_dept; //	varchar2(5)
	private String d_name; //	nvarchar2(30)
	private String d_tel; //	varchar2(20)
	private String s_subject; //	varchar2(4)
	private String sb_name; //	nvarchar2(20)
	private int s_score; //	number(3)
	private int s_id; //	number
	public String s_remark;
	
	
	
	
	
	
}
