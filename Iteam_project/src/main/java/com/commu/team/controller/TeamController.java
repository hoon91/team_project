package com.commu.team.controller;


import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

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
import com.commu.team.service.TeamReplyService;
import com.commu.team.service.TeamService;
import com.commu.team.vo.TeamReplyVO;
import com.commu.team.vo.TeamVO;
import com.commu.user.vo.UserVO;

@Controller
//@RequestMapping("/team/*")
public class TeamController {

	private static final Logger logger = LoggerFactory.getLogger(TeamController.class);

	@Inject
	TeamService tservice;

	@Inject
	TeamReplyService treplyService;

	// 게시판 글 작성 화면
	@RequestMapping(value = "/team/writeView.do", method = RequestMethod.GET)
	public void writeView() throws Exception {
		logger.info("/writeView");

	}

	// 게시판 글 작성
	@RequestMapping(value = "/team/write.do", method = RequestMethod.POST)
	public String write(TeamVO teamVO) throws Exception {

	
			tservice.write(teamVO);

		return "redirect:/team/list.do";
	}

	// 게시판 목록 조회
	@RequestMapping(value = "/team/list.do", method = RequestMethod.GET)
	public String list(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception {
		logger.info("list");
		System.out.println(scri);
		model.addAttribute("list", tservice.list(scri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(tservice.listCount(scri));

		model.addAttribute("pageMaker", pageMaker);

		return "/team/list";

	}

	// 게시판 조회
	@RequestMapping(value = "/team/readView.do", method = RequestMethod.GET)
	public String read(TeamVO teamVO, UserVO vo ,  @RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
		logger.info("read");
		
		tservice.boardCnt(bno); //조회수
		
		tservice.replyCount(bno);
		
		model.addAttribute("read", tservice.read(teamVO.getBno()));
		model.addAttribute("scri", scri);
		model.addAttribute("number",teamVO.getBno());
		
		
		vo.setPno(teamVO.getBno());
		model.addAttribute("teamCnt", tservice.teamCnt(vo));

		List<TeamReplyVO> replyList = treplyService.readReply(teamVO.getBno());
		model.addAttribute("replyList", replyList);

		return "/team/readView";
	}
	//신고하기
	@RequestMapping(value = "/team/goReport.do", method = RequestMethod.GET)
	public String goReport(TeamVO teamVO,UserVO vo,@ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("goReport");
		
		System.out.println(vo.getId());
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		tservice.goReport(vo);
		
		return "redirect:/team/list.do";

	}
	
	//팀 게시판 생성(06.14수정)
	@RequestMapping(value = "/team/makeTeam.do", method = RequestMethod.GET)
	public String makeTeam(TeamVO teamVO,UserVO vo,@ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr,  HttpSession session) throws Exception {
		logger.info("makeTeam");
		
		System.out.println(vo.getPno());
		System.out.println(vo.getId());
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		
		tservice.makeTeam(vo);

		System.out.println(tservice.teamCnt(vo));
		session.setAttribute("user", tservice.sessionUpdate(vo));
		return "redirect:/team/list.do";
		
	}
	
	//프로젝트 팀원 리스트(06.17수정)
	@RequestMapping(value = "/team/teamProject.do", method = RequestMethod.GET)
	public String teamList(TeamVO teamVO,UserVO vo,@ModelAttribute("scri") SearchCriteria scri, Model model, RedirectAttributes rttr) throws Exception {
		
		teamVO.setBno(vo.getPno());
		System.out.println(teamVO.getBno());
		model.addAttribute("teamList", tservice.teamList(vo));
		model.addAttribute("teamInfo", tservice.teamInfo(teamVO));
		model.addAttribute("read", tservice.read(vo.getPno()));

		System.out.println(tservice.teamList(vo).get(0).getId());
		return "/team/teamProject";
		
	}
	//프로젝트 나가기 추방 기능(06.18수정)
	@RequestMapping(value = "/team/teamQuick.do", method = RequestMethod.GET)
	public String teamQuick(TeamVO teamVO,UserVO vo,@ModelAttribute("scri") SearchCriteria scri, Model model, RedirectAttributes rttr,HttpSession session) throws Exception {
		
		tservice.teamQuick(vo);
		session.setAttribute("user", tservice.sessionUpdate(vo));
		return "redirect:/";
		
	}

	// 게시판 수정뷰
	@RequestMapping(value = "/team/updateView.do", method = RequestMethod.GET)
	public String updateView(TeamVO teamVO, @ModelAttribute("scri") SearchCriteria scri, Model model)
			throws Exception {
		logger.info("updateView");

		model.addAttribute("update", tservice.read(teamVO.getBno()));
		model.addAttribute("scri", scri);

		return "/team/updateView";
	}

	// 게시판 수정
	@RequestMapping(value = "/team/update.do", method = RequestMethod.POST)
	public String update(TeamVO teamVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr)
			throws Exception {
		logger.info("update");


		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());	
		

		tservice.update(teamVO); 

		return "redirect:/team/list.do";
	}

	// 게시판 삭제
	@RequestMapping(value = "/team/delete.do", method = RequestMethod.POST)
	public String delete(TeamVO teamVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr)
			throws Exception {
		logger.info("delete");

		tservice.delete(teamVO.getBno());

		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/team/list.do";
	}
	
	@RequestMapping(value = "/team/teamComplete.do", method = RequestMethod.GET)
	public String teamComplete(TeamVO teamVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr,UserVO vo, HttpSession session)
			throws Exception {
		logger.info("complete");
		vo.setPno(teamVO.getBno());
		tservice.teamComplete(teamVO);
		tservice.teamDelete(vo);
		session.setAttribute("user", tservice.sessionUpdate(vo));

		
		return "redirect:/team/list.do";
	}
	
	@RequestMapping(value="/team/readView.do", method = RequestMethod.POST)
	public String recommend(@RequestParam("bno") int bno, TeamVO teamVO, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		
		tservice.recommend(bno);
		rttr.addAttribute("bno", teamVO.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/team/readView.do";
	}
	
	// 댓글 작성
	@RequestMapping(value = "/team/replyWrite.do", method = RequestMethod.POST)
	public String replyWrite(TeamReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");

		treplyService.writeReply(vo);

		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/team/readView.do";
	}

	// 댓글 수정 GET
	@RequestMapping(value = "/team/replyUpdateView.do", method = RequestMethod.GET)
	public String replyUpdateView(TeamReplyVO vo, SearchCriteria scri, Model model) throws Exception {
		logger.info("reply Write");

		model.addAttribute("replyUpdate", treplyService.selectReply(vo.getRno()));
		model.addAttribute("scri", scri);

		return "/team/replyUpdateView";
	}

	// 댓글 수정 POST
	@RequestMapping(value = "/team/replyUpdate.do", method = RequestMethod.POST)
	public String replyUpdate(TeamReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");

		treplyService.updateReply(vo);

		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/team/readView.do";
	}

	// 댓글 삭제 GET
	@RequestMapping(value = "/team/replyDeleteView.do", method = RequestMethod.GET)
	public String replyDeleteView(TeamReplyVO vo, SearchCriteria scri, Model model) throws Exception {
		logger.info("reply Write");

		model.addAttribute("replyDelete", treplyService.selectReply(vo.getRno()));
		model.addAttribute("scri", scri);

		return "/team/replyDeleteView";
	}

	// 댓글 삭제
	@RequestMapping(value = "/team/replyDelete.do", method = RequestMethod.POST)
	public String replyDelete(TeamReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");

		treplyService.deleteReply(vo);

		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/team/readView.do";
	}

}