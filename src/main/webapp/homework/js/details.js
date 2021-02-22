$(function () {
    $.ajax({
        url:'homework/getHomeworkById.do',
        data:{id:window.parent.frames.global},
        type:'get',
        dataType:'json',
        success:function(data){
            //{"homework":{"id":"bcd41266f1434246b8e4760399b7d877","title":"Java实验报告一","course":"Java","deadline":"2021-03-06","briefInfo":"简略","detailInfo":"# Java实验报告一\nhhhh\n\n尽快完成"},"success":true}
            let hw = data.homework;
            let dl =hw.deadline.split('-');

            $('#title').text(hw.title);
            $('#badge-course').attr('src','https://img.shields.io/badge/学科-'+hw.course+'-5FB878.svg');
            $('#badge-deadline').attr('src','https://img.shields.io/badge/最后提交日期-'+dl[0]+'--'+dl[1]+'--'+dl[2]+'-5FB878.svg');
            //渲染 markdown
            document.getElementById("content").innerHTML=marked(hw.detailInfo);
        }
    })

})