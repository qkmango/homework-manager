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
				window.parent.cocoMessage.error(2000, data.msg,function () {
					window.location.href='../../system/login2/login.html'
				})

			}
		}
		// ,
		// onerror:function () {
		// 	window.parent.cocoMessage.error(2000, "请登陆！",function () {
		// 		window.location.href='system/login.html'
		// 	})
		// }
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
		"changeProgressCallback":function (p) {
			element.progress('uploadProgress', (p*100).toFixed(2)+'%');
		},
		"uploadSuccessCallBack":function () {
			window.parent.cocoMessage.success(2000, "上传成功")
		},
		"uploadErrorCallback":function () {
			window.parent.cocoMessage.error(2000, "上传失败,请刷新页面!")
		},

		// "commitSuccessCallback":function () {
		// 	window.parent.cocoMessage.success(2000, "提交成功")
		// },
		// "commitErrorCallback":function (){
		// 	window.parent.cocoMessage.error(2000, "提交失败")
		// },
		"uploadSuccessTodo":function (innerConf) {
			console.log(innerConf.filePath)
			console.log(innerConf.hid)
			$.ajax({
				url:'homework/commitHomework.do',
				data:{
					"filePath":innerConf.filePath,
					"hid":innerConf.hid
				},
				type:'post',
				dataType:'json',
				success:function (data) {
					if (data.success) {
						window.parent.cocoMessage.success(2000, data.msg)
					} else {
						window.parent.cocoMessage.error(2000, data.msg)
					}
				}
			})
		}
	});



})




