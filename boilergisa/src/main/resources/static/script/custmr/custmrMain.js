$(document).ready(function () {
	
	$('.leftHeader button').on('click', function(){
		$('.overlay').addClass('active');
		$('.collapse.in').toggleClass('in');
		$('a[aria-expanded=true]').attr('aria-expanded', 'false');
	});
	
	$('#closeSidebar,.overlay').on('click', function(){
		// hide sidebar
	$('.collapse.in').toggleClass('in');
		// hide overlay
	$('.overlay').removeClass('active');
	});
});