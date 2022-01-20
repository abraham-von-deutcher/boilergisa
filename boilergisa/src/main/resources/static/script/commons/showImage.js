 $(document).ready(function(){
	
	var swiper = new Swiper(".swiper-container", {
        pagination: {
	          el: ".swiper-pagination",
	          type: "fraction",
        },		
	});
	
	swiper.on('slideChange',function(){
		console.log(swiper.realIndex);
		
	})
	
	
    
    $("#delBtn").click(function(){
	
	    var curIdx = $(".swiper-wrapper").attr('id');
        console.log(curIdx);
	})

})

// swiper-slide-active 의 값이 파일인덱스 값이되겠다.