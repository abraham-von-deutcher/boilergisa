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
                   document.getElementById("address1").value = extraAddr;
               } else {
                   document.getElementById("address1").value = '';
                }
            document.getElementById("address1").value = addr;
            document.getElementById('address1').value = (addr +' '+ extraAddr);
            document.getElementById("address2").focus();

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