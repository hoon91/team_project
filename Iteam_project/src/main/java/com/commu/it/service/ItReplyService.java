package com.commu.it.service;

import java.util.List;

import com.commu.it.vo.ItReplyVO;



public interface ItReplyService {

	// 댓글 조회
	public List<ItReplyVO> readReply(int bno) throws Exception;

	// 댓글 작성
	public void writeReply(ItReplyVO vo) throws Exception;

	// 댓글 수정
	public void updateReply(ItReplyVO vo) throws Exception;

	// 댓글 삭제
	public void deleteReply(ItReplyVO vo) throws Exception;

	// 선택된 댓글 조회
	public ItReplyVO selectReply(int rno) throws Exception;
}
