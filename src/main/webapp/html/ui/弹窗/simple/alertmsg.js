/**
 * {msg:,mode:,success:,timeout:}
 * @param msg 需要提示的信息
 * @param mode mode为空，即只有一个确认按钮，mode为1时有确认和取消两个按钮
 * @param success	提示关闭后执行的方法
 * @param timeout 倒计时关闭时间 秒
 */
function alertMsg(settings) { 
	    this.settings = settings;
	    if(typeof settings == 'undefined'){
	    	return;
	    }
	    var tip1 = this.settings.tip_ok==null?"<cite>确定</cite>":this.settings.tip_ok;
	    var tip2 = this.settings.tip_cancel==null?"<cite>取消</cite>":this.settings.tip_cancel;
        var msg = this.settings.msg || '';
        var mode = this.settings.mode || 0;
        var top = document.body.scrollTop || document.documentElement.scrollTop;
        var isIe = (document.all) ? true : false;
        var isIE6 = isIe && !window.XMLHttpRequest;
        var sTop = document.documentElement.scrollTop || document.body.scrollTop;
        var sLeft = document.documentElement.scrollLeft || document.body.scrollLeft;
        var winSize = function(){
            var xScroll, yScroll, windowWidth, windowHeight, pageWidth, pageHeight;
            // innerHeight获取的是可视窗口的高度，IE不支持此属性
            if (window.innerHeight && window.scrollMaxY) {
                xScroll = document.body.scrollWidth;
                yScroll = window.innerHeight + window.scrollMaxY;
            } else if (document.body.scrollHeight > document.body.offsetHeight) { // all but Explorer Mac
                xScroll = document.body.scrollWidth;
                yScroll = document.body.scrollHeight;
            } else { // Explorer Mac...would also work in Explorer 6 Strict, Mozilla and Safari
                xScroll = document.body.offsetWidth;
                yScroll = document.body.offsetHeight;
            }

            if (self.innerHeight) {    // all except Explorer
                windowWidth = self.innerWidth;
                windowHeight = self.innerHeight;
            } else if (document.documentElement && document.documentElement.clientHeight) { // Explorer 6 Strict Mode
                windowWidth = document.documentElement.clientWidth;
                windowHeight = document.documentElement.clientHeight;
            } else if (document.body) { // other Explorers
                windowWidth = document.body.clientWidth;
                windowHeight = document.body.clientHeight;
            }

            // for small pages with total height less then height of the viewport
            if (yScroll < windowHeight) {
                pageHeight = windowHeight;
            } else {
                pageHeight = yScroll;
            }

            // for small pages with total width less then width of the viewport
            if (xScroll < windowWidth) {
                pageWidth = windowWidth;
            } else {
                pageWidth = xScroll;
            }

            return{
                'pageWidth':pageWidth,
                'pageHeight':pageHeight,
                'windowWidth':windowWidth,
                'windowHeight':windowHeight
            }
        }();
        //alert(winSize.pageWidth);
        //遮罩层
        var styleStr = 'top:0;left:0;position:absolute;z-index:10000;background:#666;width:' + winSize.pageWidth + 'px;height:' +  (winSize.pageHeight + 30) + 'px;';
        styleStr += (isIe) ? "filter:alpha(opacity=80);" : "opacity:0.8;"; //遮罩层DIV
        var shadowDiv = document.createElement('div'); //添加阴影DIV
        shadowDiv.style.cssText = styleStr; //添加样式
        shadowDiv.id = "shadowDiv";
        //如果是IE6则创建IFRAME遮罩SELECT
        //TODO 目前全部使用 iframe  为了能够覆盖ocx插件 
        isIE6=true;
        if (isIE6) {
            var maskIframe = document.createElement('iframe');
            maskIframe.style.cssText = 'width:' + winSize.pageWidth + 'px;height:' + (winSize.pageHeight + 30) + 'px;position:absolute;visibility:inherit;z-index:-1;filter:alpha(opacity=0);';
            maskIframe.frameborder = 0;
            maskIframe.src = "about:blank";
            shadowDiv.appendChild(maskIframe);
        }
        document.body.insertBefore(shadowDiv, document.body.firstChild); //遮罩层加入文档
        //弹出框
        var styleStr1 = 'display:block;position:fixed;_position:absolute;left:' + (winSize.windowWidth / 2 - 200) + 'px;top:' + (winSize.windowHeight / 2 - 150) + 'px;_top:' + (winSize.windowHeight / 2 + top - 150)+ 'px;'; //弹出框的位置
        var alertBox = document.createElement('div');
        alertBox.id = 'alertMsg';
        alertBox.style.cssText = styleStr1;
        //创建弹出框里面的内容P标签
        var alertMsg_info = document.createElement('P');
        alertMsg_info.id = 'alertMsg_info';
        alertMsg_info.innerHTML = msg;
        alertBox.appendChild(alertMsg_info);
        //创建按钮
        var btn1 = document.createElement('a');
        btn1.id = 'alertMsg_btn1';
        btn1.href = 'javas' + 'cript:void(0)';
        btn1.innerHTML = tip1;
        btn1.onclick = function () {
            remove(true);
        };
        alertBox.appendChild(btn1);
        if (mode === 1) {
            var btn2 = document.createElement('a');
            btn2.id = 'alertMsg_btn2';
            btn2.href = 'javas' + 'cript:void(0)';
            btn2.innerHTML = tip2;
            btn2.onclick = function () {
            	remove(false);
            };
            alertBox.appendChild(btn2);
        }
        document.body.appendChild(alertBox);
        if(typeof this.settings.timeout != 'undefined'){
        	 timeout(this.settings.timeout)
        }
        /*关闭窗口，执行回调函数*/
        function remove(bool){
            document.body.removeChild(alertBox);
            document.body.removeChild(shadowDiv);
            if(typeof this.setTime != 'undefined'){
            	clearInterval(this.setTime);
            }
            if(typeof this.settings.success != 'undefined'){
            	this.settings.success(bool);
            }
        }
        /*倒计时程序 动态显示剩余时间 */
        function timeout(ntime){
        	this.setTime=setInterval(function(){
                if(ntime<=0){
        			//clearInterval(this.setTime);
        			remove(true);
                }
                if(ntime<=-10){
                	alert(-10);
                }
                $('#alertMsg_btn1').html(tip1+"("+ntime+")");
                ntime--;
            },1000);		
        }
}