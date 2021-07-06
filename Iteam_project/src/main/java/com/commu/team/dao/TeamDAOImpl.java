package com.commu.team.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.commu.page.SearchCriteria;
import com.commu.team.vo.TeamVO;
import com.commu.user.vo.UserVO;

@Repository
public class TeamDAOImpl implements TeamDAO {

	@Inject
	private SqlSession sqlSession;

	// 게시글 작성
	@Override
	public void write(TeamVO teamVO) throws Exception {
		sqlSession.insert("teamMapper.insert", teamVO);
	}

	// 게시물 목록 조회
	@Override
	public List<TeamVO> list(SearchCriteria scri) throws Exception {
		return sqlSession.selectList("teamMapper.listPage", scri);
	}
	
	//게시물 총 갯수
	@Override
	public int listCount(SearchCriteria scri) throws Exception {	
		return sqlSession.selectOne("teamMapper.listCount", scri);
	}

	// 게시물 조회
	@Override
	public TeamVO read(int bno) throws Exception {
		return sqlSession.selectOne("teamMapper.read", bno);
	}

	// 게시물 수정
	@Override
	public void update(TeamVO teamVO) throws Exception {
		sqlSession.update("teamMapper.update", teamVO);
	}

	// 게시물 삭제
	@Override
	public void delete(int bno) throws Exception {
		sqlSession.delete("teamMapper.delete", bno);
	}
	// 신고 처리
	@Override
	public void goReport(UserVO userVO) throws Exception {	
		sqlSession.delete("teamMapper.goReport", userVO);
	}
	
	// 게시물 조회수
	@Override
	public void boardCnt(int bno) throws Exception {
		sqlSession.update("teamMapper.boardCnt", bno);
	}
	
	// 게시물 추천
	@Override
	public void recommend(int bno) throws Exception {
		sqlSession.update("teamMapper.recommend", bno);	
	}
	
	//팀매칭 처리(06.14)
	@Override
	public void makeTeam(UserVO userVO) throws Exception {
		sqlSession.update("teamMapper.makeTeam",userVO);
	}
	
	// 팀 카운팅
	@Override
	public int teamCnt(UserVO userVO) throws Exception {
		return sqlSession.selectOne("teamMapper.teamCnt", userVO);
	}
	
	// 프로젝트 팀원 리스트
	@Override
	public List<UserVO> teamList(UserVO userVO) throws Exception {
		return sqlSession.selectList("teamMapper.teamList", userVO);
	}
	
	// 프로젝트 정보
	@Override
	public TeamVO teamInfo(TeamVO teamVO) throws Exception {
		return sqlSession.selectOne("teamMapper.teamInfo",teamVO);
	}
	
	// 프로젝트 그만두기
	@Override
	public void teamQuick(UserVO userVO) throws Exception {
		sqlSession.update("teamMapper.teamQuick", userVO);
	}
	
	// 세션 바꾸기
	@Override
	public UserVO sessionUpdate(UserVO userVO) throws Exception {
		return sqlSession.selectOne("teamMapper.sessionUpdate", userVO);
	}
	
	// 완료 여부
	@Override
	public void teamComplete(TeamVO teamVO) throws Exception {
		sqlSession.update("teamMapper.teamComplete", teamVO);
	}
	
	// 프로젝트 완료 시 삭제 
	@Override
	public void teamDelete(UserVO userVO) throws Exception {
		sqlSession.update("teamMapper.teamDelete", userVO);
	}

	@Override
	public void replyCount(int bno) throws Exception {
		sqlSession.update("teamMapper.replyCount", bno);
	}

}