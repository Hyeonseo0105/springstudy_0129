package com.sist.web;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.service.*;
import com.sist.vo.*;

@RestController
public class ReserveRestController {

	@Autowired
	private ReserveService rService;
	
	@GetMapping(value="show/reserve_vue.do",produces = "text/plain;charset=UTF-8")
	   public String reserve_vue(int sno) throws Exception
	   {
		   ShowVO vo=rService.reserveDetailData(sno);
		   ObjectMapper mapper=new ObjectMapper();
		   String json=mapper.writeValueAsString(vo);
		   return json;
	   }
}
