package com.sist.service;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sist.dao.*;
import com.sist.vo.*;

@Service
public class FreeBoardServiceImpl implements FreeBoardService{

	@Autowired
	private FreeBoardDAO fDAO;
	
	@Override
	public List<FreeBoardVO> freeboardListData(int start, int end) {
		// TODO Auto-generated method stub
		return fDAO.freeboardListData(start, end);
	}

	@Override
	public int freeboardTotalPage() {
		// TODO Auto-generated method stub
		return fDAO.freeboardTotalPage();
	}

	@Override
	public void freeboardInsert(FreeBoardVO vo) {
		// TODO Auto-generated method stub
		fDAO.freeboardInsert(vo);
	}

	@Override
	public FreeBoardVO freeboardDetailData(int no) {
		// TODO Auto-generated method stub
		return fDAO.freeboardDetailData(no);
	}

	@Override
	public String freeboardDelete(int no, String pwd) {
		// TODO Auto-generated method stub
		return fDAO.freeboardDelete(no, pwd);
	}

	@Override
	public FreeBoardVO freeboardUpdateData(int no) {
		// TODO Auto-generated method stub
		return fDAO.freeboardUpdateData(no);
	}

	@Override
	public String freeboard_update(FreeBoardVO vo) {
		// TODO Auto-generated method stub
		return fDAO.freeboard_update(vo);
	}

}
