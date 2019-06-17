function show(checkId) {
    var dd = checkId.split(',');
    var finalString = "";
    for (var i in dd)
    {
        if (document.getElementById(dd[i]).checked) {
            finalString += dd[i] + ",";
        }
    }
    finalString += "/";
    finalString += document.getElementById('date1').value + ",";
    finalString += document.getElementById('date2').value;

    if (document.getElementById('date1').value != ""
        && document.getElementById('date2').value != "") {
        window.location = "/comparisonShop/" + finalString;
    }
}