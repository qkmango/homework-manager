var $ = window.parent.$;


$(function () {
    RenderRecentCommitCount();
})

/**
 * 渲染 各学科最近一次作业提交人数
 * @constructor
 */
function RenderRecentCommitCount() {

    var dom = document.getElementById("RecentCommitCount");
    var myChart = echarts.init(dom);
    var app = {};
    var source = [];
    source[0]=['count', 'course'];
    var option;


    $.ajax({
        url:'homework/getRecentCommitCount.do',
        type:'get',
        dataType:'json',
        success:function (data) {
            $.each(data.data,function (i,n) {
                source[i+1]=[n.count,n.course];
            })
            option =  {
                title: {
                    text: '各学科最近一次作业提交人数',
                    textStyle:{
                        color:'#58a6fd',
                        fontSize:'16px'
                    }
                },
                dataset: {
                    source: source
                },
                grid: {containLabel: true},
                xAxis: {name: 'count'},
                yAxis: {type: 'category'},
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
                        color:'#AAA'
                    }
                }]
            };

            if (option && typeof option === 'object') {
                myChart.setOption(option);
            }

        }
    })
}