package com.commu.qna.controller;

import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;


import com.commu.page.PageMaker;
import com.commu.page.SearchCriteria;
import com.commu.qna.service.QnaBoardService;
import com.commu.qna.service.QnaReplyService;
import com.commu.qna.vo.QnaBoardVO;
import com.commu.qna.vo.QnaReplyVO;
import com.commu.user.vo.UserVO;

@Controller
//@RequestMapping("/qna/*")
public class QnaBoardController {

	private static final Logger logger = LoggerFactory.getLogger(QnaBoardController.class);

	@Inject
	QnaBoardService qnaservice;

	@Inject
	QnaReplyService qnareplyService;

	// 게시판 글 작성 화면
	@RequestMapping(value = "/qna/writeView.do", method = RequestMethod.GET)
	public void writeView() throws Exception {
		logger.info("/writeView");

	}

	// 게시판 글 작성
	@RequestMapping(value = "/qna/write.do", method = RequestMethod.POST)
	public String write(QnaBoardVO qnaboardVO) throws Exception {
		logger.info("write");

		qnaservice.write(qnaboardVO);

		return "redirect:/qna/list.do";
	}

	// 게시판 목록 조회
	@RequestMapping(value = "/qna/list.do", method = RequestMethod.GET)
	public String list(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception {
		logger.info("list");
		System.out.println(scri);
		model.addAttribute("list", qnaservice.list(scri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(qnaservice.listCount(scri));

		model.addAttribute("pageMaker", pageMaker);

		return "/qna/list";

	}

	// 게시판 조회
	@RequestMapping(value = "/qna/readView.do", method = RequestMethod.GET)
	public String read(QnaBoardVO boardVO, @RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
		logger.info("read");
		
		qnaservice.boardCnt(bno); //조회수
		
		qnaservice.replyCount(bno);
		
		model.addAttribute("read", qnaservice.read(boardVO.getBno()));
		model.addAttribute("scri", scri);

		List<QnaReplyVO> replyList = qnareplyService.readReply(boardVO.getBno());
		model.addAttribute("replyList", replyList);

		return "/qna/readView";
	}
	//신고하기
	@RequestMapping(value = "/qna/goReport.do", method = RequestMethod.GET)
	public String goReport(QnaBoardVO boardVO,UserVO vo,@ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("goReport");
		
		System.out.println(vo.getId());
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		qnaservice.goReport(vo);
		
		return "redirect:/qna/list.do";

	}

	// 게시판 수정뷰
	@RequestMapping(value = "/qna/updateView.do", method = RequestMethod.GET)
	public String updateView(QnaBoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, Model model)
			throws Exception {
		logger.info("updateView");

		model.addAttribute("update", qnaservice.read(boardVO.getBno()));
		model.addAttribute("scri", scri);

		return "/qna/updateView";
	}

	// 게시판 수정
	@RequestMapping(value = "/qna/update.do", method = RequestMethod.POST)
	public String update(QnaBoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr)
			throws Exception {
		logger.info("update");

		qnaservice.update(boardVO);

		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/qna/list.do";
	}

	// 게시판 삭제
	@RequestMapping(value = "/qna/delete.do", method = RequestMethod.POST)
	public String delete(QnaBoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr)
			throws Exception {
		logger.info("delete");

		qnaservice.delete(boardVO.getBno());

		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/qna/list.do";
	}
	
	@RequestMapping(value="/qna/readView.do", method = RequestMethod.POST)
	public String recommend(@RequestParam("bno") int bno, QnaBoardVO boardVO, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		
		qnaservice.recommend(bno);
		
		rttr.addAttribute("bno", boardVO.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/qna/readView.do";
	}
	
	// 댓글 작성
	@RequestMapping(value = "/qna/replyWrite.do", method = RequestMethod.POST)
	public String replyWrite(QnaReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");

		qnareplyService.writeReply(vo);

		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/qna/readView.do";
	}

	// 댓글 수정 GET
	@RequestMapping(value = "/qna/replyUpdateView.do", method = RequestMethod.GET)
	public String replyUpdateView(QnaReplyVO vo, SearchCriteria scri, Model model) throws Exception {
		logger.info("reply Write");

		model.addAttribute("replyUpdate", qnareplyService.selectReply(vo.getRno()));
		model.addAttribute("scri", scri);

		return "/qna/replyUpdateView";
	}

	// 댓글 수정 POST
	@RequestMapping(value = "/qna/replyUpdate.do", method = RequestMethod.POST)
	public String replyUpdate(QnaReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");
		
		qnareplyService.updateReply(vo);

		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/qna/readView.do";
	}

	// 댓글 삭제 GET
	@RequestMapping(value = "/qna/replyDeleteView.do", method = RequestMethod.GET)
	public String replyDeleteView(QnaReplyVO vo, SearchCriteria scri, Model model) throws Exception {
		logger.info("reply Write");

		model.addAttribute("replyDelete", qnareplyService.selectReply(vo.getRno()));
		model.addAttribute("scri", scri);

		return "/qna/replyDeleteView";
	}

	// 댓글 삭제
	@RequestMapping(value = "/qna/replyDelete.do", method = RequestMethod.POST)
	public String replyDelete(QnaReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");

		qnareplyService.deleteReply(vo);

		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/qna/readView.do";
	}

}