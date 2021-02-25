$(function () {

    let hid = getUrlParam('hid');
    let homework;

    homework = getHomeworkById(hid);
    getHomeworkIsCommit(homework);

    /**
     * 通过id获取homework信息
     */
    function getHomeworkById(hid) {
        let homework;
        $.ajax({
            url:'homework/getHomeworkById.do',
            async:false,
            data:{id:hid},
            type:'get',
            dataType:'json',
            success:function(data){
                //{"homework":{"id":"10001","title":"Java实验报告一","course":"Java","deadline":"2021-03-06","briefInfo":"简略","detailInfo":"# Java实验报告一\nhhhh\n\n尽快完成"},"success":true}
                if (data.success) {
                    homework = data.homework;
                    let dl =homework.lastCommitDate.split('-');

                    $('#title').text(homework.title);
                    $('#badge-course').attr('src','https://img.shields.io/badge/学科-'+homework.course+'-5FB878.svg');
                    $('#badge-deadline').attr('src','https://img.shields.io/badge/最后提交日期-'+dl[0]+'--'+dl[1]+'--'+dl[2]+'-5FB878.svg');
                    //渲染 markdown
                    document.getElementById("content").innerHTML=marked(homework.detailInfo);
                } else {
                    window.parent.cocoMessage.error(2000, "获取作业列表失败，请刷新页面！",function (){
                        window.location.href='/hm/homework/homework.html';
                    });
                }
            },
            error:function (error) {
                window.parent.cocoMessage.error(2000, "请求失败，请重试！")
            }
        })

        return homework;
    }

    /**
     * 获取此homework是否已经提交
     */
    function getHomeworkIsCommit(homework) {
        $.ajax({
            url:'homework/getHomeworkIsCommit.do',
            data:{hid:homework.id},
            type:'get',
            dataType:'json',
            success:function(data){
                if (data.success) {
                    $('#btn-box').html('<a class="layui-btn layui-layout-right"><i class="layui-icon">&#x1006;</i>撤销提交</a>')
                } else {
                    $('#btn-box').html('<a href="homework/upload.html?hid='+homework.id+'&course='+homework.course+'&title='+homework.title+'" class="layui-btn layui-layout-right"><i class="layui-icon">&#xe681;</i>去提交</a>')
                }
            },
            error:function () {
                window.parent.cocoMessage.error(2000, "服务器请求失败，请刷新页面！");
            }
        })
    }

})