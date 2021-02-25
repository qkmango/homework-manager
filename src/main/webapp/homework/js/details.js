$(function () {

    let hid = getUrlParam('hid');
    let course = getUrlParam('course');
    $.ajax({
        url:'homework/getHomeworkIsCommit.do',
        data:{hid:hid},
        type:'get',
        dataType:'json',
        success:function(data){
            if (data.success) {
                $('#btn-box').html('<a class="layui-btn layui-layout-right"><i class="layui-icon">&#x1006;</i>撤销提交</a>')
            } else {
                $('#btn-box').html('<a href="homework/upload.html?hid='+hid+'&course='+course+'" class="layui-btn layui-layout-right"><i class="layui-icon">&#xe681;</i>去提交</a>')
            }
        },
        error:function () {
            // alert("获取失败，请刷新页面！");
            window.parent.cocoMessage.error(3000, "服务器请求失败，请刷新页面！");
        }
    })



    $.ajax({
        url:'homework/getHomeworkById.do',
        data:{id:getUrlParam('hid')},
        type:'get',
        dataType:'json',
        success:function(data){
            //{"homework":{"id":"10001","title":"Java实验报告一","course":"Java","deadline":"2021-03-06","briefInfo":"简略","detailInfo":"# Java实验报告一\nhhhh\n\n尽快完成"},"success":true}
            if (data.success) {

                let hw = data.homework;

                //将homework信息存入父窗口变量
                // window.parent.frames.global_homework = hw;

                let dl =hw.lastCommitDate.split('-');

                $('#title').text(hw.title);
                $('#badge-course').attr('src','https://img.shields.io/badge/学科-'+hw.course+'-5FB878.svg');
                $('#badge-deadline').attr('src','https://img.shields.io/badge/最后提交日期-'+dl[0]+'--'+dl[1]+'--'+dl[2]+'-5FB878.svg');
                //渲染 markdown
                document.getElementById("content").innerHTML=marked(hw.detailInfo);
            } else {
                // alert('获取作业详情失败！')
                // window.location.href='/hm/homework/homework.html';
                window.parent.cocoMessage.error(3000, "获取作业列表失败，请刷新页面！",function (){
                    window.location.href='/hm/homework/homework.html';
                });
            }
        },
        error:function () {
            // alert('连接超时，请重试！')
            window.parent.cocoMessage.error(3000, "连接超时，请重试！")
        }
    })

})