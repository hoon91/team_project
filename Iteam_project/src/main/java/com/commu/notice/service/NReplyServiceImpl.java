package com.commu.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.commu.notice.dao.NReplyDAO;
import com.commu.notice.vo.NReplyVO;


@Service
public class NReplyServiceImpl implements NReplyService {

	@Inject
	private NReplyDAO ndao;

	// 댓글 조회
	@Override
	public List<NReplyVO> readReply(int bno) throws Exception {
		return ndao.readReply(bno);
	}

	// 댓글 작성
	@Override
	public void writeReply(NReplyVO rvo) throws Exception {
		ndao.writeReply(rvo);
	}

	// 댓글 수정
	@Override
	public void updateReply(NReplyVO rvo) throws Exception {
		ndao.updateReply(rvo);
	}

	// 댓글 삭제
	@Override
	public void deleteReply(NReplyVO rvo) throws Exception {
		ndao.deleteReply(rvo);
	}

	// 선택된 댓글 조회
	@Override
	public NReplyVO selectReply(int rno) throws Exception {
		return ndao.selectReply(rno);
	}

}
