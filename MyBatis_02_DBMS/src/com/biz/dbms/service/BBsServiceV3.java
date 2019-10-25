package com.biz.dbms.service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import org.apache.ibatis.session.SqlSession;

import com.biz.dbms.config.DBConnection;
import com.biz.dbms.dao.BBsDao;
import com.biz.dbms.persistence.BBsDTO;

public class BBsServiceV3 {

	SqlSession sqlSession = null;
	Scanner scan = null;

	public BBsServiceV3() {

		sqlSession = DBConnection.getSqlSessionFactory().openSession(true);
		scan = new Scanner(System.in);

	}

	public void bbsMenu() {
		while(true) {
			System.out.println("내용보기(SQ입력) W.작성 U.수정 D.삭제 Q.종료");
			System.out.println("보기를 골라 : ");
			String strMenu = scan.nextLine();
			
			if(strMenu.equalsIgnoreCase("Q")) {
				return ;
			}else if(strMenu.equalsIgnoreCase("Q")) {
				
			}else if(strMenu.equalsIgnoreCase("W")) {
				this.writeBBS();
				this.viewBBsList();
			}else if(strMenu.equalsIgnoreCase("U")) {
				
			}else if(strMenu.equalsIgnoreCase("D")) {
				this.deleteBBs();
				this.viewBBsList();
			}else {
				
				try {
					long longSEQ = Integer.valueOf(strMenu);
					this.viewText(longSEQ);
				} catch (Exception e) {
					// TODO: handle exception
				}
			}
		}
		
	
	}
	
	public void viewText(long bs_id) {
		
		BBsDao bbsDao = sqlSession.getMapper(BBsDao.class);
		BBsDTO bbsDTO = bbsDao.findById(bs_id);
		
		if(bbsDTO == null) {
			System.out.println("내용없어용");
			
		}else {
			System.out.println("제목 : " + bbsDTO.getBs_subject());
			System.out.println("작성 : " + bbsDTO.getBs_writer());
			System.out.println("작성일 : " + bbsDTO.getBs_date());
			System.out.println("작성시각 " + bbsDTO.getBs_time());
			System.out.println(bbsDTO.getBs_text());
		}
		
	}
	
	public void deleteBBs() {
		// 삭제할 게시판 ID입력받기
		// 해당 ID의 내용을 보여주고
		// 삭제하실 ?
		// 삭제 진행
		System.out.println("==================================");
		System.out.print("삭제할 ID(-Q:quit) >> ");
		String strID = scan.nextLine();
		
		try {
			
			int intID = Integer.valueOf(strID);
			this.viewText(intID);
			System.out.println("-----------------------------------");
			System.out.print("삭제 하실 ?(Y/N)");
			String yesNo = scan.nextLine();
			if(yesNo.equals("Y")) {
				
				BBsDao bbsDao = sqlSession.getMapper(BBsDao.class);
				bbsDao.delete(intID);
				
			}
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		
	}
	
	public void writeBBS() {

		/*
		 * 작성자, 제목, 내용을 입력하지 않으면 메시지를 보여주고 다시 입력을 받도록 하자 이유 : bs_writer, bs_subject,
		 * bs_text는 NOT NULL이기 때문에 값이 있어야한다.
		 */
		while (true) {

			System.out.print("작성자(-Q:작성중단) >> ");
			String strWriter = scan.nextLine();
			if (strWriter.equals("-Q"))
				break;
			if (strWriter.trim().length() < 1) {
				System.out.println("작성자를 입력하라");
				continue;
			}

			System.out.print("제목 >>(-Q:quit) ");
			String strSubject = scan.nextLine();
			if (strSubject.equals("Q"))
				break;
			if (strSubject.trim().length() < 1) {
				System.out.println("제목을 입력하라");
				continue;
			}
				System.out.print("내용 >>(-Q:quit) ");
				String strText = scan.nextLine();
				if (strText.equals("-Q"))
					break;
				if (strText.trim().length() < 1) {
					System.out.println("내용을 입력하라");
					continue;

				}
				/*
				 * 작성일자, 작성시각은 컴퓨터시간을 참조하여 자동생성을 하자
				 */
				// java 1.7이하의 코드작성

				// 컴퓨터의 현재 시각가져오기
				Date date = new Date(System.currentTimeMillis());

				// date 날짜형 값을 "2019-10-24"의 문자열형으로 변환
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");

				// date 날짜형 값을 "14:00:00"의 문자열형으로 변환
				SimpleDateFormat tf = new SimpleDateFormat("HH:mm:SS");

				String curDate = df.format(date);
				String curTime = tf.format(date);
				
				// 입력받은 데이터와 날짜, 시각을 DTO에 담기
				BBsDTO bbsDTO = BBsDTO.builder()
						.bs_date(curDate)
						.bs_time(curTime)
						.bs_writer(strWriter)
						.bs_subject(strSubject)
						.bs_text(strText)
						.build();
				
				BBsDao bbsDao = sqlSession.getMapper(BBsDao.class);
				int ret = bbsDao.insert(bbsDTO);
				if(ret > 0) {
					System.out.println("게시판 작성 완료");
				}else {
					System.out.println("게시판 작성 실패");
				}
				break;
			}
		
		}

	public void viewBBsList() {

		BBsDao bbsDao = sqlSession.getMapper(BBsDao.class);
		List<BBsDTO> bbsList = bbsDao.selectAll();
		
		System.out.println("================================");
		System.out.println("슈퍼 BBS v1");
		System.out.println("================================");
		System.out.println("SQ\t작성일\t\t시각\t\t작성자\t제목");
		for(BBsDTO bbs : bbsList) {
			System.out.print(bbs.getBs_id() + "\t");
			System.out.print(bbs.getBs_date() + "\t");
			System.out.print(bbs.getBs_time() + "\t");
			System.out.print(bbs.getBs_writer() + "\t");
			System.out.println(bbs.getBs_subject());
		}
		System.out.println("================================");
		
		
	}
}
