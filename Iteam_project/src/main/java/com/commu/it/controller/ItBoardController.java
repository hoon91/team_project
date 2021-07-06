package com.commu.it.controller;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Map;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.commu.it.service.ItBoardService;
import com.commu.it.service.ItReplyService;
import com.commu.it.vo.ItBoardVO;
import com.commu.it.vo.ItReplyVO;
import com.commu.page.PageMaker;
import com.commu.page.SearchCriteria;
import com.commu.user.vo.UserVO;

@Controller
//@RequestMapping("/itboard/*")
public class ItBoardController {

	private static final Logger logger = LoggerFactory.getLogger(ItBoardController.class);

	@Inject
	ItBoardService service;

	@Inject
	ItReplyService replyService;

	// 게시판 글 작성 화면
	@RequestMapping(value = "/itboard/writeView.do", method = RequestMethod.GET)
	public void writeView() throws Exception {
		logger.info("/writeView");

	}

	// 게시판 글 작성
	@RequestMapping(value = "/itboard/write.do", method = RequestMethod.POST)
	public String write(ItBoardVO itboardVO, MultipartHttpServletRequest mpRequest) throws Exception {
		logger.info("write");

		service.write(itboardVO, mpRequest);

		return "redirect:/itboard/list.do";
	}

	// 게시판 목록 조회
	@RequestMapping(value = "/itboard/list.do", method = RequestMethod.GET)
	public String list(Model model, @ModelAttribute("scri") SearchCriteria scri) throws Exception {
		logger.info("list");
		System.out.println(scri);
		model.addAttribute("list", service.list(scri));

		PageMaker pageMaker = new PageMaker();
		pageMaker.setCri(scri);
		pageMaker.setTotalCount(service.listCount(scri));

		model.addAttribute("pageMaker", pageMaker);

		return "/itboard/list";

	}

	// 게시판 조회
	@RequestMapping(value = "/itboard/readView.do", method = RequestMethod.GET)
	public String read(ItBoardVO itboardVO, @RequestParam("bno") int bno, @ModelAttribute("scri") SearchCriteria scri, Model model) throws Exception {
		logger.info("read");
		
		service.boardCnt(bno); //조회수
		
		service.replyCount(bno);
		
		model.addAttribute("read", service.read(itboardVO.getBno()));
		model.addAttribute("scri", scri);

		List<ItReplyVO> replyList = replyService.readReply(itboardVO.getBno());
		model.addAttribute("replyList", replyList);
		
		List<Map<String, Object>> fileList = service.selectFileList(itboardVO.getBno());
		model.addAttribute("file", fileList);
		
		return "/itboard/readView";
	}
	//신고하기
	@RequestMapping(value = "/itboard/goReport.do", method = RequestMethod.GET)
	public String goReport(ItBoardVO itboardVO,UserVO vo,@ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("goReport");
		
		System.out.println(vo.getId());
		
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		service.goReport(vo);
		
		return "redirect:/itboard/list.do";

	}

	// 게시판 수정뷰
	@RequestMapping(value = "/itboard/updateView.do", method = RequestMethod.GET)
	public String updateView(ItBoardVO itboardVO, @ModelAttribute("scri") SearchCriteria scri, Model model)
			throws Exception {
		logger.info("updateView");

		model.addAttribute("update", service.read(itboardVO.getBno()));
		model.addAttribute("scri", scri);
		
		List<Map<String, Object>> fileList = service.selectFileList(itboardVO.getBno());
		model.addAttribute("file", fileList);
		
		return "/itboard/updateView";
	}

	// 게시판 수정
	@RequestMapping(value = "/itboard/update.do", method = RequestMethod.POST)
	public String update(ItBoardVO itboardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr,
						@RequestParam(value="fileNoDel[]") String[] files, @RequestParam(value="fileNameDel[]") String[] fileNames,
						MultipartHttpServletRequest mpRequest) throws Exception {
		logger.info("update");

		service.update(itboardVO, files, fileNames, mpRequest);

		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/itboard/list.do";
	}

	// 게시판 삭제
	@RequestMapping(value = "/itboard/delete.do", method = RequestMethod.POST)
	public String delete(ItBoardVO itboardVO, @ModelAttribute("scri") SearchCriteria scri, RedirectAttributes rttr)
			throws Exception {
		logger.info("delete");

		service.delete(itboardVO.getBno());

		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/itboard/list.do";
	}
	
	@RequestMapping(value="/itboard/readView.do", method = RequestMethod.POST)
	public String recommend(@RequestParam("bno") int bno, ItBoardVO itboardVO, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		
		service.recommend(bno);
		
		rttr.addAttribute("bno", itboardVO.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());
		
		return "redirect:/itboard/readView.do";
	}
	
	// 댓글 작성
	@RequestMapping(value = "/itboard/replyWrite.do", method = RequestMethod.POST)
	public String replyWrite(ItReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");

		replyService.writeReply(vo);

		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/itboard/readView.do";
	}

	// 댓글 수정 GET
	@RequestMapping(value = "/itboard/replyUpdateView.do", method = RequestMethod.GET)
	public String replyUpdateView(ItReplyVO vo, SearchCriteria scri, Model model) throws Exception {
		logger.info("reply Write");

		model.addAttribute("replyUpdate", replyService.selectReply(vo.getRno()));
		model.addAttribute("scri", scri);

		return "/itboard/replyUpdateView";
	}

	// 댓글 수정 POST
	@RequestMapping(value = "/itboard/replyUpdate.do", method = RequestMethod.POST)
	public String replyUpdate(ItReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");

		replyService.updateReply(vo);

		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/itboard/readView.do";
	}

	// 댓글 삭제 GET
	@RequestMapping(value = "/itboard/replyDeleteView.do", method = RequestMethod.GET)
	public String replyDeleteView(ItReplyVO vo, SearchCriteria scri, Model model) throws Exception {
		logger.info("reply Write");

		model.addAttribute("replyDelete", replyService.selectReply(vo.getRno()));
		model.addAttribute("scri", scri);

		return "/itboard/replyDeleteView";
	}

	// 댓글 삭제
	@RequestMapping(value = "/itboard/replyDelete.do", method = RequestMethod.POST)
	public String replyDelete(ItReplyVO vo, SearchCriteria scri, RedirectAttributes rttr) throws Exception {
		logger.info("reply Write");

		replyService.deleteReply(vo);

		rttr.addAttribute("bno", vo.getBno());
		rttr.addAttribute("page", scri.getPage());
		rttr.addAttribute("perPageNum", scri.getPerPageNum());
		rttr.addAttribute("searchType", scri.getSearchType());
		rttr.addAttribute("keyword", scri.getKeyword());

		return "redirect:/itboard/readView.do";
	}
	
	//첨부파일 다운받기
	@RequestMapping(value="/itboard/fileDown.do")
	public void fileDown(@RequestParam Map<String, Object> map, HttpServletResponse response) throws Exception{
		Map<String, Object> resultMap = service.selectFileInfo(map);
		String storedFileName = (String) resultMap.get("STORED_FILE_NAME");
		String originalFileName = (String) resultMap.get("ORG_FILE_NAME");
		
		// 파일을 저장했던 위치에서 첨부파일을 읽어 byte[]형식으로 변환한다.
		byte fileByte[] = FileUtils.readFileToByteArray(new File("C:\\upload\\"+storedFileName));
		
		response.setContentType("application/octet-stream");
		response.setContentLength(fileByte.length);
		response.setHeader("Content-Disposition",  "attachment; fileName=\""+ URLEncoder.encode(originalFileName, "UTF-8")+"\";");
		response.getOutputStream().write(fileByte);
		response.getOutputStream().flush();
		response.getOutputStream().close();
		
	}
}
