$(document).ready(function() {
	
	$("#custmrBtn").prop("disable", true);
	var emailOk = false;
    var pw1Ok = false;
    var pw2Ok = false;
    var nameOk = false;
    var agreeOk = false;
    var isOk = false;
    
    //E-mail 체크
    $("#custmrID").blur(function(){
        
        var chkEmail = /^[a-zA-Z0-9]+@([a-zA-Z0-9]{1,10})\.([a-zA-Z]{1,10})+$/;
        
		if($("#custmrID").val()==''){			
			$('#emailCheck').html('아이디를 입력하세요.');
			$('#emailCheck').css('color', 'red');
			emailOk = false;
			$("#custmrBtn").prop("disabled",true);
			
		}else if(chkEmail.test($("#custmrID").val())!= true){			
			$('#emailCheck').html('4~12자의 영문, 숫자만 사용 가능합니다.');
			$('#emailCheck').css('color', 'red');
			emailOk = false;
			$("#custmrBtn").prop("disabled",true);
			
		}else if($("#custmrID").val()!=''){
			var custmrEmail = $("#custmrID").val();			
			$.ajax({
		    url: '/custmr/custmrIdCheck',
		    type: 'POST',
		    dataType: 'json', //서버로부터 내가 받는 데이터의 타입
		    data: custmrEmail,
			contentType: "application/json; charset=UTF-8",
		    success: function(data){
				if(data.confirm === "NO"){
					emailOk = false;
					$('#emailCheck').html('이미 존재하는 아이디입니다.');  
					$('#emailCheck').css('color', 'red');
					$("#custmrBtn").prop("disabled",true);
				}else{
					if (chkEmail.test(custmrEmail)){
						emailOk = true;
						$('#emailCheck').html('사용 가능한 아이디입니다.');  
						$('#emailCheck').css('color', '#33B8FF');
						
						isOk = allOk(emailOk, pw1Ok, pw2Ok, nameOk, agreeOk);
			            if( isOk == true ){
			                $("#custmrBtn").prop("disabled",false);
			            }
					} else if(custmrEmail==""){
						emailOk = false;
						$('#emailCheck').html('아이디를 입력하세요.');
						$('#emailCheck').css('color', 'red');
						$("#custmrBtn").prop("disabled",true);
					}else{
						emailOk = false;
						$('#emailCheck').html("이메일 양식을 확인해주세요.");
						$('#emailCheck').css('color', 'red');
						$("#custmrBtn").prop("disabled",true);
					}
				}
			},
			    error: function (){
					
			    }
			});
		}
    });
		
	//이름 길이 체크. 2자~20자까지
	$("#custmrName").blur(function(){
        var inputLen = $("#custmrName").val().length;
        var chkName = /([^가-힣\x20])/i; 
        var nameResult = chkName.test( $("#custmrName").val() );
        
        //길이에 맞지 않거나 공백이 포함되어 있는 경우
        if((inputLen < 2 || inputLen > 5) || nameResult){            
            nameOk = false;            
            $("#custmrBtn").prop("disabled",true);
			$('#nameCheck').html("한글 2~4자 이내로 입력하세요.(특수기호, 공백 사용 불가)");
			$('#nameCheck').css('color', 'red');
            
        }else{            
            nameOk = true;
            $("#custmrBtn").prop("disabled",true);
			$('#nameCheck').html("사용 가능한 이름입니다.");
			$('#nameCheck').css('color', '#33B8FF');
			
            isOk = allOk(emailOk, pw1Ok, pw2Ok, nameOk, agreeOk);
            if( isOk == true ){
                $("#custmrBtn").prop("disabled",false);
                
            }
        }
    });
	
	//PW 길이 체크
    $("#custmrPassword").blur(function(){
		var chkPw = /^[A-Za-z0-9]{4,12}$/;
		
        if( $("#custmrPassword").val().length < 8 ){
            pw1Ok = false;
            $("#custmrBtn").prop("disabled",true);
            $('#pwCheck').html("비밀번호는 8자 이상으로 입력해주세요.");
			$('#pwCheck').css('color', 'red');            
        }else if(chkPw.test( $("#custmrPassword").val() )){
			 pw1Ok = false;
			$("#custmrBtn").prop("disabled",true);
            $('#pwCheck').html("4~12자의 숫자, 문자로만 사용 가능합니다.");
			$('#pwCheck').css('color', 'red');
		}else{
            pw1Ok = true;
            $('#pwCheck').html("사용 가능한 비밀번호입니다.");
			$('#pwCheck').css('color', '#33B8FF');
			    
            isOk = allOk(emailOk, pw1Ok, pw2Ok, nameOk, agreeOk);
            if( isOk == true ){
                $("#custmrBtn").prop("disabled",false);                
            }
        }
    });
    
    //PW 확인 체크
    $("#checkPass").blur(function(){
        var pw1 = $("#custmrPassword").val();
        var pw2 = $("#checkPass").val();
        if( pw1 == pw2 ){
            pw2Ok = true;
			$('#pw2Check').html("비밀번호가 일치합니다.");
			$('#pw2Check').css('color', '#33B8FF');
			
            isOk = allOk(emailOk, pw1Ok, pw2Ok, nameOk, agreeOk);
            if( isOk == true ){
                $("#custmrBtn").prop("disabled",false);
            }
        }else{
            pw2Ok = false;
			$('#pw2Check').html("비밀번호가 일치하지 않습니다.");
            $('#pw2Check').css('color', 'red');
            $("#custmrBtn").prop("disabled",true);
        }
    });
	
	
	$("#agreeRegister").change(function(){
		if($("#agreeRegister").is(":checked")){
			agreeOk = true;
			isOk = allOk(emailOk, pw1Ok, pw2Ok, nameOk, agreeOk);
            if( isOk == true ){
                $("#custmrBtn").prop("disabled",false);
            }
		}else{
			agreeOk = false;
			$("#custmrBtn").prop("disabled",true);
		}	
	});
	
	
	$('#custmrBtn').click(function(){
		var json = {
			custmrID : $("#custmrID").val()
			, custmrPassword : $("#custmrPassword").val()
			, custmrName : $("#custmrName").val()
		}
		
		$.ajax({
		type:'POST',
		url: "/custmr/custmrRegister",
		data:JSON.stringify(json),
		contentType: "application/json; charset=UTF-8",
		dataType: "json",
		success : function(data){
				if(data.result === "Success"){
					$('#checkMessage').html('회원가입 되었습니다.');  
					$("#checkModal").modal('show');
					
					$(".btn, .modal").click(function(){
						location.href="/custmr/custmrLogin";
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
	});
});

function allOk( ok1, ok2, ok3, ok4, ok5 ){
	if( ok1 && ok2 && ok3 && ok4 && ok5 ){
        return true;
	} else {
        return false;
    }
}