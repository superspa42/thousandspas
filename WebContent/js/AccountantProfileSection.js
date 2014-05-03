var mbApp = angular

.module('AccountantProfileSection', ['ui.bootstrap','ngRoute'])
.config(['$routeProvider', function($routeProvider) {
	  $routeProvider

.when('/basic_info', {templateUrl: '../ac/partials/editprofile_basicinfo.html', controller: PartialProfileViewController  })
.when('/photo', {templateUrl: '../ac/partials/editprofile_photo.html', controller: PhotoController })
.when('/description', {templateUrl: '../ac/partials/editprofile_description.html', controller: PartialProfileViewController  })
.when('/social', {templateUrl: '../ac/partials/editprofile_social.html', controller: PartialProfileViewController  })
.when('/exp', {templateUrl: '../ac/partials/editprofile_exp.html', controller:PartialProfileViewController})
.when('/education', {templateUrl: '../ac/partials/editprofile_education.html'})
.when('/address', {templateUrl: '../ac/partials/editprofile_address.html'})
	              .otherwise({redirectTo : '/basic_info'});

	  //$locationProvider.html5Mode(true);
}]);	

/**
 * Main controller for the accountant profile screen
 * @param $scope
 * @param $http
 * @returns
 */
function AccountantController($scope, $http, $location, $rootScope, $modal) {
	$scope.accountant = [];
	$scope.expertises = [{id: 'expertise1',value: ''}, {id: 'expertise2',value: ''}, {id: 'expertise3',value: ''}];
	$scope.accountant.actualImgDisplay = "hidden";
	$scope.accountant.dummyImgDisplay = "";
	$scope.accountant.PreviewLinkHidden = "hidden";
	$scope.accountant.SampleLinkHidden = "";
	
    $scope.getAccountant = function() {
        $http({method: 'GET', url: '../api/accountant/profile'}).
            success(function(data, status, headers, config) {
            	if(data.code == 1){
	                $scope.accountant = data.data;
	                //If photo is uploaded, unhide the section.
	                if($scope.accountant.photoFileName != "null" && $scope.accountant.photoFileName != null){
	                	$scope.accountant.actualImgDisplay = "";
	                	$scope.accountant.dummyImgDisplay = "hidden";
	                }else{
	                	$scope.accountant.actualImgDisplay = "hidden";
	                	$scope.accountant.dummyImgDisplay = "";
	                }
	                //build the expertise fields from "areasOfExpertise"
	                var expertiseString = $scope.accountant.areasOfExpertise;	                
	                //If no expertise is entered, just show the 3 text boxes
	                if(expertiseString != null  && expertiseString != undefined && expertiseString != ""){
	                	var expertiseArr = expertiseString.split("|");
	                	$scope.expertises = [];
	                	for(var i = 0; i < expertiseArr.length; i++){
	                		var id = i+1;
	                		var val = expertiseArr[i];
	              		    $scope.expertises.push({'id':'expertise' + id,'value': val});
	                	}
	                }
	                //If FTU, open the FTU modal dialog - editprofile page
	                if($scope.accountant.noOfVisitToEditProfilepage == 1)
	                   $scope.openFTUModalDialog($modal);
	                
	                //Show preview only if all the necessary sections are filled.
	                //Else show the sample page
	                if($scope.accountant.profileCompletionStatus.allMandatorySectionsFilled ==  true){
	                	$scope.accountant.PreviewLinkHidden = "";
	                	$scope.accountant.SampleLinkHidden = "hidden";
	                }else{
	                	$scope.accountant.PreviewLinkHidden = "hidden";
	                	$scope.accountant.SampleLinkHidden = "";
	                }
	                
	                //TODO: Need to create a function
	                //Change the + plus sign to green tick
	                if($scope.accountant.profileCompletionStatus.basicInfoSectionFilled == true){
	                	editProfileLeftNavs_intro[0].IsItemFilled = "glyphicon-ok";
	                	editProfileLeftNavs_intro[0].itemIconColor = "green";
	                }
	                if($scope.accountant.profileCompletionStatus.photoUploaded == true){
	                	editProfileLeftNavs_intro[1].IsItemFilled = "glyphicon-ok";
	                	editProfileLeftNavs_intro[1].itemIconColor = "green";		                	
	                }
	                if($scope.accountant.profileCompletionStatus.descriptionSectionFilled == true){
	                	editProfileLeftNavs_intro[2].IsItemFilled = "glyphicon-ok";
	                	editProfileLeftNavs_intro[2].itemIconColor = "green";		                	
	                }
	                if($scope.accountant.profileCompletionStatus.experienceSectionFilled == true){
	                	editProfileLeftNavs_prof[0].IsItemFilled = "glyphicon-ok";
	                	editProfileLeftNavs_prof[0].itemIconColor = "green";		                	
	                }
	                if($scope.accountant.profileCompletionStatus.contactSectionFilled == true){
	                	editProfileLeftNavs_prof[2].IsItemFilled = "glyphicon-ok";
	                	editProfileLeftNavs_prof[2].itemIconColor = "green";		                	
	                }
	            }
            	//Need to login
            	else if(data.code == 2){
            		window.location.href = "signin.html";
	            }else{
	            	$scope.errorMessageFromServer = data.description;
	            }
            }).
            error(function(data, status, headers, config) {
                $scope.apps = data || "Request failed";
                $scope.status = status;
                $scope.view = './Partials/list.html';
            });
  };
                
  $scope.getAccountant();
  $scope.successMessageDivVisibility = "none";       
  $scope.errorMessageDivVisibility = "none";       
	  
	  //Save and Next
		$scope.saveFormDataAndNextView = function saveFormDataAndNextView(nextView) {
			$scope.successMessageDivVisibility = "none"; 
        	$scope.errorMessageDivVisibility = "none";  
        	//Add all the expertises and make a string.
        	var expertiseString = "";
        	var expertiseCount = 0;
        	for(var i = 0; i < $scope.expertises.length; i++){
        		var expertiseVal = $scope.expertises[i].value;
        		if(expertiseVal != "" && expertiseVal != null  && expertiseVal != undefined){
        			if(expertiseCount != 0){
        				expertiseString += '|';
        			}
        			expertiseString += expertiseVal;
        			expertiseCount++;
        		}
        	}
        	//Add expertise to the scope
        	$scope.accountant.areasOfExpertise = expertiseString;
	        $http({
	        	method: 'POST', 
	        	url: '../api/accountant/saveProfile', 
	        	data: $.param($scope.accountant),
	        	headers : { 'Content-Type': 'application/x-www-form-urlencoded'}}).
	            success(function(data, status, headers, config) {
	            	if(data.code == 1){   		                
		                if(nextView == null){
		                	$scope.successMessageDivVisibility = "none";       
		                	$scope.successMessageDivVisibility = "block";       
		                	$scope.successMessageDivSuccessMessage = data.description;
		                }else{		                	   
			                $scope.successMessageDivSuccessMessage = data.description;
			                $location.path(nextView);
		                }
		              //TODO: Need to create a function
		              //Change the + plus sign to green tick
		                if(data.data.basicInfoSectionFilled == true){
		                	editProfileLeftNavs_intro[0].IsItemFilled = "glyphicon-ok";
		                	editProfileLeftNavs_intro[0].itemIconColor = "green";
		                }
		                if(data.data.photoUploaded == true){
		                	editProfileLeftNavs_intro[1].IsItemFilled = "glyphicon-ok";
		                	editProfileLeftNavs_intro[1].itemIconColor = "green";		                	
		                }
		                if(data.data.descriptionSectionFilled == true){
		                	editProfileLeftNavs_intro[2].IsItemFilled = "glyphicon-ok";
		                	editProfileLeftNavs_intro[2].itemIconColor = "green";		                	
		                }
		                if(data.data.experienceSectionFilled == true){
		                	editProfileLeftNavs_prof[0].IsItemFilled = "glyphicon-ok";
		                	editProfileLeftNavs_prof[0].itemIconColor = "green";		                	
		                }
		                if(data.data.contactSectionFilled == true){
		                	editProfileLeftNavs_prof[2].IsItemFilled = "glyphicon-ok";
		                	editProfileLeftNavs_prof[2].itemIconColor = "green";		                	
		                }
		            }//Need to login
	            	else if(data.code == 2){
	            		window.location.href = "signin.html";
		            }else{
		            	$scope.successMessageDivVisibility = "none"; 
		            	$scope.errorMessageDivVisibility = "block";       
		                $scope.errorMessageDivSuccessMessage = data.description;
		            }
	            }).
	            error(function(data, status, headers, config) {
	            	$scope.successMessageDivVisibility = "none"; 
	            	$scope.errorMessageDivVisibility = "block";       
	                $scope.errorMessageDivSuccessMessage = data.description;
	            });
	  };
	  
	   //Save form data
		$scope.saveFormData = function saveFormData() {
			$scope.saveFormDataAndNextView(null);
		};
	  
	  $scope.addNewExpertise = function() {
		  var newItemNo = $scope.expertises.length+1;
		  $scope.expertises.push({'id':'expertise' + newItemNo});
	  };
	  
	  //If first time, show FTU dialog
	  $scope.openFTUModalDialog = function ($modal) {
		    var modalInstance = $modal.open({
		      templateUrl: '../ac/modals/profilemodalftu.html',
		      controller: ModalInstanceCtrl,
		      resolve: {
		      }
		    });

		    modalInstance.result.then(function () {
		    }, function () {
		    });
	 };
	 
	  $scope.openDisplayContactModalDialog = function () {
		    var modalInstance = $modal.open({
		      templateUrl: '../ac/modals/displaycontactmodal.html',
		      controller: ModalInstanceCtrl,
		      resolve: {
		    	  accountant: function () {
		              return $scope.accountant;
		            }
		      }
		    });

		    modalInstance.result.then(function () {
		    }, function () {
		    });
	 };
	 
	 $scope.openDisplaySampleProfileModal = function () {
		    var modalInstance = $modal.open({
			      templateUrl: '../ac/modals/displaysampleprofilemodal.html',
			      controller: ModalInstanceCtrl,
			      resolve: {
			    	  accountant: function () {
			              return $scope.accountant;
			            }
			      }
			    });

			    modalInstance.result.then(function () {
			    }, function () {
			    });
		 };
	 
	 var ModalInstanceCtrl = function ($scope, $modalInstance) {
			$scope.ok = function () {
				$modalInstance.close();
			};
	  };
	  
	  //City list for typeahead
	  $scope.cities = ['Delhi', 'Mumbai', 'Bangalore', 'Chennai', 'Hyderabad', 'Kolkata', 'Pune', 'Chandigarh', 'Ahmedabad', 'Amritsar', 'Bhubaneswar', 'Bhopal', 'Coimbatore', 'Ernakulum', 'Goa', 'Guntur', 'Haldwani', 'Indore', 'Jaipur', 'Jalandhar', 'Kakinada', 'Kanpur', 'Kota', 'Lucknow', 'Ludhiana', 'Mysore', 'Nagpur', 'Patiala', 'Patna', 'Rajamundry', 'Salem', 'Surat', 'Thiruvanthapuram', 'Tirrupur', 'Udaipur', 'Ujjain', 'Vadodara', 'Vijayawada', 'Vizag', 'Others'];
	  $scope.states = ['Andaman and Nicobar Islands', 'Andhra Pradesh', 'Arunachal Pradesh', 'Assam','Telengana', 'Bihar', 'Chandigarh', 'Chhattisgarh', 'Delhi', 'Goa', 'Gujarat', 'Haryana', 'Himachal Pradesh', 'Jammu and Kashmir', 'Jharkhand', 'Karnataka', 'Kerala', 'Madhya Pradesh', 'Maharashtra', 'Manipur', 'Meghalaya', 'Mizoram', 'Nagaland', 'Orissa', 'Puducherry', 'Punjab', 'Rajasthan', 'Sikkim', 'Tamil Nadu', 'Tripura', 'Uttar Pradesh', 'Uttarakhand', 'West Bengal'];
	  
	  //Sidebar
	  //Create left nav of the edit profile
		var editProfileLeftNavs_intro = $scope.editProfileLeftNavs_intro = [];
		var editProfileLeftNavs_prof = $scope.editProfileLeftNavs_prof = [];

		editProfileLeftNavs_intro.push({
			class : "active",
			link : "basic_info",
			glyphicon : "glyphicon-user",
			LinkText : "Basic Info",
			itemIconColor : "red",
			IsItemFilled : "glyphicon-plus"
		});
		editProfileLeftNavs_intro.push({
			class : "inactive",
			link : "photo",
			glyphicon : "glyphicon-camera",
			LinkText : "Photo",
			itemIconColor : "red",
			IsItemFilled : "glyphicon-plus"
		});
		editProfileLeftNavs_intro.push({
			class : "inactive",
			link : "description",
			glyphicon : "glyphicon-file",
			LinkText : "Description",
			itemIconColor : "red",
			IsItemFilled : "glyphicon-plus"
		});
		editProfileLeftNavs_intro.push({
			class : "inactive",
			link : "social",
			glyphicon : "glyphicon-thumbs-up",
			LinkText : "Social Profile",
			itemIconColor : "red",
			IsItemFilled : ""
		});

		editProfileLeftNavs_prof.push({
			class : "inactive",
			link : "exp",
			glyphicon : "glyphicon-briefcase",
			LinkText : "Experience",
			itemIconColor : "red",
			IsItemFilled : "glyphicon-plus"
		});
		editProfileLeftNavs_prof.push({
			class : "inactive",
			link : "education",
			glyphicon : "glyphicon-book",
			LinkText : "Education",
			itemIconColor : "red",
			IsItemFilled : ""
		});
		editProfileLeftNavs_prof.push({
			class : "inactive",
			link : "address",
			glyphicon : "glyphicon-map-marker",
			LinkText : "Contact",
			itemIconColor : "red",
			IsItemFilled : "glyphicon-plus"
		});
		
		//Watch the location change. If it happens, update the sidebar as well
		$scope.$on('$locationChangeSuccess', function (event, newLoc, oldLoc){
			//Make everything inactive
			for ( var i = 0; i < editProfileLeftNavs_intro.length; i++) {
				$scope.editProfileLeftNavs_intro[i].class = "inactive";			
			}
			for ( var i = 0; i < editProfileLeftNavs_prof.length; i++) {
				$scope.editProfileLeftNavs_prof[i].class = "inactve";
			}
			//search which nav corresponds to the URL
			for ( var i = 0; i < editProfileLeftNavs_intro.length; i++) {
				if(newLoc.indexOf($scope.editProfileLeftNavs_intro[i].link) != -1){
				   $scope.editProfileLeftNavs_intro[i].class = "active";
				   return;
				}
			}
			for ( var i = 0; i < editProfileLeftNavs_prof.length; i++) {
				if(newLoc.indexOf($scope.editProfileLeftNavs_prof[i].link) != -1){
					$scope.editProfileLeftNavs_prof[i].class = "active";
				}
			}
		});
}
AccountantController.$inject = ['$scope', '$http', '$location', '$rootScope', '$modal'];

/**
 * PartialProfileViewController 
 * @param $scope
 * @param $http
 * @param $templateCache
 */
function PartialProfileViewController($scope, $http, $templateCache){
	//alert("hello");	
}

/**
 * Photo Upload controller
 * @param $scope
 * @param $http
 * @param $templateCache
 */

function PhotoController($scope, $http, $templateCache){
      
	 $scope.getPhotoLocation = function() {
	        $http({method: 'GET', url: '../api/accountant/getPhotoLocation'}).
	            success(function(data, status, headers, config) {
	            	if(data.code == 1){
		                $scope.accountant.photoFileName = data.data;
		                //If photo is uploaded, unhide the section.
		                if($scope.accountant.photoFileName != "null" && $scope.accountant.photoFileName != null){
		                	$scope.accountant.actualImgDisplay = "";
		                	$scope.accountant.dummyImgDisplay = "hidden";
		                }else{
		                	$scope.accountant.actualImgDisplay = "hidden";
		                	$scope.accountant.dummyImgDisplay = "";
		                }		      
		            }
	            	//Need to login
	            	else if(data.code == 2){
	            		window.location.href = "signin.html";
		            }else{
		            	$scope.errorMessageFromServer = data.description;
		            }
	            }).
	            error(function(data, status, headers, config) {
	                $scope.apps = data || "Request failed";
	            });
	  };
	  $scope.getPhotoLocation();
	
	/**
	 * To handle image upload
	 */
	$(function() {

		$('#file-input-button').click(function() {
			// Simulate a click on the file input button
			// to show the file browser dialog
			$('#fileupload').click();
			$scope.photoImgSrc = "img";
		});

		
		// Initialize the jQuery File Upload plugin
		$('#upload1').fileupload({

			// This function is called when a file is added to the queue;
			// either via the browse button, or via drag/drop:
			add : function(e, data) {
				
				// Automatically upload the file once it is added to the queue
				var jqXHR = data.submit();
			},
			progress : function(e, data) {

			},
			fail : function(e, data) {
			},
			success : function(e, data) {
				$("#photoImgSrc").attr("src", e[0].url);
				$("#photoImgSrc").removeClass("hidden");
				$("#dummyImg").addClass("hidden");
			},
			
			
		});
		// Helper function that formats the file sizes
		function formatFileSize(bytes) {
			if (typeof bytes !== 'number') {
				return '';
			}
			if (bytes >= 1000000000) {
				return (bytes / 1000000000).toFixed(2) + ' GB';
			}
			if (bytes >= 1000000) {
				return (bytes / 1000000).toFixed(2) + ' MB';
			}
			return (bytes / 1000).toFixed(2) + ' KB';
		}
	});
}

/**
 * Side bar controller
 * @param $scope
 */
function EditProfileSideBarController($scope) {

	
	
}

function CityTypeaheadCtrl($scope) {

	  $scope.selected = undefined;
	  $scope.cities = ['Alabama', 'Alaska', 'Arizona', 'Arkansas', 'California', 'Colorado', 'Connecticut', 'Delaware', 'Florida', 'Georgia', 'Hawaii', 'Idaho', 'Illinois', 'Indiana', 'Iowa', 'Kansas', 'Kentucky', 'Louisiana', 'Maine', 'Maryland', 'Massachusetts', 'Michigan', 'Minnesota', 'Mississippi', 'Missouri', 'Montana', 'Nebraska', 'Nevada', 'New Hampshire', 'New Jersey', 'New Mexico', 'New York', 'North Dakota', 'North Carolina', 'Ohio', 'Oklahoma', 'Oregon', 'Pennsylvania', 'Rhode Island', 'South Carolina', 'South Dakota', 'Tennessee', 'Texas', 'Utah', 'Vermont', 'Virginia', 'Washington', 'West Virginia', 'Wisconsin', 'Wyoming'];
	  // Any function returning a promise object can be used to load values asynchronously
}


