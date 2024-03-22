package com.sist.dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MainClass {

	public static void main(String[] args) {		

		String fileName = "c:\\database\\travel.txt";
        String jdbcURL = "jdbc:oracle:thin:@localhost:1521:XE";
        String username = "hr";
        String password = "happy";

        try (Connection conn = DriverManager.getConnection(jdbcURL, username, password);
             BufferedReader br = new BufferedReader(new FileReader(fileName))) {

            String line;
            while ((line = br.readLine()) != null) {
                // "|"를 기준으로 필드를 추출
                String[] fields = line.split("\\|");

                // 필드 추출 후 데이터베이스에 저장
                saveToDatabase(fields, conn);
            }
            System.out.println("데이터베이스에 저장 완료!");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void saveToDatabase(String[] fields, Connection conn) throws SQLException {
        String sql = "INSERT INTO GNtravel VALUES("
                	+"?,?,?,?,?,?,?,?,?,?,?) "; // 적절한 테이블 이름과 컬럼을 사용하세요

        try (PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fields[0]); // 첫 번째 필드
            pstmt.setString(2, fields[1]); // 두 번째 필드
            pstmt.setString(3, fields[2]); // 세 번째 필드
            pstmt.setString(4, fields[3]);
            pstmt.setString(5, fields[4]);
            pstmt.setString(6, fields[5]);
            pstmt.setString(7, fields[6]);
            pstmt.setString(8, fields[7]);
            pstmt.setString(9, fields[8]);
            pstmt.setString(10, fields[9]);
            pstmt.setString(11, fields[10]);
            pstmt.setString(12, fields[11]);
            
            pstmt.executeUpdate();
        }
        catch(Exception ex) {ex.printStackTrace();}
       
//		String filePath = "c:\\database\\travel.txt";
//        List<MainVO> shows = new ArrayList<>();
//
//        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
//
//            String line;
//            while ((line = br.readLine()) != null) {
//                String[] fields = line.split("\\|");
//                if (fields.length >= 10) {
//                    MainVO show = new MainVO();
//                    show.setType(Integer.parseInt(fields[0]));
//                    show.setGenre(fields[1]);
//                    show.setNo(Integer.parseInt(fields[2]));
//                    show.setTitle(fields[3]);
//                    show.setPoster1(fields[4]);
//                    show.setPoster2(fields[5]);
//                    show.setPoster3(fields[6]);
//                    show.setAddress(fields[7]);
//                    show.setManage(fields[8]);
//                    show.setPhone(fields[9]);
//                    shows.add(show);
//                } else {
//                    System.err.println("토큰 수가 부족합니다: " + line);
//                }
//            }

		    	  
//		    	  MainVO vo=new MainVO();
//	              vo.setType(shows[0].getType());
//	              vo.setGenre(shows[1].getGenre());
//	              vo.setNo(shows[2].getNo());
//	              vo.setTitle(shows[3].getTitle());
//	              vo.setPoster1(shows[4].getPoster1());
//	              vo.setPoster2(shows[5].getPoster2());
//	              vo.setPoster3(shows[6].getPoster3());
//	              vo.setAddress(shows[7].getAddress());
//	              vo.setManage(shows[8].getManage());
//	              vo.setPhone(shows[9].getPhone());
//	              vo.setContent(shows[10].getContent());
//	              
//		    	  ShowDAO dao=new ShowData();
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

	}
}