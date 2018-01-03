<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<!-- Trigger the modal with a button -->
<div class="container">

  <!-- Modal -->
  <div class="modal fade" id="writeModal" role="dialog">
    <div class="modal-dialog modal-80size">
    
      <!-- Modal content-->
      <div class="modal-content modal-80size">
        <div class="modal-header">
          <button type="button" class="close" data-dismiss="modal">&times;</button>
          <h4 class="modal-title">글쓰기</h4>
        </div>
        <div class="modal-body">
        	<form name="writeForm" id="writeForm">
			    <label>작성일</label>
			    <input type="text" class="form-control" name="datetime" id="inputDate" placeholder="작성일" >
			    <label>게시판종류</label>
			    <select class="form-control" name="cate" id="cate" >
				  <option>유머</option>
				  <option>게임</option>
				  <option>패션</option>
				</select>
				<label>출처</label><br>
				<label class="checkbox-inline">
				  <input type="checkbox" name="source" id="inlineCheckbox1" value="창" checked>창작
				</label>
				<label class="checkbox-inline">
				  <input type="checkbox" name="source" id="inlineCheckbox2" value="펌"> 펌
				</label><br>
			    <label>작성자</label>
			    <input type="text" class="form-control" name="writer" id="inputName" placeholder="작성자" >
			  	<label>제목</label>
			  	<input type="text" class="form-control" name="title" id="inputTitle" placeholder="제목">
			  	<label>내용</label>
			  	<textarea class="form-control" rows="6" placeholder="내용" name="content" id="inputContent"></textarea>
			  	<div class="radio pull-right">
					<label class="radio-inline">
				  		<input type="radio" name="pubpriv" id="bRadio1" value="Y" checked> 공개
					</label>
					<label class="radio-inline">
				  		<input type="radio" name="pubpriv" id="bRadio2" value="N"> 비공개
					</label>
				</div>
		  	</form>
        </div>
        <div class="modal-footer">
          <button type="button" class="btn btn-primary" data-dismiss="modal"id="btnWrite">확인</button>
          <button type="button" class="btn btn-default" data-dismiss="modal">취소</button>
          <button type="button" class="btn btn-warning" onClick="boardUpdate()" data-dismiss="modal">수정</button>
        </div>
      </div>
      
    </div>
  </div>
  
</div>