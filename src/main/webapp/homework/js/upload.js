$(function () {

	var $inputFile = $('#inputFile');
	var $startBtn = $("#startBtn");
	var $resetBtn = $("#resetBtn");
	var $suspendBtn = $("#suspendBtn");

	//用户信息 json
	var user = '';

	//homework id、course、title、format（从url中获取）
	var hid = getUrlParam('hid');
	var course = getUrlParam('course');
	var title = getUrlParam('title');
	var format = getUrlParam('format');

	console.log(format)

	var ossConfig;

	//文件提交格式
	var formatData = {};


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
					window.location.href='system/login/login.html'
				})

			}
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
		"title":title,
		"user":user,
		"format":format,
		"getFilePath":function (innerConf) {
			//这个innerConfig其实就是调用uploadOSS函数传入的此json，再加入了一个fileType而已


			let uid 		= innerConf.user.id;
			let lastUid 	= innerConf.user.id.substring(uid.length-2);
			let realName 	= innerConf.user.realName;

			let formats = innerConf.format.split('');

			let fileName = '';
			console.log(formats)



			$.each(formats,function (i,n){
				switch (n) {
					case '0':fileName+=lastUid;break;
					case '1':fileName+=uid;break;
					case '2':fileName+=realName;break;
					case '3':fileName+="_";break;
					case '4':fileName+=innerConf.title;break;
				}
				console.log(fileName)
			})

			console.log(fileName)
			return innerConf.course+'/'+innerConf.hid+'/'+fileName+'.'+innerConf.fileType;
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
						//撒花
						sh();
						window.parent.cocoMessage.success(2000, data.msg)
					} else {
						window.parent.cocoMessage.error(2000, data.msg)
					}
				}
			})
		}
	});



})




