$(document).ready(function() {
	
       $('#engnrBtn').click(function(){
	     
		if($("#engnrID").val()==''){
			$('#checkMessage').html('아이디를 입력해주세요.');
			$("#checkModal").modal('show');
		}else if($("#engnrPassword").val() == ""){
			$('#checkMessage').html('비밀번호를 입력해주세요.');
			$("#checkModal").modal('show');
		}else{	
	
		var json = {engnrID : $('#engnrID').val(), engnrPassword : $('#engnrPassword').val(), engnrCookie :  $('#engnrCookie').is(':checked')}
		
		$.ajax({
		type:'POST',
		url: "/engnr/engnrLogin",
		data:JSON.stringify(json),
		contentType: "application/json; charset=UTF-8",
		dataType: "json",
		success : function(data){
				if(data.result === "Success"){
					$('#checkMessage').html('로그인 되었습니다.');  
					$("#checkModal").modal('show');
					
					$(".btn, .modal").click(function(){
						location.href="/engnr/engnrHome";	
					})
					
				}else if(data.result === "DBFailed"){
					$('#checkMessage').html('데이터베이스 처리 과정에 문제가 발생하였습니다.');  
					$("#checkModal").modal('show');
				}else if(data.result === "SystemFailed"){
					$('#checkMessage').html('시스템에 문제가 발생하였습니다.');  
					$("#checkModal").modal('show');
				}else{
					$('#checkMessage').html('아이디 또는 비밀번호가 잘못되었습니다.');  
					$("#checkModal").modal('show');
				}
		},
        error: function(xhr, status, error){
                alert(error);
				},
        	});
		}
	})
})