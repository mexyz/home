$(function(){
    $('#dg').treegrid({
    	url:'data',
        fit:true,
        lines:true,
        fitColumns:true,
        singleSelect:false,
        idField:'caId',
        treeField:'name',
        columns:[[
            {field:'ck',checkbox:true},
            {field:'name',title:'名称',width:100,align:'left'}
        ]],
        queryParams:{
        	"type":0
        },
        toolbar: '#tb'
    });
    $("#addBtn").click(function(){
    	var rows=$("#dg").treegrid("getSelections");
    	var t=$(".searchDiv input[name='type']:first").prop("checked");
    	if(rows.length==0){
    		$("#addForm1 input[name='type']").val(t?0:1);
    		$('#addDialog1').dialog("open");
    	}else if(rows.length==1){
    		if($("#dg").treegrid("getLevel",rows[0].caId)==1){
    			$("#addForm2 input[name='type']").val(t?0:1);
    			$("#addForm2 input[name='pId']").val(rows[0].caId);
    			$('#addDialog2').dialog("open");
    		}else{
    			$.messager.alert('提示','请选择一级分类','info');
    		}
    		
    	}else{
    		$.messager.alert('提示','一次只能添加一个分类','info');
    	}
    });
    
    $("#modBtn").click(function(){
    	var rows=$("#dg").treegrid("getSelections");
    	if(rows.length==1){
    		if($("#dg").treegrid("getLevel",rows[0].caId)==1){
    			$("#modForm1 input[name='caId']").val(rows[0].caId);
    			$("#modName1").textbox("setText",rows[0].name);
    			$('#modDialog1').dialog("open");
    		}else{
    			$("#modForm2 input[name='caId']").val(rows[0].caId);
    			$("#modName2").textbox("setValue",rows[0].name);
    			$('#modDialog2').dialog("open");
    		}
    	}else{
    		$.messager.alert('提示','一次只能修改一条记录','info');
    	}
    });
    
    $("#delBtn").click(function(){
    	var rows=$("#dg").treegrid("getSelections");
    	if(rows.length>0){
			$.messager.confirm('提示',"确定要删除这"+rows.length+"行吗?",function(r){
			    if (r){
			    	var ids=[];
			        for(var i=0;i<rows.length;i++){
			        	ids.push(rows[i].caId);
			        }
			        $.ajax({
						url:'del',
						type : "POST", 
						dataType:"json",
						data:{ids:ids.toString()},
						success:function(data){
							if(data.msg=="Y"){
								$.messager.alert('提示','删除成功','info',function(){
									$('#dg').treegrid("reload");
									$('#dg').treegrid("uncheckAll");
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
    
    $('#addDialog1').dialog({
        title: '增加一级分类',
        width: 400,
        height: 150,
        closed: true,
        cache: false,
        modal:true,
        onClose:function(){
        	$('#addForm1').form("reset");
        },
        buttons:[{
			text:'提交',
			handler:function(){
				if($('#addForm1').form('validate')){
					$('#addForm1').form('submit', {
				        url:'add',
				        success:function(data){
				        	var data = jQuery.parseJSON(data); 
				            if(data.msg=="Y"){
				            	$.messager.alert('提示','提交成功!','info',function(){
				            		$('#dg').treegrid("reload");
					            	$('#addDialog1').dialog("close");
					            	$('#addForm1').form("reset");
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
				$('#addDialog1').dialog("close");
				$('#addForm1').form("reset");
			}
		}]
    });
    
    
    
    $('#addDialog2').dialog({
        title: '增加二级分类',
        width: 400,
        height: 150,
        closed: true,
        cache: false,
        modal:true,
        onClose:function(){
        	$('#addForm2').form("reset");
        },
        buttons:[{
			text:'提交',
			handler:function(){
				if($('#addForm2').form('validate')){
					$('#addForm2').form('submit', {
				        url:'add',
				        success:function(data){
				        	var data = jQuery.parseJSON(data); 
				            if(data.msg=="Y"){
				            	$.messager.alert('提示','提交成功!','info',function(){
				            		$('#dg').treegrid("reload");
					            	$('#addDialog2').dialog("close");
					            	$('#addForm2').form("reset");
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
				$('#addDialog2').dialog("close");
				$('#addForm2').form("reset");
			}
		}]
    });
    
    $('#modDialog1').dialog({
        title: '修改一级分类',
        width: 400,
        height: 150,
        closed: true,
        cache: false,
        modal:true,
        onClose:function(){
        	$('#modForm1').form("reset");
        },
        buttons:[{
			text:'保存',
			handler:function(){
				if($('#modForm1').form('validate')){
					$('#modForm1').form('submit', {
				        url:'mod',
				        success:function(data){
				        	var data = jQuery.parseJSON(data); 
				            if(data.msg=="Y"){
				            	$.messager.alert('提示','保存成功!','info',function(){
				            		$('#dg').treegrid("reload");
					            	$('#modDialog1').dialog("close");
					            	$('#modForm1').form("reset");
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
				$('#modDialog1').dialog("close");
				$('#modForm1').form("reset");
			}
		}]
    });
    
    
    
    $('#modDialog2').dialog({
        title: '修改二级分类',
        width: 400,
        height: 150,
        closed: true,
        cache: false,
        modal:true,
        onClose:function(){
        	$('#modForm2').form("reset");
        },
        buttons:[{
			text:'保存',
			handler:function(){
				if($('#modForm2').form('validate')){
					$('#modForm2').form('submit', {
				        url:'mod',
				        success:function(data){
				        	var data = jQuery.parseJSON(data); 
				            if(data.msg=="Y"){
				            	$.messager.alert('提示','保存成功!','info',function(){
				            		$('#dg').treegrid("reload");
					            	$('#modDialog2').dialog("close");
					            	$('#modForm2').form("reset");
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
				$('#modDialog2').dialog("close");
				$('#modForm2').form("reset");
			}
		}]
    });
    
    
    
    $("#searchBtn").click(function(){
    	var t=$(".searchDiv input[name='type']:first").prop("checked");
    	$('#dg').treegrid("options").queryParams={
    		type: t?0:1
    	};
    	$('#dg').treegrid("reload");
    });
    
    $("#clearBtn").click(function(){
    	$(".searchDiv input[name='type']:first").prop("checked",true)
    	$('#dg').treegrid("options").queryParams={
    		type:0
    	};
    	$('#dg').treegrid("reload");
    });
    
    
})



