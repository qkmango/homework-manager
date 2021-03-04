/**
 * 通过id获取homework信息
 */
var form;
var hid = getUrlParam('hid')

function getHomeworkById(hid) {
    let homework;
    $.ajax({
        url:'homework/getHomeworkByIdOfEdit.admin',
        async:false,
        data:{hid:hid},
        type:'get',
        dataType:'json',
        success:function(data){
            //{"homework":{"id":"10001","title":"Java实验报告一","course":"Java","deadline":"2021-03-06","briefInfo":"简略","detailInfo":"# Java实验报告一\nhhhh\n\n尽快完成"},"success":true}
            if (data.success) {
                homework = data.homework;
            } else {
                window.parent.cocoMessage.error(2000,data.msg);
            }
        }
    })
    return homework;
}
// 获取homework 数据
var homework = getHomeworkById(hid);

$(function () {

    //获取学科列表
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

    //渲染表格
    layui.use(['form','laydate'], function(){
        form = layui.form;
        var laydate = layui.laydate;
        laydate.render({
            elem: '#data' //指定元素
        });

        //监听提交
        form.on('submit(formSubmit)', function (data) {
            data.field.id=hid;
            $.ajax({
                url: 'homework/editHomework.admin',
                data: data.field,
                type:'post',
                async:false,
                dataType:'json',
                success: function (data) {
                    if (data.success) {
                        window.parent.cocoMessage.success(2000, data.msg);
                    }else {
                        window.parent.cocoMessage.error(2000, data.msg);
                    }
                }
            });
            //如果返回true，就会刷新跳转页面了，所以固定false
            return false;
        });
    });

    //md编辑器渲染
    layui.config({base: 'lib/laymd/'}).use(['laymd'], function(){
        var laymd = layui.laymd;
        var md = laymd('.markdown', {name:'detailInfo'});
        //添加内容
        document.getElementsByName('detailInfo')[0].value = homework.detailInfo;
        //内容改变事件
        md.on('change', function () {
            //这里借用marked.js解析效率比HyperDown快,用户可自行找解析器
            this.setPreview(marked(this.getText()));
        });
        //初始化数据预览
        md.do('change');
    });

    //添加内容
    document.getElementsByName('title')[0].value = homework.title;
    document.getElementsByName('lastCommitDate')[0].value = homework.lastCommitDate;
    document.getElementsByName('course')[0].value = homework.course;
    document.getElementsByName('briefInfo')[0].value = homework.briefInfo;
    // document.getElementsByName('detailInfo')[0].value = homework.detailInfo;

})

