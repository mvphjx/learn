//删除 数组指定元素  依赖jq
var arr = ['a','b','c','d'];
arr.splice($.inArray('c',arr),1);
alert(arr);