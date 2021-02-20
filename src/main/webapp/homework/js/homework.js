layui.use(['element','form'], function(){
	var element = layui.element;
	var form = layui.form;
});

layui.use('laypage', function(){
	var laypage = layui.laypage;
	laypage.render({
		elem: 'pagination'
		,count: 100	//总条数
		// ,limit:5		//每页显示的数量
		,groups:10
		,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
		,jump: function(obj, first){
		  //obj包含了当前分页的所有参数，比如：
			console.log(obj.curr); //得到当前页，以便向服务端请求对应页的数据。
			console.log(obj.limit); //得到每页显示的条数
		  
		  //首次不执行
			if(!first){
			//do something
			}
		}
	});
});