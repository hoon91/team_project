package com.commu.user.service;

import java.sql.SQLException;
import java.util.List;

import com.commu.user.vo.UserVO;



public interface UserService {
	// CRUD 기능 구현

	// 회원등록
	public void insertUser(UserVO vo) throws Exception;

	// 회원 조회
	public UserVO getUser(UserVO vo) throws SQLException;

	// 회원탈퇴
	public int deleteUser(UserVO vo) throws SQLException;

	// 회원수정
	public void updateUser(UserVO vo) throws Exception;

	// 동일아이디 존재 체크
	public int getUserCnt(UserVO vo);
	
	// 중복체크(06.21)
	public int ajaxUserCnt(String id);
		
	// 회원 로그인 처리
	public UserVO getLogin(UserVO vo);

	// 아이디 패스로 회원 존재 여부확인
	public int getUserCntByPass(UserVO vo);
	
	// 회원정보 읽기 (06.19 추가)
	public UserVO readInfo(String id) throws Exception;
			
	// 회원정보 수정 (06.19 추가)
	public void updateInfo(UserVO vo) throws Exception;
	
	// 회원 리스트
	public List<UserVO> getUsers(UserVO vo);

	// 신고된 회원 리스트
	public List<UserVO> getReportUser(UserVO vo);
	
	// 신고 회원 리스트 숫자
	public int countReportUser(UserVO vo);
	
	// 신고해제(06.14)
	public void returnReport(UserVO vo);
		
	// 회원 등급 나열
	public List<UserVO> getUserGrade(UserVO vo);
	
	// 회원 승강제
	public void updateUserGrade(UserVO vo) throws Exception;
	
	// 등급별 유저 리스트
	public List<UserVO> getUserGradeList(UserVO vo);
	
	// 등급별 유저 카운팅
	public int getGradeCnt(UserVO vo);
	
	// 등급별 유저리스트(06.16수정)
	public List<UserVO> getTeamtList(UserVO vo);

}
