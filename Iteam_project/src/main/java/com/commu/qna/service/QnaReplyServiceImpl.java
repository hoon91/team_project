package com.commu.qna.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;

import com.commu.qna.dao.QnaReplyDAO;
import com.commu.qna.vo.QnaReplyVO;




@Service
public class QnaReplyServiceImpl implements QnaReplyService {

	@Inject
	private QnaReplyDAO qnadao;

	// 댓글 조회
	@Override
	public List<QnaReplyVO> readReply(int bno) throws Exception {
		return qnadao.readReply(bno);
	}

	// 댓글 작성
	@Override
	public void writeReply(QnaReplyVO rvo) throws Exception {
		qnadao.writeReply(rvo);
	}

	// 댓글 수정
	@Override
	public void updateReply(QnaReplyVO rvo) throws Exception {
		qnadao.updateReply(rvo);
	}

	// 댓글 삭제
	@Override
	public void deleteReply(QnaReplyVO rvo) throws Exception {
		qnadao.deleteReply(rvo);
	}

	// 선택된 댓글 조회
	@Override
	public QnaReplyVO selectReply(int rno) throws Exception {
		return qnadao.selectReply(rno);
	}

}
