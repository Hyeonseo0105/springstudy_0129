package com.sist.dao;

import com.sist.mapper.*;
import com.sist.vo.*;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

// 스프링에 의해 메모리 할당 => 필요시마다 사용이 가능 => 클래스 관리자
// 클래스 : 컴포넌트 , => 여러개 묶어서 관리 (컨테이너) ==> 컨테이너
// Vue.createAp : 컨테이너 , components
/*
 *    컴퓨터
 *      CPU / 하드디스크 ... 메모리 => 컴포넌트
 *      -----------------------
 *        조립 : 메인보드 (스프링)
 */
@Repository
public class FoodDAO {

	@Autowired        // new FoodMapper()
	private FoodMapper mapper;
	
	public List<FoodVO> foodListData(int start, int end)
	{
		return mapper.foodListData(start, end);
	}
	
	public int foodTotalPage()
	{
		return mapper.foodTotalPage();
	}
}
