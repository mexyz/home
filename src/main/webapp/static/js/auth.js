$(function(){
	$('#role').datagrid({
        url:'role',
        width:200,
        height:400,
        fitColumns:true,
        singleSelect:true,
        columns:[[
            {field:'roleName',title:'角色',width:100,align:'center'}
        ]],
        onSelect:function(index,row){
        	$('#member').datagrid("options").queryParams={
        		rId: row.rId
        	};
        	$('#member').datagrid("reload");
        	$('#menu').datagrid("options").queryParams={
        		rId: row.rId
        	};
        	$('#menu').datagrid("reload");
        	$('#action').datagrid("options").queryParams={
        		rId: row.rId
        	};
        	$('#action').datagrid("reload");
        }
    });
	$('#member').datagrid({
        url:'member',
        width:200,
        height:400,
        fitColumns:true,
        columns:[[
            {field:'ck',checkbox:true},
            {field:'realName',title:'家庭成员',width:100,align:'center',formatter:function(value,row,index){
            	if(row.callName){
            		return row.callName;
            	}else{
            		return row.realName;
            	}
            }}
        ]],
        onLoadSuccess:function(data){
        	for(var i=0;i<data.rows.length;i++){
        		if(data.rows[i].rId&&$('#role').datagrid("getSelections").length>0){
        			$('#member').datagrid('checkRow',i);
        		}
        	}
        	
        }
    });
	
	 $('#menu').datagrid({
	        url:"menu",
	        width:200,
	        height:400,
	        idField:'mId',
	        fitColumns:true,
	        columns:[[
	                  {field:'ck',checkbox:true},
	                  {title:'功能',field:'text',width:100,align:'center'}
              ]],
              onLoadSuccess:function(data){
            	  
            	  $('#menu').datagrid('unselectAll');
            	 data=data.rows;
            	 for(var n in data){
            		 if(data[n].rId!=null){
            			 $('#menu').datagrid('selectRecord',data[n].mId);
            		 }
            	 }
            	
              	
              	
              }//,
              /*  onSelect:function(row){
            	  if(row.id=="10100"||row.id=="10200"||row.id=="10300"){
            		  var ch=$('#menu').treegrid('getChildren',row.id);
            		  for(var i=0;i<ch.length;i++){
            			  $('#menu').treegrid('select',ch[i].id);
            		  }
            		  
            	  }
              },
              onUnselect:function(row){
            	  if(row.id=="10100"||row.id=="10200"||row.id=="10300"){
            		  var ch=$('#menu').treegrid('getChildren',row.id);
            		  for(var i=0;i<ch.length;i++){
            			  $('#menu').treegrid('unselect',ch[i].id);
            		  }
            		  
            	  }
              }*/
	  });
	 $('#action').datagrid({
	        url:'action',
	        width:200,
	        height:400,
	        fitColumns:true,
	        columns:[[
	            {field:'ck',checkbox:true},
	            {field:'describe',title:'操作名称',width:100,align:'center'}
	        ]],
	        onLoadSuccess:function(data){
	        	for(var i=0;i<data.rows.length;i++){
	        		if(data.rows[i].rId&&$('#role').datagrid("getSelections").length>0){
	        			$('#action').datagrid('checkRow',i);
	        		}
	        	}
	        	
	        }
	    });
	 
	 $("#modBtn").click(function(){
		 if($('#role').datagrid("getSelections").length>0){
			 var menuIds=[];
			 var memberIds=[];
			 var actionIds=[];
			 var mb=$('#member').datagrid("getSelections");
			 for(var i=0;i<mb.length;i++){
				 memberIds.push(mb[i].mId);
			 }
			 var m=$('#menu').datagrid("getSelections");
			 var m1="";
			 var m2="";
			 var m3="";
			 for(var i=0;i<m.length;i++){
				 menuIds.push(m[i].mId);
				 var ss=(m[i].mId+"").substring(0,3);
				 if(ss=="101"&&m1==""){
					 m1="10100";
				 }
				 if(ss=="102"&&m2==""){
					 m2="10200";
				 }
				 if(ss=="103"&&m3==""){
					 m3="10300";
				 }
			 }
			 if(m1!=""){
				 menuIds.push(m1);
			 }
			 if(m2!=""){
				 menuIds.push(m2);
			 }
			 if(m3!=""){
				 menuIds.push(m3);
			 }
			 var a=$('#action').datagrid("getSelections");
			 for(var i=0;i<a.length;i++){
				 actionIds.push(a[i].aId);
			 }
			 $.ajax({
				  type: "post",
				  url: "mod",
				  dataType: "json",
				  data:{"rId":$('#role').datagrid('getSelected').rId,"memberIds":memberIds.toString(),"menuIds":menuIds.toString(),"actionIds":actionIds.toString()},
				  success: function(data){
					  if(data.msg=="N"){
						  $.messager.alert('提示','保存失败!','error');
					  }else{
						  $.messager.alert('提示','保存成功!','info');
					  }
					  
				  }
			});
		 }else{
			 $.messager.alert('提示','请选择角色!','info');
		 }
		 
	 });
})

