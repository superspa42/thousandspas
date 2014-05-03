var AccountantConnect = angular
.module('AccountantConnect', ['ui.bootstrap'])
.config(['$routeProvider', function($routeProvider) {
	
}]);

function SignUpController($scope, $http) {

	$scope.registerUser = function() {
		// alert($scope.user.email);
		// alert($scope.user.number);

		$http(
				{
					method : 'GET',
					url : '../api/addPotentialCustomer.json' + '?' + 'email='
							+ $scope.user.email + '&mobile='
							+ $scope.user.number + '&businessname='
							+ $scope.user.businessName
				}).success(function(data, status, headers) {
			$scope.userAddSuccess = true;
		}).error(function(data, status, header) {
			$scope.userAddError = true;
			// alert('Error in creating user');
		});

	};
	
	$scope.login = function() {
		// alert($scope.user.email);
		// alert($scope.user.number);

		$http(
				{
					method : 'GET',
					url : '../api/addPotentialCustomer.json' + '?' + 'email='
							+ $scope.user.email + '&mobile='
							+ $scope.user.number + '&businessname='
							+ $scope.user.businessName
				}).success(function(data, status, headers) {
			$scope.userAddSuccess = true;
		}).error(function(data, status, header) {
			$scope.userAddError = true;
			// alert('Error in creating user');
		});

	};
	
	$scope.registerUserWithEmailAndName = function() {
		alert($scope.accountant.accountantEmail);
	};

	$scope.userAddSuccess = false;
	$scope.userAddError = false;

}

