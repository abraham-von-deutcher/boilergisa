//파일 업로드
$('#profileChangeBtn').click(function(){
	$('#file').click();
})

$('#file').on("change", function(e) {
	
	
	
	$("#changeYn").val("Y");
	console.log($("#changeYn").val());
	$('.preProfile').empty();
	
    var reader = new FileReader();
	reader.onload = function (e) {
		var img = document.createElement("img");
		img.setAttribute("src", e.target.result);
		document.querySelector("div.preProfile").appendChild(img);
	}
	reader.readAsDataURL( e.target.files[0]);	
});

//회원정보 수정 버튼
$('#engnrUpdateBtn').click(function(){
	var engnrNo = $("#engnrNo").val();
	var file = $('#file')[0].files[0]
	var formData = new FormData();
		formData.append('file',file );
	
	var dataset = {
		engnrNo: $('#engnrNo').val(),
		engnrID: $('#engnrID').val(),
		changeYn: $('#changeYn').val(),
		engnrName: $('#engnrName').val(),
		address1: $('#address1').val(),
		address2: $('#address2').val(),
		engnrNumber: $('#engnrNumber').val(),
		engnrIntro: $('#engnrIntro').val(),
		businessType: $('#businessType').val(),
		workTime1: $('#workTime1').val(),
		workTime1: $('#workTime1').val(),
		breakTime: $('#breakTime').val()
	}	
	
	if(file != null){
		multipart(formData, "/engnr/engnrFile/" + engnrNo)
	}	
	   
	common_ajax("POST", "/engnr/engnrInfo/" + engnrNo, dataset, function(data) {
		if(data.result == "Success") {
			
			$('#checkMessage').html('회원님 정보가 수정되었습니다.');  
			$("#checkModal").modal('show');
			
			$(".btn, .modal").click(function(){
				location.href="/engnr/engnrMypage";	
			});
		
		} else {
			$('#checkMessage').html('문제가 발생하였습니다\n관리자에게 문의하여 주세요.');
			$("#checkModal").modal('show');
		}
	
		
	});
	
});	
	
function multipart(formData, url) {
		
		$.ajax({
		type:'POST',
		url: url,
		data: formData,
		contentType: false,
		processData: false,
		enctype:'multipart/form-data',
		success : function(data){
			
			console.log(data);				
		},
		error: function(xhr, status, error){
			alert(error);
		},
	});
}
	
function common_ajax(method, url, dataset, callback){
	var data;
	if(method == 'get'){
		data = dataset
	} else {
		data = JSON.stringify(dataset)
	}	

	$.ajax({
		url:url, //request 보낼 서버의 경로
		type : method, // 메소드(get, post, put 등)
		data : data, //보낼 데이터
		dataType : "json", 
	    contentType: 'application/json',
	    async : true,		
		success: function(data) {         
			callback(data);
		},
      error: function(err) { //서버로부터 응답이 정상적으로 처리되지 못햇을 때 실행
         if(err.status == "401"){
           $('#checkMessage').html("세션이 만료되었거나 잘못된 요청입니다.\n로그인 화면으로 이동합니다.");
           $("#checkModal").modal('show');
           $(".btn, .modal").click(function(){
				location.href="/engnr/engnrLogin";	
			});
         } else {
            $('#checkMessage').html("문제가 발생하였습니다\n관리자에게 문의하여 주세요.");
            $("#checkModal").modal('show');
         }
         return false;
      }
   });
}

function fileCheck(obj){
	pathpoint = obj.value.lastIndexOf('.');
	filepoint = obj.value.substring(pathpoint + 1,obj.length);
	
}





