package com.commu.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import com.commu.user.vo.UserVO;

public class UserRowMapper implements RowMapper<UserVO> {
	// 06.20 주소 추가
	@Override
	public UserVO mapRow(ResultSet rs, int rowNum) throws SQLException {
		UserVO vo = new UserVO();
		vo.setId(rs.getString(1));
		vo.setPassword(rs.getString(2));
		vo.setName(rs.getString(3));
		vo.setPostcode(rs.getString(4));
		vo.setRoadAddress(rs.getString(5));
		vo.setJubunAddress(rs.getString(6));
		vo.setDetailAddress(rs.getString(7));
		vo.setExtraAddress(rs.getString(8));
		vo.setIsreport(rs.getString(10));
		vo.setGrade(rs.getString(11));
		vo.setPno(rs.getInt(12));
		vo.setPhone(rs.getInt(13));
		vo.setCity(rs.getString(14));
		vo.setAge(rs.getInt(15));

		return vo;
	}

}
