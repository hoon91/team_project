package com.commu.board.service;

import java.util.List;

import com.commu.board.vo.BoardVO;
import com.commu.page.SearchCriteria;
import com.commu.user.vo.UserVO;

public interface BoardService {

	// 게시글 작성
	public void write(BoardVO boardVO) throws Exception;

	// 게시물 목록 조회
	public List<BoardVO> list(SearchCriteria scri) throws Exception;

	// 게시물 총 갯수
	public int listCount(SearchCriteria scri) throws Exception;

	// 게시물 목록 조회
	public BoardVO read(int bno) throws Exception;

	// 게시물 수정
	public void update(BoardVO boardVO) throws Exception;

	// 게시물 삭제
	public void delete(int bno) throws Exception;

	// 게시물 신고
	public void goReport(UserVO userVO) throws Exception;
	
	// 게시물 추천
	public void recommend(int bno) throws Exception;
	
	// 게시물 조회수
	public void boardCnt(int bno) throws Exception;
	
	// (21.06.21 추가) 게시물 댓글 수 
	public void replyCount(int bno) throws Exception;
	
}