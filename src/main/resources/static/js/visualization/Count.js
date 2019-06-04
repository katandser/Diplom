
google.charts.load('current', {'packages':['corechart']});

google.charts.setOnLoadCallback(drawChart);

function drawChart() {

    var data1 = new google.visualization.arrayToDataTable([
            ['OLD','1999','2000','2001','2002','2003','2004','2005','2006','2007','2008','2009'],
            ["15–19 years",3988,3627,3727,3643,3301,3310,2974,2949,2788,3226,3037],
            ["20–24 years",13674,13062,12712,12625,12360,12133,11591,11567,11779,12619,11920],
            ["25–29 years",27944,27500,25673,25164,24448,23709,23255,23359,23993,25234,25146],
            ["30–34 years",26198,27088,26980,28749,29352,29423,30442,30146,30224,30908,30348],
            ["35–39 years",12644,13027,12878,13505,13988,14273,15252,16144,17317,18903,8500],
            ["40–44 years",2240,2343,2463,2733,2743,2886,2932,3015,3228,3635,3630],
            ["45–49 years",92,101,116,104,114,143,138,153,161,158,199]
        ]
    );


    var data = data1.clone();

    var options = {'title':'1',
        'width':1000,
        'height':800,
        isStacked: true,
    };


    var chart = new google.visualization.SteppedAreaChart(document.getElementById('chart_div'));
    chart.draw(data, options);
}