$(function(){
	//验证姓名
	$('#input_name').blur(function(){
		if(!$('#input_name').val()){
			$(this).parent().find('input.name_warning').val('请填写姓名！！').css({
				display:'block',
				left:'330px'
			});
		}else{
			$(this).parent().find('input.name_warning').css({
				display:'none'
			});
		}	
	});
	//验证民族
	$('#input_nation').blur(function(){
		if(!$('#input_nation').val()){
			$(this).parent().find('input.name_warning').val('请填选民族！').css({
				display:'block',
				left:'330px'
			});
		}else{
			$(this).parent().find('input.name_warning').css({
				display:'none'
			});
		}

	});
	//验证身份证
	$('#input_id').blur(function(){
		if(!$('#input_id').val()){
			$(this).parent().find('input.name_warning').val('请填选身份证！').css({
				display:'block',
				left:'330px'
			});
		}else{
			$(this).parent().find('input.name_warning').css({
				display:'none'
			});
		}	
	});
	//验证从事专业方向
	$('#input_dir').blur(function(){
		if(!$('#input_dir').val()){
			$(this).parent().find('input.name_warning').val('请填选方向！').css({
				display:'block',
				left:'330px'
			});
		}else{
			$(this).parent().find('input.name_warning').css({
				display:'none'
			});
		}	
	});
	//取消退出
	//详情页面退出
	$('#content .save div .abolish').on('click',function(){
		window.opener=null;
		window.open('','_self');
		window.close();
	}); 
	//单击修改按钮
	$('#content .save div .keep').on('click',function(){
		if(!$('#input_name').val()){
			alert('请输入姓名');
			return false;
		};
		if(!$('#input_nation').val()){
			alert('请输入民族');
			return false;
		};

		if(!$('#input_birth').val()){
			alert('请输入出生年月');
			return false;
		};
		if(!$('#input_id').val()){
			alert('请输入身份证');
			return false;
		};
		if(!$('#input_work').val()){
			alert('请输入工作时间');
			return false;
		};
		if(!$('#input_school').val()){
			alert('其输入来校时间');
			return false;
		};

	});
});