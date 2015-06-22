var editControllers = angular.module('editControllers', ['ngAnimate']);

editControllers.factory('editService', ['$http', '$window', function($http, $window){
	return{
		getData: function(){
			var currentReportId = this.getReportId();
			if(currentReportId != null){
				return $http.get('/app/getData',
				{params: {reportId: currentReportId}});
			}
		},
		getReportId: function(){
			var absoluteUrlPath = $window.location.href;
			var results = String(absoluteUrlPath).split("/");
			if (results != null && results.length > 0) {
				var reportId = results[results.length - 1];
				return reportId;
			}
		},
		postReportData: function (reportToedit){
			reportToedit.Id = this.getReportId();
			$http({
				url: 'updateReport',
				method: "POST",
				data: reportToedit
				
			})	
		.then(function(response){
			if(response !== 'undefined'
				&& typeof (response) == 'object') {
				window.location.href = '/report'
			}
		},
			function (response){
				//failed
			}
		
		);
		}
		
	};
}]);

editControllers.controller('editController',
	['$scope', '$http', '$window', 'editService', function($scope, $http, $window, editService){
	
	editService.getData().success(function(data){
		$scope.report = data;
		$scope.firstName = data.firstName;
		$scope.lastName = data.lastName;
	});
	
	$scope.edit = function(){
		var report = {};
		report["firstName"] = $scope.firstName;
		report["lastName"] = $scope.lastName;
		editService.postReportData(report);
	}
	
	
	
	}
]);


