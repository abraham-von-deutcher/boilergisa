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
	
	
    
    $(document).on("click", ".delBtn", function (event) {
        event.preventDefault();
        var that = $(this);
        deleteFileWrtPage(that);
    });

})

// swiper-slide-active 의 값이 파일인덱스 값이되겠다.