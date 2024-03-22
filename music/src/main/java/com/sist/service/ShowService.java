package com.sist.service;

import java.util.List;
import java.util.Map;

import com.sist.vo.*;

public interface ShowService {

	
	public List<ShowVO> Topshow();
	
	
	public List<ShowVO> showconListData();
	public List<ShowVO> showmuListData();
	public List<ShowVO> showclListData();
	
	public List<ShowVO> conListData(int start,int end,int scate);
	public int conTotalPage(int scate);
	public ShowVO showDetailData(int sno);
	
	public List<reviewVO> replyListData(int sno);
	public int countshowreview(int sno);
	public void showreplyInsert(reviewVO vo);
}
