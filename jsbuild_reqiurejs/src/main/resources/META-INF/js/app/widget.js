// abstract class
define(["jquery"], function ($) {
    function Widget() {
        this.handlers = null;
        this.boundingBox = null;//field:outermost container
    }

    Widget.prototype = {
        constructor: Widget,
        //implemented method
        // observer pattern
        on: function (type, handler) {
            if (!this.handlers[type]) {
                this.handlers[type] = [];
            }
            this.handlers[type].push(handler);
            return this;
        },
        fire: function (type, data) {
            if (this.handlers[type] instanceof Array) {
                var handlers = this.handlers[type];
                for (let i = 0, len = handlers.length; i < len; i++) {
                    handlers[i](data);
                }
            }
        },
        render:function (container) {//method:render component
            this.renderUI();
            this.handlers = {};
            this.bindUI();
            this.syncUI();
            $(container || document.body).append(this.boundingBox);
        },
        destroy:function () {
            this.destructor();
            //The .off() method removes event handlers that were attached with .on().
            this.boundingBox.off();
            this.boundingBox.remove();
        },
        //abstract method
        renderUI:function () {//interface:add dom node
            console.warn("renderUI should be override");
        },
        bindUI:function () {//interface:event listener
            console.warn("bindUI should be override");
        },
        syncUI:function () {//interface:init component field
            console.warn("syncUI should be override");
        },
        destructor:function () {//interface:invoke before destroy
            console.warn("destructor should be override")
        },
    };
    return {
        Widget: Widget
    }
});