
google.charts.load('current', {'packages':['corechart']});


function drawChart(date) {

    var data = new google.visualization.DataTable();
    data.addColumn('string', 'День');
    data.addColumn('number', 'Количество');
    data.addRows();

    var options = {'title': '2019-05-25 -- 2019-06-06',
        'width':1000,
        'height':800,
        isStacked: true,
        curveType: 'function'
    };

    var chart = new google.visualization.LineChart(document.getElementById('chart_div'));


    chart.draw(data, options);


}