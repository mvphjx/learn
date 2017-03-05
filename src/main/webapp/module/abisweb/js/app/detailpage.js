//保存初始赋值
DetailPage.prototype.type 			= null;
DetailPage.prototype.splitChar 		= '_And_';
//为了实现单例,外界不要修改
DetailPage.prototype.unique=null;
//候选指位 时间问题，checkbox控件未抽象出统一操作的对象，先特殊处理 
DetailPage.prototype.candFgps  = null;
function DetailPage(type)
{	
	if(!WebUtil.isNull(DetailPage.prototype.unique)){
		return DetailPage.prototype.unique;
	}	
	DetailPage.prototype.unique = this;
	this.type 			= type;
	this.init();	
}
//普通控件缓存数组
DetailPage.prototype.webInputArr 	= [];
//表格控件缓存数组
DetailPage.prototype.webTableArr 	= [];
//初始化&缓存&加载字典项&提示信息
DetailPage.prototype.initCache = function(contro)
{
	if(WebUtil.isNull(contro)){
		return;
	}
	var type = contro.constructor;
	if(type==WebInput){
		this.webInputArr.push(contro);
		if(!WebUtil.isNull(contro.columnName)){
			contro.setComboData(null)		
		}		
	}else if(type==WebTableMgr){
 		this.webTableArr.push(contro);
	}
}
//给表单赋值
DetailPage.prototype.setObject = function(jsonData){
	if(WebUtil.isNull(jsonData)){
		return;
	}else if(typeof jsonData =='string'){
		jsonData = eval('(' + jsonData + ')');
	}
	var nthis = this;
	//表格控件
	for(var i=0,l=this.webTableArr.length;i<l;i++){
		var webTable=this.webTableArr[i];
		var data = getJson(webTable.tblId,this.splitChar)
		if(!WebUtil.isNull(data)){
			this.setTableData(webTable,this.webInputArr,data,this);	
		}	
	}
	//只读td填充
	$("td").each(function(){
		var settingStr = $(this).attr('setting')
		var setting = eval('('+ settingStr +')');
		if(WebUtil.isNull(setting)||WebUtil.isNull(setting.id))
			return;
		var id = setting.id;
		var value = getJson(id,nthis.splitChar);
		if(!WebUtil.isNull(setting.columnName)){
			value=WebComboUtil.getCodeText(setting.columnName,value)
		}	
		$(this).html(value)	
	});
	function getJson(id,splitChar){
		var data = null;
		var arr=new Array();
		arr=id.split(splitChar);
		var data = jsonData;
		for (var i = 0; i < arr.length; i++) {
			if(data[arr[i]]!= undefined){
				data = data[arr[i]]
			}else{
				break
			}
		}
		return data;
	}
}
/*
初始化
控件
字典
编辑状态
*/
DetailPage.prototype.init = function()
{
	this.initUI();
	this.initTable();
}
/*
 * 解析页面配置 翻译控件
 */
DetailPage.prototype.initUI = function()
{
	var nthis = this;
	$("div").each(function(){
	  var nthis = DetailPage.prototype.unique;
	  var settingStr = $(this).attr('setting')
	  var setting = eval('('+ settingStr +')');
	  if(WebUtil.isNull(setting)||WebUtil.isNull(setting.id))
		 return;
	  var arr = setting.id.split(nthis.splitChar);
	  var id = setting.id;
	  var dataType = setting.dataType;
	  //创建父容器
	  var divid = id+"div";
	  $(this).append($("<div id=\""+divid+"\"></div>"));
	  var abisInput = null;
	  if(dataType == ABISCode.InputType.CODE)
	  {
		var codeName = "";
		if(!WebUtil.isNull(setting.columnName)){
			codeName = setting.columnName;
		}	
		var issearchall = true;
		if(!WebUtil.isNull(setting.all)){
			issearchall = false;
		}
		abisInput = WebUI.createCombo(divid,id,null,null,true,issearchall,codeName,"",nthis.requiredField);
	  }else if(dataType == ABISCode.InputType.DATE){
		abisInput = WebUI.createDateText(divid,id,"WebTextField","",nthis.requiredField);	
	  }else if(dataType == ABISCode.InputType.TEXT){
		abisInput = WebUI.createText(divid,id,"WebTextField","",nthis.requiredField);
	  }else if(dataType == ABISCode.InputType.MULTEXT){
		abisInput =WebUI.createMulText(divid,id,"WebTextArea","",nthis.requiredField);
	  }else if(dataType == ABISCode.InputType.TABLE){
		$("#"+divid).attr('id',id);
		abisInput = new WebTableMgr(id,"",20,null);
	  }else{
		alert(settingStr)  
	  }
	  nthis.initCache(abisInput);
    });
	return;	
}
/*
TableStart
页面表格操作相关方法

*/
DetailPage.prototype.initTable= function()
{
	//表格控件 初始化
	for(var i=0,l=this.webTableArr.length;i<l;i++){
		var webTable=this.webTableArr[i];
		this.initTableData(webTable,this);
	}
}
DetailPage.prototype.initTableData = function(tableMgr,nthis){
	
	if(WebUtil.isNull(tableMgr)){
		return null;
	}
	var tblId = tableMgr.tblId;
	var tableJson = $.extend(true, {}, tableJsonTemple);
	$.each(nthis.webInputArr,function(index,input) {
		if(input.getId().indexOf(tblId)>-1){
			var arr=new Array();
			arr=input.getId().split(nthis.splitChar);
			var name = arr[arr.length-1];
			//prevAll('label').html()  or  .prev().html()
			tableJson["headerText"][name] = $("#"+input.getId()+"div").prev().html();
			tableJson["header"].push(name) 
		}
	});
	tableMgr.setInput(tableJson);
}
DetailPage.prototype.setTableData = function(tableMgr,webInputArr,data,nthis){
	if(WebUtil.isNull(tableMgr)){
		return null;
	}
	var tblId = tableMgr.tblId;
	var table = tableMgr.getTable();
	if(!WebUtil.isNull(data)){
		$.each(data,function(index,rowdata) {
			var array ={'data':{}};
			$.each(webInputArr,function(index,input) {
			if(input.getId().indexOf(tblId)>-1){
				var arr=new Array();
				arr=input.getId().split(nthis.splitChar);
				var name = arr[arr.length-1];
				var value = rowdata[name];
				if(input.type=="Combo"){
					var text = WebComboUtil.getCodeText(input.columnName,value);
					value=value + ':' + text;
				}
				array.data[name] = value;
			}
			});
			table.addRow(array);
		});
	}
}	
/*
候选指位 操作相关方法
*/
DetailPage.prototype.candFgpsClick=function()
{
	var newCandFgps=this.getCandFgpsData();
	if(this.srcCardObj != null)
	{
		var oldCandFgps=this.srcCardObj['candFgps'];
		if(newCandFgps==oldCandFgps)
		{
			this.changeField['candFgps']=false;
			if(this.changeListener != null)
			{
				this.changeListener(false);
			}
		}
		else
		{
			this.changeField['candFgps']=true;	
			if(this.changeListener != null)
			{
				this.changeListener(true);
			}
		}
	}
	return newCandFgps == "0000000000";
}
DetailPage.prototype.getCandFgpsData=function()
{
	var arr=this.candFgps.getSelectData();
	var data=new Array();
	data[0]=0;
	data[1]=0;
	data[2]=0;
	data[3]=0;
	data[4]=0;
	data[5]=0;
	data[6]=0;
	data[7]=0;
	data[8]=0;
	data[9]=0;
	for(var i=0;i<arr.length;i++)
	{
		var index=parseInt(arr[i].id);
		data[index]=1;
	}
	var candFgps=data.toString();
	return candFgps.replace(/,/g,'');
}
DetailPage.prototype.setCandFgpsData=function(data)
{	
	for(var i=0;i<data.length;i++)
	{
		if(data.substr(i,1)=='1')
		{
			this.candFgps.setSelected(i,true);
		}		
		else
		{
			this.candFgps.setSelected(i,false);
		}			
	}
}