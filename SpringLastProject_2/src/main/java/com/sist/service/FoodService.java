package com.sist.service;

import java.util.*;
import com.sist.vo.*;

public interface FoodService {

	public List<FoodVO> foodFindData(Map map);	
	public int foodFindCount(Map map);
	public FoodVO foodDetailData(int fno);
	public List<FoodVO> foodListData(Map map);
	public int foodListCount();
	public FoodVO foodListDetailData(int fno);
	public List<FoodVO> foodHome12();
	public List<String> foodAllData();
	public List<FoodVO> foodNameInfoData(String name);
	public List<RecipeVO> foodRecipeData(String title);
	
	// 풋터 인기맛집
	public List<FoodVO> foodTop7();
	
	// 풋터 공지사항
	public List<NoticeVO> noticeTop7();
	
	// recipe
}
