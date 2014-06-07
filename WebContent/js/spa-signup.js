var ThousandSpas = angular
.module('SignUp', ['ui.bootstrap'])
.config(['$routeProvider', function($routeProvider) {
	
}]);

function validateFieldsOnSignUpForm(){

	var spaprimaryemail = $("#spa_primaryemail").val();
	
	var atpos=spaprimaryemail.indexOf("@");
	var dotpos=spaprimaryemail.lastIndexOf(".");

	var pattern = new RegExp("/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/");
	if(atpos<1 || dotpos<atpos+2 || dotpos+2 >= spaprimaryemail.length){
		$("#infoEmailTextMsg").hide();
		$("#spaprimaryemailInvalidFormat").show();
		$("#spa_primaryemail").focus();
		$("#spa_primaryemail_div").addClass("has-error");
		return false;
	}

	else {
		$("#spaprimaryemailInvalidFormat").hide();
		$("#spa_primaryemail_div").removeClass("has-error");
		return true;
	}

}

function SignUpControllerSpa($scope, $http, $location, $rootScope) {
	//Get email and name from cookie.
	var email = $.cookie("profile_email");
	var name = $.cookie("profile_name");
	$scope.nameIsHidden = "hidden";
	$scope.emailReadOnly = "readonly";
	//If cookies are not set, user got this fresh page
	//We should hide the name span and remove "readonly"
	//from the email.
	if(name != null && name != "" && name != undefined){
		$scope.nameIsHidden = "";
		$scope.emailReadOnly = "";
	}
	
	$scope.spaprimaryemail = email;
	$scope.spaname = name;
	
	$scope.submitPassword = function(){
		 $http({
	        	method: 'POST', 
	        	url: '../login', 
	        	data: $.param({email: $scope.spaprimaryemail, password: $scope.spa_password, action: "choosePassword"}),
	        	headers : { 'Content-Type': 'application/x-www-form-urlencoded'}}).
	            success(function(data, status, headers, config) {
	            	window.location.href = "editprofile.html";
	            }).
	            error(function(data, status, headers, config) {
	            });
	};
	
	$scope.login = function(){
		 $http({
	        	method: 'POST', 
	        	url: '../login', 
	        	data: $.param({email: $scope.spaprimaryemail, password: $scope.spa_password, action: "login"}),
	        	headers : { 'Content-Type': 'application/x-www-form-urlencoded'}}).
	            success(function(data, status, headers, config) {
	            	window.location.href = "editprofile.html";
	            }).
	            error(function(data, status, headers, config) {
	            });
	};
	
	$scope.logout = function(){
		 $http({
	        	method: 'POST', 
	        	url: '../login', 
	        	data: $.param({email: $scope.spaprimaryemail, action: "logout"}),
	        	headers : { 'Content-Type': 'application/x-www-form-urlencoded'}}).
	            success(function(data, status, headers, config) {
	            	window.location.href = "signin.html";
	            }).
	            error(function(data, status, headers, config) {
	            });
	};
}






