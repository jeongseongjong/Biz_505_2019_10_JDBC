package com.biz.grade.exec;

import java.util.Random;

import com.biz.grade.persistence.domain.ScoreDTO;
import com.biz.grade.service.ScoreServiceV3;

public class ScoreInput_02 {

	public static void main(String[] args) {
		
		ScoreServiceV3 sc = new ScoreServiceV3();

		
		String strStNum = sc.inputStudent();
		/*
		if(strStNum != null) {
			String strSubject = sc.inputSubject();
			if(strSubject != null) {
				String strScore = sc.inputScore();
				if(strScore != null) {
					// input처리
					
				}
			}
		}
		*/
		if(strStNum == null) {
			System.out.println("성적입력 종료");
			return; // 프로젝트 종료
		}
		
		String strSubject = sc.inputSubject();
		if(strSubject == null) {
			System.out.println("성적입력 종료!");
			return ; // 프로젝트 종료
		}
		/*
		 * method에서 숫자값을 return할때
		 * 만약 값을 입력 안할 경우
		 * int형 같으면 보통 0을 return 할 것이다.
		 * 그럼 값을 입력하지 않아서 0인지 다른이유가 있는지
		 * 판단이 상당히 어려움
		 * 
		 * 이럴때
		 * Integer형을 사용하면
		 * 정말 0을 입력햇으면 값이 0인경우로 처리하고
		 * 입력하지 않았으면 null return하여
		 * 입력하지 않았음을 처리하도록 할 수 있다.
		 */
		
		Integer intScore = sc.inputScore();
		if(intScore == null) {
			System.out.println("성적입력 종료");
			return; // 프로젝트 종료
		}
		Random rnd = new Random();
		ScoreDTO scoreDTO = ScoreDTO.builder()
				.s_id(rnd.nextLong())
				.s_std(strStNum.toUpperCase())
				.s_subject(strSubject.toUpperCase())
				.s_score(intScore)
				.build();
		
		int ret = sc.insert(scoreDTO);
		if(ret > 0) {
			System.out.println("데이터 추가 성공 !");
		}else {
			System.out.println("데이터 추가 실패!");
		}
		
		
		
		
	}
}
