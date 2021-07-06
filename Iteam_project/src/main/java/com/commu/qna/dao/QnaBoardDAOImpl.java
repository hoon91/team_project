package com.commu.qna.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.commu.page.SearchCriteria;
import com.commu.qna.vo.QnaBoardVO;
import com.commu.user.vo.UserVO;

@Repository
public class QnaBoardDAOImpl implements QnaBoardDAO {

	@Inject
	private SqlSession sqlSession;

	// 게시글 작성
	@Override
	public void write(QnaBoardVO qnaboardVO) throws Exception {
		sqlSession.insert("qnaboardMapper.insert", qnaboardVO);
	}

	// 게시물 목록 조회
	@Override
	public List<QnaBoardVO> list(SearchCriteria scri) throws Exception {
		return sqlSession.selectList("qnaboardMapper.listPage", scri);
	}
	
	//게시물 총 갯수
	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		return sqlSession.selectOne("qnaboardMapper.listCount", scri);
	}

	// 게시물 조회
	@Override
	public QnaBoardVO read(int bno) throws Exception {
		return sqlSession.selectOne("qnaboardMapper.read", bno);
	}

	// 게시물 수정
	@Override
	public void update(QnaBoardVO qnaboardVO) throws Exception {
		sqlSession.update("qnaboardMapper.update", qnaboardVO);
	}

	// 게시물 삭제
	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("qnaboardMapper.delete", bno);
	}
	
	// 신고 처리
	@Override
	public void goReport(UserVO userVO) throws Exception {
		sqlSession.delete("qnaboardMapper.goReport", userVO);
	}
	
	// 게시물 조회수
	@Override
	public void boardCnt(int bno) throws Exception {
		sqlSession.update("qnaboardMapper.boardCnt", bno);
	}
	
	// 게시물 추천
	@Override
	public void recommend(int bno) throws Exception {
		sqlSession.update("qnaboardMapper.recommend", bno);
	}

	@Override
	public void replyCount(int bno) throws Exception {
		sqlSession.update("qnaboardMapper.replyCount", bno);
	}

}