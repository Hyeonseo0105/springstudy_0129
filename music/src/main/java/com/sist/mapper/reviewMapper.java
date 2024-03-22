package com.sist.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Select;

import com.sist.vo.reviewVO;

public interface reviewMapper {

	// 목록 
	  @Select("SELECT no,typeno,fno,userId,userName,msg,TO_CHAR(regdate,'YYYY-MM-DD HH24:MI:SS') as dbday "
			 +"FROM all_reply "
			 +"WHERE fno=#{fno} "
			 +"ORDER BY no DESC")
	  public List<reviewVO> replyListData(int sno);
	  
	  @Select("SELECT COUNT(*) FROM all_reply "
			  +"WHERE typeno=5 AND fno=#{fno}")
	  public int countshowreview(int sno);
	  
	  // 추가 
	  @Insert("INSERT INTO all_reply(no,typeno,fno,userId,userName,msg) "
			 +"VALUES(prr_no_seq.nextval,#{typeno},#{fno},#{userId},#{userName},#{msg})")
	  public void showreplyInsert(reviewVO vo);
	  // 수정 
//	  @Update("UPDATE projectRecipeReply SET "
//			 +"msg=#{msg} "
//			 +"WHERE no=#{no}")
//	  public void replyUpdate(ReplyVO vo);
//	  // 삭제 
//	  @Delete("DELETE FROM projectRecipeReply "
//			 +"WHERE no=#{no}")
//	  public void replyDelete(int no);
}
