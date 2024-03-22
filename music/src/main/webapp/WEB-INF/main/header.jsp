<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="http://code.jquery.com/jquery.js"></script>
<script type="text/javascript">
$(function(){
	$('#showmain').click(function(){
		window.location.href = "../show/main.do";
	})
})
</script>
<style type="text/css">
#showmain{
	cursor:pointer;
}
</style>
</head>
<body>
<div class="container-fluid nav-bar bg-transparent">
            <nav class="navbar navbar-expand-lg bg-white navbar-light py-0 px-4">
                <a href="../main/main.do" class="navbar-brand d-flex align-items-center text-center">
                    <div class="icon p-2 me-2">
                        <img class="img-fluid" src="../img/icon-deal.png" alt="Icon" style="width: 30px; height: 30px;">
                    </div>
                    <h1 class="m-0 text-primary">Makaan</h1>
                </a>
                <button type="button" class="navbar-toggler" data-bs-toggle="collapse" data-bs-target="#navbarCollapse">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarCollapse">
                    <div class="navbar-nav ms-auto">
                        <a href="../main/main.do" class="nav-item nav-link active">Home</a>
                        <a href="../etc/about.html" class="nav-item nav-link">About</a>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Property</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="../etc/property-list.html" class="dropdown-item">Property List</a>
                                <a href="../etc/property-type.html" class="dropdown-item">Property Type</a>
                                <a href="../etc/property-agent.html" class="dropdown-item">Property Agent</a>
                            </div>
                        </div>
                        <div class="nav-item dropdown">
                            <a href="#" class="nav-link dropdown-toggle" data-bs-toggle="dropdown">Pages</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="../etc/testimonial.html" class="dropdown-item">Testimonial</a>
                                <a href="../etc/404.html" class="dropdown-item">404 Error</a>
                            </div>
                        </div>
                        <div class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" data-bs-toggle="dropdown" id="showmain">공연</a>
                            <div class="dropdown-menu rounded-0 m-0">
                                <a href="../show/list.do" class="dropdown-item">콘서트</a>
                                <a href="../show/musical.do" class="dropdown-item">뮤지컬</a>
                                <a href="../show/classic.do" class="dropdown-item">클래식</a>
                            </div>
                        </div>
                        <c:if test="${sessionScope.userId!=null }">
    <div class="fl_right">
      <ul class="inline">
        <li><i class="fa fa-phone"></i> ${sessionScope.userName }(<sec:authorize access="hasRole('ROLE_ADMIN')">관리자</sec:authorize>
         <sec:authorize access="hasRole('ROLE_USER')">일반사용자</sec:authorize>
        )</li>
        <li>님 로그인되었습니다</li>
      </ul>
    </div>
    </c:if>
    <sec:authorize access="hasRole('ROLE_USER')"> 
        <li><a href="../mypage/showreserve.do">마이페이지</a></li>
      </sec:authorize>
      <sec:authorize access="hasRole('ROLE_ADMIN')"> 
        <li><a href="../adminpage/adminpage.do">관리자페이지</a></li>
      </sec:authorize>
    <c:if test="${principal.username==null }">
       <li class="nav navbar-nav navbar-right"><a href="../member/login.do"><span class="glyphicon glyphicon-log-in"></span> Login</a></li>
      </c:if>
      <c:if test="${principal.username!=null }">
       <li class="nav navbar-nav navbar-right"><a href="../member/logout.do"><span class="glyphicon glyphicon-log-in"></span> Logout</a></li>
      </c:if>
                        <a href="../etc/contact.html" class="nav-item nav-link">Contact</a>
                    </div>
                    <a href="" class="btn btn-primary px-3 d-none d-lg-flex">Add Property</a>
                </div>
            </nav>
        </div>
</body>
</html>