package com.commu.team.service;

import java.util.List;

import com.commu.page.SearchCriteria;
import com.commu.team.vo.TeamVO;
import com.commu.user.vo.UserVO;

public interface TeamService {

	// 게시글 작성
	public void write(TeamVO teamVO) throws Exception;

	// 게시물 목록 조회
	public List<TeamVO> list(SearchCriteria scri) throws Exception;

	// 게시물 총 갯수
	public int listCount(SearchCriteria scri) throws Exception;

	// 게시물 목록 조회
	public TeamVO read(int bno) throws Exception;

	// 게시물 수정
	public void update(TeamVO teamVO) throws Exception;

	// 게시물 삭제
	public void delete(int bno) throws Exception;

	// 게시물 신고
	public void goReport(UserVO userVO) throws Exception;
	
	// 게시물 추천
	public void recommend(int bno) throws Exception;
	
	// 게시물 조회수
	public void boardCnt(int bno) throws Exception;
	
	// 팀 매칭(06.14)
	public void makeTeam(UserVO userVO) throws Exception;
	
	// 팀카운팅
	public int teamCnt(UserVO userVO ) throws Exception;
	
	// 프로젝트 팀원 리스트(06.17)
	public List<UserVO> teamList(UserVO userVO) throws Exception;
	
	// 프로젝트 정보(06.17)
	public TeamVO teamInfo(TeamVO teamVO) throws Exception;
	
	// 프로젝트 그만두기
	public void teamQuick(UserVO userVO) throws Exception;
	
	// 세션 바꾸기
	public UserVO sessionUpdate(UserVO userVO) throws Exception;
	
	//완료 여부
	public void teamComplete(TeamVO teamVO) throws Exception;
	
	//프로젝트 완료시 제거(06.18)
	public void teamDelete(UserVO userVO) throws Exception;
	
	public void replyCount(int bno) throws Exception;

}