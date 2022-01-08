<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page trimDirectiveWhitespaces="true"%>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ko">
<head>

<meta http-equiv="Content-type" content="text/css; charset=UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">

<link rel="stylesheet" href="https://unpkg.com/swiper/swiper-bundle.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
<link rel="stylesheet" href="/resources/dist/css/customer/resultRequest.css">
<link rel="stylesheet" href="/resources/dist/css/bgs.css">
<script rel="stylesheet" href="/resources/bower_components/lightbox/lightbox.css"></script>

<title>보일러 견적요청</title>

<script src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
<script src="https://unpkg.com/swiper/swiper-bundle.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/handlebars.js/4.1.2/handlebars.min.js" integrity="sha256-ngJY93C4H39YbmrWhnLzSyiepRuQDVKDNCWO2iyMzFw=" crossorigin="anonymous"></script>
<script type="text/javascript" src="/resources/bower_components/bootstrap/bootstrap.js"></script>
<script type="text/javascript" src="/resources/dist/js/requestQuote.js"></script>

<script type="text/javascript" src="/resources/bower_components/lightbox/lightbox.js"></script>


</head>
<body>
 <header data-role="header" id ="bgHeader">
  <div class="backHeader"><img src="/resources/dist/img/arrow-back.png" class="history-back" onclick="history.back();"></div>
  <div class="centerHeader"><h3>보일러 의뢰작성</h3></div>
  <div class="nextHeader"></div>
 </header>
 <input type="file" name="multifiles" id="files" multiple="multiple" style="display:none;"/>
 
<form id ="reqUpload" name="reqUpload" method="post" action="${path}/request/requestBoiler" enctype="multipart/form-data">
   <div class="main">
	<div class="uploadPhotos">
	 <div class="uploadContent">
	  	<span class="addPhotos">
	  		<img src="/resources/dist/img/add_img_white.png">사진업로드
	  	</span>
		<div class="uploadContext">
		 <h4>설치관련 사진 추가</h4>
		 <p>보일러,배관,분배기 등 사진을 등록하시면<br>업자분들에게 더욱더<br>정확한 견적을 받을 수 있습니다.</p>
		</div>
	 </div>
		<ul class="slider-nav">
		 <li><img src="/resources/dist/img/add_img_white.png"></li>
		 <li><img src="/resources/dist/img/add_img_white.png"></li>
		 <li><img src="/resources/dist/img/add_img_white.png"></li>
		 <li><img src="/resources/dist/img/add_img_white.png"></li>
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
	<input type="hidden" name="userID" value="${userID.userID}">	
	<input type="hidden" name="equipment" value="보일러">
  	<div class="form-group">
	  <h4>사진설명</h4>
	  <textarea class="form-control" id="explainPhotos" name ="explainPhotos" maxlength="500" rows="3" placeholder="설치 장소에 구체적인 내용이나 요청을 적어주세요.  예) 보일러 연통이 길게 나갑니다.(500자)"></textarea>
  	</div>
  	<div class="btn-group btn-group-toggle" data-toggle="buttons">
     <h4>거주지 유형*</h4>
     <label class="btn btn-warning btn-notActive active"><input type="radio" name="typeHouse" value="아파트">아파트</label>
     <label class="btn btn-warning btn-notActive"><input type="radio" name="typeHouse" value="빌라">빌라</label>
     <label class="btn btn-warning btn-notActive"><input type="radio" name="typeHouse" value="주택">주택</label>
     <label class="btn btn-warning btn-notActive"><input type="radio" name="typeHouse" value="원룸">원룸</label>
  	</div>
  	<div class ="infoHouse">
     <h4>거주지 정보*</h4>
     <div class="form-group">
       <label for="spaceHouse"><span>평수 : </span>
       <input type="text" class="form-control" id ="spaceHouse" name ="spaceHouse" style="text-align:center; width:50px;" placeholder="0" maxlength="2" onkeyup="recommandSpace();"><span>평형</span></label>
     </div>
      <input type="text" class ="resultSpace" id="resultSpace" name="resultSpace" value="평형를 입력하시면 적합한 용량의 보일러를 추천해드립니다." readonly style="border:none; outline:none;padding:4px;
      ">
     <div class="optionBoiler">
          <div class ="floor">
            <select class="form-control" name="floor" id="floor" data-mini="true" data-iconpos="none" style="vertical-align: middle; text-align-last: center;">
                <option value="" selected>보일러위치</option>
                <option value="같은층">같은 층</option>
                <option value="다른층">다른 층</option>
            </select> 
          </div>          
          <div class ="kinds">
            <select class="form-control" name="kinds" id="kinds" data-mini="true" data-iconpos="none" style="vertical-align: middle; text-align-last: center;">
              <option value="" selected>보일러종류</option>
              <option value="일반">일반</option>
              <option value="콘덴싱">콘덴싱</option>
            </select>
          </div>
          <div class ="fuel">
            <select class="form-control" name="fuel" id="fuel" data-mini="true" data-iconpos="none" style="vertical-align: middle; text-align-last: center;">
              <option value="" selected>연료 유형</option>
              <option value="LNG">LNG(도시가스)</option>
              <option value="LPG">LPG</option>
            </select>
          </div>
      </div>
      <p class="prerequisite">* 보일러가 설치된 곳에 배수구가 있으면,<br>꼭! 콘덴싱 보일러를 설치해야합니다.</p>
      </div>

      <div class="brand">
        <h4>견적받을 브랜드를 선택해주세요.*</h4>
        <div class="custom-control custom-radio">
           <label  class="custom-control-label">
            <input type="radio" class="custom-control-input" id="brand" name ="brand" value="경동나비엔">
            <img src="/resources/dist/img/bgs_gd_logo.png">
           </label>
        </div>
        <div class="custom-control custom-radio">
          <label  class="custom-control-label">
            <input type="radio" class="custom-control-input" id="brand" name ="brand" value="귀뚜라미">
              <img src="/resources/dist/img/bgs_gdrmi_logo.png">
          </label>
        </div>
        <div class="custom-control custom-radio">
          <label  class="custom-control-label">
            <input type="radio" class="custom-control-input" id="brand" name ="brand" value="대성셀틱">
            <img src="/resources/dist/img/bgs_dsst_logo.png">
          </label>
         </div>
         <div class="custom-control custom-radio">
          <label  class="custom-control-label">
              <input type="radio" class="custom-control-input" id="brand" name ="brand" value="롯데이앤엠">
             <img src="/resources/dist/img/bgs_rotte_logo.png">
            </label>
        </div>
        <div class="custom-control custom-radio">
          <label  class="custom-control-label">
            <input type="radio" class="custom-control-input" id="brand" name ="brand"value="린나이">
           <img src="/resources/dist/img/bgs_rinnai_logo.png">
          </label>
        </div>
        <div class="custom-control custom-radio">
          <label  class="custom-control-label">
          <input type="radio" class="custom-control-input" id="brand" name ="brand" value="알토엔대우">
           <img src="/resources/dist/img/bgs_altoendaewoo_logo.png">
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
	<button disabled type="button" id="requestInstall" onclick="requestInputCheck();">설치 견적요청하기</button>
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

<script id="fileTemplate" type="text/x-handlebars-template">
	<li class="mailbox-attachment-info swiper-slide">
		<div class="mailbox-attachment-info">
			<a href="{{fullName}}" class="btn btn-default btn-xs float-right delBtn">X
				<i class="fa fa-fw fa-remove"></i>
			</a>
        </div>
		<span class="mailbox-attachment-icon has-img">
            <img src="{{imgSrc}}" alt="Attachment">
			<a href="{{originalFileUrl}}" class="mailbox-attachment-name">
				<i class="fa fa-paperclip"></i> {{originalFileName}}
            </a>
        </span>
        
    </li>
</script>

<script>
 $(document).ready(function () {
        // 게시글 저장 버튼 클릭 이벤트 처리
        $("#writeForm").submit(function (event) {
            event.preventDefault();
            var that = $(this);
            filesSubmit(that);
        });
        // 파일 삭제 버튼 클릭 이벤트
        $(document).on("click", ".delBtn", function (event) {
            event.preventDefault();
            var that = $(this);
            deleteFileWrtPage(that);
        });
});

</script>

<script>
//Handlebars 파일템플릿 컴파일
var fileTemplate = Handlebars.compile($("#fileTemplate").html());

var fileAdd = $("#files");
$(".addPhotos").on("click", function(){
	fileAdd.click();
})


// 첨부파일 add 이벤트 처리 : 파일업로드 처리 -> 파일 출력
fileAdd.on("change", function(e) {   
	
    var file = e.target.files;
    var files = Array.prototype.slice.call(file);
    
    var formData = new FormData();
    for(var i = 0 ; i < files.length ; i++){
    	if(files.length < 6){
    		var getFiles = [];
    		$('.addSlider ul li div a').each(function(){
    			getFiles.push($(this).attr("href"));
    		});
    		console.log(getFiles);
    		
    		if(getFiles.length != null){
    			$.post(
    				"/request/file/deleteAll"
    				,{fileNames: getFiles}
    				, function(){
    					
            	});
    		}
    		formData.append("files", files[i]);
    		$('.swiper-wrapper').empty();
        	//console.log(getFiles);
    		
    	}else{
        	$('#checkModal').modal("show");
    		$('#checkMessage').html('5개까지 업로드 가능합니다.');
    		return false;
        }
    }
    uploadFile(formData);
	
    //멀티플파일 널로 만들기
});

// 파일 업로드 AJAX 통신
function uploadFile(formData){	
    $.ajax({    	
    	url: "/request/file/upload",
    	 data: formData,
    	 enctype: 'multipart/form-data',
    	 dataType: "json",
         processData: false,
         contentType: false,
         type: "POST",
         success: function(data){
        	 for(var i = 0 ; i < data.length ; i++){
        	 	printFiles(data[i]); // 첨부파일 출력 메서드 호출
        	 	//getFiles.push(data[i]);
        	}
         }
     });
 }

// 첨부파일 출력
function printFiles(data) {
    // 파일 정보 처리
    var fileInfo = getFileInfo(data);
    // Handlebars 파일 템플릿에 파일 정보들을 바인딩하고 HTML 생성
    var html = fileTemplate(fileInfo);
    // Handlebars 파일 템플릿 컴파일을 통해 생성된 HTML을 DOM에 주입
    $(".swiper-wrapper").append(html);
    
    // 이미지 파일인 경우 파일 템플릿에 lightbox 속성 추가
    if (fileInfo.fullName.substr(12, 10) === "boilergisa_") {
        // 마지막에 추가된 첨부파일 템플릿 선택자
        var that = $(".swiper-wrapper li").first();
        // lightbox 속성 추가
        that.find(".mailbox-attachment-name").attr("data-lightbox", "uploadImages");        
    }
}

function filesSubmit(that) {
    var str = "";
    $(".swiper-wrapper .delBtn").each(function (index) {
        str += "<input type='hidden' name='files[" + index + "]' value='" + $(this).attr("href") + "'>"
    });
    that.append(str);
    that.get(0).submit();
}

// 파일 삭제(입력페이지) : 첨부파일만 삭제처리
function deleteFileWrtPage(that) {
    var url = "/request/file/delete";
    deleteFile(url, that);
}
//파일 삭제 AJAX 통신
function deleteFile(url, that) {
    $.ajax({
        url: url,
        type: "post",
        data: {fileName: that.attr("href")},
        dataType: "text",
        success: function (result) {
            if (result === "DELETED") {
				var delfile = that.attr("href");
        		console.log(delfile);
        		$('a[href$="' + delfile+ '"]').parents("li").remove();
        		$('#checkModal').modal("show");
        		$('#checkMessage').html('삭제되었습니다.');
            }
        }
    });
}

// 파일 정보 처리
function getFileInfo(fullName){
    var originalFileName;   // 화면에 출력할 파일명
    var imgSrc;             // 썸네일 or 파일아이콘 이미지 파일 출력 요청 URL
    var originalFileUrl;    // 원본파일 요청 URL
    var uuidFileName;       // 날짜경로를 제외한 나머지 파일명 (UUID_파일명.확장자)

    // 이미지 파일이면
    if (checkImageType(fullName)) {
        imgSrc = "/request/file/display?fileName=" + fullName; // 썸네일 이미지 링크
        uuidFileName = fullName.substr(23);
        var originalImg = fullName.substr(0, 12) + fullName.substr(23);
        // 원본 이미지 요청 링크
        originalFileUrl = "/request/file/display?fileName=" + originalImg;
    }else {
        return false;
    }
    originalFileName = uuidFileName.substr(uuidFileName.indexOf("_") + 1);

    return {originalFileName: originalFileName, imgSrc: imgSrc, originalFileUrl: originalFileUrl, fullName: fullName};
}

// 이미지 파일 유무 확인
function checkImageType(fullName) {
    var pattern = /jpg$|gif$|png$|jpge$/i;
    return fullName.match(pattern);
}
</script>
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