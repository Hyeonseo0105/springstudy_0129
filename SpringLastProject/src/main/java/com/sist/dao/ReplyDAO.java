package com.sist.dao;
import java.util.*;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import com.sist.mapper.*;
@Repository
public class ReplyDAO {
   @Autowired
   private ReplyMapper mapper;
   
// 목록 
  /*@Select("SELECT no,rno,userId,userName,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday "
		 +"FROM projectRecipeReply "
		 +"WHERE rno=#{rno} "
		 +"ORDER BY no DESC")*/
  public List<ReplyVO> replyListData(int rno)
  {
	  return mapper.replyListData(rno);
  }
  // 추가 
  /*@Insert("INSERT INTO projectRecipeReply(no,rno,userId,userName,msg) "
		 +"VALUES(prr_no_seq.nextval,#{rno},#{userId},#{userName},#{msg})")*/
  public void replyInsert(ReplyVO vo)
  {
	  mapper.replyInsert(vo);
  }
  // 수정 
  /*@Update("UPDATE projectRecipeReply SET "
		 +"msg=#{msg} "
		 +"WHERE no=#{no}")*/
  public void replyUpdate(ReplyVO vo)
  {
	  mapper.replyUpdate(vo);
  }
  // 삭제 
  /*@Delete("DELETE FROM projectRecipeReply "
		 +"WHERE no=#{no}")*/
  public void replyDelete(int no)
  {
	  mapper.replyDelete(no);
  }
}