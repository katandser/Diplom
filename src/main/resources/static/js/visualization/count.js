
google.charts.load('current', {'packages':['corechart']});

// google.charts.setOnLoadCallback(drawChart);

function drawChart(date) {
    if (date) {
    alert(date);
        var data = new google.visualization.DataTable();
        data.addColumn('string', 'Element');
        data.addColumn('number', 'Percentage');
        data.addRows([
            ['Nitrogen', 0.78],
            ['Oxygen', 0.21],
            ['Other', 0.01]
        ]);


    var options = {'title':String(date),
        'width':1000,
        'height':800,
        isStacked: true,
    };

    var chart = new google.visualization.SteppedAreaChart(document.getElementById('chart_div'));


    chart.draw(data, options);


    }
}
