<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<%
	String mesg= (String)session.getAttribute("memberAdd");
	if(mesg!=null){//회원가입 정보 있음
%>
	<script type="text/javascript">
		alert("<%=mesg%>");
	</script>
<%} %>
</head>
<body>
<h1>Main화면입니다.</h1>
<jsp:include page="common/top.jsp" flush="true"></jsp:include><br>
<jsp:include page="common/menu.jsp" flush="true"></jsp:include>
</body>
</html>