package com.commu.user.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.commu.user.vo.UserVO;

@Repository("userDao")
public class UserDAOSpring {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	private Connection conn = null;
	private PreparedStatement stmt = null;
	private ResultSet rs = null;

	String str;

	private final String USER_INSERT = "insert into users(id, password, name, postcode, roadAddress, jubunAddress, detailAddress, extraAddress, isreport, phone, city, age) "
			+ " values(?,?,?,?,?,?,?,?,?,?,?,?)";
	private final String USER_GET = "select * from users where id=?";
	private final String USER_REPORT_LIST = "select * from (select a.*, rownum rn from (select *from users where isreport='true' order by grade)a) where rn between ? and ?";
	private final String USER_REPORT_COUNT = "select count(*) from users where isreport='true'";
	private final String USER_UPDATE_REPORT = "update users set isreport='' where id=?";
	
	private final String USER_CNT = "select count(*) from users where id=?";
	private final String USER_CNT_AJAX = "select count(*) from users where id=?";
	private final String USER_LOGIN = "select * from users where id=? and password=?";
	private final String USER_CHANGE_PASS = "update users set password=? where id=?";
	private final String USER_CNT_BYPASS = "select count(*) from users where id=? and password=?";
	private final String USER_LIST = "select * from users order by id";
	private final String USER_DELETE = "delete from users where id=?";
	private final String USER_GRADE = "select * from users where grade=? ";
	private final String USER_CHANGE_GRADE = "update users set grade=? where id=?";
	private final String USER_GRADE_LIST = "select * from (select a.*, rownum rn from (select *from users where grade=? order by grade)a) where rn between ? and ?";
	private final String USER_CNT_GRADE = "select count(*) from users where grade=?";
	
	
	private final String TEAM_LIST = "select * from users where pno=?";
	
	//???????????? ?????? (21.06.19 ??????)
	private final String USERINFO = "select * from users where id=?";
	private final String USERINFO_UPDATE = "update users set postcode=?, roadAddress=?, jubunAddress=?, detailAddress=?, extraAddress=?, phone=?, city=?, age=? where id = ?";
		
	// ????????????
	public void insertUser(UserVO vo) {
		System.out.println("===> SPRING JDBC??? insertUser() ?????? ??????");
		jdbcTemplate.update(USER_INSERT, vo.getId(), vo.getPassword(), vo.getName(), vo.getPostcode(),
				vo.getRoadAddress(), vo.getJubunAddress(), vo.getDetailAddress(), vo.getExtraAddress(),
				vo.getIsreport(),vo.getPhone(),vo.getCity(),vo.getAge());
	}
	
	// ??????????????? ??????
	public int getUserCnt(UserVO vo) {
		System.out.println("===> SPRING JDBC??? getUserCnt() ?????? ??????");
int result=jdbcTemplate.queryForInt(USER_CNT, vo.getId());
		
		System.out.println("??????: "+result);
		return result;
	}
	
	// ajax ??????????????????
	public int ajaxUserCnt(String id) {
		System.out.println("===> SPRING JDBC??? ajaxUserCnt() ?????? ??????");
		System.out.println("id???: "+id);
		int result = jdbcTemplate.queryForInt(USER_CNT_AJAX, id);
		System.out.println("??????: "+result);
		return result;
	}
	// ????????? ????????? ?????? ???????????? ??????
	public int getUserCntByPass(UserVO vo) {
		System.out.println("===> SPRING JDBC??? getUserCntByPass() ?????? ??????");
		return jdbcTemplate.queryForInt(USER_CNT_BYPASS, vo.getId(), vo.getPassword());
	}
	
	// ?????????
	public UserVO getLogin(UserVO vo) {
		System.out.println("===> SPRING JDBC??? getLogin() ?????? ??????");
		Object[] args = { vo.getId(), vo.getPassword() };
		return jdbcTemplate.queryForObject(USER_LOGIN, args, new UserRowMapper());
	}
	
	// ???????????? ??????
	public void updateUser(UserVO vo) {
		jdbcTemplate.update(USER_CHANGE_PASS, vo.getPassword(), vo.getId());
	}
	
	// ????????????
	public UserVO getUser(UserVO vo) {
		System.out.println("===> SPRING JDBC??? getUser() ?????? ??????");
		Object[] args = { vo.getId() };
		return jdbcTemplate.queryForObject(USER_GET, args, new UserRowMapper());
	}
	
	// ?????? ??????
	public int deleteUser(UserVO vo) {
		return jdbcTemplate.update(USER_DELETE, vo.getId());// ????????? ?????? ???
	}
	
	// ???????????? ?????? (21.06.19)
	public UserVO readInfo(String id) throws Exception {
		Object[] args = {id};
		try {
			return (UserVO) jdbcTemplate.queryForObject(USERINFO, args, new UserRowMapper());
		} catch (EmptyResultDataAccessException e) { // EmptyResultDataAccessException ?????? ????????? null ??????
			return null;
		}
	}
		
	// ???????????? ??????
	public void updateInfo(UserVO vo) throws Exception {
		jdbcTemplate.update(USERINFO_UPDATE, vo.getPostcode(), vo.getRoadAddress(), vo.getJubunAddress(), vo.getDetailAddress(), 
				vo.getExtraAddress(), vo.getPhone(), vo.getCity(), vo.getAge(), vo.getId());
	}
		
		
	// ???????????? ??????
	public List<UserVO> getReportUser(UserVO vo) {
		System.out.println("===> SPRING JDBC??? getReportUser() ?????? ??????");
		Object[] args = { vo.getStartRow(), vo.getEndRow() };
		return jdbcTemplate.query(USER_REPORT_LIST, args, new UserRowMapper());
	}
	
	// ?????? ?????? ???
	public int countReportUser(UserVO vo) {
		System.out.println("===> SPRING JDBC??? countReportUser() ?????? ??????");
		return jdbcTemplate.queryForInt(USER_REPORT_COUNT);
	}
	
	//?????? ??????(06.14)
	public void returnReport(UserVO vo) {
		jdbcTemplate.update(USER_UPDATE_REPORT, vo.getId());
	}
		
	// ?????? ?????? ??????
	public List<UserVO> getUsers(UserVO vo) {
		return jdbcTemplate.query(USER_LIST, new UserRowMapper());
	}
		
	// ?????? ?????? ??????
	public List<UserVO> getUserGrade(UserVO vo) {
		System.out.println("===> SPRING JDBC??? getReportUser() ?????? ??????");
		Object[] args = { vo.getGrade() };
		return jdbcTemplate.query(USER_GRADE, args, new UserRowMapper());
	}

	// ?????? ?????? ??????
	public void updateUserGrade(UserVO vo) {
		jdbcTemplate.update(USER_CHANGE_GRADE, vo.getGrade(), vo.getId());
	}
	
	//????????? ???????????????(06.06??????)
	public List<UserVO> getUserGradeList(UserVO vo) {
		System.out.println("===> SPRING JDBC??? getUserGradeList() ?????? ??????");
		Object[] args = { vo.getGrade(), vo.getStartRow(), vo.getEndRow() };
		return jdbcTemplate.query(USER_GRADE_LIST, args, new UserRowMapper());
	}
	
	//????????? ?????? ?????????(06.06??????)
	public int getGradeCnt(UserVO vo) {
		System.out.println("===> SPRING JDBC??? getGradeCnt() ?????? ??????");
		return jdbcTemplate.queryForInt(USER_CNT_GRADE, vo.getGrade());
	}
	
	//????????? ???????????????(06.16??????)
	public List<UserVO> getTeamtList(UserVO vo) {
		System.out.println("===> SPRING JDBC??? getTeamtList() ?????? ??????");
		Object[] args = {vo.getPno()};
		return jdbcTemplate.query(TEAM_LIST, args, new UserRowMapper());
	}
	
	
	
	
}
