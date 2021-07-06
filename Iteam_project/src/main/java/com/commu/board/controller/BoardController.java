package com.commu.board.controller;

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

import com.commu.board.service.BoardService;
import com.commu.board.service.ReplyService;
import com.commu.board.vo.BoardVO;
import com.commu.board.vo.ReplyVO;
import com.commu.page.PageMaker;
import com.commu.page.SearchCriteria;
import com.commu.user.vo.UserVO;

@Controller
//@RequestMapping("/board/*")
public class BoardController {

	private static final Logger logger = LoggerFactory.getLogger(BoardController.class);

	@Inject
	BoardService service;

	@Inject
	ReplyService replyService;

	// 게시판 글 작성 화면
	@RequestMapping(value = "/board/writeView.do", method = RequestMethod.GET)
	public void writeView() throws Exception {
		logger.info("/writeView");

	}

	// 게시판 글 작성
	@RequestMapping(value = "/board/write.do", method = RequestMethod.POST)
	public String write(BoardVO boardVO) throws Exception {
		logger.info("write");

		service.write(boardVO);

		return "redirect:/board/list.do";
	}

	// 게시판 목록 조회
	@RequestMapping(value = "/board/list.do", method = RequestMethod.GET)
	public String list(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception {
		logger.info("list");
		System.out.println(scri);
		
		model.addAttribute("list", service.list(scri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(service.listCount(scri));

		model.addAttribute("pageMaker", pageMaker);

		return "/board/list";

	}

	// 게시판 조회
	@RequestMapping(value = "/board/readView.do", method = RequestMethod.GET)
	public String read(BoardVO boardVO, @RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
		logger.info("read");
		
		service.boardCnt(bno); //조회수
		
		service.replyCount(bno);
		
		model.addAttribute("read", service.read(boardVO.getBno()));
		model.addAttribute("scri", scri);

		List<ReplyVO> replyList = replyService.readReply(boardVO.getBno());
		model.addAttribute("replyList", replyList);
		
		return "/board/readView";
	}
	//신고하기
	@RequestMapping(value = "/board/goReport.do", method = RequestMethod.GET)
	public String goReport(BoardVO boardVO,UserVO vo,@ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("goReport");
		
		System.out.println(vo.getId());
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		service.goReport(vo);
		
		return "redirect:/board/list.do";

	}

	// 게시판 수정뷰
	@RequestMapping(value = "/board/updateView.do", method = RequestMethod.GET)
	public String updateView(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, Model model)
			throws Exception {
		logger.info("updateView");

		model.addAttribute("update", service.read(boardVO.getBno()));
		model.addAttribute("scri", scri);

		return "/board/updateView";
	}

	// 게시판 수정
	@RequestMapping(value = "/board/update.do", method = RequestMethod.POST)
	public String update(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr)
			throws Exception {
		logger.info("update");

		service.update(boardVO);

		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/board/list.do";
	}

	// 게시판 삭제
	@RequestMapping(value = "/board/delete.do", method = RequestMethod.POST)
	public String delete(BoardVO boardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr)
			throws Exception {
		logger.info("delete");

		service.delete(boardVO.getBno());

		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/board/list.do";
	}
	
	// 게시글 추천
	@RequestMapping(value="/board/readView.do", method = RequestMethod.POST)
	public String recommend(@RequestParam("bno") int bno, BoardVO boardVO, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		
		service.recommend(bno);
		
		rttr.addAttribute("bno", boardVO.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/board/readView.do";
	}
		
	// 댓글 작성
	@RequestMapping(value = "/board/replyWrite.do", method = RequestMethod.POST)
	public String replyWrite(ReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");

		replyService.writeReply(vo);

		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/board/readView.do";
	}

	// 댓글 수정 GET
	@RequestMapping(value = "/board/replyUpdateView.do", method = RequestMethod.GET)
	public String replyUpdateView(ReplyVO vo, SearchCriteria scri, Model model) throws Exception {
		logger.info("reply Write");

		model.addAttribute("replyUpdate", replyService.selectReply(vo.getRno()));
		model.addAttribute("scri", scri);

		return "/board/replyUpdateView";
	}

	// 댓글 수정 POST
	@RequestMapping(value = "/board/replyUpdate.do", method = RequestMethod.POST)
	public String replyUpdate(ReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");

		replyService.updateReply(vo);

		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/board/readView.do";
	}

	// 댓글 삭제 GET
	@RequestMapping(value = "/board/replyDeleteView.do", method = RequestMethod.GET)
	public String replyDeleteView(ReplyVO vo, SearchCriteria scri, Model model) throws Exception {
		logger.info("reply Write");

		model.addAttribute("replyDelete", replyService.selectReply(vo.getRno()));
		model.addAttribute("scri", scri);

		return "/board/replyDeleteView";
	}

	// 댓글 삭제
	@RequestMapping(value = "/board/replyDelete.do", method = RequestMethod.POST)
	public String replyDelete(ReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");

		replyService.deleteReply(vo);

		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/board/readView.do";
	}
	
}