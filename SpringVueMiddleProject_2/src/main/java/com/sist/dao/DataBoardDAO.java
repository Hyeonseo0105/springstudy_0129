package com.sist.dao;

import java.util.*;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.sist.mapper.*;
import com.sist.vo.*;

/*
 *    선택적 메모리 할당
 *    ------------------------ 기능별로 분리
 *    => @Component : AOP,MainClass,Open Api => 일반 클래스
 *    => @Repository : DAO (저장소 데이터베이스 연결)
 *    => @Service : BI => DAO여러개 통합 (기능 통합)
 *                  BoardDAO / ReplyDAO => 기능별로 클래스 만든다 (재사용)
 *    => @Controller : Model (요청 처리 => 응답) => 페이지 설정
 *         => @ResponseBody
 *    => @RestController : Model (요청 처리 => 응답) => 실제 처리된 데이터만 전송
 *        ==== 다른 프로그램과 연동 (자바스크립트) => JSON
 *        Front / Back
 *    => @ControllerAdvice : Controller에서 오류발생시 예외처리 (공통)
 *    => @RestControllerAdvice : RestController에서 오류발생시 예외처리 (공통)
 *    => @Configuration : XML에 대신 클래스설정을 자바로 변경
 */
@Repository
public class DataBoardDAO {

	@Autowired
	private DataBoardMapper mapper;
	
	public List<DataBoardVO> databoardListData(int start,int end)
	{
		return mapper.databoardListData(start, end);
	}
	
	public int databoardTotalPage()
	{
		return mapper.databoardTotalPage();
	}
	
	public void databoardInsert(DataBoardVO vo)
	{
		mapper.databoardInsert(vo);
	}
	
	public DataBoardVO databoardDetailData(int no)
	{
		mapper.hitIncrement(no);
		return mapper.databoardDetailData(no);
	}
}
