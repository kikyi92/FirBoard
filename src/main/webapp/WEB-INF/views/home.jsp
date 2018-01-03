<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<!-- <title>Home</title>
부트스트랩
<link href="bootstrap-3.3.2/css/bootstrap.min.css" rel="stylesheet">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script> -->
<meta charset='utf-8'>
<title>Home</title>
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<style></style>
<script></script>
</head>
<body>

 	<div class="container">
	<h1>게시판</h1>
	<%-- <P>The Time on the server is ${serverTime}.</P> --%>
	<div>
      <table class="table table-bordered table-striped nanum">
        <thead>
          <tr class="success">
            <th width="10%">번호</th>
            
            <th width="10%">분류</th>
            <th width="10%">제목</th>
            <th width="20%">내용</th>
            <th width="10%">작성자</th>
            <th width="10%">작성일</th>
            <th width="10%">조회수</th>
            <th width="10%">옵션</th>
            <th width="10%">공개여부</th>
          </tr>
        </thead>
        <tbody>
           <c:forEach items="${BoardList}" var="board">
            <tr class="active">
              <td>${board.no}</td>
              <td>${board.cate}</td>
              <td>${board.title}</td>
              <td>${board.content}</td>
              <td>${board.writer}</td>
              <td>${board.datetime}</td>
              <td>${board.viewcnt}</td>
              <td>${board.option}</td>
              <td>${board.pubpriv}</td>
              </tr>
            </c:forEach>
        </tbody>
      </table>
</div> 
	
</body>
</html>
