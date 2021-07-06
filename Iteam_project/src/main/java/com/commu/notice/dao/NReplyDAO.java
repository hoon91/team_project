package com.commu.notice.dao;

import java.util.List;

import com.commu.notice.vo.NReplyVO;

public interface NReplyDAO {

	// 댓글 조회
	public List<NReplyVO> readReply(int bno) throws Exception;

	// 댓글 작성
	public void writeReply(NReplyVO vo) throws Exception;

	// 댓글 수정
	public void updateReply(NReplyVO vo) throws Exception;

	// 댓글 삭제
	public void deleteReply(NReplyVO vo) throws Exception;

	// 선택된 댓글 조회
	public NReplyVO selectReply(int rno) throws Exception;


}
