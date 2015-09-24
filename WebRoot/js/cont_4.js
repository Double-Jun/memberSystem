$(function(){
	$('.form_age').focus(function(){
		$('.hidden').val('请输入0到99的数字').css({
			color:'#333',
			fontSize:'14px'
		});
	});
	$('.form_age').blur(function(){
		$('.hidden').val('');
	});



	$('.sub').click(function(){
		var reg = /\b[0-9]{1,2}\b/;
		var text1 = $('.form_age').eq(0).val();
		var text2 = $('.form_age').eq(1).val();
		var result1 = reg.test(text1);
		var result2 = reg.test(text2);
		if(!text1 && result2){
			$('.form_age').eq(0).val(0);
		}
		if(result1&&!text2){
			$('.form_age').eq(1).val(99);
		}	
		text1 = $('.form_age').eq(0).val();
		text2 = $('.form_age').eq(1).val();
		result1 = reg.test(text1);
		result2 = reg.test(text2);
		if(text1 == '' && text2 == ''){
			return ;
		}
		if(!(result1 && result2)){
				$('.hidden').val('请输入0到99的数字！').css({
						color:'red'
					});
				$('.form_age').eq(0).val('');
				$('.form_age').eq(1).val('');
				return false;
		}else if(text1 > text2){
				$('.hidden').val('年龄由小到大输入！').css({
						color:'red'
					});	
				$('.form_age').eq(0).val('');
				$('.form_age').eq(1).val('');
				return false;
		}
	});

	//鼠标放在表格上变色
	$('#cont_4 table').eq(0).find('tr').each(displayColor);
	function displayColor(){
		if($(this).index() != 0){
			$(this).hover(function(){
				$(this).css('background','#CCFFFF');
			},function(){
				$(this).css('background','#FBFBFB');
			});		
		}
	}

});