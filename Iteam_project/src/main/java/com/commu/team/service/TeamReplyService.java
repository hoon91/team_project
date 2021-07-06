package com.commu.team.service;

import java.util.List;

import com.commu.team.vo.TeamReplyVO;



public interface TeamReplyService {

	// 댓글 조회
	public List<TeamReplyVO> readReply(int bno) throws Exception;

	// 댓글 작성
	public void writeReply(TeamReplyVO vo) throws Exception;

	// 댓글 수정
	public void updateReply(TeamReplyVO vo) throws Exception;

	// 댓글 삭제
	public void deleteReply(TeamReplyVO vo) throws Exception;

	// 선택된 댓글 조회
	public TeamReplyVO selectReply(int rno) throws Exception;
}
