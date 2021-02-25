// 七牛相关================
// token
var qiniuToken;
// 空间外链
var link = 'http://qjqdf1rtz.hn-bkt.clouddn.com/';
// subscription对象，可以调用此对象方法取消上传，全局变量
var subscription;

// DOM对象==================
// 文件列表
var $filelist = $('#filelist');
// 进度条===========
var $uploading = $('.sr-only');
// 文件域
var $file = $('#file');
$file.on('change',function() {
	$uploading.css("width","0%");
})
// 清空按钮
var $cleanBtn = $('#clean');
$cleanBtn.on('click',function() {
	if(isuploading) {
		alert("文件正在上传，请先取消！");
		return;
	}
	$uploading.css("width","0%");
	$file.val('');
})

// 取消按钮
var $cancel = $('#cancel');
$cancel.on('click',function() {
	if(!isuploading) {
		return;
	}
	subscription.unsubscribe();
	var $types = $('#filelist td:last');
	$types.css("color","orange");
	$types.text("取消上传！");
	$uploading.css("width","0%");
	isuploading = false;
})


// js逻辑===========================
// 正在上传文件吗，防止重复上传
var isuploading = false;



// 获取七牛token===========
$.ajax({
	url:"http://localhost:8080/qiniu/getQiniuToken",
	type:"get",
	async:true,
	error:function(){
		// 请求错误时执行的函数
	},
	success:function(data){
		//data就是responseText
		qiniuToken = data;
		console.log(qiniuToken);
	}
})



// 上传按钮点击函数
$("#upload").click(function(){

	var obj = $("#file");
	if(obj.val() == '') {
		alert("未选择文件");
		return;
	}
	// 正在上传，防止重复上传
	if(isuploading) {
		return;
	}
	
	isuploading = true;

	// 获取文件在本地的绝对路径 C:\fakepath\cover.jpg
	var filePath = obj.val();
	// 获取文件后缀名 .jpg
	var suffix = filePath.substring(filePath.lastIndexOf("."),filePath.length)
	// 获取文件对象
	var file = obj.get(0).files[0];
	// 文件名
	var fileName = file.name;
	// 获取文件大小 单位KB
	var size = (file.size / 1024).toFixed(2)
	
	// 设置上传的七牛key 上传成功需要把这个值提交到后台
	// 文件最终在空间中的名称
	// var qiniuKey = "user/image/20190906/" + suffix;
	var qiniuKey = fileName;

	//返回位于String对象中指定位置的子字符串并转换为小写
	var fileType = fileName.substring(fileName.lastIndexOf(".")).toLowerCase();

	
	// 上传文件到七牛
	var observer = {
		//上传过程的监听函数 result参数带有total字段的 object，包含loaded、total、percent三个属性)
		next(result){
			// 实现上传进度条 ...
			console.log(result.total.percent);
			$uploading.css("width",result.total.percent+"%");

		},
		//上传失败回调
		error(err){
			isuploading = false;
			console.log(err.message);
			// alert(err.message);

			$types.css("color","red");
			$types.text("上传失败！");

		},
		// 上传完成回调
		complete(res){
			isuploading = false;
			// 提交数据给后端同事等业务逻辑 ....
			console.log(res)
			console.log(link+file.name);
			$('#filelist tr:last-child td:nth-child(4)').html("<a href='"+link+file.name+"' target='_blank'>链接</a>");
			$types.text("上传成功！");
		}
	};
	var putExtra = {
		//原文件名
		fname: fileName,
		//用来放置自定义变量
		params: {},
		//限制上传文件类型
		mimeType: null
	};
	var config = {
		//存储区域(z0:代表华东;z2:代表华南,不写默认自动识别)
		region:qiniu.region.z2,
		//分片上传的并发请求量
		concurrentRequestLimit:2
	};
	var observable = qiniu.upload(file,qiniuKey,qiniuToken,putExtra,config);
	// 上传开始
	subscription = observable.subscribe(observer);
	// 取消上传
	// subscription.unsubscribe();
})