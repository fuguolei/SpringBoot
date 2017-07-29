/**
 * Created by Administrator on 2017/5/31.
 */
 //form
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
function auto_submit(address,obj,method){
    method=method || 'POST';
    if(method.toLowerCase()=='get'){
        window.location.href=address+'?'+obj;
    }else{
        var frm=$('<form id="frmSubmit" action='+address+' method="post"></form>');
        for(var key in obj){
            frm.append('<input type="text" name="'+key+'" value="'+obj[key]+'">');
        }
        $('body').append(frm);
        $('#frmSubmit').submit();
    }
}


//event
function addEvent(obj,evName,evFn){
    window.fn1 = function(){
        evFn.call(obj);
    }
    if(obj.addEventListener){
        obj.addEventListener(evName,evFn,false);
    }else{
        obj.attachEvent('on'+evName,window.fn1);
    }
}
function addWhell(obj,fn){
    //判断是否为ff浏览器
    if(window.navigator.userAgent.toLowerCase().indexOf('firefox')!=-1){
        addEvent(obj,'DOMMouseScroll',evFn);
    }else{
        addEvent(obj,'mousewheel',evFn);
    }
    function evFn(ev){
        var down = 0;
        //查看是向上滚动还是向下滚动
        if(ev.wheelDelta){
            //在非FF下大于0就是向上小于0就是向下
            down = ev.wheelDelta > 0 ? true : false;
        }else{
            //在FF下大于0就是向下小于0就是向上
            down = ev.detail < 0 ? true : false;
        }
        //判断是否有fn并且fn必须是个函数
        if(fn && typeof fn === 'function'){
            //调用fn将查看出来方向值传给fn的第一个参数。
            fn(down,ev);
        }
        //阻止浏览器默认行为。
        // if(ev.preventDefault){
        //     ev.preventDefault();
        // }
        return false;
    }
}

//fn
/**
 * 分页
 * @param element 元素
 * @returns {object}
 */
function pageSet(page){
    page['totalPage']=Math.ceil(page['totalCount']/page['pageSize']);
    return page;
}
function loading(){
    if(!document.getElementById('loading')){
        var element='<div id="loading"><img src="/img/load.gif"/><p>正在加载中…</p></div>';
        $(document.body).append(element);
    }
    return $('#loading');
}
function addFullBg(){
    if(!document.getElementById('fullBg')){
        var element='<div id="fullBg"></div>';
        $(document.body).append(element);
    }
    return $('#fullBg');
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
function checkEmail(email){
    var emailReg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
    return emailReg.test(email);
}