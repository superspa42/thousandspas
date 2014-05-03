var AccountantConnect = angular
.module('SignUp', ['ui.bootstrap'])
.config(['$routeProvider', function($routeProvider) {
	
}]);

function validateFieldsOnSignUpForm(){

	var accountantEmail = $("#accountant_email").val();
	
	var atpos=accountantEmail.indexOf("@");
	var dotpos=accountantEmail.lastIndexOf(".");

	var pattern = new RegExp("/^(([^<>()[\]\\.,;:\s@\"]+(\.[^<>()[\]\\.,;:\s@\"]+)*)|(\".+\"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/");
	if(atpos<1 || dotpos<atpos+2 || dotpos+2 >= accountantEmail.length){
		$("#infoEmailTextMsg").hide();
		$("#accountantEmailInvalidFormat").show();
		$("#accountant_email").focus();
		$("#accountant_email_div").addClass("has-error");
		return false;
	}

	else {
		$("#accountantEmailInvalidFormat").hide();
		$("#accountant_email_div").removeClass("has-error");
		return true;
	}

}

function SignUpController($scope, $http, $location, $rootScope) {
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
	
	$scope.accountantEmail = email;
	$scope.accountantName = name;
	
	$scope.submitPassword = function(){
		 $http({
	        	method: 'POST', 
	        	url: '../login', 
	        	data: $.param({email: $scope.accountantEmail, password: $scope.accountantPassword, action: "choosePassword"}),
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
	        	data: $.param({email: $scope.accountantEmail, password: $scope.accountantPassword, action: "login"}),
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
	        	data: $.param({email: $scope.accountantEmail, action: "logout"}),
	        	headers : { 'Content-Type': 'application/x-www-form-urlencoded'}}).
	            success(function(data, status, headers, config) {
	            	window.location.href = "signin.html";
	            }).
	            error(function(data, status, headers, config) {
	            });
	};
}






