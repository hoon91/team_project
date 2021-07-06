package com.commu.team.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.commu.page.SearchCriteria;
import com.commu.team.dao.TeamDAO;
import com.commu.team.vo.TeamVO;
import com.commu.user.vo.UserVO;

@Service
public class TeamServiceImpl implements TeamService {

	@Inject
	private TeamDAO dao;

	// 게시글 작성
	@Override
	public void write(TeamVO TeamVO) throws Exception {
		dao.write(TeamVO);
	}

	// 게시물 목록 조회
	@Override
	public List<TeamVO> list(SearchCriteria scri) throws Exception {
		return dao.list(scri);
	}

	// 게시물 총 갯수
	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		return dao.listCount(scri);
	}

	// 게시물 조회, 조회수
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public TeamVO read(int bno) throws Exception {
			dao.boardCnt(bno);
		return dao.read(bno);
	}

	// 게시글 수정
	@Override
	public void update(TeamVO teamVO) throws Exception {
		dao.update(teamVO);
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
	
	// 팀 매칭
	@Override
	public void makeTeam(UserVO userVO) throws Exception {
		dao.makeTeam(userVO);
	}
	
	// 팀 카운팅
	@Override
	public int teamCnt(UserVO userVO) throws Exception {
		return dao.teamCnt(userVO);
	}
	
	// 프로젝트 팀원 리스트
	@Override
	public List<UserVO> teamList(UserVO userVO) throws Exception {
		return dao.teamList(userVO);
	}
	
	// 프로젝트 정보
	@Override
	public TeamVO teamInfo(TeamVO teamVO) throws Exception {
		return dao.teamInfo(teamVO);
	}
	
	// 프로젝트 그만두기
	@Override
	public void teamQuick(UserVO userVO) throws Exception {
		dao.teamQuick(userVO);
	}
	
	// 세션 바꾸기
	@Override
	public UserVO sessionUpdate(UserVO userVO) throws Exception {
		System.out.println("sessionUpdate-userVO:"+userVO.getId());
		return dao.sessionUpdate(userVO);
	}
	
	// 완료 여부
	@Override
	public void teamComplete(TeamVO teamVO) throws Exception {
		dao.teamComplete(teamVO);
	}
	
	// 프로젝트 완료 시 제거
	@Override
	public void teamDelete(UserVO userVO) throws Exception {
		dao.teamDelete(userVO);
	}

	@Override
	public void replyCount(int bno) throws Exception {
		dao.replyCount(bno);
	}

}