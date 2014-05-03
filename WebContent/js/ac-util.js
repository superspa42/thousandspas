function getParameterByNameFromURL(name) {
    name = name.replace(/[\[]/, "\\[").replace(/[\]]/, "\\]");
    var regex = new RegExp("[\\?&]" + name + "=([^&#]*)"),
        results = regex.exec(location.search);
    return results == null ? "" : decodeURIComponent(results[1].replace(/\+/g, " "));
}

function setTrackingDetailsInCookieIfNotAlreadySet(){
	ac_mail = getParameterByNameFromURL("ac_mail");
	ac_mobile = getParameterByNameFromURL("ac_mobile");
	ac_id = getParameterByNameFromURL("ac_id");
	bs_id = getParameterByNameFromURL("bs_id");
	
	if($.cookie("ac_mail") == null || $.cookie("ac_mail") == "")
	   $.cookie("ac_mail", ac_mail, { expires : 100 });
	if($.cookie("ac_mobile") == null || $.cookie("ac_mobile") == "")
	   $.cookie("ac_mobile", ac_mobile, { expires : 100 });
	if($.cookie("ac_id") == null || $.cookie("ac_id") == "")
	   $.cookie("ac_id", ac_id, { expires : 100 });
	if($.cookie("bs_id") == null || $.cookie("bs_id") == "")
	   $.cookie("bs_id", bs_id, { expires : 100 });
}

function trackUserActivity(activity){
   $.post("../api/tracking/addTracking",
        {
            email:$.cookie("ac_mail"),
            mobile:$.cookie("ac_mobile"),
            accounant_id:$.cookie("ac_id"),
            business_id:$.cookie("bs_id"),
            feature:activity
        },
        function(data, status) {
        });
}