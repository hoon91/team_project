package com.commu.team.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.commu.team.dao.TeamReplyDAO;
import com.commu.team.vo.TeamReplyVO;



@Service
public class TeamReplyServiceImpl implements TeamReplyService {

	@Inject
	private TeamReplyDAO dao;

	// 댓글 조회
	@Override
	public List<TeamReplyVO> readReply(int bno) throws Exception {
		return dao.readReply(bno);
	}

	// 댓글 작성
	@Override
	public void writeReply(TeamReplyVO vo) throws Exception {
		dao.writeReply(vo);
	}

	// 댓글 수정
	@Override
	public void updateReply(TeamReplyVO vo) throws Exception {
		dao.updateReply(vo);
	}

	// 댓글 삭제
	@Override
	public void deleteReply(TeamReplyVO vo) throws Exception {
		dao.deleteReply(vo);
	}

	// 선택된 댓글 조회
	@Override
	public TeamReplyVO selectReply(int rno) throws Exception {
		return dao.selectReply(rno);
	}

}
