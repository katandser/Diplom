function show(checkId) {
    var dd = checkId.split(',');
    var finalString = "";
    for (var i in dd)
    {
        if (document.getElementById(dd[i]).checked) {
            finalString += dd[i] + ",";
        }
    }


    window.location = "/comparisonShop/" + finalString;
}