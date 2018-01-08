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
	            <th class=text-center width="10%">번호</th>
	            <th class=text-center width="10%">분류</th>
	            <th class=text-center width="40%">제목</th>
	            <!-- <th width="20%">내용</th> -->
	            <th class=text-center width="10%">작성자</th>
	            <th class=text-center width="20%">작성일</th>
	            <th class=text-center width="10%">조회수</th>
	            <!-- <th width="10%">옵션</th>
	            <th width="10%">공개여부</th> -->
	          </tr>
	        </thead>
	        <tbody>
	           <c:forEach items="${page}" var="board">
					<tr class="active">
						<td>${board.no}</td>
						<td>${board.cate}</td>
						<td id="detailView" class="text-left"><a href="javascript:clickTitle(${board.no})">${board.title}</a></td>
						<td>${board.writer}</td>
						<td>${board.datetime}</td>
						<td>${board.viewcnt}</td>
					</tr>
				</c:forEach>
	        </tbody>
	      </table>
		</div> 
	<!-- Paging Start  -->
			<c:choose>
				<c:when
					test="${paging.numberOfRecords ne NULL and paging.numberOfRecords ne '' and paging.numberOfRecords ne 0}">
					<div class="text-center marg-top">
						<ul class="pagination">
							<c:if test="${paging.currentPageNo gt 5}">
								<!-- 현재 페이지가 5보다 크다면(즉, 6페이지 이상이라면) -->
								<li><a
									href="javascript:goPage(${paging.prevPageNo}, ${paging.maxPost})">이전</a></li>
								<!-- 이전페이지 표시 -->
							</c:if>
							<!-- 다른 페이지를 클릭하였을 시, 그 페이지의 내용 및 하단의 페이징 버튼을 생성하는 조건문-->
							<c:forEach var="i" begin="${paging.startPageNo}"
								end="${paging.endPageNo}" step="1">
								<!-- 변수선언 (var="i"), 조건식, 증감식 -->
								<c:choose>
									<c:when test="${i eq paging.currentPageNo}">
										<li class="active"><a
											href="javascript:goPage(${i}, ${paging.maxPost})">${i}</a></li>
										<!-- 1페이지부터 10개씩 뽑아내고, 1,2,3페이지순으로 나타내라-->
									</c:when>
									<c:otherwise>
										<li><a href="javascript:goPage(${i}, ${paging.maxPost})">${i}</a></li>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<!-- begin에 의해서 변수 i는 1이기 때문에, 처음에는 c:when이 수행된다. 그 후 페이징의 숫자 2를 클릭하면 ${i}는 2로변하고, 현재는 ${i}는 1이므로 otherwise를 수행한다
					         그래서 otherwise에 있는 함수를 수행하여 2페이지의 게시물이 나타나고, 반복문 실행으로 다시 forEach를 수행한다. 이제는 i도 2이고, currentPageNo도 2이기 때문에
					     active에 의해서 페이징부분의 2에 대해서만 파란색으로 나타난다. 그리고 나머지 1,3,4,5,이전,다음을 표시하기위해 다시 c:otherwise를 수행하여 페이징도 나타나게한다.-->
							<!-- // 다른 페이지를 클릭하였을 시, 그 페이지의 내용 및 하단의 페이징 버튼을 생성하는 조건문-->

							<!-- 소수점 제거 =>-->
							<fmt:parseNumber var="currentPage" integerOnly="true"
								value="${(paging.currentPageNo-1)/5}" />
							<fmt:parseNumber var="finalPage" integerOnly="true"
								value="${(paging.finalPageNo-1)/5}" />

							<c:if test="${currentPage < finalPage}">
								<!-- 현재 페이지가 마지막 페이지보다 작으면 '다음'을 표시한다. -->
								<li><a
									href="javascript:goPage(${paging.nextPageNo}, ${paging.maxPost})">다음</a></li>
							</c:if>
						</ul>
					</div>
				</c:when>
			</c:choose>
			<!-- Paging End -->
		</div>
		<button type="button" class="btn btn-lg btn-primary pull-right"
			id="writeBtn">글쓰기</button>

		<!-- Search Start -->
		<center>
			<div id="searchForm">
				<script>
					function goPage(pages, lines) {
						location.href = '?' + "pages=" + pages;
					}
				</script>
				<form name="serach" method="post">
					<select name="searchOption">
						<option value="title" id="title">제목</option>
					</select> 
					<input type="text" id="keyword" name="keyword" /> 
					<input type="button" id="searchBtn" value="검색"/>
				</form>
			</div>
		</center>
		<!-- Search End -->
	</div>
	
	
<%@include file="/WEB-INF/views/write.jsp"%>	
<%@include file="/WEB-INF/views/detail.jsp"%>	
<%@include file="/WEB-INF/views/reply.jsp"%>	
</body>
</html>
