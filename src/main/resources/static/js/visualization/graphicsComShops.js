
google.charts.load('current', {'packages':['corechart']});


function drawChart(date) {
    var data_prev1 = date.split("_");

    var vv = [['e','2','5','8']];
    for (var i in data_prev1) {
        data_prev2 = data_prev1[i].split(";");
        var v = [i];
        for (var j in data_prev2) {
            var data_post1 = data_prev2[j].split(",");
            for (var k in data_post1) {
                v.push(data_post1[1]);
            }
            vv.push(v);
        }
    }

    var data = google.visualization.arrayToDataTable([
        ['Day', 'Мария-ра, Ленина 189', 'Мария-ра, Островского 8', 'Мария-ра, Юрина 167'],
        ['2019-06-01', 173455, 153452, 87954],
        ['2019-06-02', 177129, 162516, 88203],
        ['2019-06-03', 281669, 170032, 87461],
        ['2019-06-04', 282780, 189650, 88315],
        ['2019-06-05', 282738, 171892, 87559],
        ['2019-06-06', 282403, 168225, 87199],
        ['2019-06-07', 40, 192562, 695],
    ]);

    var data2 = google.visualization.arrayToDataTable([
        ['Day', 'Мария-ра, Ленина 189', 'Мария-ра, Островского 8', 'Мария-ра, Юрина 167'],
        ['2019-06-01', 867451313.96, 76726525.91, 440261513.96],
        ['2019-06-02', 884756867.28, 81252595.95, 440414813.44],
        ['2019-06-03', 1406905390.11, 85016824.31, 437985388.70],
        ['2019-06-04', 1413232854.55, 948255921.58, 442369334.83],
        ['2019-06-05', 1413513809.00, 859469165.12, 437897988.28],
        ['2019-06-06', 1411420238.30, 841125192.12, 436157752.21],
        ['2019-06-07', 219685.82,    962810245.76 ,3489262.01]
    ]);




    var options = {'title': 'Количество чеков 2019-06-01 -- 2019-06-06',
        'width':1000,
        'height':800,
        isStacked: true,
        curveType: 'function'
    };

    var options2 = {'title': 'Сумма чеков 2019-06-01 -- 2019-06-06',
        'width':1000,
        'height':800,
        isStacked: true,
        curveType: 'function'
    };

    var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
    var chart2 = new google.visualization.LineChart(document.getElementById('chart_div2'));


    chart.draw(data, options);
    chart2.draw(data2, options2);


}