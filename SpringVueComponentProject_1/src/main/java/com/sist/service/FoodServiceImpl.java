package com.sist.service;

import com.sist.dao.*;
import com.sist.vo.FoodVO;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FoodServiceImpl implements FoodService {

	@Autowired      // 메모리 할당된 주소를 달라
	private FoodDAO dao;

	@Override
	public List<FoodVO> foodListData(int start, int end) {
		// TODO Auto-generated method stub
		return dao.foodListData(start, end);
	}

	@Override
	public int foodTotalPage() {
		// TODO Auto-generated method stub
		return dao.foodTotalPage();
	}
}
