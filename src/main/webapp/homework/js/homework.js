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
