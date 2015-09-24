$(function(){
	var width = $(window).width();
	var height = $(window).height();
	$('#hi_change_pwd').on('click',function(){
		$('#change_pop_up').css({
			left:(width-$('#change_pop_up').width())/2,
			top:(height-$('#change_pop_up').height())/2,
			display:'block'
		});
		$('#shade').css({
			width:width,
			height:height
		});
	});
	$(window).scroll(function(){
		if($('#change_pop_up').css('display') == 'block')
		 $(window).scrollTop(0);
	});

	$('#change_pop_up .change_close').on('click',function(){
		$('#change_pop_up').css({
			display:'none'
		});
		$('#shade').css({
			width:0,
			height:0
		});
	});

});