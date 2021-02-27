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

	var ossConfig;


	//如果从url中获取hid失败，则返回作业列表页面
	if (hid==null) {
		window.parent.cocoMessage.error(2000, "获取作业id失败，请重试！",function () {
			window.location.href='homework/homework.html';
		})
	}

	//设置标题title
	$('#title').text(title);
	//设置徽章：学科
	$('#badge-course').attr('src','https://img.shields.io/badge/学科-'+course+'-5FB878.svg');

	//获取OSS STS配置
	$.ajax({
		url:'system/oss/getOssSts.do',
		type:'get',
		dataType:'json',
		async:false,
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
		async:false,
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


	uploadOSS({
		"ossConfig":ossConfig,
		"$inputFile":$inputFile,
		"$resetBtn":$resetBtn,
		"$startBtn":$startBtn,
		"$suspendBtn":$suspendBtn,
		"course":course,
		"hid":hid,
		"user":user,
		"getFilePath":function (innerConf) {
			//这个innerConfig其实就是调用uploadOSS函数传入的此json，再加入了一个fileType而已
			return innerConf.course+'/'+innerConf.hid+'/'+innerConf.user.id+'.'+innerConf.fileType;
		},
		"uploadSuccessCallBack":function () {
			window.parent.cocoMessage.success(2000, "上传成功")
		},
		"uploadErrorCallback":function () {
			window.parent.cocoMessage.error(2000, "上传失败,请刷新页面!")
		},
		"commitSuccessCallback":function () {
			window.parent.cocoMessage.success(2000, "提交成功")
		},
		"commitErrorCallback":function (){
			window.parent.cocoMessage.error(2000, "提交失败")
		},
		"uploadSuccessTodo":function (config) {
			console.log(config.filePath)
			console.log(config.hid)
			$.ajax({
				url:'homework/commitHomework.do',
				data:{
					"filePath":config.filePath,
					"hid":config.hid
				},
				type:'post',
				dataType:'json',
				success:function (data) {
					if (data.success) {
						config.commitSuccessCallback(config);
					} else {
						config.commitErrorCallback(config);
					}
				}
			})
		}
	});



})


/**
 * 上传至阿里云OSS
 * @param config
 */
function uploadOSS(config) {
	let tempCheckpoint;	//断点记录变量
	let client;			//上传客户端对象
	let flag = true;	// flag标志， 新的未上传的文件true，可继续上传的文件false

	//开始上传
	function startUpload () {
		let data = config.$inputFile[0].files[0];
		let fileType = getFileType(data.name);
		//将传入的json参数再加入一个值
		config.fileType = fileType;
		let filePath = config.getFilePath(config);

		multipartUpload(filePath, data);
	}

	//开始断点续传（继续上传）
	function startMultipartUpload () {
		let data = config.$inputFile[0].files[0];
		let fileType = getFileType(data.name);
		config.fileType = fileType;
		let filePath = config.getFilePath(config);
		resumeUpload(filePath, data);
	}

	// 暂停上传
	function suspendUpload() {
		client.cancel();
	}

	// 上传方法
	async function multipartUpload (filePath, data) {
		client = new OSS(config.ossConfig);
		try {
			let result = await client.multipartUpload(filePath, data, {
				progress: function (p, checkpoint) {
					// 断点记录点。浏览器重启后无法直接继续上传，您需要手动触发上传操作。
					tempCheckpoint = checkpoint;
					element.progress('uploadProgress', (p*100).toFixed(2)+'%');
				}
			})
			config.filePath = filePath;
			console.log(result);
			config.uploadSuccessCallBack(config);
			uploadSuccess(config);
		} catch(e){
			console.log(e);
			config.uploadErrorCallback(config);
		}
	}

	// 断点续传方法
	async function resumeUpload(filePath, data) {
		client = new OSS(config.ossConfig);
		try {
			let result = await client.multipartUpload(filePath,data,{
				progress: function (p, checkpoint) {
					tempCheckpoint = checkpoint;
					console.log(p);
					element.progress('uploadProgress', (p*100).toFixed(2)+'%');
				},
				checkpoint: tempCheckpoint
			})
			config.filePath = filePath;
			console.log(result);
			config.uploadSuccessCallBack(config);
			uploadSuccess(config);
		} catch (e) {
			console.log(e);
			config.uploadErrorCallback(config);
		}
	}

	//上传完成调用
	function uploadSuccess(config) {
		//撒花
		sh();
		changeDisable({
			inputFile:true,
			startBtn:true,
			suspendBtn:true,
			resetBtn:true
		});
		config.uploadSuccessTodo(config);
	}

	// 绑定事件：清空文件
	config.$resetBtn.click(function () {
		//判断是否为空
		config.$inputFile.val('');
		changeDisable({
			inputFile:false,
			startBtn:true,
			suspendBtn:true,
			resetBtn:true
		})
		//进度条
		element.progress('uploadProgress', 0+'%');

		flag = true;
	})

	// 绑定事件：文件域更改时
	config.$inputFile.change(function (){
		if(config.$inputFile.val() != '') {
			changeDisable({
				inputFile:false,
				startBtn:false,
				suspendBtn:true,
				resetBtn:false
			})

			element.progress('uploadProgress', 0+'%');
			flag = true;
		}
	})

	// 绑定事件：start按钮 上传/继续
	config.$startBtn.click(function() {
		changeDisable({
			"inputFile":true,
			"startBtn":true,
			"suspendBtn":false,
			"resetBtn":true
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
	})

	// 绑定事件：暂停
	config.$suspendBtn.click(function() {
		suspendUpload();

		changeDisable({
			"inputFile":false,
			"startBtn":false,
			"suspendBtn":true,
			"resetBtn":false
		})
	})


	//更改禁用DOM
	function changeDisable(disableConf) {
		if (disableConf['inputFile']) {
			config.$inputFile.attr("disabled",true);
			config.$inputFile.addClass('layui-disabled');
		} else {
			config.$inputFile.attr("disabled",false);
			config.$inputFile.removeClass('layui-disabled');
		}

		if (disableConf['startBtn']) {
			config.$startBtn.attr("disabled",true);
			config.$startBtn.addClass('layui-disabled');
		} else {
			config.$startBtn.attr("disabled",false);
			config.$startBtn.removeClass('layui-disabled');
		}

		if (disableConf['suspendBtn']) {
			config.$suspendBtn.attr("disabled",true);
			config.$suspendBtn.addClass('layui-disabled');
		} else {
			config.$suspendBtn.attr("disabled",false);
			config.$suspendBtn.removeClass('layui-disabled');
		}

		if (disableConf['resetBtn']) {
			config.$resetBtn.attr("disabled",true);
			config.$resetBtn.addClass('layui-disabled');
		} else {
			config.$resetBtn.attr("disabled",false);
			config.$resetBtn.removeClass('layui-disabled');
		}
	}

}


