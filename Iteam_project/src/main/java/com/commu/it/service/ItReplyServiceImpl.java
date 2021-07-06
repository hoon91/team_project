package com.commu.it.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.commu.it.dao.ItReplyDAO;
import com.commu.it.vo.ItReplyVO;



@Service
public class ItReplyServiceImpl implements ItReplyService {

	@Inject
	private ItReplyDAO dao;

	// 댓글 조회
	@Override
	public List<ItReplyVO> readReply(int bno) throws Exception {
		return dao.readReply(bno);
	}

	// 댓글 작성
	@Override
	public void writeReply(ItReplyVO vo) throws Exception {
		dao.writeReply(vo);
	}

	// 댓글 수정
	@Override
	public void updateReply(ItReplyVO vo) throws Exception {
		dao.updateReply(vo);
	}

	// 댓글 삭제
	@Override
	public void deleteReply(ItReplyVO vo) throws Exception {
		dao.deleteReply(vo);
	}

	// 선택된 댓글 조회
	@Override
	public ItReplyVO selectReply(int rno) throws Exception {
		return dao.selectReply(rno);
	}

}
