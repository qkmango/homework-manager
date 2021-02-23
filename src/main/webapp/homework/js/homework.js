// var queryCount = 0;


$.ajax({
	url:'homework/getCourseList.do',
	type:'get',
	dataType:'json',
	async:false,
	success:function (data){
		let html = '<option value="0">全部</option>';
		$.each(data,function (i,n) {
			html += '<option value="'+n.id+'">'+n.name+'</option>'
		})
		$('#selectCourse').html(html);
	}
})

layui.use(['element','form'], function(){
	var element = layui.element;
	var form = layui.form;
});





layui.use('laypage', function(){




	var laypage = layui.laypage;
	var queryCount = getHomeworkPageList(1,10)

	laypage.render({
		elem: 'pagination'
		,count: queryCount	//总条数
		// ,limit:5		//每页显示的数量
		,groups:10
		,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
		,jump: function(obj, first){
			//首次不执行
			if(!first){
				obj.count = getHomeworkPageList(obj.curr,obj.limit);
			}
		}
	});
});






/**
 * 获取分页数据
 * @param pageNo	当前页
 * @param pageSize	每页的条数
 * @param course	学科id
 * @param status	是否完成
 * @param title		标题关键词
 */
function getHomeworkPageList(pageNo,pageSize) {

	let course = $('#selectCourse').val();
	let status = $('#status').val();
	let title = $('#title').val();

	let count;
	$.ajax({
		url:'homework/getHomeworkPageList.do',
		async:false,
		data:{
			pageNo: 	pageNo,		//当前页页码
			pageSize: 	pageSize,	//每页条数
			course:		course,		//学科
			status:		status,		//状态（是否已经提交？已提交1 未提交0）
			title:		title		//标题关键字
		},
		type:'post',
		dataType:'json',
		success:function (data) {
			count = data.count;
				/*
				{
					count:1000,
					list:[
						{},
						{},
						{},
						{},
					]
				}
				 */
			// console.log(data.count)
			let html = '';
			let nowDate = new Date();
			$.each(data.list,function (i, n) {
				// console.log(n)

				let thisLastCommitDate = new Date(n.lastCommitDate);

				let color;
				let distance = nowDate-thisLastCommitDate;
				//离最后提交时间大于两天
				if (distance < -2*24*60*60*1000) {
					color = 'layui-bg-green';
				//已过期
				} else if (distance > 0) {
					color = 'layui-bg-gray';
				//临期（两天内）
				} else {
					color = 'layui-bg-red';
				}
				html += '<div class="layui-col-sm6 layui-col-md4"><div class="layui-card">';
				html += '<div class="layui-card-header '+color+'">'+n.course;
				html += '<span class="layui-layout-right">'+n.createDate;
				html += '<i class="layui-icon">&#xe642;</i>';
				html += '<a href="/hm/homework/details.html" id="'+n.id+'" onclick="window.parent.frames.global_homework_id = this.id">详情</a>';
				html +=	'</span></div><div class="layui-card-body"><p>';
				html += '<span>'+n.title+'</span>';
				html += '<span class="layui-layout-right">最后提交日期:'+n.lastCommitDate+'</span>';
				html += '</p><p>'+n.briefInfo+'</p></div></div></div>';
			})

			$('#homework-list').html(html);
		}
	})

	return count;
}