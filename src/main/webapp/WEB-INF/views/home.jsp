<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" contentType="text/html; charset=UTF-8"%> 

<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Hello Spring~!<br/>
<ol>
	<li><a href="/mvc">spring mvc1.0</a></li>
	<li><a href="/board/list">게시판 리스트</a></li>
	<li><a href="/uploadForm">파일 업로드(폼 방식)</a></li>
	<li><a href="/uploadAjax">파일 업로드(Ajax)</a></li>
	<li><a href="/customLogin">커스텀 로그인</a>
</ol>
	
</h1>

<P>  The time on the server is ${serverTime}. </P>
</body>
</html>
