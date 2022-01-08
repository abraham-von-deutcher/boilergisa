<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="user.UserDTO" %>
<%@ page import="user.UserDAO" %>
<!DOCTYPE html>
<html lang="ko">
<head>
<%
	String userID = null;
	if (session.getAttribute("userID") != null) {
		userID = (String) session.getAttribute("userID");
	}
	if (userID == null){
		session.setAttribute("messageType", "오류메시지");
		session.setAttribute("messageContent", "현재 로그인이 되어 있지 않습니다.");
		response.sendRedirect("index.jsp");
		return;
	}
	UserDTO user = new UserDAO().getUser(userID);
	String engnrID = null;
	if (session.getAttribute("engnrID") != null) {
		engnrID = (String) session.getAttribute("engnrID");
	}
	if (engnrID != null){
		session.setAttribute("messageType", "오류메시지");
		session.setAttribute("messageContent", "현재 로그인된 상태입니다.");
		response.sendRedirect("engnrHome.jsp");
		return;
	}
%>
<meta http-equiv="Content-type" content="text/css; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta charset="UTF-8">

<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">

<link rel="stylesheet" href="css/customer/resultRequest.css">
<link rel="stylesheet" href="css/bgs.css">

<title>온수기 견적요청</title>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script src="js/bootstrap.js"></script>

 <script type="text/javascript">
 $(document).ready(function(){
	 $('.morePurpose').click(function(){		 
		 if($(".detailsPurpose").is(":visible")){
			 $(".morePurpose").text("▼ 권장용량 보기");
			 $(".detailsPurpose").hide();
		 }else{
			 $(".morePurpose").text("▲ 권장용량 닫기");
			 $(".detailsPurpose").show();
		 }
	 });
	 $("#inputImgs").on("change", handleImgFileSelect);
	 
	 var galleryThumbs = new Swiper('.addSlider-nav', {
	      slidesPerView: 4,
	      freeMode: true,
	      watchSlidesVisibility: true,
	      watchSlidesProgress: true,
	    });
	    var galleryTop = new Swiper('.addSlider', {
	      thumbs: {
	        swiper: galleryThumbs
	      }
	    });
})
	function handleImgFileSelect(e) {
		$('.addSlider ul').empty();
    	$('.addSlider-nav ul').empty();
			var files = e.target.files;
            var filesArr = Array.prototype.slice.call(files);

            filesArr.forEach(function(f) {
            	if(files.length < 6){

                var reader = new FileReader();
                reader.onload = function(e) {
                	var sliderNav = '<li class=\"swiper-slide\">' +
        			"<img src=\""+ e.target.result +  "\"></li>";
        			 $('.swiper-wrapper').append(sliderNav);
                }
                reader.readAsDataURL(f);
            	}
            	else{
            		$('#checkModal').modal("show");
            		$('#checkMessage').html('5개까지 업로드 가능합니다.');
            		return false;
            	}
            });
	}
	function checkAgree(){
		if($("#agreeBox").is(":checked") == false){
			$('#checkMessage').html('이용약관에 동의해주세요.');
			$("#requestInstall").attr("disabled", true);
		}else{
			$("#requestInstall").attr("disabled",false);
		}		
	}
	
	function inputCheck(){
		if($('#inputImgs').val() == ''){
			$('#checkMessage').html('사진을 업로드 해주세요.');  
		    $("#checkModal").modal('show');
		    $("#inputImgs").focus();			    
		} else if($('#explainPhotos').val() == ''){
			$('#checkMessage').html('사진설명 작성해주세요.');  
		    $("#checkModal").modal('show');
		    $("#explainPhotos").focus();			    
		    
		} else if($('#floor').val() == ''){
			$('#checkMessage').html('설치장소를 선택해주세요.');  
		    $("#checkModal").modal('show');
		    $("#floor").focus();			    
		} else if($('#kinds').val() == ''){
			$('#checkMessage').html('온수기 종류를 선택해주세요.');  
		    $("#checkModal").modal('show');
		    $("#kinds").focus();			    
		} else if($('#fuel').val() == ''){
			$('#checkMessage').html('용량을 선택해주세요.');  
		    $("#checkModal").modal('show');
		    $("#fuel").focus();			    
		} else if($('#brand').val() == ''){
			$('#checkMessage').html('선호 브랜드를 선택해주세요.');  
		    $("#checkModal").modal('show');
		    $("#brand").focus();			    
		} else if($('#addr').val() == ''){
			$('#checkMessage').html('주소를 작성해주세요.');  
		    $("#checkModal").modal('show');
		    $("#addr").focus();			    
		}else if($('#detailAddress').val() == ''){
			$('#checkMessage').html('세부주소를 작성해주세요.');  
		    $("#checkModal").modal('show');
		    $("#addr").focus();			    
		}else if($('#pNumber').val() == ''){
			$('#checkMessage').html('전화번호를 작성해주세요.');  
		    $("#checkModal").modal('show');
		    $("#pNumber").focus();			    
		}else{
			$('#checkUploadMessage').html('업로드 하시겠습니까?');  
		    $("#checkUpload").modal('show');
			var modalConfirm = function(callback){
				  $("#modal-btn-yes").on("click", function(){
				    callback(true);
				    $("#checkUpload").modal('hide');
				  });		  
				  $("#modal-btn-no").on("click", function(){
				    callback(false);
				    $("#checkUpload").modal('hide');
				  });
				};
			modalConfirm(function(confirm){
				if(confirm){
					$('#reqUpload').submit();
				}else{		    
				}
			});
		};
	}
	
</script>

</head>
<body>
 <header data-role="header" id ="bgHeader">
 <div class="backHeader"><img src="img/arrow-back.png" onclick="location.href='userMain.jsp'"></div>
	<div class="centerHeader"><h3>온수기 의뢰작성</h3></div>
	<div class="nextHeader"></div>
 </header>
 <div class="main">
  <form id = "reqUpload" name="reqUpload" method="post" action="./requestQuote" enctype="multipart/form-data">
   <div class="inputInfo">
	<div class="uploadPhotos">
	 <div class="uploadContent">
		<label for="inputImgs">
	  	<input type="file" name="uploadPhotos" id="inputImgs" multiple="multiple"/>
	  	<span class="addPhotos">
	  		<img src="img/add_img_white.png">사진업로드
	  	</span>
		</label>
		<div class="uploadContext">
		 <h4>설치관련 사진 추가</h4>
		 <p>온수기, 배관 등 사진을 등록하시면,<br>업자분들에게 더욱더<br>정확한 견적을 받을 수 있습니다.</p>
		</div>
	 </div>
		<ul class="slider-nav">
		 <li><img src="img/add_img_white.png"></li>
		 <li><img src="img/add_img_white.png"></li>
		 <li><img src="img/add_img_white.png"></li>
		 <li><img src="img/add_img_white.png"></li>
		</ul>
		<div class="addFiles">
		 <div class="swiper-container addSlider">
		  <ul class="swiper-wrapper"></ul>
		 </div>
		 <div class="swiper-container addSlider-nav">
		  <ul class="swiper-wrapper"></ul>
		 </div>
		</div>
	</div>
	<input type="hidden" name="userID" value="<%= user.getUserID() %>">	
	<input type="hidden" name="equipment" value="온수기">
  	<div class="form-group" style="width:100%; min-height:50px;">
	  <h4>장소 설명</h4>
	  <textarea class="form-control" id="explainPhotos" name ="explainPhotos" maxlength="500" rows="3" placeholder="온수기 사용용도를 적어주세요.&#13;&#10; 예) 간단한 설거지용으로 싱크대 안에 설치하고 싶습니다.(500자)"></textarea>
  	</div>
     <div class="install-info-wthtr">
          <div class="infoPurpose">
          	<p>- 전기온수기는 기본적으로 2.5kw를 사용하기 때문에,<br> 영업용(식당제외)으로 사용됩니다.(기본가정집 3kw)</p>
          	<p class="morePurpose">▼ 권장용량 보기</p>          
          </div>
          <div class="detailsPurpose">
	          <p>1. 15L,30L는 주로 손씻기, 설거지 등의  용도로 사용됩니다.</p>
	          <p>2. 50L는 1인 샤워 가능합니다.(성인남자 기준).</p>
	          <p>3. 80~100L는 1~2인 샤워 가능합니다.(성인남자 기준).</p>
          </div>
        </div> 
  	<div class ="infoHouse">
	 <h4>설치장소 정보*</h4>
	  <div class="optionBoiler">
     	<div class ="installPlace">
          <select class="form-control" name="floor" id="floor" data-mini="true" data-iconpos="none" style="vertical-align: middle; text-align-last: center;">
              <option value=""selected>설치 장소</option>
              <option value="화장실">화장실</option>
              <option value="싱크(세면)대">싱크대(세면대)</option>
          </select> 
        </div>          
        <div class ="kindsWaterHeater">
          <select class="form-control" name="kinds" id="kinds" data-mini="true" data-iconpos="none" style="vertical-align: middle; text-align-last: center;">
            <option value=""selected>온수기 종류</option>
                <option value="벽걸이형">벽걸이형(하향식)</option>
                <option value="바닥형">바닥형(상향식)</option>
          </select>
        </div>
        <div class ="volume">
          <select class="form-control" name="fuel" id="fuel" data-mini="true" data-iconpos="none" style="vertical-align: middle; text-align-last: center;">
            <option value=""selected>용량</option>
              <option value="15L">15L</option>
              <option value="30L">30L</option>
              <option value="50L">50L</option>
              <option value="80L">80L</option>
              <option value="100L">100L</option>  
          </select>
        </div>
      </div>
      </div>

      <div class="brand">
        <h4>견적받을 브랜드를 선택해주세요.*</h4>
        <div class="custom-control custom-radio">
           <label  class="custom-control-label">
            <input type="radio" class="custom-control-input" id="brand" name ="brand" value="경동나비엔">
            <img src="img/bgs_gd_logo.png">
           </label>
        </div>
        <div class="custom-control custom-radio">
          <label  class="custom-control-label">
            <input type="radio" class="custom-control-input" id="brand" name ="brand" value="귀뚜라미">
              <img src="img/bgs_gdrmi_logo.png">
          </label>
        </div>
        <div class="custom-control custom-radio">
          <label  class="custom-control-label">
              <input type="radio" class="custom-control-input" id="brand" name ="brand" value="나노에너텍">
             <img src="img/bgs_nanoenertec_logo.png">
            </label>
        </div>
        <div class="custom-control custom-radio">
          <label  class="custom-control-label">
            <input type="radio" class="custom-control-input" id="brand" name ="brand" value="대성셀틱">
            <img src="img/bgs_dsst_logo.png">
          </label>
         </div>         
        <div class="custom-control custom-radio">
          <label  class="custom-control-label">
            <input type="radio" class="custom-control-input" id="brand" name ="brand"value="린나이">
           <img src="img/bgs_rinnai_logo.png">
          </label>
        </div>
        <div class="custom-control custom-radio">
          <label  class="custom-control-label">
          <input type="radio" class="custom-control-input" id="brand" name ="brand" value="타사제품">
          <span>그 외 타사제품</span>
         </label>
        </div>
      </div>
      
      <div class = "inputAddr">
        <h4>위치설정*</h4>
          <p>주소를 입력하시면 합리적인 견적을 받아 볼 수 있습니다.</p>
        <div class="findBtn">
          <button class="btn" id="findBtn" type="button" onclick="sample2_execDaumPostcode()" value="주소검색">주소검색</button>          
          <div class="form-group">
           <input type="text" class="form-control" id="addr" name ="addr" placeholder="주소를 입력해주세요.">
           <input type="text" class="form-control" id="detailAddress" name ="detailAddress" placeholder="상세주소 입력. 예) XX아파트  101동 101호" size ="10" >
          </div>
        </div>
        <div class ="layer" id="layer">
         <button type="button" id="btnCloseLayer" onclick="closeDaumPostcode()">닫기</button>
        </div>
      </div>
      
      <div class="pNumber">
          <h4>연락처*</h4>
          <p>입력하신 전화번호는 고객님께서 먼저 연락하지 않는 이상, 절대 기사님들에게 전달되지 않습니다.</p>
          <input type="text" class="form-control" name="pNumber" id ="pNumber" placeholder="'-' 없이 작성해주세요.">
      </div>
      
      <div class="form-check">
        <input type="checkbox" class="form-check-input" id="agreeBox" name="agreeBox" onclick="checkAgree()">
        <label class="form-check-label" for="agreeBox">전체 약관에 동의합니다.</label>
        <p><a href="#">사용자 이용 약관 개인정보 취급방침 및 수집 이용동의 위치정보 서비스 이용약관 및 수집 이용동의</a></p>
      </div>
		</div>
	<button disabled type="button" id="requestInstall" onclick="inputCheck();">설치 견적요청하기</button>
    </form>
<div class="modal fade" id="checkUpload" tabindex="-1" role="dialog" aria-hidden="true">
	 <div class="vertical-alignment-helper">
	 	<div class="modal-dialog vertical-align-center">
	 	  <div id="checkType" class="modal-content">
			<div class="modal-header panel-heading">
			<button type="button" class="close" data-dismiss="modal">
				<span aria-hidden="true">&times</span>
				<span class="sr-only">Close</span>
			</button>
				<h4 class="modal-title">확인메시지</h4>
			</div> 	
	 		<div id="checkUploadMessage" class="modal-body">
	 		</div>
	 	<div class="modal-footer">
	 		<button type="button" class="btn btn-primary" id="modal-btn-yes">네</button>
	 		<button type="button" class="btn btn-default" id="modal-btn-no">아니오</button>
	 	</div>
	 	</div>
	 </div>
	 </div>
</div>  
<div class="modal fade" id="checkModal" tabindex="-1" role="dialog" aria-hidden="true">
	 <div class="vertical-alignment-helper">
	 	<div class="modal-dialog vertical-align-center">
	 	  <div id="checkType" class="modal-content">
			<div class="modal-header panel-heading">
			<button type="button" class="close" data-dismiss="modal">
				<span aria-hidden="true">&times</span>
				<span class="sr-only">Close</span>
			</button>
				<h4 class="modal-title">확인 메시지</h4>
			</div> 	
	 		<div id="checkMessage" class="modal-body">
	 		</div>
	 	<div class="modal-footer">
	 		 <button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
	 	</div>
	 	</div>
	 </div>
	 </div>
</div>

<%
	String messageContent = null;
	if(session.getAttribute("messageContent") != null){
		messageContent = (String) session.getAttribute("messageContent");
	}
	String messageType = null;
	if(session.getAttribute("messageType") != null){
		messageType = (String) session.getAttribute("messageType");
	}
	if(messageContent != null){
	%>
	<div class="modal fade" id="messageModal" tabindex="-1" role="dialog" aria-hidden="true">
	 <div class="vertical-alignment-helper">
	 	<div class="modal-dialog vertical-align-center">
	 		<div class="modal-content">
			<div class="modal-header panel-heading">
			<button type="button" class="close" data-dismiss="modal">
				<span aria-hidden="true">&times</span>
				<span class="sr-only">Close</span>
			</button>
			<h4 class="modal-title">
				<%= messageType %>
			</h4>
			</div> 	
	 	<div class="modal-body">
	 		<%= messageContent %>
	 	</div>
	 	<div class="modal-footer">
	 	 <button type="button" class="btn btn-primary" data-dismiss="modal">확인</button>
	 	</div>
	 	</div>
	 </div>
	 </div>
	 </div>
	 
	 <script>
	 $('#messageModal').modal("show");
	 </script>	

	 <%
	 	session.removeAttribute("messageContent");
		session.removeAttribute("messageType");
	 	}
	 %>
</div>
	     
<script src="https://t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
  
  var element_layer = document.getElementById('layer');
function closeDaumPostcode() {
    // iframe을 넣은 element를 안보이게 한다.
    element_layer.style.display = 'none';
}

function sample2_execDaumPostcode() {

    new daum.Postcode({
        oncomplete: function(data) {
            var addr = ''; 
            var extraAddr = ''; 
            if (data.userSelectedType === 'R') { 
              addr = data.roadAddress;
          } else { 
               addr = data.jibunAddress;
           }
           if(data.userSelectedType === 'R'){
                 if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                        extraAddr += data.bname;
                 }
                    if(data.buildingName !== '' && data.apartment === 'Y'){
                     extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                 }
                   if(extraAddr !== ''){
                       extraAddr = ' (' + extraAddr + ')';
                   }
                   document.getElementById("addr").value = extraAddr;
               } else {
                   document.getElementById("addr").value = '';
                }
            document.getElementById("addr").value = addr;
            document.getElementById('addr').value = (addr +' '+ extraAddr);
            document.getElementById("detailAddress").focus();

            element_layer.style.display = 'none';
            },

            width : '100%',
            height : '100%',
			maxSuggestItems : 5
        }).embed(element_layer);

        // iframe을 넣은 element를 보이게 한다.
        element_layer.style.display = 'block';

        // iframe을 넣은 element의 위치를 화면의 가운데로 이동시킨다.
        initLayerPosition();
    }
    function initLayerPosition(){
        var width = 350; //우편번호서비스가 들어갈 element의 width
        var height = 650; //우편번호서비스가 들어갈 element의 height
        var borderWidth = 2; //샘플에서 사용하는 border의 두께

        // 위에서 선언한 값들을 실제 element에 넣는다.
        element_layer.style.width = width + 'px';
        element_layer.style.height = height + 'px';
        element_layer.style.border = borderWidth + 'px solid';
        // 실행되는 순간의 화면 너비와 높이 값을 가져와서 중앙에 뜰 수 있도록 위치를 계산한다.
        element_layer.style.left = (((window.innerWidth || document.documentElement.clientWidth) - width)/2 - borderWidth) + 'px';
        element_layer.style.top = (((window.innerHeight || document.documentElement.clientHeight) - height)/2 - borderWidth) + 'px';
    }
 
 
</script>
 
</body>
</html>