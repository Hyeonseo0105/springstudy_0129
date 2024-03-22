package com.sist.dao;

import java.util.*;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.sist.mapper.*;

@Repository
public class FoodDAO {
	
	@Autowired
	private FoodMapper mapper;   // 메모리 할당 완료
	
	public List<FoodVO> foodListData(Map map)
	{
		return mapper.foodListData(map);
	}
	
	public int foodTotalPage()
	{
		return mapper.foodTotalPage();
	}
}
