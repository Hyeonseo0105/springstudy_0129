package com.sist.dao;

import java.util.*;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.vo.*;
import com.sist.mapper.*;

// => 구현된 메퍼를 사용하는 클래스 => 유지보수를 할 경우에 => 다른 클래스에 영향
@Repository
public class FoodDAO {

	@Autowired
	private FoodMapper mapper;
	
	public List<FoodVO> foodListData(int start, int end)
	{
		return mapper.foodListData(start, end);
	}
	
	public int foodTotalPage()
	{
		return mapper.foodTotalPage();
	}

	public FoodVO foodDetailData(int fno)
	{
		return mapper.foodDetailData(fno);
	}
}
