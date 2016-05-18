$(function(){
    $('#dg').datagrid({
        url:'data',
        fit:true,
        pagination:true,
        fitColumns:true,
        sortName:'createDate',
        sortOrder:'desc',
        columns:[[
            {field:'ck',checkbox:true},
            {field:'name',title:'类别',width:100,align:'center',sortable:true},
            {field:'callName',title:'成员',width:100,align:'center',sortable:true,formatter: function(value,row,index){
				if (row.callName==null){
					return row.realName;
				} else {
					return row.callName;
				}
			}},
            {field:'money',title:'金额',width:100,align:'center',sortable:true,formatter: function(value,row,index){
				return value.toFixed(2);
			}},
			{field:'spendDate',title:'消费时间',width:100,align:'center',sortable:true},
            {field:'createDate',title:'添加时间',width:100,align:'center',sortable:true,order:'desc'},
            {field:'remark',title:'备注',width:200,align:'center'}
        ]],
        toolbar: '#tb'
    });
    
    $("#search_caId").combotree({
        url: '../category/treeData?type=1',
        width:150,
        onlyLeafCheck:true,
        multiple:true
    });
    
    $("#search_mId").combobox({
        url: '../member/comboboxData',
        valueField:'mId',
        textField:'callName',
        width:150,
        multiple:true
    });
    
    $('#addDialog').dialog({
        title: '增加支出',
        width: 400,
        height: 300,
        closed: true,
        cache: false,
        modal:true,
        onClose:function(){
        	$('#addForm').form("reset");
        },
        buttons:[{
			text:'提交',
			handler:function(){
				if($('#addForm').form('validate')){
					$('#addForm').form('submit', {
				        url:'add',
				        success:function(data){
				        	var data = jQuery.parseJSON(data); 
				            if(data.msg=="Y"){
				            	$.messager.alert('提示','提交成功!','info',function(){
				            		$('#dg').datagrid("reload");
					            	$('#addDialog').dialog("close");
					            	$('#addForm').form("reset");
								});
				            	
				            }else{
				            	$.messager.alert('提示','提交失败!','error');
				            }
				        }
				    });
				}
			}
		},{
			text:'取消',
			handler:function(){
				$('#addDialog').dialog("close");
				$('#addForm').form("reset");
			}
		}]
    });
    
    $('#modDialog').dialog({
        title: '修改支出',
        width: 400,
        height: 300,
        closed: true,
        cache: false,
        modal:true,
        buttons:[{
			text:'保存',
			handler:function(){
				if($('#modForm').form('validate')){
					$('#modForm').form('submit', {
				        url:'mod',
				        success:function(data){
				        	var data = jQuery.parseJSON(data); 
				            if(data.msg=="Y"){
				            	$.messager.alert('提示','保存成功!','info',function(){
				            		$('#dg').datagrid("reload");
					            	$('#modDialog').dialog("close");
					            	$('#modForm').form("reset");
								});
				            	
				            }else{
				            	$.messager.alert('提示','保存失败!','error');
				            }
				        }
				    });
				}
			}
		},{
			text:'取消',
			handler:function(){
				$('#modDialog').dialog("close");
			}
		}]
    });
    
    $("#searchBtn").click(function(){
    	$('#dg').datagrid("options").queryParams={
    		caId: $("#search_caId").combotree("getValues").toString(),
    		mId: $("#search_mId").combobox("getValues").toString(),
    		beginDate: $("#search_beginDate").datebox("getValue"),
    		endDate: $("#search_endDate").datebox("getValue"),
    		beginMoney: $("#search_beginMoney").numberbox("getValue"),
    		endMoney: $("#search_endMoney").numberbox("getValue")
    	};
    	$('#dg').datagrid("reload");
    });
    
    $("#clearBtn").click(function(){
    	$("#search_caId").combotree("clear");
		$("#search_mId").combobox("clear");
		$("#search_beginDate").datebox("clear");
		$("#search_endDate").datebox("clear");
		$("#search_beginMoney").numberbox("clear");
		$("#search_endMoney").numberbox("clear");
    	$('#dg').datagrid("options").queryParams={};
    	$('#dg').datagrid("reload");
    });
    
    $("#addBtn").click(function(){
    	$("#add_caId").combotree({
            url: '../category/treeData?type=1',
            required:true,
            onlyLeafCheck:true,
            width:144
        });
        $("#add_mId").combobox({
            url: '../member/comboboxData',
            valueField:'mId',
            textField:'callName',
            width:144,
            required:true,
            editable:false
        });
        $('#add_spend_date').datebox('setValue', formatterDate(new Date()));
    	$('#addDialog').dialog("open");
    });
    
    $("#modBtn").click(function(){
    	var rows=$('#dg').datagrid('getChecked');
		if(rows.length==0){
			$.messager.alert('提示','请选择要修改的行!','info');
		}else if(rows.length>1){
			$.messager.alert('提示','一次只能修改一行!','info');
		}else{
			$("#mod_caId").combotree({
	            url: '../category/treeData?type=1',
	            required:true,
	            width:144,
	            onlyLeafCheck:true,
	            onLoadSuccess:function(){
	            	$('#mod_caId').combotree('setValue', rows[0].caId);
	            }
	        });
	    	
	        $("#mod_mId").combobox({
	            url: '../member/comboboxData',
	            valueField:'mId',
	            textField:'callName',
	            width:144,
	            required:true,
	            editable:false,
	            onLoadSuccess:function(){
	            	$('#mod_mId').combobox('setValue', rows[0].mId);
	            }
	        });
	        $("#mod_money").textbox("setValue",rows[0].money);
	        $("#modForm input[name='sId']").val(rows[0].sId);
	        $('#mod_spend_date').datebox('setValue', rows[0].spendDate);
	        $("#mod_remark").textbox("setValue",rows[0].remark);
	    	$('#modDialog').dialog("open");
		}
    	
    });


	$("#delBtn").click(function(){
		var rows=$('#dg').datagrid('getChecked');
		if(rows.length>0){
			$.messager.confirm('提示',"确定要删除这"+rows.length+"行吗?",function(r){
			    if (r){
			    	var ids=[];
			        for(var i=0;i<rows.length;i++){
			        	ids.push(rows[i].sId);
			        }
			        $.ajax({
						url:'del',
						type : "POST", 
						dataType:"json",
						data:{ids:ids.toString()},
						success:function(data){
							if(data.msg=="Y"){
								$.messager.alert('提示','删除成功','info',function(){
									$('#dg').datagrid("reload");
								});
							}else{
								$.messager.alert('提示','删除失败','error');
							}
						}
					});
			    }
			});
		}else{
			$.messager.alert('提示','请选择要删除的行!','info');
		}
	});
    
})
function formatterDate(date) {
var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
+ (date.getMonth() + 1);
return date.getFullYear() + '-' + month + '-' + day;
};
