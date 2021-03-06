if (window.top!=window) {
	window.top.location=window.location;
}


layui.use('form', function(){
	var form = layui.form;
	 
	form.verify({
		username: function(value, item){ //value：表单的值、item：表单的DOM对象
			if(!new RegExp("^[a-zA-Z0-9]+$").test(value)||
				value.length > 10 || value.length <5) {
				return '[5-10]位字母或数字组合';
			}
		}
	});
	
	//校验完毕执行
	form.on('submit(formsubmit)', function(data){
		login();
	    return false;
	});
});


function login() {
	$.ajax({
		url:"system/user/login.do",
		data:{
			username:$.trim($("#username").val()),
			password:$.trim($("#password").val())
		},
		type:"post",
		dataType:"json",
		success:function (data) {
			if (data.success) {
				console.log("登陆成功");
				window.location.href = "index.html";
			} else{
				alert("登陆失败,请重试!");
			}
		}
	})
}
