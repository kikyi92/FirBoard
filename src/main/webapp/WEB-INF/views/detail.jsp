<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Trigger the modal with a button -->
<div class="container">

	<!-- Modal -->
	<div class="modal fade" id="detailModal" role="dialog">
		<div class="modal-dialog modal-80size">

			<!-- Modal content-->
			<div class="modal-content modal-80size">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal">&times;</button>
					<h4 class="modal-title">상세보기111</h4>
				</div>
				<div class="modal-body">
					<form name="detailForm" id="detailForm">
						<input type="hidden" name="no" id="contentNo">							
						<label>작성일</label>
					    <input type="text" class="form-control" name="datetime" id="inputDatetime" readonly="readonly">
					    
					    <label>게시판종류</label>
					    <input type="text" class="form-control" name="cate" id="inputCate"  >
						
						<label>출처</label><br>
						<input type="text" class="form-control" name="source" id="inputSource"  >
					    
					    <label>작성자</label>
					    <input type="text" class="form-control" name="writer" id="inputWriter"  >
					  	
					  	<label>제목</label>
					  	<input type="text" class="form-control" name="title" id="Title"  >
					  	
					  	<label>내용</label>
					  	<textarea class="form-control" rows="6" name="content" id="Content"></textarea>
					  	
					  	<label>공개여부</label>
					  	<input type="text" class="form-control" name="pubpriv" id="inputPubpriv"> 
					</form>
				</div>
				<div class="modal-footer">
					<button type="button" class="btn btn-primary" data-dismiss="modal"
						id="btnWrite">취소</button>
					<button type="button" class="btn btn-warning" id="updateBtn" data-dismiss="modal">수정</button>
					<button type="button" class="btn btn-danger" id="deleteBtn" data-dismiss="modal">삭제</button>
					<button type="button" class="btn btn-info" data-dismiss="modal" id="replyBtn">답글</button>
				</div>
			</div>

		</div>
	</div>

</div>