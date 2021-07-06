package com.commu.it.dao;

import java.util.List;

import java.util.Map;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.commu.it.vo.ItBoardVO;
import com.commu.page.SearchCriteria;
import com.commu.user.vo.UserVO;

@Repository
public class ItBoardDAOimpl implements ItBoardDAO{
	@Inject
	private SqlSession sqlSession;

	// 게시글 작성
	@Override
	public void write(ItBoardVO itboardVO) throws Exception {
		sqlSession.insert("itboardMapper.insert", itboardVO);
	}

	// 게시물 목록 조회
	@Override
	public List<ItBoardVO> list(SearchCriteria scri) throws Exception {
		return sqlSession.selectList("itboardMapper.listPage", scri);
	}
	
	//게시물 총 갯수
	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		return sqlSession.selectOne("itboardMapper.listCount", scri);
	}

	// 게시물 조회
	@Override
	public ItBoardVO read(int bno) throws Exception {
		return sqlSession.selectOne("itboardMapper.read", bno);
	}

	// 게시물 수정
	@Override
	public void update(ItBoardVO itboardVO) throws Exception {
		sqlSession.update("itboardMapper.update", itboardVO);
	}

	// 게시물 삭제
	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("itboardMapper.delete", bno);
	}
	// 신고 처리
	@Override
	public void goReport(UserVO userVO) throws Exception {
		sqlSession.delete("itboardMapper.goReport", userVO);
	}
	
	// 조회수
	@Override
	public void boardCnt(int bno) throws Exception {
		sqlSession.update("itboardMapper.boardCnt", bno);
	}
	
	// 추천
	@Override
	public void recommend(int bno) throws Exception {
		sqlSession.update("itboardMapper.recommend", bno);
	}
	
	// 파일첨부
	@Override
	public void insertFile(Map<String, Object> map) throws Exception {
		sqlSession.insert("itboardMapper.insertFile", map);
	}
	
	// 첨부파일 조회
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		return sqlSession.selectList("itboardMapper.selectFileList", bno);
	}
	
	// 첨부파일 다운로드
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return sqlSession.selectOne("itboardMapper.selectFileInfo", map);
	}
	
	// 첨부파일 수정
	@Override
	public void updateFile(Map<String, Object> map) throws Exception {	
		sqlSession.update("itboardMapper.updateFile", map);
	}

	@Override
	public void replyCount(int bno) throws Exception {
		sqlSession.update("itboardMapper.replyCount", bno);
	}
}
