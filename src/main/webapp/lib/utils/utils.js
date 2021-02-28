function getFileType(filePath) {
    let index = filePath.lastIndexOf(".");
    let ext = filePath.substr(index+1);
    return ext;
}

function getUrlParam(key) {
    var reg = new RegExp("(^|&)" + key + "=([^&]*)(&|$)");
    var r = window.location.search.substr(1).match(reg);
    if(r != null) return decodeURI(r[2]);
    return null;
}

/**
 * 序列化表单，表单的jquery对象调用此方法返回json对象
 * @returns {{}}
 */
$.fn.serializeObject = function() {
    var o = {};
    var a = this.serializeArray();
    $.each(a, function() {
        if (o[this.name]) {
            if (!o[this.name].push) {
                o[this.name] = [ o[this.name] ];
            }
            o[this.name].push(this.value || '');
        } else {
            o[this.name] = this.value || '';
        }
    });
    return o;
};


/**
 * 获取文件类型
 * @param filePath
 * @returns {string}
 */
function getFileType(filePath) {
    let index = filePath.lastIndexOf(".");
    let ext = filePath.substr(index+1);
    return ext;
}



/**
 * 上传至阿里云OSS
 * 函数依赖   jquery.js
 *          oss.js
 * 函数使用的是临时授权的方式进行上传 sts
 * @param config
 */
/*

config {
    "ossConfig":ossConfig,
    "$inputFile":$inputFile,
    "$resetBtn":$resetBtn,
    "$startBtn":$startBtn,
    "$suspendBtn":$suspendBtn,
    "course":course,
    "hid":hid,
    "user":user,
    "getFilePath":function (innerConf) {
        return innerConf.course+'/'+innerConf.hid+'/'+innerConf.user.id+'.'+innerConf.fileType;
    },
    "changeProgressCallback":function (p) {
        element.progress('uploadProgress', (p*100).toFixed(2)+'%');
    },
    "uploadSuccessCallBack":function (innerConf) {
        window.parent.cocoMessage.success(2000, "上传成功")
    },
    "uploadErrorCallback":function (innerConf) {
        window.parent.cocoMessage.error(2000, "上传失败,请刷新页面!")
    },
    "commitSuccessCallback":function (innerConf) {
        window.parent.cocoMessage.success(2000, "提交成功")
    },
    "commitErrorCallback":function (innerConf){
        window.parent.cocoMessage.error(2000, "提交失败")
    },
    "uploadSuccessTodo":function(innerConf) {
        //ajax
    }
}

config中值的解读：
    ossConfig：  json对象，为阿里OSS上传时所需要的配置，格式如下
                ossConfig = {
                    accessKeyId: "STS.NTgzrsutPazQU87DaD..."
                    accessKeySecret: "3hYSN94NM7KK2njhR4512h9H2At675X..."
                    bucket: "qkm..."
                    region: "oss-cn-beijing"
                    stsEndpoint: "sts.cn-beijing.aliyuncs.com"
                    stsToken: "CAIS/AR1q6Ft5B2yfSjIr5fSMcjHmKtx1ri6Vx6"
                }
    hid：        homework id值
    course：     homework所对应的学科，如Java
    $系类：       为jquery对象的 文件域、开始按钮、重置按钮、暂停按钮
    user：       json对象，包含user的信息，如 {username: "11111", realName: "芒果小洛", id: "100000"}
    getFilePath：        function(innerConf)，获取给上传文件指定文件上传后的路径
    CallBack：           function(innerConf)，回调函数系类
    uploadSuccessTodo：  function(innerConf)：上传完成后要去做...下面的配置中是发送了ajax告诉后端
    changeProgressCallback:function (p) 进度条回调函数，传入的p，取值为 0~1

    innerConf 是什么？：innerConf就是调用uploadOSS(config)函数传入的此json配置，
        不过在不同的阶段，此config中又加入了如 fileType、filePath等配置，就形成了innerConf

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
                    config.changeProgressCallback(p)
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
                    config.changeProgressCallback(p)
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