
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
        ['Day', 'Sales', 'Expenses', 'Profit'],
        ['2019-06-01', 1000, 400, 200],
        ['2015', 1170, 460, 250],
        ['2016', 660, 1120, 300],
        ['2017', 1030, 540, 350]
    ]);




    var options = {'title': '2019-05-25 -- 2019-06-06',
        'width':1000,
        'height':800,
        isStacked: true,
        curveType: 'function'
    };

    var chart = new google.visualization.LineChart(document.getElementById('chart_div'));


    chart.draw(data, options);


}