$(function () {

var $inputFile = $('#inputFile');
var $startBtn = $("#startBtn");
var $resetBtn = $("#resetBtn");
var $suspendBtn = $("#suspendBtn");

//断点记录变量
let tempCheckpoint;
//
let client;
// flag标志，
// 新的未上传的文件true，可继续上传的文件false
let flag = true;
//OSS_STS配置
let ossConfig;

$.ajax({
	url:'system/oss/getOssSts.do',
	data:{},
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
		// alert('请登陆');
		// window.location.href = 'system/login.html';
	}
})




// 上传方法
async function multipartUpload (fileName, data) {
	client = new OSS(ossConfig);
	try {
		let result = await client.multipartUpload(fileName, data, {
			progress: function (p, checkpoint) {
				// 断点记录点。浏览器重启后无法直接继续上传，您需要手动触发上传操作。
				tempCheckpoint = checkpoint;
				console.log(p);
				element.progress('uploadProgress', (p*100).toFixed(2)+'%');
			}
		})
		console.log(result);
		uploadSuccess();
	} catch(e){
		console.log(e);
		alert('上传失败！请刷新页面');
	}
}

// 断点续传方法
async function resumeUpload(fileName, data) {
	client = new OSS(ossConfig);
	try {
		let result = await client.multipartUpload(fileName,data,{
			progress: function (p, checkpoint) {
				tempCheckpoint = checkpoint;
				console.log(p);
				element.progress('uploadProgress', (p*100).toFixed(2)+'%');
			},
			checkpoint: tempCheckpoint
		})
		console.log(result);
		uploadSuccess();
	} catch (e) {
		console.log(e);
		alert('上传失败！请刷新页面');
	}
}

//上传完成调用
function uploadSuccess() {
	changeDisable({
		inputFile:true,
		startBtn:true,
		suspendBtn:true,
		resetBtn:true
	});
	alert('上传成功');
	$('.layui-card .layui-card-header i').html('&#xe6af;');
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
	const data = inputFile.files[0];
	var fileName = data.name;
	multipartUpload(fileName, data);
}

//开始断点续传（继续上传）
function startMultipartUpload () {
	const data = inputFile.files[0];
	var fileName = data.name;
	resumeUpload(fileName, data);
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