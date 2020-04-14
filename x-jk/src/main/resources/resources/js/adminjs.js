$(document).ready(function(){
	//缩进
	$(".shrink").click(function(){
		if($(this).hasClass("shrink_on")){
			$(this).removeClass("shrink_on")
			$(".head_left").css({"left":"220px"})
			$(".logo").removeClass("shrink_logo")
			$(".side").removeClass("side_on")
			$(".admin-body").removeClass("admin-body_on")
			console.log($(".this").parents('.li-nav-item').find("dl").length)
			if($(".this").parents('.li-nav-item').find("dl").length > 0){
				$(".one").removeClass('this')
			}
			$(".yiji").removeClass("oneclass")
		}else{
			$(this).addClass("shrink_on")
			$(".head_left").css({"left":"60px"})
			$(".logo").addClass("shrink_logo")
			$(".side").addClass("side_on")
			$(".admin-body").addClass("admin-body_on")	
			$('.two').attr('style', '');
			$(".yiji").addClass("oneclass")
		}
	})
	
	//菜单展开
	$(".li-nav-item .one").click(function(){
		if($(".shrink").hasClass("shrink_on")){
		}else{
			$(this).parents(".li-nav-item").siblings().find("dl").slideUp(100);
		}
		if($(this).attr("admin-href")){
			if($(this).attr("admin-href")!==$(".admin-iframe").attr("src")){
				$(".admin-iframe").attr("src",$(this).attr("admin-href"))
				$(this).addClass("this")
				$(this).parents(".li-nav-item").siblings().find('a').removeClass('this')
			}
		}else{
			if($(".shrink").hasClass("shrink_on")){
			}else{
				$(this).parents(".li-nav-item").find("dl").slideToggle(100);
			}
			
		}
	})
	
	$(".two a").click(function(){
		if($(this).attr("admin-href")!==$(".admin-iframe").attr("src")){
			$(".admin-iframe").attr("src",$(this).attr("admin-href"))
			$(".li-nav-item").find('a').removeClass('this')
			$(this).addClass("this")
			if($(".shrink").hasClass("shrink_on")){
				$(this).parents(".li-nav-item").find(".one").addClass('this')
			}
		}
	})
	
	$(".li-nav-item").hover(function(){
		$(".nav-bar").show()
		$(".nav-bar").css({"top": $(this).offset().top - 50 + "px","height": "55px","opacity": "1"})
	},function(){
    	$(".nav-bar").hide()
    	$(".nav-bar").css({"top": $(this).offset().top - 50 + "px","height": "55px","opacity": "0"})
	})
	
	$('#refresh').click(function () {
        var currUrl = $('#mainFrame').attr('src');
        $('#mainFrame').attr('src', currUrl);
    });

});
