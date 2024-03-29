package com.sist.service;

import java.util.*;
import com.sist.vo.ReplyVO;

public interface ReplyService {
	
	public List<ReplyVO> replyListData(int rno);
	public void replyInsert(ReplyVO vo);
	public void replyUpdate(ReplyVO vo);
	public void replyDelete(int no);
}