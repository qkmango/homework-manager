$(function () {

    layui.config({base: 'lib/laymd/'}).use(['laymd'], function(){
        var laymd = layui.laymd;

        //实例化编辑器,可以多个实例
        //改用css选择器,去除init冗余
        var md = laymd('.markdown', {name:'detailInfo'});

        //内容改变事件
        md.on('change', function () {
            //这里借用marked.js解析效率比HyperDown快,用户可自行找解析器
            this.setPreview(marked(this.getText()));
        });

        //初始化数据预览
        md.do('change');
    });

    $.ajax({
        url:'homework/getCourseList.do',
        type:'get',
        dataType:'json',
        async:false,
        success:function (data){
            let html = '<option></option>';
            $.each(data,function (i,n) {
                html += '<option value="'+n.id+'">'+n.name+'</option>'
            })
            $('#selectCourse').html(html);
        }
    })

    layui.use(['form','laydate'], function(){
        var form = layui.form;
        var laydate = layui.laydate;
        //执行一个laydate实例
        laydate.render({
            elem: '#data' //指定元素
        });

        //监听提交
        form.on('submit(formSubmit)', function (data) {
            // data.field.shstate = 1;
            // return false;
            // console.log(data.field)
            $.ajax({
                url: 'homework/addHomework.do',
                data: data.field,
                type:'post',
                async:false,
                dataType:'json',
                success: function (data) {
                    if (data.success) {
                        // layer.msg("提交成功");
                        window.parent.cocoMessage.success(2000, "提交成功");
                    }else {
                        // layer.msg("提交失败！");
                        window.parent.cocoMessage.error(2000, "提交失败");
                    }
                },
                error:function () {
                    // layer.msg("提交失败！")
                    window.parent.cocoMessage.error(2000, "提交失败");
                }
            });
            //如果返回true，就会刷新跳转页面了，所以固定false
            return false;
        });
    });

})


