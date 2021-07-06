package com.commu.notice.dao;

import java.util.List;

import javax.inject.Inject;

import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Repository;

import com.commu.notice.vo.NReplyVO;

@Repository
public class NReplyDAOImpl implements NReplyDAO {
	
	@Inject SqlSession sql;

	// 댓글 조회
	@Override
	public List<NReplyVO> readReply(int bno) throws Exception {
		return sql.selectList("noticereplyMapper.readReply", bno);
	}
	
	// 댓글 작성
	@Override
	public void writeReply(NReplyVO vo) throws Exception {
		sql.insert("noticereplyMapper.writeReply", vo);
	}
	
	// 댓글 수정
	@Override
	public void updateReply(NReplyVO vo) throws Exception {
		sql.update("noticereplyMapper.updateReply", vo);
	}
	
	// 댓글 삭제
	@Override
	public void deleteReply(NReplyVO vo) throws Exception {
		sql.delete("noticereplyMapper.deleteReply", vo);
	}
	
	// 선택된 댓글 조회
	@Override
	public NReplyVO selectReply(int rno) throws Exception {
		return sql.selectOne("noticereplyMapper.selectReply",rno);
	}

}
