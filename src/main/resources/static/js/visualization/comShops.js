function show(checkId) {
    var dd = checkId.split(',');
    var finalString = "";
    for (var i in dd)
    {
        if (document.getElementById(dd[i]).checked) {
            finalString += dd[i] + ",";
        }
    }

    // var checkbox = document.get ('fluency');
    // if (checkbox.checked != true){
    //     alert("you need to be fluent in English to apply for the job");
    //
    //


    window.location = "/comparisonShop/" + finalString;
}