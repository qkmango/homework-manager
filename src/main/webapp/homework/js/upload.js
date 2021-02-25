$(function () {

var $inputFile = $('#inputFile');
var $startBtn = $("#startBtn");
var $resetBtn = $("#resetBtn");
var $suspendBtn = $("#suspendBtn");

//用户信息 json
var user = '';

//homework id、course、title（从url中获取）
var hid = getUrlParam('hid');
var course = getUrlParam('course');
var title = getUrlParam('title');

//断点记录变量
let tempCheckpoint;
//上传客户端对象
let client;
// flag标志，
// 新的未上传的文件true，可继续上传的文件false
let flag = true;
//OSS_STS配置
let ossConfig;


//如果从url中获取hid失败，则返回作业列表页面
if (hid==null) {
	window.parent.cocoMessage.error(2000, "获取作业id失败，请重试！",function () {
		window.location.href='homework/homework.html';
	})
}

//设置标题title
$('#title').text(course+' '+title);



//获取OSS STS配置
$.ajax({
	url:'system/oss/getOssSts.do',
	type:'get',
	dataType:'json',
	success:function (data) {
		// OSS配置
		ossConfig = {
			region: data.region,
			accessKeyId: data.accessKeyId,
			accessKeySecret: data.accessKeySecret,
			bucket: data.bucket,
			stsToken:data.stsToken
		}
	},
	error:function () {
		window.parent.cocoMessage.error(2000, "请求失败，请刷新页面！")
	}
})

//获userinfo
$.ajax({
	url:'system/user/getUserinfo.do',
	type:'get',
	dataType:'json',
	success:function (data) {
		if (data.success) {
			user = data.user;
		} else {
			window.parent.cocoMessage.error(2000, "请登陆！",function () {
				window.location.href='system/login.html'
			})

		}
	},
	onerror:function () {
		window.parent.cocoMessage.error(2000, "请登陆！",function () {
			window.location.href='system/login.html'
		})
	}
})


// 上传方法
async function multipartUpload (filePath, data) {
	client = new OSS(ossConfig);
	try {
		let result = await client.multipartUpload(filePath, data, {
			progress: function (p, checkpoint) {
				// 断点记录点。浏览器重启后无法直接继续上传，您需要手动触发上传操作。
				tempCheckpoint = checkpoint;
				console.log(p);
				element.progress('uploadProgress', (p*100).toFixed(2)+'%');
			}
		})
		console.log(result);
		uploadSuccess(filePath);
	} catch(e){
		console.log(e);
		window.parent.cocoMessage.error(2000, "上传失败,请刷新页面!")
	}
}

// 断点续传方法
async function resumeUpload(filePath, data) {
	client = new OSS(ossConfig);
	try {
		let result = await client.multipartUpload(filePath,data,{
			progress: function (p, checkpoint) {
				tempCheckpoint = checkpoint;
				console.log(p);
				element.progress('uploadProgress', (p*100).toFixed(2)+'%');
			},
			checkpoint: tempCheckpoint
		})
		console.log(result);
		uploadSuccess(filePath);
	} catch (e) {
		console.log(e);
		window.parent.cocoMessage.error(2000, "上传失败,请刷新页面!")
	}
}

//上传完成调用
function uploadSuccess(filePath) {
	changeDisable({
		inputFile:true,
		startBtn:true,
		suspendBtn:true,
		resetBtn:true
	});

	$.ajax({
		url:'homework/commitHomework.do',
		data:{
			filePath:filePath,
			hid:hid
		},
		type:'post',
		dataType:'json',
		success:function (data) {
			if (data.success) {
				window.parent.cocoMessage.success(2000, "提交成功")
			} else {
				window.parent.cocoMessage.error(2000, "提交失败")
			}
		}
	})

	window.parent.cocoMessage.success(2000, "上传成功")
}


//更改禁用DOM
function changeDisable(disableConf) {
	if (disableConf['inputFile']) {
		$inputFile.attr("disabled",true);
		$inputFile.addClass('layui-disabled');
	} else {
		$inputFile.attr("disabled",false);
		$inputFile.removeClass('layui-disabled');
	}

	if (disableConf['startBtn']) {
		$startBtn.attr("disabled",true);
		$startBtn.addClass('layui-disabled');
	} else {
		$startBtn.attr("disabled",false);
		$startBtn.removeClass('layui-disabled');
	}

	if (disableConf['suspendBtn']) {
		$suspendBtn.attr("disabled",true);
		$suspendBtn.addClass('layui-disabled');
	} else {
		$suspendBtn.attr("disabled",false);
		$suspendBtn.removeClass('layui-disabled');
	}

	if (disableConf['resetBtn']) {
		$resetBtn.attr("disabled",true);
		$resetBtn.addClass('layui-disabled');
	} else {
		$resetBtn.attr("disabled",false);
		$resetBtn.removeClass('layui-disabled');
	}
}


//开始上传
function startUpload () {
	let data = $inputFile[0].files[0];
	let fileType = getFileType(data.name);
	let filePath = course+'/'+hid+'/'+user.id+'.'+fileType;

	multipartUpload(filePath, data);
}

//开始断点续传（继续上传）
function startMultipartUpload () {
	let data = $inputFile[0].files[0];
	let fileType = getFileType(data.name);
	let filePath = course+'/'+hid+'/'+user.id+'.'+fileType;
	resumeUpload(filePath, data);
}

// 暂停上传
function suspendUpload() {
	client.cancel();
}

// 绑定事件：清空文件
resetBtn.onclick = function() {
	inputFile.value = '';

	changeDisable({
		inputFile:false,
		startBtn:true,
		suspendBtn:true,
		resetBtn:true
	})

	element.progress('uploadProgress', 0+'%');
	flag = true;
}

// 绑定事件：文件域更改时
inputFile.onchange = function() {
	if(inputFile.value != '') {
		changeDisable({
			inputFile:false,
			startBtn:false,
			suspendBtn:true,
			resetBtn:false
		})

		element.progress('uploadProgress', 0+'%');
		flag = true;
	}
}

// 绑定事件：start按钮 上传/继续
startBtn.onclick = function() {
	changeDisable({
		inputFile:true,
		startBtn:true,
		suspendBtn:false,
		resetBtn:true
	})

	//判断是否为断点续传
	if(flag) {
		flag = false;
		// 开始上传
		startUpload();
	} else {
		// 继续上传
		startMultipartUpload();
	}
}

// 绑定事件：暂停
suspendBtn.onclick = function() {
	suspendUpload();

	changeDisable({
		inputFile:false,
		startBtn:false,
		suspendBtn:true,
		resetBtn:false
	})
}
})

//获取文件类型
function getFileType(filePath) {
	let index = filePath.lastIndexOf(".");
	let ext = filePath.substr(index+1);
	return ext;
}