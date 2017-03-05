//文本编辑模式(编辑|更新|只读)
var LPTxtMode=
{
	INPUT:0,
	EDIT:1,
	VIEW:2
}
//保存初始赋值
EditPage.prototype.srcObject 	= {};
EditPage.prototype.requiredField 	= null;
EditPage.prototype.updateField 	= null;
EditPage.prototype.mode 			= null;
EditPage.prototype.type 			= null;
EditPage.prototype.splitChar 		= '_And_';
//为了实现单例,外界不要修改
EditPage.prototype.unique=null;
//候选指位 时间问题，checkbox控件未抽象出统一操作的对象，先特殊处理 
EditPage.prototype.candFgps  = null;
function EditPage(requiredField,updateField,defValue,columnlengths,type,mode)
{	
	if(!WebUtil.isNull(EditPage.prototype.unique)){
		return EditPage.prototype.unique;
	}	
	EditPage.prototype.unique = this;
	this.requiredField	= requiredField;
	this.updateField	= updateField;
	this.defValue		= defValue;
	this.mode 			= mode;
	this.type 			= type;
	this.init();	
	this.update(updateField);	//现行阶段毙掉更新项
}
//普通控件缓存数组
EditPage.prototype.webInputArr 	= [];
//表格控件缓存数组
EditPage.prototype.webTableArr 	= [];
//try json cache
EditPage.prototype.jsonCatch 	= {};
//try json jsonCatchArr
EditPage.prototype.jsonCatchArr 	= {};
//try json jsonCatchArr2
EditPage.prototype.jsonCatch2 	= {};
//初始化&缓存&加载字典项&提示信息
EditPage.prototype.initCache = function(contro)
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
		
		
		
		var arr=new Array();
		arr=contro.id.split(this.splitChar);
		var inner=this.jsonCatch;
		for (var i = 0; i < arr.length-1; i++) {
			if(inner[arr[i]]== undefined){
				inner[arr[i]]={};
			}
			inner = inner[arr[i]];
		}
		inner[arr[arr.length-1]]=contro;
		inner[arr[arr.length-1]].setText(123)
	}else if(type==WebTableMgr){
 		this.webTableArr.push(contro);
	}
	

}
//给表单赋值
EditPage.prototype.setObject = function(jsonData){
	if(WebUtil.isNull(jsonData)){
		return;
	}else if(typeof jsonData =='string'){
		jsonData = eval('(' + jsonData + ')');
	}
	//普通控件
	var arrObject = this.webInputArr;
	var srcObject = this.srcObject;
	var nthis = this;
	for(var i=0,l=arrObject.length;i<l;i++){
		var control = arrObject[i];
		var data = getJson(control.getId(),this.splitChar)
		if(!WebUtil.isNull(data)){
			control.setText(data);
		}
	}
	//表格控件
	for(var i=0,l=this.webTableArr.length;i<l;i++){
		var webTable=this.webTableArr[i];
		var data = getJson(webTable.tblId,this.splitChar)
		this.setTableData(webTable,this.webInputArr,data,this);
	}
	//候选指位单独处理 checkbox
	if(!WebUtil.isNull(this.candFgps)){
		var data = getJson(this.candFgps.id,this.splitChar)
		if(!WebUtil.isNull(data)){
			this.setCandFgpsData(data);
		}			
	}
	//开始监听
	this.Txtchange();
	function getJson(id,splitChar){
		var data = null;
		var arr=new Array();
		arr=id.split(splitChar);		
		var result = jsonData;
		for (var i = 0; i < arr.length; i++) {
			if(result!= undefined){
				result=jsonData[arr[i]];
			}
		}
		//缓存初始值，检测是否改变
		srcObject[id]=data;
		return data;
	}
}
//获取表单值
EditPage.prototype.getObject = function(){
	var jsonData = {};
	//普通控件
	var arrObject = this.webInputArr;
	for(var i=0,l=arrObject.length;i<l;i++){
		var control = arrObject[i];
		createJson(control.getId(),control.getValue(),this.splitChar);
	}
	//表格控件
	for(var i=0,l=this.webTableArr.length;i<l;i++){
		var webTable=this.webTableArr[i];
		createJson(webTable.tblId,this.getTableData(webTable),this.splitChar)
	}
	//候选指位单独处理 checkbox
	if(!WebUtil.isNull(this.candFgps)){
		if(!WebUtil.isNull(this.candFgps.id)){
			createJson(this.candFgps.id,this.getCandFgpsData(),this.splitChar)
		}
	}	
	return JSON.stringify(jsonData);
	function createJson(id,value,splitChar){
		if(WebUtil.isNull(id)||WebUtil.isNull(value)){
			return;
		}
		var arr=new Array();
		arr=id.split(splitChar);	
		var result = jsonData;
		for (var i = 0; i < arr.length-1; i++) {
			if(result[arr[i]]== undefined){
				result[arr[i]]={};
			}
			result = result[arr[i]];
		}
		result[arr[arr.length-1]]=value;
	}
}
/*
初始化
控件
字典
编辑状态
*/
EditPage.prototype.init = function()
{
	this.initUI();
	this.initComboData();
	this.initUIStatus();
	this.initTable();
	//this.initDefault();
}

EditPage.prototype.initUIStatus = function()
{
	if(this.mode == LPTxtMode.INPUT)
	{
		$("#hide_line_1").hide();
		$("#hide_line_2").hide();
		
		this.createUser.setEditable(false);
		this.createUnitCode.setEditable(false);
		
	}
}
/*
 * 根据页面div  生成控件
 */
EditPage.prototype.initUI = function()
{
	this.filedMap 		= {};
	//页面控件 的 容器
	this.crolArray		= new Array();
	
	//必填项
	this.requiredMap 	= new Array();
	
	//更新项
	this.updateMap 		= new Array();
	
	this.changeField 	= {};
	
	$("div").each(function(){
	  var nthis = EditPage.prototype.unique;
	  var settingStr = $(this).attr('setting')
	  var setting = eval('('+ settingStr +')');
	  if(WebUtil.isNull(setting)||WebUtil.isNull(setting.id))
		 return;
	  var arr = setting.id.split(nthis.splitChar);
	  var id = setting.id;
	  var dataType = setting.dataType;
	  //console.log(settingStr);
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
	  }else if(dataType == 'CandFgps'){
		//候选指位单独处理
		var data = 
		[
			{id:"0",text:AbisMessageResource.FingerPosition['RightThumb'],enabled:true},
			{id:"1",text:AbisMessageResource.FingerPosition['RightIndex'],enabled:true},
			{id:"2",text:AbisMessageResource.FingerPosition['RightMiddle'],enabled:true},
			{id:"3",text:AbisMessageResource.FingerPosition['RightRing'],enabled:true},
			{id:"4",text:AbisMessageResource.FingerPosition['RightLittle'],enabled:true},
			{id:"5",text:AbisMessageResource.FingerPosition['LeftThumb'],enabled:true},
			{id:"6",text:AbisMessageResource.FingerPosition['LeftIndex'],enabled:true},
			{id:"7",text:AbisMessageResource.FingerPosition['LeftMiddle'],enabled:true},
			{id:"8",text:AbisMessageResource.FingerPosition['LeftRing'],enabled:true},
			{id:"9",text:AbisMessageResource.FingerPosition['LeftLittle'],enabled:true}			
		];
		var bntSetting = 
		{
			param:		{colCount:5},
			callback:	{onClick: candFgpsClick}
		}
		function candFgpsClick()
		{
			nthis.candFgpsClick();
		}
		$("#"+divid).attr('id',id);
		nthis.candFgps = new CheckBnt(id,data,bntSetting);
		abisInput=null;
	  }else{
		  alert(settingStr);
		  return;
	  }
	  if(!WebUtil.isNull(abisInput)&&abisInput.constructor==WebInput){
		  //TODO检验		  
		  /*
		  是否必填
		  this.requiredField
		  // 查看该控件是否属于必填项
		  var isRequired = WebArrayUtil.containsToIgnoreCase(colNameList,id);
		  需要的参数  是否必填  长度  特殊校验规则
		  var tipid = id+"tip";
	      $(this).append($("<div id=\""+tipid+"\"></div>"));		  
		  abisInput.setErrorTip(tipid);
		  var param={};	
		  param.maxlength=columnsObj["MIS_PERSON"][this.iniEnrollUser.getId()];	
		  abisInput.setValidateType(param);
		  */
	  } 
	  nthis.initCache(abisInput);
    });
	return;
	
}
/*初始化字典下拉选*/
EditPage.prototype.initComboData=function()
{
	var columnnames = Array();
	var arrObject = this.webInputArr;
	for(var i=0,l=arrObject.length;i<l;i++){
		var control = arrObject[i];
		if(!WebUtil.isNull(control.columnName)){
			//去重
			if($.inArray(control.columnName, columnnames)==-1){
				columnnames.push(control.columnName);
			}
	    }
	}
	var nthis=this;
	WebComboUtil.getCodeTable(columnnames, getComboData);
	function getComboData(data)
	{		
		if(WebUtil.isNull(data)) return;
		nthis.setComboData(data);
	}
}
EditPage.prototype.setComboData=function(data)
{
		data = eval('(' + data + ')');
		var arrObject = this.webInputArr;
		for(var i=0,l=arrObject.length;i<l;i++){
			var control = arrObject[i];
			if(!WebUtil.isNull(control.columnName)){
				if(control.columnName.indexOf(ABISCode.DBCodeName)>-1){
				//数据库特殊处理	
					if(control.columnName.split(this.splitChar).length>1){
						var dbType = control.columnName.split(this.splitChar)[1];
						this.initDBInfo(control,dbType);
					}
				}else{
					control.setComboData(data[control.columnName])
				}		
			}
		}		
}
//初始化数据库下拉选
EditPage.prototype.initDBInfo = function(control,dbType)
{	
	var nThis = this;
	var url = WebVar.VarPath + "/db/mgr/dbinfo/"+dbType;
	jQuery.ajax
	( 
		{
			type 		: 'POST',
			contentType : 'application/json',
			url 		: url,
			data 		: null,
			dataType 	: 'json',
			success 	: function(data) 
			{
				if(data != null)
				{
					if(typeof data =='string'){
						data = eval('(' + data + ')');
					}
					control.setComboData(data);
					if(nThis.dbid != null)
					{
						control.setValue(nThis.dbid) ;
					}
				}
			}
		}
	);
}
/*
TableStart
页面表格操作相关方法

*/
EditPage.prototype.initTable= function()
{
	//表格控件 初始化
	for(var i=0,l=this.webTableArr.length;i<l;i++){
		var webTable=this.webTableArr[i];
		this.initTableData(webTable,this);
	}
	//按钮事件绑定
	$("button[name='del_box']").bind("click",delBox) 
	$("button[name='edit_box']").bind("click",editBox) 
	$("button[name='add_box']").bind("click",addBox)  
	var nThis = this;
	function addBox()
	{
		nThis.addBox(this);
	}
	function delBox()
	{
		nThis.delBox(this);
	}
	function editBox()
	{
		nThis.editBox(this);
	}
}
EditPage.prototype.initTableData = function(tableMgr,nthis){
	
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
EditPage.prototype.setTableData = function(tableMgr,webInputArr,data,nthis){
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
EditPage.prototype.getTableData = function(tableMgr){
	var result = [];
	if(WebUtil.isNull(tableMgr)){
		return null;
	}
	if(WebUtil.isNull(tableMgr.getTable())){
		return null;
	}
	if(WebUtil.isNull(tableMgr.getTable().getRowCount())){
		return null;
	}
	var count = tableMgr.getTable().getRowCount();
	for(var i=0;i<count;i++)
	{
		var resultData = {}
		var rowdata=tableMgr.getTable().getRow(i);
		jQuery.extend(resultData,rowdata) 
		result.push(resultData)
	}	
	return result;
}
/**
 * 以下的增加，删除，修改操作结果，只是在页面上显示，不对库中的数据进行操作。
 * 最终的返回的object会保存着所做的修改，统一保存。
 */
EditPage.prototype.addBox = function(e)
{
	var settingStr = $(e).parent().parent().attr('setting')
	var setting = eval('('+ settingStr +')');
	var id = setting.id;
	var arr=new Array();
	arr=id.split(this.splitChar);
	var name = arr[arr.length-1];
	boxInputClear(this.webInputArr,id);
	//content 需要dom对象，规范的getElementsByName只对input有效
	var d = dialog({
		title: AbisMessageResource["Add"],
		content: $("div[name='"+name+"']")[0],
		okValue		: AbisMessageResource['Add'],
		cancelValue	: AbisMessageResource['Cancel'],
		ok: function(){
			return add(0,id);
		},
		cancel: function () {}
	});
	d.showModal();
	var nthis = this;
	function add(type,tblId)
	{
		return nthis.addRow(type,tblId);
	}
}
/**
 * type 表示 是增加还是更新 0增加 1更新
   tblId 表示缓存的表格控件tblId
 */
EditPage.prototype.addRow= function(type,tblId)
{	
/* TODO检验
	addressType.cancelErrorTip();
	addressCode.cancelErrorTip();
	
	var addrType = addressType.getCode();
	var addrCode = addressCode.getCode();
	
	if(WebUtil.isNull(addrType) )
	{
		addressType.requiredTip();
		return false;
	}
	if(WebUtil.isNull(addrCode))
	{
		addressCode.requiredTip();
		return false;
	}
*/
	//webInputArr webTableArr
	var nthis = this;
	$.each(nthis.webTableArr,function(index,tableMgr) {
		if(tableMgr.tblId==tblId){
			var table = tableMgr.getTable();
			var array ={data:{}}
			$.each(nthis.webInputArr,function(index,input) {
				if(input.getId().indexOf(tblId)>-1){
					var arr=new Array();
					arr=input.getId().split(nthis.splitChar);
					var name = arr[arr.length-1];
					var value = input.getText();
					if(input.type=="Combo"){
						value=input.getCode() + ':' + input.getText()
					}
					array.data[name] = value;
				}
			});
			if(type == 0){
				table.addRow(array);
			}else{
				//ID删除 不再需要 赋值 2016年12月16日
				//array.data['ID']=table.getSelectItems()[0]['ID'];
				table.deleteSelectRow();
				table.addRow(array);
			}
			//this.changeField['address'] = true;
			if(WebUtil.isFunction(nthis.changeListener))
			{
				nthis.changeListener();	
			}
		}
	});	
}
EditPage.prototype.delBox= function(e)
{
	var settingStr = $(e).parent().parent().attr('setting')
	var setting = eval('('+ settingStr +')');
	var tblId = setting.id;
	var arr=new Array();
	arr=tblId.split(this.splitChar);
	var name = arr[arr.length-1];
	var nthis = this;
	$.each(nthis.webTableArr,function(index,tableMgr) {
		if(tableMgr.tblId==tblId){
			var table=tableMgr.getTable();
			var data=table.getSelectItems();
			table.deleteSelectRow();
			//this.changeField['address']=true;
			if(WebUtil.isFunction(nthis.changeListener))
			{
				nthis.changeListener();	
			}
		}
	});	
}
EditPage.prototype.editBox = function(e)
{
	var settingStr = $(e).parent().parent().attr('setting')
	var setting = eval('('+ settingStr +')');
	var tblId = setting.id;
	var arr=new Array();
	arr=tblId.split(this.splitChar);
	var name = arr[arr.length-1];
	var nthis = this;
	if(setValue())
	{
		var d = dialog({
		title: AbisMessageResource["Edit"],
		content: $("div[name='"+name+"']")[0],
		okValue		: AbisMessageResource['Update'],
		cancelValue	: AbisMessageResource['Cancel'],
		ok: function () {return add(1,tblId)},
		cancel: function () {}
		});
		d.showModal();	
	}
	else
	{
		alert(AbisMessageResource.Alert["HaveNoSelectedTableRecord"]);
	}
	function add(type,tblId)
	{
		return nthis.addRow(type,tblId);
	}
	function setValue()
	{
		var result = false;
		$.each(nthis.webTableArr,function(index,tableMgr) {
			if(tableMgr.tblId==tblId){
				var table=tableMgr.getTable();
				var data=table.getSelectItems();
				if(!WebUtil.isNull(data)){
					result=true;
					$.each(nthis.webInputArr,function(index,input) {
						var inputarr=new Array();
						inputarr=input.getId().split(nthis.splitChar);
						var inputname = inputarr[inputarr.length-1];
						if(input.getId().indexOf(tblId)>-1){
							input.setText(data[0][inputname]);
						}
					});					
					
				}				
			}
		});
		return result;
	}
}
function boxInputClear(webInputArr,tblId)
{
			$.each(webInputArr,function(index,input) {
				if(input.getId().indexOf(tblId)>-1){
						input.clear();
				}
			});
}

/*
候选指位 操作相关方法
*/
EditPage.prototype.candFgpsClick=function()
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
EditPage.prototype.getCandFgpsData=function()
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
EditPage.prototype.setCandFgpsData=function(data)
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



//设置校验规则  TODO  根据json格式遍历
EditPage.prototype.setValidateColumns = function(columnsObj)
{
	if(WebUtil.isNull(columnsObj)) return;
	
	//暂时屏蔽报错    2016年12月1日 
	if(typeof columnsObj["CASE_EVENT"] == 'undefined'){
		columnsObj["CASE_EVENT"]={};
	}
	if(typeof columnsObj["TP_CARD_INFO"] == 'undefined'){
		columnsObj["TP_CARD_INFO"]={};
	}
	if(typeof columnsObj["CE_BASIC_INFO"] == 'undefined'){
		columnsObj["CE_BASIC_INFO"]={};
	}
	if(typeof columnsObj["CE_ACCEPT_INFO"] == 'undefined'){
		columnsObj["CE_ACCEPT_INFO"]={};
	}
	if(typeof columnsObj["CASE_REGISTER_INFO"] == 'undefined'){
		columnsObj["CASE_REGISTER_INFO"]={};
	}
	if(typeof columnsObj["CASE_BREAK_INFO"] == 'undefined'){
		columnsObj["CASE_BREAK_INFO"]={};
	}
	if(typeof columnsObj["CASE_EXTRACT_INFO"] == 'undefined'){
		columnsObj["CASE_EXTRACT_INFO"]={};
	}
	if(typeof columnsObj["CE_CANCEL_INFO"] == 'undefined'){
		columnsObj["CE_CANCEL_INFO"]={};
	}
	//屏蔽报错 end
	var ceNumparam={};	
	ceNumparam.maxlength= columnsObj["CASE_EVENT"][CaseEventCol.CE_NUM];	
	this.ceNum.setValidateType(ceNumparam);	
		
}

//是否通过了长度验证，既页面中存在红色字体提示长度不符合时不予保存
EditPage.prototype.isPassLenValidate = function()
{
	var nthis = this;
	var bool  = true; 
	$.each(nthis.webInputArr,function(index,input) {
		var errorinfo = input.getErrorTip();
		if(errorinfo!="") {
			bool  = false;
		}
	});	
	return bool;
}

//是否通过了验证，包括长度验证和必填项验证
EditPage.prototype.isPassValidate	= function()
{
	return this.isPassLenValidate() && this.validateRequired();
}

EditPage.prototype.initMap = function(map,contro)
{
	map.push(contro);
	this.filedMap[contro.getId()]=contro;
	var required=WebArrayUtil.containsToIgnoreCase(this.requiredField,contro.getId());
	var update=WebArrayUtil.containsToIgnoreCase(this.updateField,contro.getId());
	if(required)
	{
		this.requiredMap.push(contro);	
	}
	if(update)
	{
		this.updateMap.push(contro);	
	}
}
EditPage.prototype.initUpdateField = function(map)
{
	for(var i=0;i<map.length;i++)	
		{
		 var id= map[i].getId();
		 var flag=true;
		 for(var j=0;j<this.updateMap.length;j++)
			 {
			   var nid=this.updateMap[j].getId();
			   if(id==nid)
			   {
				   this.updateMap[j].setEditable(true);
				   flag=false;
				   break;
			   }
			 }
		 if(flag)
			 map[i].setEditable(false);
		}		
}

//为所有文本框注册当前文本是否发生了变化，编辑页面赋值后调用。
EditPage.prototype.Txtchange = function()
{
	var nthis = this;	
	$.each(nthis.webInputArr,function(index,input) {
		var inputarr=new Array();
		inputarr=input.getId().split(nthis.splitChar);
		var inputname = inputarr[inputarr.length-1];
		nthis.register(input);
	});	
}

EditPage.prototype.register = function(contro)
{
	var nThis = this;
	contro.addChangeListener(textChange);
	function textChange(id,value,txt)
	{				
		var oldtext = null;
		if(nThis.srcCaseObj != null)
		{
			oldtext = nThis.srcCaseObj[id];		
		}
		if(oldtext == null) 
		{
			oldtext = "";
		}
		if(value != oldtext)
		{
			nThis.changeField[id] = true;		  
			flag = true;
		}	
		else
		{
			nThis.changeField[id] = false;
		    flag = false;
		}
		if(WebUtil.isFunction(nThis.changeListener))
		{
			nThis.changeListener(flag);	
		}
	}
}

//编辑页面的信息是否发生了变化
EditPage.prototype.isTxtInfoChanged = function()
{	
	var flag=false;
	for(var name in this.changeField)
	{
		if(this.changeField[name])
		{
			flag=true;
		    break;
		}					 
	}	    
	return flag;
}
//验证所有必填项是否都填了
EditPage.prototype.validateRequired = function()
{
	var flag=true;
	//验证必填项是否都填了
	var n=0;
	for(var i=0;i<this.requiredMap.length;i++)
	{
		 var text=this.requiredMap[i].getText();
		 if(text == null || text == "")
		 {
			 n=n+1;
		 }
	}		
	if(n>0)
	{
		flag=false;
	}
	return flag;
}
/**
 * 注册文本改变事件
 * @param {Object} listener
 */
EditPage.prototype.registerChangeListener = function(listener)
{
	this.changeListener = listener;
}

EditPage.prototype.clear = function()
{
	
}

EditPage.prototype.initDefault = function()
{
	if(this.defValue == null)return;
	
	for(var i=0;i<this.defValue.length;i++)
	{
		var v = this.defValue[i];	  
		var field = this.filedMap[v.k];
		if(field == null)continue;
		field.setValue(v.v);
	}
}

EditPage.prototype.reSet = function()
{
	this.clear();
	this.initDefault();
}
EditPage.prototype.update = function(field)
{
	if(field!=null&&field.length>0)
	{
		var mainInfo="mainInfo";
		var acceptInfo="acceptInfo";
		var basicInfo="basicInfo";
		var registerInfo="registerInfo";
		var breakInfo="breakInfo";
		var extractInfo="extractInfo";
		var cancelInfo="cancelInfo";
		var mainInfoA= this.crolArray[mainInfo];
		for(var i=0;i<mainInfoA.length;i++)
			{
			     var contral=mainInfoA[i];	
			     var id=contral.getId();
			     //禁止更新项验证
			     var forbidden = WebArrayUtil.containsToIgnoreCase(field,id);
			     if(!forbidden)
			    	 contral.setEditable(false);
			}
		var acceptInfoA= this.crolArray[acceptInfo];
		for(var i=0;i<acceptInfoA.length;i++)
		{
			var contral=acceptInfoA[i];	
			var id=contral.getId();
		    var forbidden = WebArrayUtil.containsToIgnoreCase(field,id);
		     if(!forbidden)
		    	 contral.setEditable(false);
		}
		var basicInfoA= this.crolArray[basicInfo];
		for(var i=0;i<basicInfoA.length;i++)
		{
			var contral=basicInfoA[i];	
			var id=contral.getId();
		     var forbidden = WebArrayUtil.containsToIgnoreCase(field,id);
		     if(!forbidden)
		    	 contral.setEditable(false);
		}
		var registerInfoA= this.crolArray[registerInfo];
		for(var i=0;i<registerInfoA.length;i++)
		{
			var contral=registerInfoA[i];	
			var id=contral.getId();
		     var forbidden = WebArrayUtil.containsToIgnoreCase(field,id);
		     if(!forbidden)
		    	 contral.setEditable(false);
		}
		var breakInfoA= this.crolArray[breakInfo];
		for(var i=0;i<breakInfoA.length;i++)
		{
			var contral=breakInfoA[i];	
			var id=contral.getId();
		     var forbidden = WebArrayUtil.containsToIgnoreCase(field,id);
		     if(!forbidden)
		    	 contral.setEditable(false);
		}
		var extractInfoA= this.crolArray[extractInfo];
		for(var i=0;i<extractInfoA.length;i++)
		{
			var contral=extractInfoA[i];	
			var id=contral.getId();
			var forbidden = WebArrayUtil.containsToIgnoreCase(field,id);
			if(!forbidden)
				contral.setEditable(false);
		}
		var cancelInfoA= this.crolArray[cancelInfo];
		for(var i=0;i<cancelInfoA.length;i++)
		{
			var contral=cancelInfoA[i];	
			var id=contral.getId();
			var forbidden = WebArrayUtil.containsToIgnoreCase(field,id);
			if(!forbidden)
				contral.setEditable(false);
		}
	}
	
}
EditPage.prototype.isChanged=function(src,des)
{
	if(src==null&&des=="")
	{
		return false;
	}
	else if(src==des)
	{
		return false;
	}
	else
	{
		return true;
	}
}

