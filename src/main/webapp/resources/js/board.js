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
	    // 시간포맷구현
		$("#writeBtn").click(function(){
			$('#writeForm').each(function(){
        		this.reset();
        	});
			var now = new Date();
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
	});

	// 글제목 클릭시
	function clickTitle(bno){
		var allData={"bno":bno}
		$('#deleteBtn').attr('onClick', 'boardDelete('+bno+')');
		$('#updateBtn').attr('onClick', 'boardUpdate('+bno+')');
		$.ajax({
            type : 'post',
            url : 'boardRead.do',
            data : allData,
            dataType: 'json',
            success : function(data){
                for(var key in data){
                	$("#bContentNo").val(data[key].bno);
                	$("#bContentDate").val(data[key].bdate);
                	$("#bContentCategory").val(data[key].bcategory);
                    $("#bContentWriter").val(data[key].bwriter);
                    $("#bContentTitle").val(data[key].btitle);
                    $("#bContent").val(data[key].bcontent);
                    if(data[key].bopen == 'Y'){
                    	$('input:radio[name=bopen]:input[value=Y]').prop("checked", true);
                    	$('input:radio[name=bopen]:input[value=N]').prop("checked", false);
                    }else{
                    	$('input:radio[name=bopen]:input[value=N]').prop("checked", true);
                    	$('input:radio[name=bopen]:input[value=Y]').prop("checked", false);
                    }
                    if(data[key].bsource == '창'){
                    	$('input:checkbox[name=bsource]:input[value=창]').prop("checked", true);
                    	$('input:checkbox[name=bsource]:input[value=펌]').prop("checked", false);
                    }else if(data[key].bsource == '펌'){
                    	$('input:checkbox[name=bsource]:input[value=펌]').prop("checked", true);
                    	$('input:checkbox[name=bsource]:input[value=창]').prop("checked", false);
                    }else{
                    	$('input:checkbox[name=bsource]:input[value=창]').prop("checked", true);
                    }
                }
            },
            error: function(xhr, status, error){
                alert('error >>> : ' + error);
            }
        });
		$("#readModal").modal();
	}
	
	// 글삭제 클릭시
	function boardDelete(bno){
		var allData={"bno":bno}
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
			                                + '<td><a href="#" onClick="clickTitle('+data[key].no+')">'+data[key].title+'</a></td>'
			                                + '<td style="text-align: center;">'+data[key].writer+'</td>'
			                                + '<td style="text-align: center;">'+data[key].datetime+'</td>'
			                                + '<td style="text-align: center;">'+data[key].viewcnt+'</td></tr>'
		}
		$('.center-block tbody').html(innerHtml);
	}
	
	// 글수정 클릭시
	function boardUpdate(bno){
		var queryString = $("form[name=readForm]").serialize();
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