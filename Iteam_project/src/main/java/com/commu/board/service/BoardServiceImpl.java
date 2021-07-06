package com.commu.board.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.commu.board.dao.BoardDAO;
import com.commu.board.vo.BoardVO;
import com.commu.page.SearchCriteria;
import com.commu.user.vo.UserVO;

@Service
public class BoardServiceImpl implements BoardService {

	@Inject
	private BoardDAO dao;

	// 게시글 작성
	@Override
	public void write(BoardVO boardVO) throws Exception {
		dao.write(boardVO);
	}

	// 게시물 목록 조회
	@Override
	public List<BoardVO> list(SearchCriteria scri) throws Exception {
		return dao.list(scri);
	}

	// 게시물 총 갯수
	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		return dao.listCount(scri);
	}

	// 게시물 조회, 조회수
	@Override
	public BoardVO read(int bno) throws Exception {
		return dao.read(bno);
	}

	// 게시글 수정
	@Override
	public void update(BoardVO boardVO) throws Exception {
		dao.update(boardVO);
	}

	// 게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		dao.delete(bno);
	}
	
	// 게시물 신고
	@Override
	public void goReport(UserVO userVO) throws Exception{
		dao.goReport(userVO);
	}
	
	// 게시물 추천
	@Override
	public void recommend(int bno) throws Exception {
		dao.recommend(bno);
	}
	
	// 게시물 조회수
	@Override
	public void boardCnt(int bno) throws Exception {
		dao.boardCnt(bno);
	}

	@Override
	public void replyCount(int bno) throws Exception {
		dao.replyCount(bno);
	}



}