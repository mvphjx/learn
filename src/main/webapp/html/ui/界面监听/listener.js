/**
 * timeout 倒计时时间 秒
 * msg  需要提示的信息
 * success 同意后执行的方法
 * 
 */
ScreenSaver.prototype.nTimeout=10000;
ScreenSaver.prototype.settings=null;
ScreenSaver.prototype.success=null;
ScreenSaver.prototype.msg=null;
function ScreenSaver(settings){     
    this.settings = settings;         
    this.nTimeout = this.settings.timeout;        
    // link in to body events 
    var nThis = this;
    //$(document).mousemove(this.onevent);//一直监听消耗资源
    $(document).mousedown(this.onevent);     
    $(document).keydown(this.onevent);     
    $(document).keypress(this.onevent);
    this.start(); 
}     
ScreenSaver.prototype.start = function(){
    var pThis = this;     
    var f = function(){pThis.timeout();}     
    this.timerID = window.setTimeout(f, this.nTimeout); 
}

ScreenSaver.prototype.timeout = function(){     
    if ( !this.saver ){     
		if(!$('#note').is(':visible')){ 
				$('#note').css({display:'block', top:'-100px'}).animate({top: '+100'}, 500, function(){ 
					out(10); 
				}); 
		}
		function out(ntime){
			var setTime=setInterval(function(){
                if(ntime<=0){
					$('#note').animate({top:'0'}, 500, function(){ 
						$(this).css({display:'none', top:'-100px'}); 
					}); 
					clearInterval(setTime);
                }
                $('#input1').val("同意"+"("+ntime+")");
                ntime--;
            },1000);		
		}
    }     
}  
ScreenSaver.prototype.stop = function(){ 
	alert(1);
}    
ScreenSaver.prototype.signal = function(){     
//    if ( this.saver ){     
//        this.stop();     
//    }         
    window.clearTimeout(this.timerID);
    console.log(this.timerID);
    var pThis = this;     
    var f = function(){pThis.timeout();}     
    this.timerID = window.setTimeout(f, this.nTimeout);     
}     
    
ScreenSaver.prototype.onevent = function(e){    
	saver.signal();
	console.log(e.type);
}     
    
    