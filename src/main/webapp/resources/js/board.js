/*게시판
 *시간포맷 YYYY-MM-DD hh:mm:ss
 *조회
 *글쓰기
 * */
$(function(){

		// 체크박스 1개만 클릭되도록
		$('input[type="checkbox"]').bind('click',function() {
			$('input[type="checkbox"]').not(this).prop("checked", false); 
		});
	
		// 글쓰기 버튼 클릭시
		$("#writeBtn").click(function(){
			$('#writeForm').each(function(){
        		this.reset();
        	});
			
			$('#inputDate').val(getTimeStamp());
			$("#writeModal").show();
	        $("#writeModal").modal();
	    });
	    
		// 글쓰기 확인 클릭시
		$("#btnWrite").click(function(){
			var queryString = $("form[name=writeForm]").serialize();
			$.ajax({
	            type : 'post',
	            url : 'boardInsert.do',
	            data : queryString,
	            dataType : 'json',
	            success : function(data){
	            	refreshList(data);
	            	$('#readForm').each(function(){
	            		this.reset();
	            	});
	            },
	            error: function(xhr, status, error){
	                alert('error >>> : ' + error);
	            }
	        });
		});
		
		// 답글 입력시
		$("#btnReply").click(function(){
			$("#replyModal").modal();
			var queryString = $("form[name=replyForm]").serialize();
			$.ajax({
	            type : 'post',
	            url : 'replyInsert.do',
	            data : queryString,
	            dataType : 'json',
	            success : function(data){
	            	refreshList(data);
	            	$('#replyForm').each(function(){
	            		this.reset();
	            	});
	            },
	            error: function(xhr, status, error){
	                alert('error >>> : ' + error);
	            }
	        });
		});
		
	});

	//현재시간 구하기 YYYY-MM-dd hh:mm:ss
	function getTimeStamp() {
		  var d = new Date();
		  var s =
		    leadingZeros(d.getFullYear(), 4) + '-' +
		    leadingZeros(d.getMonth() + 1, 2) + '-' +
		    leadingZeros(d.getDate(), 2) + ' ' +
		    leadingZeros(d.getHours(), 2) + ':' +
		    leadingZeros(d.getMinutes(), 2) + ':' +
		    leadingZeros(d.getSeconds(), 2);
		  	return s;
		}
	
		function leadingZeros(n, digits) {
		  var zero = '';
		  n = n.toString();
	
		  if (n.length < digits) {
		    for (i = 0; i < digits - n.length; i++)
		      zero += '0';
		  }
		  return zero + n;
	}

	// 글제목 클릭시
	function clickTitle(no){
		var allData={"no":no}
		$('#deleteBtn').attr('onClick', 'boardDelete('+no+')');
		$('#updateBtn').attr('onClick', 'boardUpdate('+no+')');
		$('#replyBtn').attr('onClick', 'clickReply('+no+')');
		$.ajax({
	        type : 'post',
	        url : 'detail.ajax',
	        data : allData,
	        dataType: 'json',
	        success : function(data){
	            for(var key in data){
	            	$("#contentNo").val(data[key].no);
	            	$("#inputDatetime").val(data[key].datetime);
	            	$("#inputCate").val(data[key].cate);
	            	$("#inputSource").val(data[key].source);
	                $("#inputWriter").val(data[key].writer);
	                $("#Title").val(data[key].title);
	                $("#Content").val(data[key].content);
	                $("#inputPubpriv").val(data[key].pubpriv);
	                
	                
	        /*        if(data[key].bopen == 'Y'){
	                	$('input:radio[name=bopen]:input[value=Y]').prop("checked", true);
	                	$('input:radio[name=bopen]:input[value=N]').prop("checked", false);
	                }else{
	                	$('input:radio[name=bopen]:input[value=N]').prop("checked", true);
	                	$('input:radio[name=bopen]:input[value=Y]').prop("checked", false);
	                }
	                if(data[key].source == '창'){
	                	$('input:checkbox[name=bsource]:input[value=창]').prop("checked", true);
	                	$('input:checkbox[name=bsource]:input[value=펌]').prop("checked", false);
	                }else if(data[key].source == '펌'){
	                	$('input:checkbox[name=bsource]:input[value=펌]').prop("checked", true);
	                	$('input:checkbox[name=bsource]:input[value=창]').prop("checked", false);
	                }else{
	                	$('input:checkbox[name=bsource]:input[value=창]').prop("checked", true);
	                }*/
	            }
	        },
	        error: function(xhr, status, error){
	            alert('error >>> : ' + error);
	        }
	    });
		$("#detailModal").show();
		$("#detailModal").modal();
	}
	
	// 글삭제 클릭시
	function boardDelete(bno){
		var allData={"no":bno}
		$.ajax({
            type : 'post',
            url : 'boardDelete.do',
            data : allData,
            dataType: 'json',
            success : function(data){
            	refreshList(data);
            },
            error: function(xhr, status, error){
                alert('error >>> : ' + error);
            }
        });
	}
	
	// 조회된 데이터 화면에 노출
	function refreshList(data){
		var innerHtml="";
		for(var key in data){
			innerHtml = innerHtml + '<tr><td style="text-align: center;">'+data[key].no+'</td>'
			                                + '<td style="text-align: center;">'+data[key].cate+'</td>'
			                                + '<td style="text-align: left;"><a href="#" onClick="clickTitle('+data[key].no+')">'+data[key].title+'</a></td>'
			                                + '<td style="text-align: center;">'+data[key].writer+'</td>'
			                                + '<td style="text-align: center;">'+data[key].datetime+'</td>'
			                                + '<td style="text-align: center;">'+data[key].viewcnt+'</td></tr>'
		}
		$('.center-block tbody').html(innerHtml);
	}
	
	// 글수정 클릭시
	function boardUpdate(bno){
		var queryString = $("form[name=detailForm]").serialize();
		$.ajax({
            type : 'post',
            url : 'boardUpdate.do',
            data : queryString,
            dataType : 'json',
            error: function(xhr, status, error){
                alert('error >>> : ' + error);
            },
            success : function(data){
                refreshList(data);
                $('#readForm').each(function(){
    				this.reset();
                    });
            }
        });
	}
	
	// 답글버튼 클릭시
	function clickReply(no){
		var allData={"no":no}
		$("#replyDate").val(getTimeStamp());
		$("#replyModal").show();
		$("#replyModal").modal();
		$("#parentNo").val(no);
		$.ajax({
	        type : 'post',
	        url : 'parentInfo.do',
	        data : allData,
	        dataType: 'json',
	        success : function(data){
	        	$("#replyTitle").val(data.title);
            }
		});
	}