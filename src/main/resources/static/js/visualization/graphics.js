
google.charts.load('current', {'packages':['corechart']});


function drawChart(date) {

    var data_prev1 = date.split(";");
    var data = new google.visualization.DataTable();
    var data1 = new google.visualization.DataTable();
    data.addColumn('string', 'День');
    data.addColumn('number', 'Количество');
    data1.addColumn('string', 'День');
    data1.addColumn('number', 'Сумма');
    data.addRows(data_prev1.length);
    data1.addRows(data_prev1.length);
    for (var i in data_prev1)
    {
        var data_post1 = data_prev1[i].split(",");
        data.setCell(parseInt(i), 0, data_post1[0]);
        data.setCell(parseInt(i), 1, parseInt(data_post1[1]));
        data1.setCell(parseInt(i), 0, data_post1[0]);
        data1.setCell(parseInt(i), 1, parseFloat(data_post1[2]));
    }
    var options = {'title': 'Количество чеков',
        'width':1000,
        'height':800,
        isStacked: true,
        curveType: 'function'
    };

    var options1 = {'title': 'Сумма чеков',
        'width':1000,
        'height':800,
        isStacked: true,
        curveType: 'function'
    };

    var chart = new google.visualization.LineChart(document.getElementById('chart_div'));
    var chart2 = new google.visualization.LineChart(document.getElementById('chart_div2'));

    chart.draw(data, options);
    chart2.draw(data1, options1);

}