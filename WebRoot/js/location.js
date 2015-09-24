$(function(){
	//modify页面返回到index页面制定位置
    var hash = window.location.hash;
    window.location.hash = '';

    if(hash){ 
        $('#content .content_left ul li').removeClass('content_add');
        $('#content .content_left ul li').eq(2).addClass('content_add');
        $('#content .content_right .cont').css('display','none');
        $('#content .content_right .cont').eq(1).css('display','block');
    }
});