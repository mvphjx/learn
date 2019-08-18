({
    appDir:'./static',//需要打包的根目录
    baseUrl:'./js',//打包的模块所在目录
    dir:'./build',//输出目录
    mainConfigFile:'./static/js/require.config.js',//配置文件
    //name:'app'
    modules:[{name:"main"}]//需要打包的模块
})