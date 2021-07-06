package com.commu.notice.service;

import java.util.List;

import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import com.commu.notice.dao.NBoardDAO;
import com.commu.notice.vo.NBoardVO;
import com.commu.page.SearchCriteria;
import com.commu.user.vo.UserVO;

@Service
public class NBoardServiceImpl implements NBoardService {

	@Inject
	private NBoardDAO ndao;

	// 게시글 작성
	@Override
	public void write(NBoardVO nboardVO) throws Exception {
		ndao.write(nboardVO);
	}

	// 게시물 목록 조회
	@Override
	public List<NBoardVO> list(SearchCriteria scri) throws Exception {
		return ndao.list(scri);
	}

	// 게시물 총 갯수
	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		return ndao.listCount(scri);
	}

	// 게시물 조회, 조회수
	@Transactional(isolation = Isolation.READ_COMMITTED)
	@Override
	public NBoardVO read(int bno) throws Exception {
			ndao.boardCnt(bno);
		return ndao.read(bno);
	}

	// 게시글 수정
	@Override
	public void update(NBoardVO nboardVO) throws Exception {
		ndao.update(nboardVO);
	}

	// 게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		ndao.delete(bno);
	}
	
	// 게시물 신고
	@Override
	public void goReport(UserVO userVO) throws Exception{
		ndao.goReport(userVO);
	}
	
	// 게시물 추천
	@Override
	public void recommend(int bno) throws Exception {
		ndao.recommend(bno);
	}
	
	// 게시물 추천수
	@Override
	public void boardCnt(int bno) throws Exception {
		ndao.boardCnt(bno);
		
	}

	@Override
	public void replyCount(int bno) throws Exception {
		ndao.replyCount(bno);
	}



}