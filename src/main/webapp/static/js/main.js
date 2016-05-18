$(function(){
	//$("body").height($(".tabs-panels").height());
	$("#add_caId").combotree({
    url: '../category/treeData?type=0',
    required:true,
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
   
	$("#addBtn").click(function(){
		if($('#addForm').form('validate')){
			var url;
			if($("#addForm input[name='type']:eq(0)").prop("checked")){
				url="../earning/add";
			}else{
				url="../spend/add";
			}
			$('#addForm').form('submit', {
		        url:url,
		        success:function(data){
		        	var data = jQuery.parseJSON(data); 
		            if(data.msg=="Y"){
		            	$.messager.alert('提示','提交成功!','info',function(){
			            	$('#addForm').form("reset");
						});
		            	
		            }else{
		            	$.messager.alert('提示','提交失败!','error');
		            }
		        }
		    });
		}
	});
	
	$("#czBtn").click(function(){
		$('#addForm').form("reset");
	});
	
	$("#addForm input[name='type']:eq(0)").click(function(){
		$("#add_caId").combotree("clear");
		$("#add_caId").combotree("reload","../category/treeData?type=0");
	});
	
	$("#addForm input[name='type']:eq(1)").click(function(){
		$("#add_caId").combotree("clear");
		$("#add_caId").combotree("reload","../category/treeData?type=1");
	});
	
	initChart();
});
function formatterDate(date) {
	var day = date.getDate() > 9 ? date.getDate() : "0" + date.getDate();
	var month = (date.getMonth() + 1) > 9 ? (date.getMonth() + 1) : "0"
	+ (date.getMonth() + 1);
	return date.getFullYear() + '-' + month + '-' + day;
};

function initChart(){
	var date_ = new Date();  
	var year = date_.getYear()+1900;  
	var month = date_.getMonth() + 1;  
	var month_first = year + '-' + (month<9?"0"+month:month) + '-01';
	  
	var day = new Date(year,month,0);      
	var month_last = year + '-' + (month<9?"0"+month:month) + '-' + day.getDate()
	$.ajax({
		type : "POST",
		url : "../stats/timeStatsData?type=1",
		dataType:"json",
		data : {
			beginDate :month_first,
			endDate :month_last
		},
		success : function(data) {
			data = data.rows;
			var xaxis = [];
			var yaxis = [];
			var seriesData2 = new Array();
			for ( var i in data) {
				xaxis.push(data[i].szDate);
				yaxis.push(data[i].money);
				var o=new Object();
            	o.name=data[i].szDate;
            	o.value=data[i].money;
            	seriesData2.push(o);
			}

			var seriesData = new Array();
			seriesData.push({
				name : "金额",
				stack : "总量",
				type : "bar",
				label : {
					normal : {
						show : true,
						position : 'inside'
					}
				},
				data : yaxis
			});
			var myChart = echarts.init(document.getElementById('chart'), 'macarons');
			var option = {
				tooltip : {
					trigger : 'axis',
					formatter: "{b}<br/>支出{c}元 ",
					axisPointer : {
						type : 'shadow'
					}
				},
				xAxis : {
					type : 'category',
					data : xaxis
				},
				yAxis : {
					type : 'value'
				},
				series : seriesData
			};
			myChart.setOption(option);
		}
	});
	
	
	$.ajax({
		type : "POST",
		url : "../stats/categoryStatsData?type=1",
		dataType:"json",
		data : {
			beginDate :month_first,
			endDate :month_last
		},
		success : function(data) {
			data = data.rows;
			var seriesData2 = new Array();
			for ( var i in data) {
				var o=new Object();
            	o.name=data[i].name;
            	o.value=data[i].money;
            	seriesData2.push(o);
			}

			var myChart2 = echarts.init(document.getElementById('chart2'), 'macarons');
			
			
			option2 = {
		    	    tooltip : {
		    	        trigger: 'item',
		    	        formatter: "{b}<br/>{c}元 ({d}%)"
		    	    },
		    	    series : [
		    	        {
		    	            name: "支出",
		    	            type: 'pie',
		    	            radius : '80%',
		    	            center: ['50%', '50%'],
		    	            data:seriesData2,
		    	            itemStyle: {
		    	                emphasis: {
		    	                    shadowBlur: 10,
		    	                    shadowOffsetX: 0,
		    	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		    	                }
		    	            }
		    	        }
		    	    ]
		    	};
		    myChart2.setOption(option2);
		}
	});
	
	$.ajax({
		type : "POST",
		dataType:"json",
		url : "../stats/memberStatsData?type=1",
		data : {
			beginDate :month_first,
			endDate :month_last
		},
		success : function(data) {
			data = data.rows;
			var seriesData2 = new Array();
			for ( var i in data) {
				var o=new Object();
            	o.name=data[i].callName==null?data[i].realName:data[i].callName;
            	o.value=data[i].money;
            	seriesData2.push(o);
			}
			var myChart2 = echarts.init(document.getElementById('chart3'), 'macarons');
			option2 = {
		    	    tooltip : {
		    	        trigger: 'item',
		    	        formatter: "{b} <br/>{a}{c}元 ({d}%)"
		    	    },
		    	    series : [
		    	        {
		    	            name: "支出",
		    	            type: 'pie',
		    	            radius : '80%',
		    	            center: ['50%', '50%'],
		    	            data:seriesData2,
		    	            itemStyle: {
		    	                emphasis: {
		    	                    shadowBlur: 10,
		    	                    shadowOffsetX: 0,
		    	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
		    	                }
		    	            }
		    	        }
		    	    ]
		    	};
		    myChart2.setOption(option2);
		}
	});
}
