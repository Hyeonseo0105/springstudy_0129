package com.sist.dao;
import java.sql.*;

public class MainDAO {
	private Connection conn;
    private PreparedStatement ps;
    private final String URL="jdbc:mysql://localhost:3306/mydb?autoReconnection=true";
	   
	   public MainDAO()
	   {
		   try
	    	{
	    		Class.forName("com.mysql.cj.jdbc.Driver");
	    	}catch(Exception ex) {}
	    }
	    
	    public void getConnection()
	    {
	    	try
	    	{
	    		conn=DriverManager.getConnection(URL,"root","root");
	    	}catch(Exception ex) {}
	    }
	    
	    public void disConnection()
	    {
	    	try
	    	{
	    		if(ps!=null) ps.close();
	    		if(conn!=null) conn.close();
	    	}catch(Exception ex) {}
	    }

	    public void musicInsert(MainVO vo)
	    {
	    	try
	    	{
	    	    getConnection();
	    	    String sql="INSERT INTO book(name,poster,author,publ,regdate,score,price,saleprice,detailposter,intro,type) "
	    	    		  +"VALUES(?,?,?,?,?,?,?,?,?,?,?)";
	    	    ps=conn.prepareStatement(sql);
	    	    ps.setString(1, vo.getName());
	    	    ps.setString(2, vo.getPoster());
	    	    ps.setString(3, vo.getAuthor());
	    	    ps.setString(4, vo.getPubl());
	    	    ps.setString(5, vo.getRegdate());
	    	    ps.setString(6, vo.getScore());
	    	    ps.setString(7, vo.getPrice());
	    	    ps.setString(8, vo.getSaleprice());
	    	    ps.setString(9, vo.getDetailposter());
	    	    ps.setString(10, vo.getIntro());
	    	    ps.setString(11, "종합");
	    	    ps.executeUpdate();
	    	}catch(Exception ex)
	    	{
	    		ex.printStackTrace();
	    	}
	    	finally
	    	{
	    		disConnection();
	    	}
	    }
}
