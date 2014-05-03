var mbApp = angular

.module('AccountantSection', ['ui.bootstrap'])

.config(['$routeProvider', function($routeProvider) {
	  $routeProvider

.when('/introduction', {templateUrl: '../ac/partials/editprofile_intro.html', controller: AccountantSectionController })
.when('/experience', {templateUrl: '../ac/partials/editprofile_exp.html', controller: AccountantSectionController })
	              .otherwise({redirectTo : '/introduction'});

	  //$locationProvider.html5Mode(true);
}]);	

function AccountantSectionController($scope){
	
}
