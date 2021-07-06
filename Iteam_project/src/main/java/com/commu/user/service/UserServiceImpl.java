package com.commu.user.service;

import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.commu.user.dao.UserDAOSpring;
import com.commu.user.vo.UserVO;



@Service("userService")
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserDAOSpring userDao;

	@Override
	public void insertUser(UserVO vo) throws Exception {
		userDao.insertUser(vo);
	}

	@Override
	public UserVO getUser(UserVO vo) throws SQLException {
		return userDao.getUser(vo);
	}

	@Override
	public int deleteUser(UserVO vo) throws SQLException {
		return userDao.deleteUser(vo);
	}

	@Override
	public void updateUser(UserVO vo) throws Exception {
		userDao.updateUser(vo);
	}
	
	@Override
	public void updateUserGrade(UserVO vo) throws Exception {
		userDao.updateUserGrade(vo);
	}

	@Override
	public int getUserCnt(UserVO vo) {
		return userDao.getUserCnt(vo);
	}

	@Override
	public UserVO getLogin(UserVO vo) {
		return userDao.getLogin(vo);
	}

	@Override
	public int getUserCntByPass(UserVO vo) {
		return userDao.getUserCntByPass(vo);
	}

	@Override
	public List<UserVO> getUsers(UserVO vo) {
		return userDao.getUsers(vo);
	}

	@Override
	public List<UserVO> getReportUser(UserVO vo) {
		return userDao.getReportUser(vo);
	}

	@Override
	public List<UserVO> getUserGrade(UserVO vo) {
		return userDao.getUserGrade(vo);
	}

	@Override
	public int countReportUser(UserVO vo) {
		return userDao.countReportUser(vo);
	}

	@Override
	public List<UserVO> getUserGradeList(UserVO vo) {
		return userDao.getUserGradeList(vo);
	}

	@Override
	public int getGradeCnt(UserVO vo) {
		return userDao.getGradeCnt(vo);
	}

	@Override
	public void returnReport(UserVO vo) {
		userDao.returnReport(vo);
	}

	@Override
	public List<UserVO> getTeamtList(UserVO vo) {
		return userDao.getTeamtList(vo);
	}

	@Override
	public UserVO readInfo(String id) throws Exception {
		return userDao.readInfo(id);
	}

	@Override
	public void updateInfo(UserVO vo) throws Exception {
		userDao.updateInfo(vo);
	}

	@Override
	public int ajaxUserCnt(String id) {	
		return userDao.ajaxUserCnt(id);
	}

}
