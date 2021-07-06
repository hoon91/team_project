package com.commu.it.dao;

import java.util.List;
import java.util.Map;

import com.commu.it.vo.ItBoardVO;
import com.commu.page.SearchCriteria;
import com.commu.user.vo.UserVO;

public interface ItBoardDAO {

	// 게시글 작성
	public void write(ItBoardVO itboardVO) throws Exception;

	// 게시물 목록 조회
	public List<ItBoardVO> list(SearchCriteria scri) throws Exception;
	
	// 게시물 총 갯수
	public int listCount(SearchCriteria scri) throws Exception;

	// 게시물 조회
	public ItBoardVO read(int bno) throws Exception;

	// 게시물 수정
	public void update(ItBoardVO itboardVO) throws Exception;

	// 게시물 삭제
	public void delete(int bno) throws Exception;
	
	// 신고 처리
	public void goReport(UserVO userVO) throws Exception; 
	
	// 게시판 조회수
	public void boardCnt(int bno) throws Exception;
	
	// 추천
	public void recommend(int bno) throws Exception;
	
	//첨부파일
	public void insertFile(Map<String, Object> map) throws Exception;

	// 첨부파일 조회
	public List<Map<String, Object>> selectFileList(int bno) throws Exception;

	// 첨부파일 다운
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception;

	// 첨부파일 수정
	public void updateFile(Map<String, Object> map) throws Exception;
	
	public void replyCount(int bno) throws Exception;
		
}