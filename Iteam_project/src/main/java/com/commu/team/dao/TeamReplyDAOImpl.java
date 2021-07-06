package com.commu.team.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.commu.team.vo.TeamReplyVO;



@Repository
public class TeamReplyDAOImpl implements TeamReplyDAO {
	
	@Inject SqlSession sql;

	// 댓글 조회
	@Override
	public List<TeamReplyVO> readReply(int bno) throws Exception {
		return sql.selectList("teamreplyMapper.readReply", bno);
	}
	
	// 댓글 작성
	@Override
	public void writeReply(TeamReplyVO vo) throws Exception {
		sql.insert("teamreplyMapper.writeReply", vo);
	}
	
	
	// 댓글 수정
	@Override
	public void updateReply(TeamReplyVO vo) throws Exception {
		sql.update("teamreplyMapper.updateReply", vo);
	}
	
	// 댓글 삭제
	@Override
	public void deleteReply(TeamReplyVO vo) throws Exception {
		sql.delete("teamreplyMapper.deleteReply", vo);
	}
	
	// 선택된 댓글 조회
	@Override
	public TeamReplyVO selectReply(int rno) throws Exception {
		return sql.selectOne("teamreplyMapper.selectReply",rno);
	}

}
