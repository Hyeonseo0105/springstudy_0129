package com.sist.dao;

import java.io.FileReader;
import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.sist.vo.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import com.sist.mapper.*;

@Repository
public class ShowDAO {

	@Autowired
	private ShowMapper mapper;
	
	@Autowired
	private reviewMapper rMapper;

	// 메인 top1
	public List<ShowVO> Topshow()
	{
		return mapper.Topshow();
	}
	
	// 공연 메인 페이지
	// 콘서트 리스트
	public List<ShowVO> showconListData()
	{
		return mapper.showconListData();
	}
	public List<ShowVO> showmuListData()
	{
		return mapper.showmuListData();
	}
	public List<ShowVO> showclListData()
	{
		return mapper.showclListData();
	}
	
	// 콘서트 페이지
	public List<ShowVO> conListData(int start,int end,int scate)
	{
		return mapper.conListData(start,end,scate);
	}
	
	public int conTotalPage(int scate)
	{
		return mapper.conTotalPage(scate);
	}
	
	// 상세페이지
	public ShowVO showDetailData(int sno)
	{
		mapper.showHitIncrement(sno);
		return mapper.showDetailData(sno);
	}
	
	// 후기
	public List<reviewVO> replyListData(int sno)
	{
		return rMapper.replyListData(sno);
	}
	
	public int countshowreview(int sno)
	{
		return rMapper.countshowreview(sno);
	}
	
	public void showreplyInsert(reviewVO vo)
	{
		rMapper.showreplyInsert(vo);
	}
}
//	public static void main(String[] args) {		
//
//		String fileName = "c:\\show\\show.txt";
//        String jdbcURL = "jdbc:oracle:thin:@localhost:1521:XE";
//        String username = "hr";
//        String password = "happy";
//
//        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
//             BufferedReader br = new BufferedReader(new FileReader(fileName))) {
//
//            String line;
//            while ((line = br.readLine()) != null) {
//                // "|"를 기준으로 필드를 추출
//                String[] fields = line.split("\\|");
//
//                // 필드 추출 후 데이터베이스에 저장
//                saveToDatabase(fields, conn);
//            }
//            System.out.println("데이터베이스에 저장 완료!");
//
//        } catch (IOException | SQLException e) {
//            e.printStackTrace();
//        }
//    }
//
//    private static void saveToDatabase(String[] fields, Connection conn) throws SQLException {
//        String sql = "INSERT INTO showInfo VALUES("
//                	+"si_sno_seq.nextval,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?) "; // 적절한 테이블 이름과 컬럼을 사용하세요
//
//        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
//            pstmt.setString(1, fields[0]); // 첫 번째 필드
//            pstmt.setString(2, fields[1]); // 두 번째 필드
//            pstmt.setString(3, fields[2]); // 세 번째 필드
//            pstmt.setString(4, fields[3]);
//            pstmt.setString(5, fields[4]);
//            pstmt.setString(6, fields[5]);
//            pstmt.setString(7, fields[6]);
//            pstmt.setString(8, fields[7]);
//            pstmt.setString(9, fields[8]);
//            pstmt.setString(10, fields[9]);
//            pstmt.setString(11, fields[10]);
//            pstmt.setString(12, fields[11]);
//            pstmt.setString(13, fields[12]);
//            pstmt.setString(14, fields[13]);
//            pstmt.setString(15, fields[14]);
//            pstmt.setString(16, fields[15]);
//            
//
//            pstmt.executeUpdate();
//        }
//        catch(Exception ex) {ex.printStackTrace();}
//       
//		ShowVO[] shows=new ShowVO[500];
//		
//		FileReader fr=null;
//		{
//		  try
//		  {
//			  fr=new FileReader("c:\\show\\show.txt");//FileNotFoundException
//		      int i=0;
//		      String show_data="";
//		      while((i=fr.read())!=-1) // -1 EOF
//		      {
//		    	  show_data+=(char)i;
//		      }
//		      
//		      String[] sds=show_data.split("\r\n");
//		      i=0;
//		      for (String s : sds) {
//		    	    String[] fields = s.split("\\|");
//		    	    // 필드 개수 확인
//		    	    if (fields.length >= 16) {
//		    	        // 필요한 작업 수행
//		    	        shows[i] = new ShowVO();
//		    	        shows[i].setScate(Integer.parseInt(fields[0]));
//		    	        shows[i].setSdetail(fields[1]);
//		    	        shows[i].setSposter(fields[2]);
//		    	        shows[i].setStitle(fields[3]);
//		    	        shows[i].setSdate(fields[4]);
//		    	        shows[i].setSloc(fields[5]);
//		    	        shows[i].setSgrade(fields[6]);
//		    	        shows[i].setShour(fields[7]);
//		    	        shows[i].setSperformer(fields[8]);
//		    	        shows[i].setSseat(fields[9]);
//		    	        shows[i].setStime(fields[10]);
//		    	        shows[i].setSdelivery(fields[11]);
//		    	        shows[i].setSdeposter(fields[12]);
//		    	        shows[i].setSdeloc(fields[13]);
//		    	        shows[i].setSaddress(fields[14]);
//		    	        shows[i].setSphone(fields[15]);
//
//		    	        //System.out.println(shows[i]);
//		    	        i++;
//		    	    } else {
//		    	        // 필드 개수가 부족한 경우 처리
//		    	        System.err.println("토큰 수가 부족합니다: " + s);
//		    	    }
//		    	}
//		    	  
//		    	  ShowVO vo=new ShowVO();
//	              vo.setScate(shows[0].getScate());
//	              vo.setSdetail(shows[1].getSdetail());
//	              vo.setSposter(shows[2].getSposter());
//	              vo.setStitle(shows[3].getStitle());
//	              vo.setSdate(shows[4].getSdate());
//	              vo.setSloc(shows[5].getSloc());
//	              vo.setSgrade(shows[6].getSgrade());
//	              vo.setShour(shows[7].getShour());
//	              vo.setSperformer(shows[8].getSperformer());
//	              vo.setSseat(shows[9].getSseat());
//	              vo.setStime(shows[10].getStime());
//	              vo.setSdelivery(shows[11].getSdelivery());
//	              vo.setSdeposter(shows[12].getSdeposter());
//	              vo.setSdeloc(shows[13].getSdeloc());
//	              vo.setSaddress(shows[14].getSaddress());
//	              vo.setSphone(shows[15].getSphone());
//	              
//		    	  ShowData dao=new ShowData();
//		    	  dao.showInsert(vo);
//	              System.out.println("save end");
//		  }catch(Exception ex) 
//		  {
//			  // 에러 확인 
//			  ex.printStackTrace();
//		  }
//		  finally
//		  {
//			  // 파일 닫기 
//			  try
//			  {
//				  fr.close();
//			  }catch(Exception ex) {}
//		  }
//		}
//	}
