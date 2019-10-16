package com.biz.grade.persistence;

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
public class ScoreDTO {

	private long s_id;
	private String s_std; // 학번
	private String s_subject; // 과목코드
	private int s_score; // 과목점수
	private String s_remark; // 비고
	
}
