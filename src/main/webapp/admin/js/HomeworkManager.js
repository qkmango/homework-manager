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


layui.use('table', function(){
    var table = layui.table;

    //表格渲染
    table.render({
        id:'homeworkPage'
        ,elem: '#homeworkPage'
        // ,cellMinWidth: 'auto'
        ,cellWidth: 'auto'
        ,method:'post'
        ,url: 'homework/getHomeworkPageList.do' //数据接口
        ,page: true //开启分页
        ,cols: [[ //表头
            {field: 'id', title: 'ID',sort: true}
            ,{field: 'title', title: '标题'}
            ,{field: 'course', title: '学科',sort: true}
            ,{field: 'lastCommitDate', title: '最后提交时间',sort: true}
            ,{field: 'createDate', title: '创建时间',sort: true}
            ,{field: 'briefInfo', title: '简略信息'}
            ,{toolbar: '#bar',title: '操作'}
        ]]
    });

    //监听工具条（右侧 查看、编辑、删除）
    table.on('tool(homeworkPage)', function(obj){
        var data = obj.data;
        if(obj.event === 'detail'){
            layer.msg('ID：'+ data.id + ' 的查看操作');
            window.location.href="homework/details.html?power=admin&hid="+data.id;
        }
        else if(obj.event === 'del'){
            layer.confirm('确认要删除么？ ID='+data.id, function(index){
                window.parent.cocoMessage.loading("删除中...");
                $.ajax({
                    url:'homework/deleteHomework.admin',
                    data:{hid:data.id},
                    type:'post',
                    dataType:'json',
                    success:function (data) {
                        window.parent.cocoMessage.destroyAll();
                        if (data.success) {
                            obj.del();
                            layer.close(index);
                            window.parent.cocoMessage.success(2000, data.msg)
                        } else {
                            window.parent.cocoMessage.error(2000, data.msg)
                        }
                    }
                    // ,error:function () {
                    //     window.parent.cocoMessage.destroyAll();
                    //     window.parent.cocoMessage.error(2000, "删除失败")
                    // }
                })
                // url: homework/deleteHomework.do
            });
        }
        else if(obj.event === 'edit'){
            // layer.alert('编辑行：<br>'+ JSON.stringify(data))
            window.location.href="admin/editHomework.html?power=admin&hid="+data.id;
        }
    });



    //搜索条件
    active = {
        reload: function(){
            let selectCourse = $('#selectCourse');
            let title = $('#title');
            //执行重载
            table.reload('homeworkPage', {
                page: { curr: 1 } //重新从第 1 页开始
                ,where: {
                    course: selectCourse.val(),
                    title: title.val()
                }
            }, 'data');
        }
    };
    //条件搜索 按钮绑定事件
    $('.queryParamTable .layui-btn').on('click', function(){
        var type = $(this).data('type');
        active[type] ? active[type].call(this) : '';
    });

});