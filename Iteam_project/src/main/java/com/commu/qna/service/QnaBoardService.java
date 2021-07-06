package com.commu.qna.service;

import java.util.List;


import com.commu.page.SearchCriteria;
import com.commu.qna.vo.QnaBoardVO;
import com.commu.user.vo.UserVO;

public interface QnaBoardService {

	// 게시글 작성
	public void write(QnaBoardVO qnaboardVO) throws Exception;

	// 게시물 목록 조회
	public List<QnaBoardVO> list(SearchCriteria scri) throws Exception;

	// 게시물 총 갯수
	public int listCount(SearchCriteria scri) throws Exception;

	// 게시물 목록 조회
	public QnaBoardVO read(int bno) throws Exception;

	// 게시물 수정
	public void update(QnaBoardVO qnaboardVO) throws Exception;

	// 게시물 삭제
	public void delete(int bno) throws Exception;

	// 게시물 신고
	public void goReport(UserVO userVO) throws Exception;
	
	// 게시물 추천
	public void recommend(int bno) throws Exception;
	
	// 게시물 조회수
	public void boardCnt(int bno) throws Exception;
	
	public void replyCount(int bno) throws Exception;
}