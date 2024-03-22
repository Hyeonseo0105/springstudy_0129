package com.sist.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.multipart;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.net.URLEncoder;
import java.util.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.sist.dao.*;
import com.sist.vo.*;

@RestController
public class DataBoardRestController {
	@Autowired
	private DataBoardDAO dao;
	
	@GetMapping(value = "databoard/list_vue.do",produces = "text/plain;charset=UTF-8")
	public String databoard_list(int page) throws Exception
	{
		int rowSize=10;
		int start=(rowSize*page)-(rowSize-1);
		int end=rowSize*page;
		List<DataBoardVO> list=dao.databoardListData(start, end);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(list);
		return json;
	}
	
	@GetMapping(value = "databoard/page_vue.do",produces = "text/plain;charset=UTF-8")
	public String databoard_page(int page) throws Exception
	{
		int totalpage=dao.databoardTotalPage();
		Map map=new HashMap();
		map.put("curpage", page);
		map.put("totalpage",totalpage);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(map);  //{map:1,map:10}
		return json;
	}
	
	@PostMapping(value = "databoard/insert_vue.do",produces = "text/plain;charset=UTF-8")
	public String databoard_insert(DataBoardVO vo,HttpServletRequest request)
	{
		String result="";
		try
		{
			String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
			path=path.replace("\\", File.separator);
			File dir=new File(path);
			if(!dir.exists())
			{
				dir.mkdir();
			}
			
			List<MultipartFile> list=vo.getFiles();   // 파일 임시저장
			if(list==null)    // 업로드가 없는 상태
			{
				vo.setFilename("");
				vo.setFilesize("");
				vo.setFilecount(0);
			}
			else    // 업로드가 있는 상태
			{
				String filename="";
				String filesize="";
				for(MultipartFile mf:list)
				{
					String name=mf.getOriginalFilename();
					File file=new File(path+name);
					mf.transferTo(file);    // 파일하나하나 업로드
					
					filename+=name+",";   // a.jpg , b.jpg...
					filesize+=file.length()+",";
				}
				filename=filename.substring(0,filename.lastIndexOf(","));
				filesize=filesize.substring(0,filesize.lastIndexOf(","));
				vo.setFilename(filename);
				vo.setFilesize(filesize);
				vo.setFilecount(list.size());
			}
			dao.databoardInsert(vo);
			result="yes";
		}
		catch(Exception ex)
		{
			result=ex.getMessage();
		}
		return result;
	}
	
	@GetMapping(value = "databoard/detail_vue.do",produces = "text/plain;charset=UTF-8")
	public String databoard_detail(int no) throws Exception
	{
		/*
		 *    no,name,su
		 *    
		 *    response.data={"no":1,name:"",subject:"",...filename:""}
		 *    a={"sabun":1,name:"홍길동"};
		 *    a.sabun a.name
		 */
		DataBoardVO vo=dao.databoardDetailData(no);
		ObjectMapper mapper=new ObjectMapper();
		String json=mapper.writeValueAsString(vo);
		return json;
	}
	
	@GetMapping(value = "databoard/download.do")
	public void databoard_download(String fn,HttpServletRequest request,HttpServletResponse response)
	{
		String path=request.getSession().getServletContext().getRealPath("/")+"upload\\";
		path=path.replace("\\", File.separator);
		
		try
		{
			File file=new File(path+fn);
			response.setHeader("Content-Disposition", "attachment;filename="+URLEncoder.encode(fn,"UTF-8"));
			response.setContentLength((int)file.length());
			// => 다운로드 창을 보여준다
			BufferedInputStream bis=new BufferedInputStream(new FileInputStream(file));
			
			// 서버에서 파일을 읽어온다                             다운로드폴더에 복사하겠다
			BufferedOutputStream bos=new BufferedOutputStream(response.getOutputStream());
			
			// 클라이언트 복사 영역
			int i=0;
			byte[] buffer=new byte[1024];
			while((i=bis.read(buffer, 0 , 1024))!=-1)
			{
				// i => 읽은 바이트수
				// -1 EOF
				bos.write(buffer,0,i);
			}
			bis.close();
			bos.close();
		}
		catch(Exception ex) {}
	}
}
