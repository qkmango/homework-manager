var step = new window.parent.Step(window.parent.NProgress,2);

$.ajax({
	url:'homework/getCourseList.do',
	type:'get',
	dataType:'json',
	async:false,
	success:function (data){
		step.stepping();
		let html = '<option value="0">全部</option>';
		$.each(data,function (i,n) {
			html += '<option value="'+n.id+'">'+n.name+'</option>'
		})
		$('#selectCourse').html(html);
	}
})

var form;
layui.use(['element','form'], function(){
	var element = layui.element;
	form = layui.form;
});
