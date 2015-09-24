$(function(){
	//栏目选项卡
	$('#content .content_left ul li').on('click',function(){
		if($(this).index() == 0){
			return false;
		}
		var index = $(this).index() - 1;
		$(this).parent().children().removeClass('content_add');
		$(this).addClass('content_add');
		$(this).parent().find('a').removeClass('content_add_a').addClass('li_a');
		$(this).find('a').removeClass('li_a').addClass('content_add_a');
		
	});

	//栏目选项卡禁止拖拽
	$('#content .content_left ul li,#hi_change_pwd').on('dragstart',function(){
		return false;
	});

	//鼠标放在表格上变色
	$('#cont_1 table').eq(0).find('tr').each(displayColor);
	$('#cont_2 table').eq(0).find('tr').each(displayColor);
	$('#cont_3 table').eq(0).find('tr').each(displayColor);
	function displayColor(){
		if($(this).index() != 0){
			$(this).hover(function(){
				$(this).css('background','#CCFFFF');
			},function(){
				$(this).css('background','#FBFBFB');
			});		
		}
	}

	//全选按钮
	var checkAll = $('#cont_2 input.check_all');
	var check_son = $('#cont_2 input.check_son');
	checkAll.on('click',function(){
		check_son.prop('checked',this.checked);
	});
	check_son.on('click',function(){
		if($('#cont_2 input.check_son:checked').length == check_son.length){
			checkAll.prop('checked',true);
		}else{
			checkAll.prop('checked',false);
		}
	});

	//删除会员
	$('.delete_').on('click',function(){
		var result = confirm("确定删除吗？");
		if(result){
			$(this).parent().parent().remove();		
		}else{
			return false;
		}

	});
	$('#delete_all').on('click',function(){
		var result = confirm("确定删除吗？");
		if(!result){		
			return false;
		}
	});
	//详情页面退出
	$('#content .save div .abolish a').on('click',function(){
		window.opener=null;
		window.open('','_self');
		window.close();
	}); 
	//cont_3页面年龄的验证
	$('.age_number').focus(function(){
		$('#age_input').val('请输入0到99的数字').css({
			color:'#333',
			fontSize:'14px'
		});
	});
	$('.age_number').blur(function(){
		$('#age_input').val('');
	});
	//点击查询对年龄的验证
	$('.sub2').click(function(){
		var reg = /\b[0-9]{1,2}\b/;
		var text1 = $('.age_number').eq(0).val();
		var text2 = $('.age_number').eq(1).val();
		var result1 = reg.test(text1);
		var result2 = reg.test(text2);
		if(!text1 && result2){
			$('.age_number').eq(0).val(0);
		}
		if(result1&&!text2){
			$('.age_number').eq(1).val(99);
		}	
		text1 = $('.age_number').eq(0).val();
		text2 = $('.age_number').eq(1).val();
		result1 = reg.test(text1);
		result2 = reg.test(text2);
		if(text1 == '' && text2 == ''){
			return ;
		}
		if(!(result1 && result2)){
				$('#age_input').val('请输入0到99的数字！').css({
						color:'red'
					});
				$('.age_number').eq(0).val('');
				$('.age_number').eq(1).val('');
				return false;
		}else if(text1 > text2){
				$('#age_input').val('年龄由小到大输入！').css({
						color:'red'
					});	
				$('.age_number').eq(0).val('');
				$('.age_number').eq(1).val('');
				return false;
		}
	});
	
	//日历控件
	$('#input_birth,#input_school,#input_work').datepicker({
		changeYear: true, 
		yearRange: '1920:2020',
		dateFormat : 'yy-mm-dd',
		dayNames : ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
		dayNamesShort : ['星期日','星期一','星期二','星期三','星期四','星期五','星期六'],
		dayNamesMin : ['日','一','二','三','四','五','六'],
		monthNames : ['一月','二月','三月','四月','五月','六月','七月','八月','九月','十月','十一月','十二月'],
		monthNamesShort : ['一','二','三','四','五','六','七','八','九','十','十一','十二']

	});

});












