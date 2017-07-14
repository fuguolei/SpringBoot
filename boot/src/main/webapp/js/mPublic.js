// verif_screen(init)
$(function(){
    init();
    progressBar();
})
$(window).on('resize',init)
$('.menu-btn').on('click',function(){
    var This=$(this),btn=$('.menu-overlay-outer'),menu=$('#menu'),menu_overlay=btn.children('.menu-overlay'),j_matrixLeft=$('.j_matrixLeft');
    if(btn.is(".open")){
        j_matrixLeft.animate({'opacity':'0'},{
            duration: 300, queue: false, complete: function() {
                $(this).css({'transform':'matrix(1, 0, 0, 1, 0, 0)'})
                menu.css({'visibility':'hidden','opacity':0});
            }
        })
        menu_overlay.animate({
                width: '3.44rem',
                height: '3.4rem',
                marginTop: 0,
                marginRight: 0
            },{
                duration: 400, queue: false, complete: function() {
                    btn.removeClass('open');This.removeClass('close');
                }
            }
        );
    }else{
        changeBackground(btn,menu,menu_overlay,This,400,function(){
            var len=j_matrixLeft.length,obj=[];
            for(var j=0;j<len;j++){
                obj[j]={n:40,o:0};
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
function loading(){
    if(!document.getElementById('loading')){
        // var element='<div id="loading"><img src="/img/load.gif"/><p>正在加载中…</p></div>';
        var element='<div id="loading"><img src="/img/load.gif"/></div>';
        $(document.body).append(element);
    }
    return $('#loading');
}
/**
 * 分页
 * @param element 元素
 * @returns {object}
 */
function pageSet(page){
    page['totalPage']=Math.ceil(page['totalCount']/page['pageSize']);
    return page;
}
function ajax(config){
    $.ajax({
        url:config['url'],
        method:config['method']||'GET',
        data:config['data']||'',
        dataType:'json',
        success:config['success'],
        error:config['error'] ||function(XMLHttpRequest, textStatus, errorThrown){
            alertPrompt(-1,"系统获取异常");
            console.log(XMLHttpRequest.status);
            console.log(XMLHttpRequest.readyState);
            console.log(textStatus);
            return false;
        }
    });
}
/**
 * 消息弹出框
 * @returns
 */
function alertPrompt(state,str){
    var style=state==0?'iconSuccess':'iconError';
    var elemnt=$('#prompt');
    if(elemnt.length>0){
        elemnt.show().find('p').text(str);
    }else{
        $('body').append('<div id="prompt" style="display:block;"><div class="'+style+'"></div><p>'+str+'</p></div>');        
    }
    setTimeout(function(){
        $('#prompt').fadeOut();
    },2000)
}
function init(orientation){
    var scale = window.devicePixelRatio;
    scale = scale > 1 ? 1 / scale : 1;
    $('meta[name=viewport]').attr('content', "width=device-width,initial-scale=" + scale + ",minimum-scale=" + scale + ",maximum-scale=" + scale + ",user-scalable=no");

    var ohtml = $("html");
    var iWidth = $(window).width();
    ohtml.css("font-size", iWidth / 15 + 'px');
}
//验证横屏或竖屏,竖屏返回true,横屏返回false
function verif_screen(callback){
    var supportOrientation = (typeof window.orientation === 'number' && typeof window.onorientationchange === 'object');
    var init = function(){
        var orientation='';
        var updateOrientation = function(){
            if(supportOrientation){
                orientation = window.orientation;
                switch(orientation){
                    case 90:
                    case -90:
                        orientation = false;
                        break;
                    default:
                        orientation = true;
                        break;
                }
            }else{
                orientation = (window.innerWidth > window.innerHeight) ? true : false;
            }
            callback(orientation);
        };
        if(supportOrientation){
            window.addEventListener('orientationchange',updateOrientation,false);
        }else{
            //监听resize事件
            window.addEventListener('resize',updateOrientation,false);
        }
        updateOrientation();
    };
    init();
}