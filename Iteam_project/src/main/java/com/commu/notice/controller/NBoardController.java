package com.commu.notice.controller;

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

import com.commu.notice.service.NBoardService;
import com.commu.notice.service.NReplyService;
import com.commu.notice.vo.NBoardVO;
import com.commu.notice.vo.NReplyVO;
import com.commu.page.PageMaker;
import com.commu.page.SearchCriteria;
import com.commu.user.vo.UserVO;

@Controller
//@RequestMapping("/notice/*")
public class NBoardController {

	private static final Logger logger = LoggerFactory.getLogger(NBoardController.class);

	@Inject
	NBoardService nservice;

	@Inject
	NReplyService nreplyService;

	// 게시판 글 작성 화면
	@RequestMapping(value = "/notice/writeView.do", method = RequestMethod.GET)
	public void writeView() throws Exception {
		logger.info("/writeView");

	}

	// 게시판 글 작성
	@RequestMapping(value = "/notice/write.do", method = RequestMethod.POST)
	public String write(NBoardVO nboardVO) throws Exception {
		logger.info("write");

		nservice.write(nboardVO);

		return "redirect:/notice/list.do";
	}

	// 게시판 목록 조회
	@RequestMapping(value = "/notice/list.do", method = RequestMethod.GET)
	public String list(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception {
		logger.info("list");
		System.out.println(scri);
		model.addAttribute("list", nservice.list(scri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(nservice.listCount(scri));

		model.addAttribute("pageMaker", pageMaker);

		return "/notice/list";

	}

	// 게시판 조회
	@RequestMapping(value = "/notice/readView.do", method = RequestMethod.GET)
	public String read(NBoardVO nboardVO, @RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
		logger.info("read");
		
		nservice.boardCnt(bno); //조회수
		
		nservice.replyCount(bno);
		
		model.addAttribute("read", nservice.read(nboardVO.getBno()));
		model.addAttribute("scri", scri);

		List<NReplyVO> replyList = nreplyService.readReply(nboardVO.getBno());
		model.addAttribute("replyList", replyList);

		return "/notice/readView";
	}
	//신고하기
	@RequestMapping(value = "/notice/goReport.do", method = RequestMethod.POST)
	public String goReport(NBoardVO nboardVO, UserVO vo,@ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("goReport");
		
		System.out.println(vo.getId());
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		nservice.goReport(vo);
		
		return "redirect:/notice/list.do";

	}

	// 게시판 수정뷰
	@RequestMapping(value = "/notice/updateView.do", method = RequestMethod.GET)
	public String updateView(NBoardVO nboardVO, @ModelAttribute("scri") SearchCriteria scri, Model model)
			throws Exception {
		logger.info("updateView");

		model.addAttribute("update", nservice.read(nboardVO.getBno()));
		model.addAttribute("scri", scri);

		return "/notice/updateView";
	}

	// 게시판 수정
	@RequestMapping(value = "/notice/update.do", method = RequestMethod.POST)
	public String update(NBoardVO nboardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr)
			throws Exception {
		logger.info("update");

		nservice.update(nboardVO);

		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/notice/list.do";
	}

	// 게시판 삭제
	@RequestMapping(value = "/notice/delete.do", method = RequestMethod.POST)
	public String delete(NBoardVO nboardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr)
			throws Exception {
		logger.info("delete");

		nservice.delete(nboardVO.getBno());

		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/notice/list.do";
	}
	
	@RequestMapping(value="/notice/readView.do", method = RequestMethod.POST)
	public String recommend(@RequestParam("bno") int bno, NBoardVO nboardVO, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		
		nservice.recommend(bno);
		
		rttr.addAttribute("bno", nboardVO.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/notice/readView.do";
	}
	
	// 댓글 작성
	@RequestMapping(value = "/notice/replyWrite.do", method = RequestMethod.POST)
	public String replyWrite(NReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");

		nreplyService.writeReply(vo);

		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/notice/readView.do";
	}

	// 댓글 수정 GET
	@RequestMapping(value = "/notice/replyUpdateView.do", method = RequestMethod.GET)
	public String replyUpdateView(NReplyVO vo, SearchCriteria scri, Model model) throws Exception {
		logger.info("reply Write");

		model.addAttribute("replyUpdate", nreplyService.selectReply(vo.getRno()));
		model.addAttribute("scri", scri);

		return "/notice/replyUpdateView";
	}

	// 댓글 수정 POST
	@RequestMapping(value = "/notice/replyUpdate.do", method = RequestMethod.POST)
	public String replyUpdate(NReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");

		nreplyService.updateReply(vo);

		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/notice/readView.do";
	}

	// 댓글 삭제 GET
	@RequestMapping(value = "/notice/replyDeleteView.do", method = RequestMethod.GET)
	public String replyDeleteView(NReplyVO vo, SearchCriteria scri, Model model) throws Exception {
		logger.info("reply Write");

		model.addAttribute("replyDelete", nreplyService.selectReply(vo.getRno()));
		model.addAttribute("scri", scri);

		return "/notice/replyDeleteView";
	}

	// 댓글 삭제
	@RequestMapping(value = "/notice/replyDelete.do", method = RequestMethod.POST)
	public String replyDelete(NReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");

		nreplyService.deleteReply(vo);

		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/notice/readView.do";
	}

}