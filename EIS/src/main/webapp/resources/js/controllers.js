var reportControllers = angular.module('reportControllers', ['ngAnimate']);

reportControllers.factory('reportService', ['$http', function($http){
	return{
		getAll: function (){
			return $http.get('GetReports');
		},
		removeReport: function (report){
			var currentId = report.Id;
			if(currentId != null && parseInt(currentId, 10) > 0){
				if(window.confirm('Are you sure you want to delete this record?')){
					return $http({
						url: 'removeReport',
						method: 'POST',
						data: {reportId : currentId}
					})
				}
			}
		},
		saveReport: function (reportTosave){
			$http({
				url: 'persistReport',
				method: "POST",
				data: reportTosave
				
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

reportControllers.controller('ReportController',
	['$scope', '$http', '$window', 'reportService', function($scope, $http, $window, reportService){
		load();
		function load(){
			reportService.getAll.success(function(data){
				$scope.reports = data;
			});
		}
		$scope.redirect = function(report){
			$window.location.href = '/report/edit/' + report.Id;
		}
		$scope.save = function(report){
			reportService.saveReport(report);
		}
		$scope.remove = function(report){
			reportService.removeReport(report).success(function(data){
				load();
			});
		}
	}
]);


reportControllers.controller('AddController',
	['$scope', '$http', 'reportService', function($scope, $http, reportService){
	
	$scope.add = function(){
		var report = {};
		report["firstName"] = $scope.firstName;
		report["lastName"] = $scope.lastName;
		
		reportService.saveReport(report);
	}	
	
	}
]);

reportControllers.controller('DetailsController', ['$scope', '$http','$routeParams', function($scope, $http, $routeParams) {
  $http.get('js/data.json').success(function(data) {
    $scope.reports = data;
    $scope.whichItem = $routeParams.itemId;

    if ($routeParams.itemId > 0) {
      $scope.prevItem = Number($routeParams.itemId)-1;
    } else {
      $scope.prevItem = $scope.reports.length-1;
    }

    if ($routeParams.itemId < $scope.reports.length-1) {
      $scope.nextItem = Number($routeParams.itemId)+1;
    } else {
      $scope.nextItem = 0;
    }

  });
}]);

reportControllers.controller('ListController', ['$scope', '$http', function($scope, $http) {
  $http.get('js/ade.json').success(function(data) {
    $scope.reports = data;
	console.log($scope.reports);
    //$scope.reportOrder = 'results.safetyreportid';
  });
}]);