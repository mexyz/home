$(function(){
    $('#dg').datagrid({
        url:'data',
        fit:true,
        pagination:true,
        fitColumns:true,
        sortName:'addDate',
        sortOrder:'desc',
        columns:[[
            {field:'ck',checkbox:true},
            {field:'userName',title:'用户名',width:100,align:'center',sortable:true},
            {field:'roleName',title:'角色',width:100,align:'center',sortable:true},
            {field:'loginIp',title:'最后一次登录IP',width:100,align:'center',sortable:true},
            {field:'loginTime',title:'最后一次登录时间',width:100,align:'center',sortable:true},
            {field:'addDate',title:'添加时间',width:100,align:'center',sortable:true,order:'desc'}
        ]],
        toolbar: '#tb'
    });
    
    
    $('#addDialog').dialog({
        title: '增加用户',
        width: 400,
        height: 250,
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
    
    $('#modPassDialog').dialog({
        title: '修改密码',
        width: 400,
        height: 200,
        closed: true,
        cache: false,
        modal:true,
        buttons:[{
			text:'保存',
			handler:function(){
				if($('#modPassForm').form('validate')){
					$('#modPassForm').form('submit', {
				        url:'mod',
				        success:function(data){
				        	var data = jQuery.parseJSON(data); 
				            if(data.msg=="Y"){
				            	$.messager.alert('提示','保存成功!','info',function(){
				            		$('#dg').datagrid("reload");
					            	$('#modPassDialog').dialog("close");
					            	$('#modPassForm').form("reset");
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
				$('#modPassDialog').dialog("close");
			}
		}]
    });
    
    
    $('#modRoleDialog').dialog({
        title: '修改角色',
        width: 400,
        height: 200,
        closed: true,
        cache: false,
        modal:true,
        buttons:[{
			text:'保存',
			handler:function(){
				if($('#modRoleForm').form('validate')){
					$('#modRoleForm').form('submit', {
				        url:'mod',
				        success:function(data){
				        	var data = jQuery.parseJSON(data); 
				            if(data.msg=="Y"){
				            	$.messager.alert('提示','保存成功!','info',function(){
				            		$('#dg').datagrid("reload");
					            	$('#modRoleDialog').dialog("close");
					            	$('#modRoleForm').form("reset");
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
				$('#modRoleDialog').dialog("close");
			}
		}]
    });
    
    $("#searchBtn").click(function(){
    	$('#dg').datagrid("options").queryParams={
    		userName: $("#userName").textbox("getText"),
    		roleName: $("#roleName").textbox("getText"),
    		beginTime: $("#beginTime").datetimebox("getValue"),
    		endTime: $("#endTime").datetimebox("getValue")
    	};
    	$('#dg').datagrid("reload");
    });
    
    $("#clearBtn").click(function(){
    	$("#userName").textbox("clear");
		$("#roleName").textbox("clear");
		$("#beginTime").datetimebox("reset");
		$("#endTime").datetimebox("reset");
    	$('#dg').datagrid("options").queryParams={
    		userName:"",
    		loginIp:"",
    		beginTime:"",
    		endTime:""
    	};
    	$('#dg').datagrid("reload");
    });
    
    
})

function add(){
    	$("#roleAdd").combobox({
            url:'../role/comboboxData',
            width:200,
            required:true,
            editable:false,
            valueField:'rId',
            textField:'roleName'
        });
    	$('#addDialog').dialog("open");
}

function mod(type){
	var rows=$('#dg').datagrid('getChecked');
	if(rows.length==0){
		$.messager.alert('提示','请选择要修改的行!','info');
	}else if(rows.length>1){
		$.messager.alert('提示','一次只能修改一行!','info');
	}else{
		if(type==0){
			$("#modPassForm input[name='uId']").val(rows[0].uId);
			$("#modUserName").text(rows[0].userName);
			$('#modPassDialog').dialog("open");
		}
		if(type==1){
			$("#roleMod").combobox({
		        url:'../role/comboboxData',
		        width:200,
		        required:true,
		        editable:false,
		        valueField:'rId',
		        textField:'roleName',
		        onLoadSuccess:function(){
		        	$("#roleMod").combobox("setValue",rows[0].rId);
		        }
		    });
			$("#modRoleForm input[name='uId']").val(rows[0].uId);
			$("#modRoleUserName").text(rows[0].userName);
			$('#modRoleDialog').dialog("open");
		}
	}
	
	
	
}


function delData(){
	var rows=$('#dg').datagrid('getChecked');
	if(rows.length>0){
		$.messager.confirm('提示',"确定要删除这"+rows.length+"行吗?",function(r){
		    if (r){
		    	var ids=[];
		        for(var i=0;i<rows.length;i++){
		        	ids.push(rows[i].uId);
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
}