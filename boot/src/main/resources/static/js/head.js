/**
 * Created by Administrator on 2017/6/1.
 */
var userAgent = navigator.userAgent.toLowerCase();
jQuery.browser = {
    version: (userAgent.match(/.+(?:rv|it|ra|ie)[\/: ]([\d.]+)/) || [])[1],
    safari: /webkit/.test(userAgent),
    opera: /opera/.test(userAgent),
    msie: (/msie/.test(userAgent)||/rv:/.test(userAgent)) && !/opera/.test(userAgent),
    mozilla: /mozilla/.test(userAgent) && !/(compatible|webkit)/.test(userAgent)
};
var allowShow_TopBtn=true;
$(function(){
    progressBar();
    floatRight();
    if($.browser.msie){
        jQuery('[placeholder]').focus(function() {
            var input = jQuery(this);
            if (input.val() == input.attr('placeholder')) {
                input.val('');
                input.removeClass('placeholder');
            }
        }).blur(function() {
            var input = jQuery(this);
            if (input.val() == '' || input.val() == input.attr('placeholder')) {
                input.addClass('placeholder');
                input.val(input.attr('placeholder'));
            }
        }).blur().parents('form').submit(function() {
            jQuery(this).find('[placeholder]').each(function() {
                var input = jQuery(this);
                if (input.val() == input.attr('placeholder')) {
                    input.val('');
                }
            })
        });
    }
});
$('.menu-btn').on('click',function(){
    var This=$(this),btn=$('.menu-overlay-outer'),menu=$('#menu'),menu_overlay=btn.children('.menu-overlay'),j_matrixLeft=$('.j_matrixLeft');
    $('#right_float').hide();
    if(btn.is(".open")){
        allowShow_TopBtn=true;
        j_matrixLeft.animate({'opacity':'0'},{
            duration: 300, queue: false, complete: function() {
                $(this).css({'transform':'matrix(1, 0, 0, 1, 0, 0)'})
                menu.css({'visibility':'hidden','opacity':0});
            }
        })
        menu_overlay.animate({
                width: 100,
                height: 100,
                marginTop: 0,
                marginRight: 0
            },{
                duration: 400, queue: false, complete: function() {
                    btn.removeClass('open');This.removeClass('close');
                }
            }
        );
    }else{
        allowShow_TopBtn=false;
        changeBackground(btn,menu,menu_overlay,This,400,function(){
            var len=j_matrixLeft.length,obj=[];
            for(var j=0;j<len;j++){
                obj[j]={n:30,o:0};
            }
            recursionAnimation(0);
            function recursionAnimation(j){//菜单依次展现出来
                setTimeout(function(){
                    obj[j]['timer']=setInterval(function(){
                        if(Number(obj[j]['o'].toFixed(1))>=1){
                            j_matrixLeft.eq(j).css({'transform':'translate3D(0,0,0)','opacity':1})
                            clearInterval(obj[j]['timer']);
                            if(++j<len){
                                recursionAnimation(j);
                            }
                            return;
                        }
                        j_matrixLeft.eq(j).css({'transform':'translate3D( '+(obj[j]['n']-=3)+'px, 0,0)','opacity':obj[j]['o']+=.1})
                    },10)
                },10)
            }
        });
    }
    return false;
});
$('.subMail').on('click',function(){
    var input=$(this).siblings('input');
    var val=input.val();
    if(checkEmail(val)){
        ajax({
            url:'/subscribe/add.json?email='+val,
            method:'post',
            success:function(data){
                if(data.code==0){
                    messageShow('您已订阅成功！',data.data.email,'将会收到申博的最新资讯。')
                }else{
                    alertPrompt(data.code,data.msg)
                }
                
            }
        })
    }else{
        alertPrompt(-1,"邮件格式不正确");
    }
    return false;
});
function messageShow(message,email,other){
    addFullBg().show();
    var alertMessage=$('#alertMessage');
    if(alertMessage.length<=0){
        var element='';
        if(other){
            element='<div id="alertMessage"><h2>CONGRATULATE</h2><div class="message">'+message+'<br/><span>'+(email || '')+'</span>'+(other || '')+'</div><a href="#" class="close" id="closeAlert"></a></div>';
        }else{
            element='<div id="alertMessage"><h2>CONGRATULATE</h2><div class="message" style="text-align:center;">'+message+'<br/></div><a href="#" class="close" id="closeAlert"></a></div>';
        }
        $(document.body).append(element);
    }else{
        alertMessage.fadeIn().find('span').text(email);
    }
}
$(document.body).on('click','#closeAlert',function(){
    $('#alertMessage').fadeOut(300);
    addFullBg().fadeOut(300);
    return false;
});
$(window).on('resize',function(){
    var This=$('.menu-btn'),btn=$('.menu-overlay-outer'),menu=$('#menu'),menu_overlay=btn.children('.menu-overlay');
    if(btn.is(".open")){
        changeBackground(btn,menu,menu_overlay,This,0);
    }
    $('.mCustomScrollBox').css('max-height',$('.menu-scroll').innerHeight())
});
//首页头部meun点击后页面_鼠标滚动响应
var sctop=0;
addWhell(document,function(down,ev){
    if($('#menu').css('opacity')==1){
        var wHeight=$(window).height();
        var menu_inner=$('.menu_inner');
        var eHeight=menu_inner.innerHeight();
        var diff=eHeight-wHeight;
        menu_inner.css({'position':'relative','left':0});
        if(wHeight<eHeight){
            if(down){
                if(sctop<0)
                    sctop+=10;
            }else{//下
                if(Math.abs(diff)>Math.abs(sctop))
                    sctop-=10;
            }
        }else{
            if(down && sctop<0){
                sctop+=10;
            }
        }
        menu_inner.css({'top':sctop})
        if(ev.preventDefault){
            ev.preventDefault();
        }
        return false;
    }
});
//右侧浮动
function floatRight(){
    createFloatRight();
    EVscrollTop();
    function createFloatRight(){
        if($('#right_float').length<=0){
            var element=$('<div id="right_float" style="display:none;"><a href="javascript:void(0)" class="back_top" onclick="backTop()" target="_self"></a></div>');
            $("body").append(element);
        }
    }
    function EVscrollTop(){
        //当鼠标滚动，超过一屏，显示右侧浮动框
        var client_height=$(window).height();
        $(window).scroll(function(){
            if(!allowShow_TopBtn){
                return false;
            }
            var scrollTop=$(window).scrollTop();
            if(client_height/2 <= scrollTop){//开始添加样式
                $('#right_float').fadeIn(500);
            }else{
                $('#right_float').hide();
            }
        })
    }
}
//返回顶部
function backTop(){
    $('body,html').animate({scrollTop:"0"},500);
    return false;
}
//首页自适应_品牌设计和UI设计
function change_homeRowFirst(){
    // var wrap=$('.wrap');
    // var row_first=$('.row_first');
    // var h2_first=row_first.children('h2');
    // h2_first.css('left',row_first.children('.row_bgBoard').width()-(h2_first.width()/2));
    // row_first.css('height',777/(1440/row_first.width()));
    //
    // var row_second=$('.row_second');
    // var h2_second=row_second.children('h2');
    // h2_second.css('right',row_second.children('.row_bgBoard').width()-(h2_second.width()/2))
    // row_second.css('height',907/(1440/row_second.width()));

    $('.shiftUp').css('height',596/(496.98/$('.shiftUp:first-child').width()));
}
function changeBackground(btn,menu,menu_overlay,This,duration,callback){
    This.addClass('close')
    var btnSize = btn.width() / 2,
        dx = 2 * menu.width(),
        dy = 2 * menu.height(),
        size = Math.sqrt(dx * dx + dy * dy);
    btn.addClass('open');
    menu_overlay.animate({
        width: size,
        height: size,
        marginTop: -size / 2 + btnSize,
        marginRight: -size / 2 + btnSize
    },{ duration: duration, queue: false, complete: function() {
        menu.css({visibility: 'visible',opacity: 1});
        callback && callback();
    }
    });
}
function progressBar(){
    var element='<div id="overlay-bar"><div id="progress"></div><div class="loadLogo"><img src="/img/logo.png"/></div><div id="progressNum">1%</div></div>';
    $(document.body).append(element);
    var num=0,arr=$('img');
    var progress=$('#progress'),progressNum=$('#progressNum');
    arr.each(function(){
        var img=new Image();
        img.onload=img.onerror=function(){
            try{
                num++;
                var scale=Math.floor((num/arr.length)*100);
                progress.width(scale+'%');
                progressNum.text(scale+'%');
                if(scale==100){
                    $('#overlay-bar').animate({'opacity':0},{ duration: 300, queue: false, complete: function() {
                        $(this).hide();
                        $('#other-wrap').animate({'opacity':1});
                        try{
                            fontLoad && fontLoad();
                        }catch(err){}
                    }
                    });
                }
            }catch(e){
                console.log(e);
            }
        }
        img.src=$(this).attr('src');
    })
}
function shiftUp(top){
    var height=$(window).height();
    $('.shiftUp').each(function(){
        if($(this).css('opacity')==0){
            var etop=$(this).offset().top;
            if(top+height-240>=etop){
                var This=$(this),i=90,o=0;
                var timer=setInterval(function(){
                    This.css({'visibility':'inherit','opacity':o+=0.2});
                    This.css({'transform':'translateY('+(i/=2)+'%) translate3D( 0,0,0)'});
                    if(Number(i.toFixed(1))==0){
                        clearInterval(timer);
                        This.css({'transform': 'matrix(1, 0, 0, 1,0,0)'})
                    }
                },20)
            }
        }
    })
}