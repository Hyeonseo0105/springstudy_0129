package com.sist.web;

import java.security.Principal;
import java.util.*;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.sist.service.*;
import com.sist.vo.*;

@Controller
public class MainController {

	@Autowired
	private ShowService sService;
	
	@Autowired
	private MemberService mService;
	
	@GetMapping("main/main.do")
    public String main_main(Model model,Principal p,HttpSession session)
    {
		if(p!=null)  // 에러방지
		{
			  MemberVO vo=mService.memberSessionInfoData(p.getName());
			  session.setAttribute("userId", vo.getUserId());
			  session.setAttribute("userName", vo.getUserName());
			  session.setAttribute("sex", vo.getSex());
			  session.setAttribute("address", vo.getAddr1()+" "+vo.getAddr2());
			  session.setAttribute("phone", vo.getPhone());
			  session.setAttribute("email", vo.getEmail());
		}
		
		List<ShowVO> Topshow=sService.Topshow();
		model.addAttribute("Topshow",Topshow);
	    return "main";
    }
}
