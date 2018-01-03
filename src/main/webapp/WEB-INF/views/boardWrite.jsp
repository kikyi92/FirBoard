<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>글 상세조회</title>
	<link rel="stylesheet" href="resources/bootstrap-3.3.2-dist/css/bootstrap.min.css">
	<link rel="stylesheet" href="resources/bootstrap-3.3.2-dist/js/bootstrap.min.js">
	
	<script src="http://code.jquery.com/jquery-latest.min.js"></script>
</head>
<body>

	<div class="center-block " style=width:70%;>
		<h1>게시판</h1>
		<div>
	  <form action="boardWriteInsert.do" method=POST accept-charset="UTF-8">
	      <table class="table">
	      	<tr>
	    		<td width="20%" class="success text-center">분류</td>
	    		<td width="80%">
					<select class="form-control" id="cate" name="cate">
					  <option>유머</option>
					  <option>게임</option>
					  <option>패션</option>
					</select>
				</td>
	    	</tr>
	    	<tr>
	    		<td width="20%" class="success text-center">작성자</td>
	    		<td width="80%">
	    			<input type="text" class="form-control" name="writer" id="writer" placeholder="작성자" >
	    		</td>
	    	</tr>
	    	<tr>
	    		<td width="20%" class="success text-center">제목</td>
	    		<td width="80%">
	    			<input type="text" class="form-control" name="title" id="title" placeholder="제목" >
	    		</td>
	    	</tr>
	    	<tr>
	    		<td width="20%" class="success text-center">내용</td>
	    		<td width="80%">
	    			<textarea class="form-control" rows="6" placeholder="내용" name="content" id="content" placeholder="내용"></textarea>
	    		</td>
	    	</tr>  
	      </table>
	    </div>
	    <button type="submit" class="btn btn-lg btn-success pull-right">등록</button>
	    <button type="button" class="btn btn-lg btn-danger pull-right" onClick="history.back()">취소</button>
      </form>

</body>
</html>