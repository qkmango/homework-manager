$(function () {
    RenderRecentCommitCount();
    RenderCommitDynamic();
    RenderHeatmap();
    RenderCourseHomeworkProportion();
})

/**
 * 渲染 各学科最近一次作业提交人数
 * @constructor
 */
function RenderRecentCommitCount() {

    var dom = document.getElementById("RecentCommitCount");
    var RCC_Chart = echarts.init(dom);
    var app = {};
    // var source = [];
    var option;

    $.ajax({
        url:'visualization/getRecentCommitCount.do',
        type:'get',
        dataType:'json',
        success:function (data) {
            if (!data.success) {
                return;
            }

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
                toolbox: {
                    show: true,
                    feature: {
                        mark: {show: true},
                        dataView: {show: true, readOnly: false},
                        restore: {show: true},
                        saveAsImage: {show: true}
                    }
                },
                dataset: {
                    source: data.data,
                    sourceHeader:false
                },
                grid: {
                    containLabel: true,
                    left: '10px',
                    right:'50px'
                },
                xAxis: {name:'count',type:'value',max:data.count},
                yAxis: {
                    type:'category',
                    axisTick:{show:false},
                    axisLine:{show:false},
                    axisLabel:{
                        formatter: function (value, index) {
                            return value+'\n'+data.data[index]['title'];
                        }
                    }
                },
                visualMap: {
                    orient: 'horizontal',
                    left: 'center',
                    min: 0,
                    max: data.count,
                    text: ['High', 'Low'],
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
    const stepSize = 7*24*60*60*1000;//毫秒

    let nowDate = new Date(new Date()-0+24*60*60*1000);
    let year = nowDate.getFullYear();
    let month = nowDate.getMonth()+1;
    let day = nowDate.getDate();

    let endDate = new Date(year+'-'+month+'-'+day);
    let endDateFormat = dateFormat('YYYY-mm-dd HH:MM:SS',endDate);

    let startDate = new Date(endDate-stepSize);
    let startDateFormat = dateFormat('YYYY-mm-dd HH:MM:SS',startDate);

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
                let stats = {};
                for (let d in data) {
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

/**
 * 各学科布置的作业数量占比
 * @constructor
 */
function RenderCourseHomeworkProportion() {
    $.ajax({
        url:'visualization/getCourseHomeworkProportion.do',
        type:'get',
        dataType:'json',
        success:function (data) {
            if (!data) {
                return;
            }

            var dom = document.getElementById("container");
            var RCHP_Chart = echarts.init(dom);
            var app = {};
            var option

            option = {
                legend: {
                    top: 'bottom'
                },
                title: {
                    left: 'center',
                    text: '各学科作业数量占比',
                    textStyle:{
                        color:'#58a6fd',
                        fontSize:'16px'
                    }
                },
                toolbox: {
                },
                series: [{
                    type: 'pie',
                    radius: ['40%', '70%'],
                    center: ['50%', '50%'],
                    emphasis: {
                        label: {
                            show: true,
                            fontWeight: 'bold'
                        }
                    },
                    data: data.data,
                }]
            };

            if (option && typeof option === 'object') {
                myChart.setOption(option);
            }
        }
    })

    window.addEventListener('resize',function() {
        RCHP_Chart.resize();
    })
}