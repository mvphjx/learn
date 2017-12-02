/**
 * jq 插件 学习使用 原型和闭包
 */
;(function($){

    //定义组件参数配置
    var Tab=function(that,config){
        var _this=this;

        _this.tab=that;

        _this.config=config;

        //默认配置
        _this.default={
            type:"mouseover",
            effect:"default",
            show:1,
            auto:false
        };

        //传入参数配置替换
        if(_this.getConfig()&&_this.getConfig()!=null){
            $.extend(_this.default,_this.getConfig());
        }

        //保存插件对象及配置参数
        _this.tabNav=_this.tab.find(".tab-nav li");
        _this.tabItem=_this.tab.find(".tab-wrap .tab-item");
        if(_this.default.type==="click"){
            _this.tabNav.bind(_this.default.type,function(e){
                _this.currentChange($(this));
            });
        }else{
            _this.tabNav.bind("mouseover",function(e){
                _this.currentChange($(this));
            });
        }

        //自动切换
        if(_this.default.auto){
            _this.timer=null;
            _this.loop=0;
            _this.autoPlay(_this);

            _this.tab.hover(function(){
                clearInterval(_this.timer);
            },function(){
                _this.autoPlay(_this);
            });
        }

        //设置默认显示
        if(_this.default.show!=1){
            _this.currentChange(_this.tabNav.eq(_this.default.show-1));
        }

    };

    //定义组件方法
    Tab.prototype={

        //获取配置参数
        getConfig:function(){
            var config=this.config;
            if(!(config&&config!=null)){
                config=null;
            }
            return config;
        },

        //标签切换
        currentChange:function(cur){
            var index=cur.index();
            cur.addClass("active").siblings().removeClass("active");
            if(this.default.effect==="default"){
                this.tabItem.eq(index).addClass("active").siblings().removeClass("active");
            }else if(this.default.effect==="fade"){
                this.tabItem.eq(index).stop().fadeIn().siblings().stop().fadeOut();
            }
            if(this.default.auto){
                this.loop=index;
            }
        },

        //自动切换
        autoPlay:function(_this){
            var tabLength=this.tabItem.size();

            this.timer=setInterval(function(){
                _this.loop++;
                if(_this.loop>=tabLength){
                    _this.loop=0;
                }
                _this.currentChange(_this.tabNav.eq(_this.loop));
            },this.default.auto);
        }

    };


    //注册为jquery方法
    $.fn.extend({
        tab:function(config){
        	var tabObject = null;
            this.each(function(){
                tabObject=new Tab($(this),config||null);
            });
			return tabObject;
        }
    });

})(jQuery);