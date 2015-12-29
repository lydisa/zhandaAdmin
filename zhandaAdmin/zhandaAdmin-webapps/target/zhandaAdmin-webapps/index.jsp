<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script type="text/javascript" src="http://code.jquery.com/jquery-1.8.0.min.js"></script>
<script type="text/javascript">

	//执行用户添加操作
		var doXml = function(){
			$.ajax({
				async: false,
				cache:false,
				type: 'POST',
				dataType : "json",//要求服务端返回的格式
				contentType:"application/json;charset=UTF-8",//请求的报文格式
				data : $("#queryStr").val(),
				url: $("#url").val()+$("#action").val(),//请求的action路径
				error: function () {//请求失败处理函数
					alert('请求失败');
				},success:function(data){
					$("#outXmlValue").val(JSON.stringify(data));
				}
			});
		}
		
		function scheduleSend(){
			doXml();
			//setInterval(doXml, 1000);
		}
</script>

</head>
<body>
<div>
	<div style="float:left;width:50%;">
		<h3>请求:</h3>
		<div style="height:30px;">请求URL: <input type="text" id="url" value="http://localhost:8080/zhandaAdmin-webapps/" style="width:70%;"/></div>
		<div style="height:30px;">请求参数: <input type="text" id="action" value="company/getCompanyById" style="width:70%;"/></div>
		<div>请求报文体： <textarea id="queryStr" rows="23" cols="80" name="queryStr">{"comId":1}</textarea></div>
	</div>
	<div style="float:right;width:50%;">
		<h3>输出结果:</h3>
		<textarea id="outXmlValue" rows="32" cols="80" name="outXmlValue" value="pageNow=1"></textarea>
	</div>
</div>
<div style="width:95%;margin-right:100px;">
	<center>
		<input type="button" value="发送业务报文" style="text-align: center;margin-top:20px;" onclick="doXml();"/>
	</center>
</div>
</body>
</html>
