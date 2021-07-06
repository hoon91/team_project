package com.commu.qna.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;



import com.commu.page.SearchCriteria;
import com.commu.qna.dao.QnaBoardDAO;
import com.commu.qna.vo.QnaBoardVO;
import com.commu.user.vo.UserVO;

@Service
public class QnaBoardServiceImpl implements QnaBoardService {

	@Inject
	private QnaBoardDAO qnadao;

	// 게시글 작성
	@Override
	public void write(QnaBoardVO nboardVO) throws Exception {
		qnadao.write(nboardVO);
	}

	// 게시물 목록 조회
	@Override
	public List<QnaBoardVO> list(SearchCriteria scri) throws Exception {
		return qnadao.list(scri);
	}

	// 게시물 총 갯수
	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		return qnadao.listCount(scri);
	}

	// 게시물 조회
	@Override
	public QnaBoardVO read(int bno) throws Exception {
			qnadao.boardCnt(bno);
		return qnadao.read(bno);
	}

	// 게시글 수정
	@Override
	public void update(QnaBoardVO nboardVO) throws Exception {
		qnadao.update(nboardVO);
	}

	// 게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		qnadao.delete(bno);
	}
	
	// 게시물 신고
	@Override
	public void goReport(UserVO userVO) throws Exception{
		qnadao.goReport(userVO);
	}
	
	// 게시물 추천
	@Override
	public void recommend(int bno) throws Exception {
		qnadao.recommend(bno);
	}
	
	// 게시물 조회수
	@Override
	public void boardCnt(int bno) throws Exception {
		qnadao.boardCnt(bno);
	}

	@Override
	public void replyCount(int bno) throws Exception {
		qnadao.replyCount(bno);
	}

}