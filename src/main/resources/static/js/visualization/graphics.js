
google.charts.load('current', {'packages':['corechart']});


function drawChart(date) {

        var data = new google.visualization.DataTable();
    data.addColumn('string', 'День');
    data.addColumn('number', 'Количество');
    data.addRows([
            ['2019-05-25',530],
            ['2019-05-26',1321],
            ['2019-05-27',1271],
            ['2019-05-28',1308],
            ['2019-05-29' ,1326],
            ['2019-05-30',1320],
            ['2019-05-31',1318],
            ['2019-06-01',1310],
            ['2019-06-02',1333],
            ['2019-06-03',1282],
            ['2019-06-04',1315],
            ['2019-06-05',1325],
            ['2019-06-06',187],
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