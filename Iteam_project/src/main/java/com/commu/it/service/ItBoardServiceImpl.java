package com.commu.it.service;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.inject.Inject;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.commu.it.dao.ItBoardDAO;
import com.commu.it.vo.ItBoardVO;
import com.commu.page.FileUtils;
import com.commu.page.SearchCriteria;
import com.commu.user.vo.UserVO;
@Service
public class ItBoardServiceImpl implements ItBoardService {
	@Resource(name="fileUtils")
	private FileUtils fileUtils;
	
	@Inject
	private ItBoardDAO dao;

	// 게시글 작성
	@Override
	public void write(ItBoardVO itboardVO,  MultipartHttpServletRequest mpRequest) throws Exception {
		dao.write(itboardVO);
		
		List<Map<String,Object>> list = fileUtils.parseInsertFileInfo(itboardVO, mpRequest); 
		int size = list.size();
		for(int i=0; i<size; i++){ 
			dao.insertFile(list.get(i)); 
		}
	}

	// 게시물 목록 조회
	@Override
	public List<ItBoardVO> list(SearchCriteria scri) throws Exception {
		return dao.list(scri);
	}

	// 게시물 총 갯수
	@Override
	public int listCount(SearchCriteria scri) throws Exception {
		return dao.listCount(scri);
	}

	// 게시물 조회
	@Override
	public ItBoardVO read(int bno) throws Exception {
		return dao.read(bno);
	}

	// 게시글 수정
	@Override
	public void update(ItBoardVO itboardVO, String[] files, String[] fileNames, MultipartHttpServletRequest mpRequest) throws Exception {
		
		dao.update(itboardVO);
		
		List<Map<String, Object>> list = fileUtils.parseUpdateFileInfo(itboardVO, files, fileNames, mpRequest);
		Map<String, Object> tempMap = null;
		int size = list.size();
		for(int i = 0; i<size; i++) {
			tempMap = list.get(i);
			if(tempMap.get("IS_NEW").equals("Y")) {
				dao.insertFile(tempMap);
			}else {
				dao.updateFile(tempMap);
			}
		}
	}

	// 게시글 삭제
	@Override
	public void delete(int bno) throws Exception {
		dao.delete(bno);
	}
	
	// 게시물 신고
	@Override
	public void goReport(UserVO userVO) throws Exception{
		dao.goReport(userVO);
	}
	
	// 추천
	@Override
	public void recommend(int bno) throws Exception {
		dao.recommend(bno);
	}

	// 조회수
	@Override
	public void boardCnt(int bno) throws Exception {
		dao.boardCnt(bno);
	}

	// 첨부파일 조회
	@Override
	public List<Map<String, Object>> selectFileList(int bno) throws Exception {
		return dao.selectFileList(bno);
	}

	// 첨부파일 다운로드
	@Override
	public Map<String, Object> selectFileInfo(Map<String, Object> map) throws Exception {
		return dao.selectFileInfo(map);
	}

	@Override
	public void replyCount(int bno) throws Exception {
		dao.replyCount(bno);
	}

}
