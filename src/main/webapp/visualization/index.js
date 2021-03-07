$(function () {
    RenderRecentCommitCount();
    RenderCommitDynamic();
    RenderHeatmap();
})

/**
 * 渲染 各学科最近一次作业提交人数
 * @constructor
 */
function RenderRecentCommitCount() {

    var dom = document.getElementById("RecentCommitCount");
    var RCC_Chart = echarts.init(dom);
    var app = {};
    var source = [];
    var option;

    $.ajax({
        url:'visualization/getRecentCommitCount.do',
        type:'get',
        dataType:'json',
        success:function (data) {
            //填充数据
            source=data.data

            option = {
                title: {
                    left: 'center',
                    text: '各学科最近一次作业提交人数',
                    textStyle:{
                        color:'#58a6fd',
                        fontSize:'16px'
                    }
                },
                tooltip: {
                    trigger: "axis",
                    axisPointer: {
                        type: "shadow",
                        shadowStyle:{color:'rgba(0, 0, 0, 0.2)'}
                    }
                },
                dataset: {
                    source: source
                },
                grid: {
                    containLabel: true,
                    left: '10px',
                    right:'50px'
                },
                xAxis: {name: 'count'},
                yAxis: {
                    type:'category',
                    axisTick:{ show: false },
                    axisLine:{show:false}
                },
                visualMap: {
                    orient: 'horizontal',
                    left: 'center',
                    min: 0,
                    max: data.count,
                    text: ['High Score', 'Low Score'],
                    dimension: 0,
                    inRange: {
                        color: ['#65B581', '#FFCE34', '#FD665F']
                    }
                },
                series: [{
                    type: 'bar',
                    encode: {
                        x: 'count',
                        y: 'course'
                    },
                    showBackground: true,
                    backgroundStyle: {
                        color: 'rgba(180, 180, 180, 0.2)'
                    },
                    label: {
                        show: true,
                        position: 'right',
                        color:'#AAA',
                        formatter: '{@count}/'+data.count
                    }
                }]
            };

            if (option && typeof option === 'object') {
                RCC_Chart.setOption(option);
            }

        }
    })

    window.addEventListener('resize',function() {
        RCC_Chart.resize();
    })
}


/**
 * 获取最近作业提交动态
 * @constructor
 */
function RenderCommitDynamic() {
    $.ajax({
        url:'visualization/getCommitDynamic.do',
        type:'get',
        dataType:'json',
        success:function (data) {
            if (!data.success) {
                return;
            }
            let html = '';
            $.each(data.data,function (i,n) {
                html += '<li class="layui-row layui-timeline-item">';
                html += '<i class="layui-col-md1 layui-col-sm1 layui-col-xs1 layui-icon layui-timeline-axis">&#xe62f;</i>';
                html += '<div class="layui-timeline-content layui-text">';
                html += '<span class="layui-col-md2 layui-col-sm2 layui-col-xs2">'+n.realname+'</span>';
                html += '<span class="layui-col-md4 layui-col-sm4 layui-col-xs4">'+n.datetime+'</span>';
                html += '<a class="layui-col-md5 layui-col-sm5 layui-col-xs5">'+n.course+'@'+n.title+'</a>';
                html += '</div>';
                html += '</li>';
            })
            $('#commit_dynamic').append(html)
        }
    })
}


/**
 * 渲染热力图
 * @constructor
 */
function RenderHeatmap() {
    // var jsonData;
    const stepSize = 7*24*60*60*1000;//毫秒

    var endDate = new Date();
    var endDateFormat = dateFormat('YYYY-mm-dd HH:MM:SS',endDate);

    var startDate = new Date(endDate-stepSize);
    var startDateFormat = dateFormat('YYYY-mm-dd HH:MM:SS',startDate);

    console.log(startDateFormat)
    console.log(endDateFormat)

    $.ajax({
        url:"visualization/getHeatmap.do",
        data:{
            startDateFormat:startDateFormat,
            endDateFormat:endDateFormat
        },
        type:'get',
        dataType:'json',
        success:function(data) {

            if (!data.success) {
                return;
            }

            var parser = function(data) {
                var stats = {};
                for (var d in data) {
                    stats[data[d].date] = data[d].value;
                }
                return stats;
            };

            var cal = new CalHeatMap();
            cal.init({
                itemSelector:"#cal-heatmap",
                domain:"day",
                subDomain:"hour",
                cellSize:15,
                itemName:["次提交"],
                subDomainTextFormat:'%H',
                tooltip:true,
                start:startDate,
                range:7,
                legend:[4,8,12,16],
                domainGutter:10,
                // colLimit:2,
                // rowLimit:1,
                data:data.data,
                afterLoadData: parser
            });

        }
    })


}