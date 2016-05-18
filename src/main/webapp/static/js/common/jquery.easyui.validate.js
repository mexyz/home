$.extend($.fn.validatebox.defaults.rules, {
    name: {
        validator: function(value, param){
        return  /^([\u4E00-\u9FA5]{2,10}|[a-zA-Z]{2,50})$/.test(value);
        },
        message: '请输入正确的姓名'
    },
    category: {
        validator: function(value, param){
        return  /^([\u4E00-\u9FA5]{2,10})$/.test(value);
        },
        message: '请输入正确的分类名称'
    },
    recategory: {
        validator: function(value, param){
        	var data={};
        	data["name"]=value;
        	if(param[0]==1){
        		data["caId"]=$("#modForm1 input[name='caId']").val();
        	}else if(param[0]==2){
        		data["caId"]=$("#modForm2 input[name='caId']").val();
        	}
	        if(/^([\u4E00-\u9FA5]{2,10})$/.test(value)){
	        	var t=true;
	        	$.ajax({
      			  type: "post",
      			  url: "../category/validateCategory",
      			  async:false,
      			  dataType: "json",
      			  data:data,
      			  success: function(data){
      				  if(data.msg=="N"){
      					t=false;
      				  }else{
      					 t=true;
      				  }
      				  
      			  }
				});
	        	return t;
	        }else{
	        	return false;
	        }
        },
        message: '该分类已存在'
    },
    call: {
        validator: function(value, param){
        return  /^([\u4E00-\u9FA5]+|[a-zA-Z]+)$/.test(value);
        },
        message: '请输入正确的称呼'
    },
    username: {
        validator: function(value, param){
	        return /^[a-z0-9-_]{6,12}$/.test(value);
        },
        message: '请输入正确的用户名'
    },
    reusername: {
        validator: function(value, param){
	        if(/^[a-z0-9-_]{6,12}$/.test(value)){
	        	var t=true;
	        	$.ajax({
      			  type: "post",
      			  url: "../user/validateUserName",
      			  async:false,
      			  dataType: "json",
      			  data:"userName="+value,
      			  success: function(data){
      				  if(data.msg=="N"){
      					t=false;
      				  }else{
      					 t=true;
      				  }
      				  
      			  }
				});
	        	return t;
	        }else{
	        	return false;
	        }
        },
        message: '该用户名已存在'
    },
    password: {
        validator: function(value, param){
        return  /^[A-Za-z0-9-_]{6,12}$/.test(value);
        },
        message: '密码格式不正确'
    },
    repassword: {
        validator: function(value, param){
	        if(param[0]==0){
	        	return value==$("#addForm input[name='password']").val();
	        }else if(param[0]==1){
	        	return value==$("#modPassForm input[name='password']").val();
	        }
        },
        message: '两次密码不一致'
    },
    roleName:{
    	validator: function(value, param){
            return  /^[\u4E00-\u9FA5]{2,10}$/.test(value);
            },
            message: '请输入正确的角色名称'
    }
    ,
    reRoleName:{
    	validator: function(value, param){
	        if(/^[\u4E00-\u9FA5]{2,10}$/.test(value)){
	        	var t=true;
	        	$.ajax({
      			  type: "post",
      			  url: "../role/validateRoleName",
      			  async:false,
      			  dataType: "json",
      			  data:"roleName="+value,
      			  success: function(data){
      				  if(data.msg=="N"){
      					t=false;
      				  }else{
      					 t=true;
      				  }
      				  
      			  }
				});
	        	return t;
	        }else{
	        	return false;
	        }
        },
        message: '该角色已存在'
    },
    modReRoleName:{
    	validator: function(value, param){
	        if(/^[\u4E00-\u9FA5]{2,10}$/.test(value)){
	        	var t=true;
	        	$.ajax({
      			  type: "post",
      			  url: "../role/validateRoleName",
      			  async:false,
      			  dataType: "json",
      			  data:"roleName="+value+"&rId="+$("#modForm input[name='rId']").val(),
      			  success: function(data){
      				  if(data.msg=="N"){
      					t=false;
      				  }else{
      					 t=true;
      				  }
      				  
      			  }
				});
	        	return t;
	        }else{
	        	return false;
	        }
        },
        message: '该角色已存在'
    }
});