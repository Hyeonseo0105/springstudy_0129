package com.sist.web;

import java.util.*;
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
	
	@GetMapping("main/main.do")
    public String main_main(Model model)
    {
		List<ShowVO> Topshow=sService.Topshow();
		model.addAttribute("Topshow",Topshow);
	    return "main";
    }
}
