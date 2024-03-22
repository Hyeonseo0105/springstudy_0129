<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%--
      web.xml 
      application-security.xml 
      => @GetMapping("member/login_ok_vue.do") =>  제거 
      => 매개변수 => HttpSession session ==> Principal p 
      => header변경 (권한별 메뉴 출력) 
      => 댓글 변경 
      
      create table persistent_logins(
		   username VARCHAR2(64) not null,
		   series VARCHAR2(64) primary key,
		   token VARCHAR2(64) not null,
		   last_used TIMESTAMP not null
		);
 --%>
<!DOCTYPE html>

<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
location.href="main/main.do"
</script>
</head>
<body>
  
</body>
</html>