package com.biz.book.persistence;

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
public class UsersDTO {
	private String u_code;//	varchar2(6)
	private String u_name;//	nvarchar2(125)
	private String u_tel;//	nvarchar2(125)
	private String u_addr;//	nvarchar2(125)

}
