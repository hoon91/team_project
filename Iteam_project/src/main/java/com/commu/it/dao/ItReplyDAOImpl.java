package com.commu.it.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.commu.it.vo.ItReplyVO;


@Repository
public class ItReplyDAOImpl implements ItReplyDAO {
	
	@Inject SqlSession sql;

	// 댓글 조회
	@Override
	public List<ItReplyVO> readReply(int bno) throws Exception {
		return sql.selectList("itreplyMapper.readReply", bno);
	}
	
	// 댓글 작성
	@Override
	public void writeReply(ItReplyVO vo) throws Exception {
		sql.insert("itreplyMapper.writeReply", vo);
	}
	
	// 댓글 수정
	@Override
	public void updateReply(ItReplyVO vo) throws Exception {
		sql.update("itreplyMapper.updateReply", vo);
	}
	
	// 댓글 삭제
	@Override
	public void deleteReply(ItReplyVO vo) throws Exception {
		sql.delete("itreplyMapper.deleteReply", vo);
	}
	
	// 선택된 댓글 조회
	@Override
	public ItReplyVO selectReply(int rno) throws Exception {
		return sql.selectOne("itreplyMapper.selectReply",rno);
	}

}
