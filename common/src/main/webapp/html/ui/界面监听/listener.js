Listener.prototype.settings=null;
Listener.prototype.object=null;
//为了实现单例,外界不要修改
Listener.prototype.unique=null;
/**
 * 创建一个监听方法，监听页面操作，如果一直无操作，时间到后，执行指定方法。
 * 然后重置时间，继续监听。
 * {success:,timeout:,id:}
 * @param success	监听超时后，执行的方法
 * @param timeout 监听时间 秒
 * @param id 需要监控的  页面对象 id 缺省值为整个页面对象
 */
function Listener(settings){       
    if( typeof Listener.unique != 'undefined' ){
        return Listener.unique; 
    }
    this.settings = settings;
    if( typeof this.settings.id == 'undefined' ){
    	this.object = $(document);
    }else{
    	this.object = $("#"+this.settings.id);
    }
    
    Listener.unique = this;
} 
Listener.prototype.init = function(){
    // link in to body events 
    //this.object.bind("mousemove",this.start);//一直监听此事件消耗资源
	this.object.bind("mousedown",this.start);
	this.object.bind("keydown",this.start);
	this.object.bind("keypress",this.start);
    this.start(); 
}
/*
 * 监听：超时倒计时
 */
Listener.prototype.start = function(){
	var nthis = new Listener();
	if(typeof nthis.timerID != 'undefined'){
		 window.clearTimeout(nthis.timerID);
	}
	nthis.timerID = window.setTimeout(success, nthis.settings.timeout*1000);
	function success(){
		nthis.stop();
		nthis.settings.success();
	}
}  
/*
 * 停止页面监听
 *  例如 已经放弃任务
 *     已经弹出窗口
 */
Listener.prototype.stop = function(){
	var nthis = new Listener();
	nthis.object.unbind("mousedown",nthis.start);
	nthis.object.unbind("keydown",nthis.start);
	nthis.object.unbind("keypress",nthis.start);
}
