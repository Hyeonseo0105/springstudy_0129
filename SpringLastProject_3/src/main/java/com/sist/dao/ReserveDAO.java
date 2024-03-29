package com.sist.dao;

import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.mapper.*;
import com.sist.vo.*;

@Repository
public class ReserveDAO {

	@Autowired
	private ReserveMapper mapper;
	
	public List<FoodVO> foodReserveData(String type)
	{
		return mapper.foodReserveData(type);
	}
	
	public void foodReserveInsert(ReserveVO vo)
	{
		mapper.foodReserveInsert(vo);
	}
	
	public List<ReserveVO> reserveMypageData(String userId)
	{
		return mapper.reserveMypageData(userId);
	}
	
	public void reserveCancel(int rno)
	{
		mapper.reserveCancel(rno);
	}
	
	public List<ReserveVO> reserveAdminpageData()
	{
		return mapper.reserveAdminpageData();
	}
	
	public void reserveOK(int rno)
	{
		mapper.reserveOK(rno);
	}
	
	public ReserveVO reserveInfoData(int rno)
	{
		return mapper.reserveInfoData(rno);
	}
}
