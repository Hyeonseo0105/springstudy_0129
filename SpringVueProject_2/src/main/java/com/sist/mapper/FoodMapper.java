package com.sist.mapper;

import java.util.*;

import org.apache.ibatis.annotations.Select;

import com.sist.dao.FoodVO;

public interface FoodMapper {
	
	@Select("SELECT fno,name,poster "
			+"FROM food_menu_house "
			+"WHERE fno BETWEEN 108 AND 127 "
			+"ORDER BY fno")
	public List<FoodVO> foodDataList();
	
	@Select("SELECT fno,name,poster,type,address,phone,theme,price,time,seat,score "
			+"FROM food_menu_house "
			+"WHERE fno=#{fno}")
	public FoodVO foodDetailData(int fno);
}
