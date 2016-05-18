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
            {field:'roleName',title:'角色',width:100,align:'center',sortable:true},
            {field:'addDate',title:'添加时间',width:100,align:'center',sortable:true,order:'desc'}
        ]],
        toolbar: '#tb'
    });
    
    $("#addBtn").click(function(){
    	/*$('#addRoleCombotree').combotree({
        url: '../menu/data',
        width:200,
        lines:true,
        onlyLeafCheck:true,
        prompt:"请选择",
        multiple:true,
        checkbox:true,
        required: true,
        editable:false
		 });*/
		 $('#addDialog').dialog("open");
    });
    
    $("#modBtn").click(function(){
    	var rows=$('#dg').datagrid('getChecked');
		if(rows.length==0){
			$.messager.alert('提示','请选择要修改的行!','info');
		}else if(rows.length>1){
			$.messager.alert('提示','一次只能修改一行!','info');
		}else{
			$("#modRoleName").textbox("setValue",rows[0].roleName);
			
			$("#modForm input[name='rId']").val(rows[0].rId);
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
			        	ids.push(rows[i].rId);
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
    
    $('#addDialog').dialog({
        title: '增加角色',
        width: 400,
        height: 140,
        closed: true,
        cache: false,
        modal:true,
        onClose:function(){
        	$('#addForm').form("reset");
        },
        buttons:[{
			text:'提交',
			handler:function(){
				/*var tree=$('#addRoleCombotree').combotree('tree');	
				var nodes=tree.tree("getChecked");
				var mIds=[];
				for(var i=0;i<nodes.length;i++){
					mIds.push(nodes[i].id);
					var node=tree.tree("getParent",nodes[i].target);
					if(node!=null){
						var t=true;
						for(var k=0;k<mIds.length;k++){
							if(node.id==mIds[k]){
								t=false;
							}
						}
						if(t){
							mIds.push(node.id);
						}
						
					}
					
				}
				
				$("#addForm input[name='mIds']").val(mIds.toString());*/
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
        title: '修改角色',
        width: 400,
        height: 140,
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
    		roleName: $("#roleName").textbox("getText")
    	};
    	$('#dg').datagrid("reload");
    });
    
    $("#clearBtn").click(function(){
    	$("#roleName").textbox("clear");
    	$('#dg').datagrid("options").queryParams={
    		roleName:""
    	};
    	$('#dg').datagrid("reload");
    });
    
});

function add(){
    	$("#roleAdd").combobox({
            url:'comboboxData',
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
		        url:'comboboxData',
		        width:200,
		        required:true,
		        editable:false,
		        valueField:'rId',
		        textField:'roleName',
		        onLoadSuccess:function(){
		        	$("#roleMod").combobox("setValue",rows[0].rId);
		        }
		    });
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
		        	ids.push(rows[i].rId);
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