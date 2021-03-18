var step = new window.parent.Step(window.parent.NProgress,3);

/**
 * 通过id获取homework信息
 */
var form;
var transfer;
var hid = getUrlParam('hid');
var formatData = {};
var formatDataChecked = [];

function getHomeworkById(hid) {
    let homework;
    $.ajax({
        url:'homework/getHomeworkByIdOfEdit.admin',
        async:false,
        data:{hid:hid},
        type:'get',
        dataType:'json',
        success:function(data){
            step.stepping();
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
function getCourseList() {
    $.ajax({
        url:'homework/getCourseList.do',
        type:'get',
        dataType:'json',
        async:false,
        success:function (data){
            step.stepping();
            let html = '<option></option>';
            $.each(data,function (i,n) {
                html += '<option value="'+n.id+'">'+n.name+'</option>'
            })
            $('#selectCourse').html(html);
        }
    })
}
function getFormat() {
    $.ajax({
        url:'homework/getFormat.do',
        type:'get',
        dataType:'json',
        async:false,
        success:function (data){
            step.stepping();
            if (!data.success) {
                return;
            }
            formatData=data.data;
            let i=0;
            $.each(formatData,function (i,n){
                if (n.checked=='1') {
                    formatDataChecked = homework.format.split('');
                    i++;
                }
            })

        }
    })
}

// 获取homework 数据
var homework = getHomeworkById(hid);

$(function () {

    //获取学科列表
    getCourseList();

    //获取格式
    getFormat();

    //渲染表格
    layui.use(['form','laydate','transfer'], function(){
        form = layui.form;
        transfer = layui.transfer;

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

        //渲染穿梭框
        transfer.render({
            elem: '#filePathRule',
            title: ['提交格式可选参数', '提交格式已选参数'],
            height:210,
            data: formatData,
            value: formatDataChecked,
            id: 'filePathRule',
            onchange: function(data, index){
                let getData = transfer.getData('filePathRule');
                let format_show = '';
                let format = '';
                $.each(getData,function(i,n) {
                    format_show += n.data;
                    format += n.value;
                })
                $('#format_show').val(format_show)
                $('#format').val(format)
            }
        });

        let getData = transfer.getData('filePathRule');
        let format_show = '';
        let format = '';
        $.each(getData,function(i,n) {
            format_show += n.data;
            format += n.value;
        })
        $('#format_show').val(format_show)
        $('#format').val(format)



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

})

