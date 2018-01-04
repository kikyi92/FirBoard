<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="resUrl" value="${ctx}/resources"/>
<html>
<head>
	<meta charset='utf-8'>
	<title>Board</title>
	<!-- <link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css"> -->
	<!-- <script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.3.2/js/bootstrap.min.js"></script> -->
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
	<script src="resources/bootstrap-3.3.2-dist/js/bootstrap.min.js"></script>
	<script src="${resUrl}/js/board.js" type="text/javascript"></script>
	
	<link rel="stylesheet" href="resources/bootstrap-3.3.2-dist/css/bootstrap.min.css"/>
</head>

<body>

 	<div class="center-block" style=width:70%;>
		<h1>게시판</h1>
		<div>
	      <table class="table table-bordered table-striped nanum text-center">
	        <thead>
	          <tr class="success">
	            <th class=text-center width="5%">번호</th>
	            <th class=text-center width="5%">분류</th>
	            <th class=text-center width="40%">제목</th>
	            <!-- <th width="20%">내용</th> -->
	            <th class=text-center width="20%">작성자</th>
	            <th class=text-center width="20%">작성일</th>
	            <th class=text-center width="10%">조회수</th>
	            <!-- <th width="10%">옵션</th>
	            <th width="10%">공개여부</th> -->
	          </tr>
	        </thead>
	        <tbody>
	           <c:forEach items="${BoardList}" var="board">
	            <tr class="active">
	              <td>${board.no}</td>
	              <td>${board.cate}</td>
	              <td class="text-left">${board.title}</td>
	              <%-- <td>${board.content}</td> --%>
	              <td>${board.writer}</td>
	              <td>${board.datetime}</td>
	              <td>${board.viewcnt}</td>
	              <%-- <td>${board.option}</td>
	              <td>${board.pubpriv}</td> --%>
	              </tr>
	        	</c:forEach>
	        </tbody>
	      </table>
		</div> 
	<button type="button" class="btn btn-lg btn-primary pull-right" id="writeBtn">글쓰기</button>
	</div>
	
<%@include file="/WEB-INF/views/write.jsp"%>	
</body>
</html>
