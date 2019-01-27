({
    appDir:'../js',//需要打包的根目录
    baseUrl:'./app',//相对于 appDir，代表查找文件的锚点（that represents the anchor path for finding files）
    dir:'../dist',//输出目录
    mainConfigFile:'../js/app/require.config.js',//配置文件
    //name:'app'
    optimize: "uglify2",//使用 UglifyJS 压缩（默认值）
    optimizeCss:'standard',//css标准压缩（去换行、空格、注释）
    removeCombined:true,//从输出目录中删除已合并的文件
    //allowSourceOverwrites:true,
    modules:[{name:"main"}]//需要打包的模块
})