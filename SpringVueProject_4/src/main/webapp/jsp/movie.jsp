<%@page import="org.jsoup.Jsoup"%>
<%@page import="org.jsoup.nodes.Document"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" import="java.util.*,java.net.*"%>
<%
	String data="";
	try
	{
		String no=request.getParameter("no");
		String[] movies={"","searchMainDailyBoxOffice.do","searchMainRealTicket.do",
				         "searchMainDailySeatTicket.do"};
		String url="https://www.kobis.or.kr/kobis/business/main/";
		Document doc=Jsoup.connect(url+movies[Integer.parseInt(no)]).get();
		//System.out.println(doc.toString());
		data=doc.toString();
		data=data.substring(data.indexOf("["),data.lastIndexOf("]")+1);
	}catch(Exception ex){}
%>
<%=data %>
