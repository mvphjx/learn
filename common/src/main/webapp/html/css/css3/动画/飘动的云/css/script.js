$(function () {

    //云彩类
    function Cloud(eParent, iIndex) {
        this.iIndex = iIndex;
        this.eParent = eParent;
        this.sClassName = '';
        this.arrZIndex = [0, 1, 2, 3];
        this._init();
    }
    Cloud.prototype = {
        constructor: Cloud,
        _getClassName: function () {
            return 'cloud-' + Math.floor(Math.random() * 5);
        },
        _getLateTime: function () {
            return this.iIndex * 1E4;
        },
        _getTotalTime: function () {
            return Math.floor(Math.random() * 5E4);
        },
        _init: function () {
            var _this = this;
            this.eCloud = $("<div class='cloud'></div>");
            this.eParent.append(this.eCloud);
            this._resetAttr();
            window.setTimeout(function () {
                _this.move();
            }, _this._getLateTime());
        },
        _resetAttr: function () {
            var sClassName = this._getClassName()
            this.eCloud.removeClass(this.sClassName).addClass(sClassName);
            this.sClassName = sClassName;
            this.eCloud.css({
                'left': window.screen.width,
                'top': Math.floor(Math.random() * 640 - this.eCloud.height() / 2),
                'z-index': this.arrZIndex[Math.floor(Math.random() * 4)]
            })
        },
        move: function () {
            var _this = this;
            _this.eCloud.animate({ 'left': -this.eCloud.width() }, 5E4, 'Linear', function () {
                _this._resetAttr();
                _this.move();
            });
        }
    }

    //启动云飘到
    ; (function () {
        var iTotal = 5, eTarget = $('#slide_content');
        while (iTotal--) {
            new Cloud(eTarget, iTotal);
        }
    })();

    //添加步骤
    function addStepsFn() {
        $.extend(this, {
            inScene: function () {
                this.nextIn();
            },
            outScene: function (callback) {
                callback && (this.outCallback = callback);
                this.nextOut();
            },
            nextIn: function () {
                this.inSteps[this.iStep]();
                this.iStep++;
            },
            nextOut: function () {
                this.iStep--;
                if (this.iStep < 0) {
                    this.iStep = 0
                }
                else {
                    this.outSteps[this.iStep]();
                }
            }
        });
    }
    //自定义动画
    function Animate(animateFn, endCallBack) {
        this.iTimer = 0;
        this.animateFn = animateFn;
        this.iTotal = 120;
        this.iT = 0;
        this.endCallBack = endCallBack;
    }
    Animate.prototype = {
        constructor: Animate,
        stop: function () {
            this.iT = 0;
            window.clearTimeout(this.iTimer);
        },
        act: function () {
            var _this = this;
            this.iTimer = window.setTimeout(function () {
                _this.iT++
                if (_this.iT < _this.iTotal) {
                    if (_this.animateFn(_this.iT)) {
                        _this.stop();
                        _this.endCallBack();
                    } else {
                        _this.act();
                    }
                } else {
                    _this.stop();
                    _this.endCallBack();
                }
            }, 13);
        }
    }

    //场景类
    var Scene0 = {
        init: function () {
            this.eStage = $('#stage_0');
            this.eComputer = $('#computer');
            this.eTitleList = $('#title-list-c li');
            this.eC = $('#slider_0')
            this.inSteps = [];
            this.outSteps = [];
            this.iStep = 0;
            this.outCallback = null;
            addStepsFn.call(this);
            this.iTimer = 0;

            this.setSteps();
        },
        stop: function () {
            this.eStage.stop();
            this.eComputer.stop();
            this.eTitleList.stop();
            window.clearTimeout(this.iTimer);
        },
        setSteps: function () {
            var _this = this;
            this.inSteps = [
                function () {
                    _this.eStage.show();
                    _this.eC.show();
                    _this.eStage.removeClass('slider-0-animate').css('opacity', 1).addClass('slider-0-animate').delay(1E3);
                    _this.iTimer = window.setTimeout(function () {
                        _this.nextIn();
                    }, 1.2E3);
                },
                function () {
                    _this.eComputer.stop().show().animate({
                        left: 280
                    }, 1E3, 'easeOutBack', function () { _this.nextIn(); });
                },
                function () {
                    var iLen = _this.eTitleList.length, iIndex = 0, eTemp = null;
                    for (; iIndex < iLen; iIndex++) {
                        eTemp = _this.eTitleList[iIndex];
                        $(eTemp).stop().delay(300 * iIndex).show().animate({
                            left: 0
                        }, .8E3, 'easeOutBounce');
                    }

                }
           ];
            this.outSteps = [
                function () {
                    _this.eStage.stop().css({ 'transform': '' }).animate({
                        'opacity': 0
                    }, .3E3, function () { _this.eC.hide(); _this.outCallback && _this.outCallback(); });
                },
                function () {
                    _this.eComputer.stop().show().animate({
                        left: 1000
                    }, 1E3, 'easeOutBack', function () { _this.nextOut(); });
                },
                function () {
                    var iLen = _this.eTitleList.length, iIndex = 0, eTemp = null, iCur = 0;
                    for (; iIndex < iLen; iIndex++) {
                        eTemp = _this.eTitleList[iIndex];
                        $(eTemp).stop().delay(100 * iIndex).animate({
                            left: -160
                        }, .5E3, 'easeOutBack', function () {
                            iCur++;
                            $(this).hide();
                            if (iCur == iLen) {
                                _this.nextOut();
                            }
                        });
                    }
                } ];
        }
    }
	
});
/*
本代码由js代码网收集并编辑整理;
尊重他人劳动成果;
转载请保留js代码网链接 - http://www.jsdaima.com
*/