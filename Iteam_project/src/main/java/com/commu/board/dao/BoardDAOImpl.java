package com.commu.board.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.commu.board.vo.BoardVO;
import com.commu.page.SearchCriteria;
import com.commu.user.vo.UserVO;

@Repository
public class BoardDAOImpl implements BoardDAO {

	@Inject
	private SqlSession sqlSession;

	// 게시글 작성
	@Override
	public void write(BoardVO boardVO) throws Exception {
		sqlSession.insert("boardMapper.insert", boardVO);
	}

	// 게시물 목록 조회
	@Override
	public List<BoardVO> list(SearchCriteria scri) throws Exception {
		return sqlSession.selectList("boardMapper.listPage", scri);
	}
	
	//게시물 총 갯수
	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		return sqlSession.selectOne("boardMapper.listCount", scri);
	}

	// 게시물 조회
	@Override
	public BoardVO read(int bno) throws Exception {
		return sqlSession.selectOne("boardMapper.read", bno);
	}

	// 게시물 수정
	@Override
	public void update(BoardVO boardVO) throws Exception {
		sqlSession.update("boardMapper.update", boardVO);
	}

	// 게시물 삭제
	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("boardMapper.delete", bno);
	}
	
	// 신고 처리
	@Override
	public void goReport(UserVO userVO) throws Exception {
		sqlSession.delete("boardMapper.goReport", userVO);
	}
	
	// 게시물 조회수
	@Override
	public void boardCnt(int bno) throws Exception {
		sqlSession.update("boardMapper.boardCnt", bno);
	}
	
	// 게시물 추천
	@Override
	public void recommend(int bno) throws Exception {
		sqlSession.update("boardMapper.recommend", bno);	
	}

	@Override
	public void replyCount(int bno) throws Exception {
		sqlSession.update("boardMapper.replyCount", bno);
	}

}