var page=1; //设置首页页码
var limit=12;  //设置一页显示的条数
var count;    //总条数
var queryParam = new Object();
var laypage

layui.use('laypage', function(){
    laypage = layui.laypage;
    pagination();
});


$("#query-btn").on('click',function () {
    page=1;
    pagination()   //分页操作
})


function pagination() {
    getHomeworkPageList();
    laypage.render({
        elem: 'pagination'
        ,count: count
        ,limit:limit
        ,groups:10
        ,layout: ['count', 'prev', 'page', 'next', 'limit', 'skip']
        ,limits:[12,24,36]
        ,jump: function(obj, first){
            page = obj.curr;
            limit = obj.limit;
            // $('#selectCourse').val(queryParam.course);
            // $('#status').val(queryParam.status);
            // $('#title').val(queryParam.title);
            if(!first){

                form.val('queryParams', {
                    "status": queryParam.status
                    ,"title": queryParam.title
                    ,"course": queryParam.course
                });

                // queryParam=JSON.stringify(form.val('queryParams'));
                // form.render('select');
                getHomeworkPageList();
                obj.count=count;
            }
        }
    });
}


/**
 * 请求数据，并将数据放到页面
 */
function getHomeworkPageList(){

    let course = $('#selectCourse').val();
    let status = $('#status').val();
    let title = $.trim($('#title').val());

    //保存参数
    queryParam.course = course;
    queryParam.status = status;
    queryParam.title = title;

    $.ajax({
        url:'homework/getHomeworkPageList.do',
        async:false,
        data:{
            pageNo: 	page,		//当前页页码
            pageSize: 	limit,	//每页条数
            course:		course,		//学科
            status:		status,		//状态（是否已经提交？已提交1 未提交0）
            title:		title		//标题关键字
        },
        type:'post',
        dataType:'json',
        success:function(data){
            count=data.count;  //设置总条数
            let html = '';
            let nowDate = new Date();
            $.each(data.list,function (i, n) {
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
                html += '<a href="/hm/homework/details.html?hid='+n.id+'&course='+n.course+'">详情</a>';
                html +=	'</span></div><div class="layui-card-body"><p>';
                html += '<span>'+n.title+'</span>';
                html += '<span class="layui-layout-right">最后提交日期:'+n.lastCommitDate+'</span>';
                html += '</p><p>'+n.briefInfo+'</p></div></div></div>';
            })
            $('#homework-list').html(html);
        },
        error:function () {
            alert("请先登陆");
            window.location.href = 'system/login.html';
        }
    });


}