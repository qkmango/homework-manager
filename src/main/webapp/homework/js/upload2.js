var qiniuTokenAndConf;

$.ajax({
	url:'system/qiniu/getQiniuTokenAndConf.do',
	type:'get',
	dataType:'json',
	success:function (data) {
		qiniuTokenAndConf = data;
	}
})


layui.config({
	base: 'lib/layui/modules/' //静态资源所在路径
}).extend({
	qiniuyun: 'qiniuyun/index',
}).use(['index', 'qiniuyun'], function(){
	var $ = layui.$
		,element = layui.element
		,layer = layui.layer
		,qiniuyun = layui.qiniuyun;
	qiniuyun.loader({
		domain: 'qiniu.qkmango.cn'              // 后台设置的域名项
		, elem: "#inputFile"          // 绑定的element
		, token: qiniuTokenAndConf.token             // 授权token
		, retryCount: 6                  // 重连次数，默认6(可选)
		, region: qiniu.region.z2        // 选择上传域名区域，默认自动分析(可选)
		, next: function(response){
			// element.progress('video-progress', response.total.percent + '%');       // 进度条
		}
		, complete: function(res){
			// layer.closeAll('loading'); // 关闭loading关闭
			layer.msg("上传成功！");
			console.log(res)
		}
	});
});