// var inputFile = document.getElementById("inputFile");
// var startBtn = document.getElementById('startBtn');
// var resetBtn = document.getElementById('resetBtn');
// var suspendBtn = document.getElementById('suspendBtn');

var $inputFile = $('#inputFile');
var $startBtn = $("#startBtn");
var $resetBtn = $("#resetBtn");
var $suspendBtn = $("#suspendBtn");

var $progress = $('#progress');


// OSS配置
let ossConfig = {
	region: 'oss-cn-beijing',
	accessKeyId: 'LTAI4GBrZSGmGBHKLW9NsrpZ',
	accessKeySecret: 'JG2eVcbSh0Urye19SQrTD063ltHEW2',
	bucket: 'qkmango'
}


//断点记录变量
let tempCheckpoint;
// 
let client;
// flag标志，
// 新的未上传的文件true，可继续上传的文件false
let flag = true;


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
	} catch(e){
		console.log(e);
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
	} catch (e) {
		console.log(e);
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
	// startBtn.disabled = true;
	// suspendBtn.disabled = true;
	// resetBtn.disabled = true;
	
	$startBtn.attr("disabled",true);
	$suspendBtn.attr("disabled",true);
	$resetBtn.attr("disabled",true);
	
	$startBtn.addClass('layui-disabled');
	$suspendBtn.addClass('layui-disabled');
	$resetBtn.addClass('layui-disabled');
	
	element.progress('uploadProgress', 0+'%');
	flag = true;
}

// 绑定事件：文件域更改时
inputFile.onchange = function() {
	if(inputFile.value != '') {
		// startBtn.disabled = false;
		// suspendBtn.disabled = true;
		// resetBtn.disabled = false;
		
		$startBtn.attr("disabled",false);
		$suspendBtn.attr("disabled",true);
		$resetBtn.attr("disabled",false);
		
		$startBtn.removeClass('layui-disabled');
		$suspendBtn.addClass('layui-disabled');
		$resetBtn.removeClass('layui-disabled');
		
		element.progress('uploadProgress', 0+'%');
		flag = true;
	}
}

// 绑定事件：start按钮 上传/继续
startBtn.onclick = function() {
	
	// inputFile.disabled = true;
	// startBtn.disabled = true;
	// suspendBtn.disabled = false;
	// resetBtn.disabled = true;
	
	
	$inputFile.attr("disabled",true);
	$startBtn.attr("disabled",true);
	$suspendBtn.attr("disabled",false);
	$resetBtn.attr("disabled",true);
	
	
	$inputFile.addClass('layui-disabled');
	$startBtn.addClass('layui-disabled');
	$suspendBtn.removeClass('layui-disabled');
	$resetBtn.addClass('layui-disabled');
	
	
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
	// inputFile.disabled = false;
	// startBtn.disabled = false;
	// suspendBtn.disabled = true;
	// resetBtn.disabled = false;
	
	$inputFile.attr("disabled",false);
	$startBtn.attr("disabled",false);
	$suspendBtn.attr("disabled",true);
	$resetBtn.attr("disabled",false);
	
	$inputFile.removeClass('layui-disabled');
	$startBtn.removeClass('layui-disabled');
	$suspendBtn.addClass('layui-disabled');
	$resetBtn.removeClass('layui-disabled');
}
