package com.commu.notice.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.commu.notice.vo.NBoardVO;
import com.commu.page.SearchCriteria;
import com.commu.user.vo.UserVO;

@Repository
public class NBoardDAOImpl implements NBoardDAO {

	@Inject
	private SqlSession nSqlSession;

	// 게시글 작성
	@Override
	public void write(NBoardVO vboardVO) throws Exception {
		nSqlSession.insert("noticeboardMapper.insert", vboardVO);
	}

	// 게시물 목록 조회
	@Override
	public List<NBoardVO> list(SearchCriteria scri) throws Exception {
		return nSqlSession.selectList("noticeboardMapper.listPage", scri);
	}
	
	//게시물 총 갯수
	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		return nSqlSession.selectOne("noticeboardMapper.listCount", scri);
	}

	// 게시물 조회
	@Override
	public NBoardVO read(int bno) throws Exception {
		return nSqlSession.selectOne("noticeboardMapper.read", bno);
	}

	// 게시물 수정
	@Override
	public void update(NBoardVO vboardVO) throws Exception {
		nSqlSession.update("noticeboardMapper.update", vboardVO);
	}

	// 게시물 삭제
	@Override
	public void delete(int bno) throws Exception {
		nSqlSession.delete("noticeboardMapper.delete", bno);
	}
	// 신고 처리
	@Override
	public void goReport(UserVO userVO) throws Exception {
		nSqlSession.update("noticeboardMapper.goReport", userVO);
	}
	
	// 게시물 조회수
	@Override
	public void boardCnt(int bno) throws Exception {
		nSqlSession.update("noticeboardMapper.boardCnt", bno);
	}
	
	// 게시물 추천
	@Override
	public void recommend(int bno) throws Exception {
		nSqlSession.update("noticeboardMapper.recommend", bno);
	}

	@Override
	public void replyCount(int bno) throws Exception {
		nSqlSession.update("noticeboardMapper.replyCount", bno);
	}

}