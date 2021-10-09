<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
    $(document).ready(function(){
    	//form 서브밋
 $("form").on("submit",function(event){		
	 var userid = $("#userid").val();
	 var passwd = $("#passwd").val();
    		if(userid.length==0){
    			alert("id 필수 입력")
    			$("#userid").focus();
    			event.preventDefault();
    		}else if(passwd.length==0){
    			alert("passwd 필수 입력")
    			$("#passwd").focus();
    			event.preventDefault();
    		}
    	});
 });
</script>
<form action="LoginServelt" method="get">
 아이디:<input type="text" name="userid" id="userid"><br>
 비밀번호:<input type="text" name="passwd" id="passwd"><br>
<input type="submit" value="로그인">
 <input type="reset" value="취소">
</form>
</head>
<body>

</body>
</html>
<