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
            {field:'realName',title:'姓名',width:100,align:'center',sortable:true},
            {field:'callName',title:'称呼',width:100,align:'center',sortable:true},
            {field:'gender',title:'性别',width:100,align:'center',sortable:true,
            	formatter: function(value,row,index){
    				if (row.gender==0){
    					return "男";
    				} else {
    					return "女";
    				}
    			}},
            {field:'addDate',title:'添加时间',width:100,align:'center',sortable:true,order:'desc'}
        ]],
        toolbar: '#tb'
    });
    
    $("#addBtn").click(function(){
    	 $('#addDialog').dialog("open");
    });
    
    $("#modBtn").click(function(){
    	var rows=$('#dg').datagrid('getChecked');
		if(rows.length==0){
			$.messager.alert('提示','请选择要修改的行!','info');
		}else if(rows.length>1){
			$.messager.alert('提示','一次只能修改一行!','info');
		}else{
			$("#modRealName").textbox("setValue",rows[0].realName);
			$("#modCallName").textbox("setValue",rows[0].callName);
			$("#modForm input[name='mId']").val(rows[0].mId);
			$("#modForm input[name='gender']:eq("+rows[0].gender+")").prop("checked","checked");
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
			        	ids.push(rows[i].mId);
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
        title: '增加家庭成员',
        width: 400,
        height: 200,
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
        title: '修改家庭成员',
        width: 400,
        height: 200,
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
    		realName: $("#realName").textbox("getText")
    	};
    	$('#dg').datagrid("reload");
    });
    
    $("#clearBtn").click(function(){
    	$("#realName").textbox("clear");
    	$('#dg').datagrid("options").queryParams={
    		realName: ""
    	};
    	$('#dg').datagrid("reload");
    });
});
