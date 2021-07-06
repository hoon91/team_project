
package com.commu.user.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;



import com.commu.user.service.UserService;
import com.commu.user.vo.UserVO;

@Controller
public class UserController {
	@Autowired
	private UserService service;
	
	//로그인 get
	@RequestMapping(value = "/login.do", method = RequestMethod.GET)
	public String loginForm() {
		return "users/login";
	}
	
	// 로그인 post
	@RequestMapping(value = "/login.do", method = RequestMethod.POST)
	public String login(UserVO user, HttpSession session) {

		UserVO vo;
		System.out.println(user);
		try {
			if (service.getUserCntByPass(user) > 0) {
				vo = service.getLogin(user);
				if (vo != null) {
					if (vo.getId().equals("admin")) {
						session.setAttribute("admin", vo);
						return "redirect:admin.do";
					} else {
						session.setAttribute("user", vo);
						return "redirect:/";
					}
				} else {
					return "redirect:login.do";
				}
			} else
				return "redirect:login.do";
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	
	// 로그아웃 2021.06.09 수정 (로그아웃 시 홈으로 가게 변경)
	@RequestMapping(value = "logout.do", method = RequestMethod.GET)
	public String logout(HttpSession session) {
		session.invalidate();// 세션정보 삭제
		return "redirect:/"; 
	}
	
	//회원가입
	@RequestMapping(value = "membership.do", method = RequestMethod.GET)
	public String membership() {
		return "users/membership";
	}
			
	//회원 가입시 db추가 (06.04수정)
	@RequestMapping(value = "insertMember.do", method = RequestMethod.POST)
	public String insertMember(UserVO user, Model model) throws Exception {
		int result = service.getUserCnt(user);
		model.addAttribute("cnt", result);
		model.addAttribute("msg", "이미 사용중인 아이디입니다.");
		model.addAttribute("url", "membership.do");
			
		try {
			if(result == 1) {
				System.out.println(result);
				return "users/redirect";
			}else if(result == 0) {	
					service.insertUser(user);
			}
			// 요기에서~ 입력된 아이디가 존재한다면 -> 다시 회원가입 페이지로 돌아가기 
			// 존재하지 않는다면 -> register
		} catch (Exception e) {
			throw new RuntimeException();
		}
			System.out.println(result);
			return "redirect:login.do";
		}
			
	//아이디 유무 체크
		@RequestMapping(value="/idChk.do", method = RequestMethod.POST, produces= {MediaType.APPLICATION_JSON_VALUE})
		public @ResponseBody Map<String,Integer> idchk(@RequestBody String id) throws Exception {
			System.out.println("userVO-id: "+id);
	        int count = 0;
	        Map<String, Integer> map = new HashMap<String, Integer>();
	 
	        count = service.ajaxUserCnt(id);
	        System.out.println(count);
	        
	       map.put("cnt", count);
	        System.out.println(map.get("cnt"));
	     
	        return map;
		}
	
	//비밀번호 변경
	@RequestMapping(value = "changePass.do", method = RequestMethod.GET)
	public String changepassForm() {
		return "users/changePass";
	}
	
	//비밀번호 변경
	@RequestMapping(value = "updatePass.do", method = RequestMethod.POST)
	public String updatePass(UserVO user, Model model) throws Exception {
		service.updateUser(user);
		return "redirect:login.do";
	}
	
	@RequestMapping(value = "idPassCheck.do", method = RequestMethod.POST)
	public String idPassCheck(UserVO user) {
		if (service.getUserCntByPass(user) > 0) {
			return "redirect:changePassForm.do";
		} else {
			return "redirect:changePass.do";
		}
	} // idPassCheck()끝.
	
	
	//회원탈퇴
	@RequestMapping(value = "deleteUser.do", method = RequestMethod.GET)
	public String deleteUser() {
		return "users/deleteUser";
	}
	
	@RequestMapping(value = "deleteUser.do", method = RequestMethod.POST)
	public String deleteUser(UserVO user) throws Exception {
		if (service.getUserCntByPass(user) > 0) {
			service.deleteUser(user);
			return "redirect:login.do";
		} else {
			return "redirect:deleteUser.do";
		}
	}
	
	// 회원 페이지 (21.06.19 추가)
	@RequestMapping(value = "mypage.do", method = RequestMethod.GET)
	public String mypage() throws Exception {
		return "users/mypage";
	}
		
	// 회원정보 보기
	@RequestMapping(value="readInfo.do", method=RequestMethod.GET)
	public String readInfo(UserVO vo, Model model) throws Exception {	
		model.addAttribute("userInfo", service.readInfo(vo.getId()));
		return "users/readInfo";
	}
	
	// 회원정보 변경
	@RequestMapping(value = "updateInfo.do", method = RequestMethod.GET)
	public String getUpdateInfo(UserVO vo, Model model) throws Exception {
		model.addAttribute("userInfo", service.readInfo(vo.getId()));
		return "users/updateInfo";
	}
		
	// 회원정보 변경
	@RequestMapping(value = "updateInfo.do", method = RequestMethod.POST)
	public String postUpdateInfo(UserVO vo) throws Exception {
	    service.updateInfo(vo);
		return "redirect:/readInfo.do?id="+vo.getId();
	}
		
	
	/*--------------------- ADMIN ---------------------------------- */
	
	//관리자 모드
	@RequestMapping(value = "admin.do", method = RequestMethod.GET)
	public String admin() {
		return "admin/admin";
	}
	
	//신고 접수 회원 명단
	@RequestMapping(value = "userreport.do", method = RequestMethod.GET)
	public String getBoardList(UserVO user, Model model,HttpSession session)
			throws Exception {

		if(user.getPageNum()==null) user.setPageNum("1");
		
		int startRow= (Integer.parseInt(user.getPageNum()) -1 )*5 +1;
		int endRow = startRow + 4;
		user.setStartRow(startRow);
		user.setEndRow(endRow);

		List<UserVO> reportlist = service.getReportUser(user);

		
		int totalCount = service.countReportUser(user);
		int total = (int) Math.ceil(totalCount / 5.0);// 정수/실수=>실수 10.0->10, 10.3->11

		int lastPage = (int) Math.ceil(totalCount / (double) 5);// 554/10 =>55

		// if(totalCount%10==0) totalCount/10;// 50/10 =5;
		// else if(totalCount%10!=0) totalCount/10 +1;//52/10 =5*10+2;

		int endPage = ((int) Math.ceil(Integer.parseInt(user.getPageNum()) / (double) 10)) * 10;// 4/10=0, 23

		if (lastPage < endPage)
			endPage = lastPage;
		int startPage = (((int) ((Integer.parseInt(user.getPageNum()) - 1) / (double) 10) + 1) - 1) * 10 + 1;
		if (startPage < 1)
			startPage = 1;

		session.setAttribute("reportlist", reportlist);
		model.addAttribute("total", total);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("lastPage", lastPage);

		return "admin/userreport";
	}
	
	// 신고회원 제명
	@RequestMapping(value = "deleteReportUser.do", method = RequestMethod.GET)
	public String deleteReportUser(UserVO user, HttpServletRequest rs) throws Exception {
		System.out.println(rs.getAttribute("id"));
		System.out.println(user);
		service.deleteUser(user);
		return "redirect:userreport.do";
	}
	
	//신고해제(06.14)
	@RequestMapping(value = "returnReportUser.do", method = RequestMethod.GET)
	public String returnReportUser(UserVO user, HttpServletRequest rs) throws Exception {
		service.returnReport(user);
		return "redirect:userreport.do";
	}
	
	// 회원 등급
	@RequestMapping(value = "usergrade.do", method = RequestMethod.GET)
	public String getBoardList2(UserVO user, Model model) throws Exception {

		UserVO vo0 = new UserVO();
		vo0.setGrade("normal");
		UserVO vo1 = new UserVO();
		vo1.setGrade("silver");
		UserVO vo2 = new UserVO();
		vo2.setGrade("gold");
		UserVO vo3 = new UserVO();
		vo3.setGrade("VIP");

		List<UserVO> gradelist0 = service.getUserGrade(vo0);
		model.addAttribute("gradelist0", gradelist0);
		List<UserVO> gradelist1 = service.getUserGrade(vo1);
		model.addAttribute("gradelist1", gradelist1);
		List<UserVO> gradelist2 = service.getUserGrade(vo2);
		model.addAttribute("gradelist2", gradelist2);
		List<UserVO> gradelist3 = service.getUserGrade(vo3);
		model.addAttribute("gradelist3", gradelist3);

		return "admin/usergrade";
	}
	
	// 회원 목록
	@RequestMapping(value="/memberList.do")
	public String memberList(UserVO user, Model model, HttpSession session) {
		
		if (user.getPageNum() == null)
			user.setPageNum("1");

		int startRow = (Integer.parseInt(user.getPageNum()) - 1) * 5 + 1;
		int endRow = startRow + 4;
		user.setStartRow(startRow);
		user.setEndRow(endRow);
		
		List<UserVO> memberlist = service.getUsers(user);
		
		int totalCount = service.getUserCnt(user);

		int total = (int) Math.ceil(totalCount / 5.0);// 정수/실수=>실수 10.0->10, 10.3->11

		int lastPage = (int) Math.ceil(totalCount / (double) 5);// 554/10 =>55

		// if(totalCount%10==0) totalCount/10;// 50/10 =5;
		// else if(totalCount%10!=0) totalCount/10 +1;//52/10 =5*10+2;

		int endPage = ((int) Math.ceil(Integer.parseInt(user.getPageNum()) / (double) 10)) * 10;// 4/10=0, 23

		if (lastPage < endPage)
			endPage = lastPage;
		int startPage = (((int) ((Integer.parseInt(user.getPageNum()) - 1) / (double) 10) + 1) - 1) * 10 + 1;
		if (startPage < 1)
			startPage = 1;
		
		model.addAttribute("list", memberlist);
		model.addAttribute("total", total);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("lastPage", lastPage);
		
		return "admin/memberList";
	}

	// 등급별 리스트 이동(06.06수정)
	@RequestMapping(value = "selectGrade.do", method = RequestMethod.GET)
	public String selectGrade(UserVO user, Model model) {
		UserVO vip = new UserVO();
		vip.setGrade("VIP");
		UserVO gold = new UserVO();
		gold.setGrade("gold");
		UserVO silver = new UserVO();
		silver.setGrade("silver");
		UserVO normal = new UserVO();
		normal.setGrade("normal");

		model.addAttribute("vip", vip);
		model.addAttribute("gold", gold);
		model.addAttribute("silver", silver);
		model.addAttribute("normal", normal);

		return "admin/selectGrade";
	}
	
	//등급별 리스트 페이징처리(06.06수정)
	@RequestMapping(value = "userGradeList.do", method = RequestMethod.GET)
	public String userGradeList(UserVO user, Model model, HttpSession session) throws Exception {

		System.out.println(user.getGrade());

		if (user.getPageNum() == null)
			user.setPageNum("1");

		int startRow = (Integer.parseInt(user.getPageNum()) - 1) * 5 + 1;
		int endRow = startRow + 4;
		user.setStartRow(startRow);
		user.setEndRow(endRow);

		List<UserVO> gradelist = service.getUserGradeList(user);

		int totalCount = service.getGradeCnt(user);

		int total = (int) Math.ceil(totalCount / 5.0);// 정수/실수=>실수 10.0->10, 10.3->11

		int lastPage = (int) Math.ceil(totalCount / (double) 5);// 554/10 =>55

		// if(totalCount%10==0) totalCount/10;// 50/10 =5;
		// else if(totalCount%10!=0) totalCount/10 +1;//52/10 =5*10+2;

		int endPage = ((int) Math.ceil(Integer.parseInt(user.getPageNum()) / (double) 10)) * 10;// 4/10=0, 23

		if (lastPage < endPage)
			endPage = lastPage;
		int startPage = (((int) ((Integer.parseInt(user.getPageNum()) - 1) / (double) 10) + 1) - 1) * 10 + 1;
		if (startPage < 1)
			startPage = 1;
		
		session.setAttribute("gradelist", gradelist);
		model.addAttribute("grade",user.getGrade());
		model.addAttribute("total", total);
		model.addAttribute("startPage", startPage);
		model.addAttribute("endPage", endPage);
		model.addAttribute("lastPage", lastPage);
		return "admin/gradePage";
	}
	
	//VIP회원(상세)
	@RequestMapping(value = "updateVIP.do", method = RequestMethod.GET)
	public String updateVIP(UserVO user, Model model) throws Exception {
		user.setGrade("VIP");
		service.updateUserGrade(user);
		return "redirect:selectGrade.do";
	}
	
	//골드회원(상세)
		@RequestMapping(value = "updateGold.do", method = RequestMethod.GET)
		public String updateGold(UserVO user, Model model) throws Exception {
			user.setGrade("gold");
			service.updateUserGrade(user);
			return "redirect:selectGrade.do";
	}
	
	//실버회원(상세)
	@RequestMapping(value = "updateSilver.do", method = RequestMethod.GET)
	public String updateSilver(UserVO user, Model model) throws Exception {
		user.setGrade("silver");
		service.updateUserGrade(user);
		return "redirect:selectGrade.do";
	}
	
	//일반회원(상세)
	@RequestMapping(value = "updateNormal.do", method = RequestMethod.GET)
	public String updateNormal(UserVO user, Model model) throws Exception {
		user.setGrade("normal");
		service.updateUserGrade(user);
		return "redirect:selectGrade.do";
	}
	
	//VIP회원(한눈에)
	@RequestMapping(value = "updateVIP1.do", method = RequestMethod.GET)
	public String updateVIP1(UserVO user, Model model) throws Exception {
		user.setGrade("VIP");
		service.updateUserGrade(user);
		return "redirect:usergrade.do";
	}
	
	//골드회원(한눈에)
	@RequestMapping(value = "updateGold1.do", method = RequestMethod.GET)
	public String updateGold1(UserVO user, Model model) throws Exception {
		user.setGrade("gold");
		service.updateUserGrade(user);
		return "redirect:usergrade.do";
	}
	
	//실버회원(한눈에)
	@RequestMapping(value = "updateSilver1.do", method = RequestMethod.GET)
	public String updateSilver1(UserVO user, Model model) throws Exception {
		user.setGrade("silver");
		service.updateUserGrade(user);
		return "redirect:usergrade.do";
	}
	
	//일반회원(한눈에)
	@RequestMapping(value = "updateNormal1.do", method = RequestMethod.GET)
	public String updateNormal1(UserVO user, Model model) throws Exception {
		user.setGrade("normal");
		service.updateUserGrade(user);
		return "redirect:usergrade.do";
	}

}
