package com.commu.notice.dao;

import java.util.List;

import com.commu.notice.vo.NBoardVO;
import com.commu.page.SearchCriteria;
import com.commu.user.vo.UserVO;

public interface NBoardDAO {

	// 게시글 작성
	public void write(NBoardVO vboardVO) throws Exception;

	// 게시물 목록 조회
	public List<NBoardVO> list(SearchCriteria scri) throws Exception;
	
	// 게시물 총 갯수
	public int listCount(SearchCriteria scri) throws Exception;

	// 게시물 조회
	public NBoardVO read(int bno) throws Exception;

	// 게시물 수정
	public void update(NBoardVO vboardVO) throws Exception;

	// 게시물 삭제
	public void delete(int bno) throws Exception;
	
	// 신고 처리
	public void goReport(UserVO userVO) throws Exception; 
	
	// 게시물 조회수
	public void boardCnt(int bno) throws Exception;
	
	// 게시물 추천
	public void recommend(int bno) throws Exception;
	
	public void replyCount(int bno) throws Exception;
}