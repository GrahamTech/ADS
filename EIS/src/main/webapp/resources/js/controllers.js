var reportControllers = angular.module('reportControllers', ['ngAnimate']);

reportControllers.factory('reportService', ['$http', function($http){
	return{
		getAll: function (){
			return $http.get('yourURLtoRetreiveAll');
		},
		removeReport: function (report){
			var currentId = report.event_id;
			if(currentId != null && parseInt(currentId, 10) > 0){
				if(window.confirm('Are you sure you want to delete this record?')){
					return $http({
						url: 'http://localhost:8080/ADS/gt/delete/db/event',
						method: 'POST',
						params: {event_id : currentId},
						data: {event_id : currentId}
						//may only need one or the other (data is passing event_id as well)
					})
				}
			}
		},
		saveReport: function (report){
			$http({
				url: 'yourURL/',
				method: "POST",
				data: report
				
			})	
		.then(function(response){
			if(response !== 'undefined'
				&& typeof (response) == 'object') {
				window.location.href = '/list'
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
			$window.location.href = '#/edit/' + report;
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
		report["event_id"] = $scope.event_id;
		report["safetyreportid"] = $scope.safetyreportid;
		report["sender"] = $scope.sender;
		report["serious"] = $scope.serious;
		report["companynumb"] = $scope.companynumb;
		report["reaction"] = $scope.reaction;
		reportService.saveReport(report);
	}	
	
	}
]);

reportControllers.controller('DetailsController', ['$scope', '$http','$routeParams', function($scope, $http, $routeParams) {
  $http.get('js/ade.json').success(function(data) {
    $scope.results = data.results;
    $scope.whichItem = $routeParams.itemId;

    if ($routeParams.itemId > 0) {
      $scope.prevItem = Number($routeParams.itemId)-1;
    } else {
      $scope.prevItem = $scope.results.length-1;
    }

    if ($routeParams.itemId < $scope.results.length-1) {
      $scope.nextItem = Number($routeParams.itemId)+1;
    } else {
      $scope.nextItem = 0;
    }

  });
}]);

reportControllers.controller('EditController', ['$scope', '$http','$routeParams', function($scope, $http, $routeParams) {
  $http.get('js/ade.json').success(function(data) {
    $scope.results = data.results;
    $scope.whichItem = $routeParams.itemId;

    if ($routeParams.itemId > 0) {
      $scope.prevItem = Number($routeParams.itemId)-1;
    } else {
      $scope.prevItem = $scope.results.length-1;
    }

    if ($routeParams.itemId < $scope.results.length-1) {
      $scope.nextItem = Number($routeParams.itemId)+1;
    } else {
      $scope.nextItem = 0;
    }

  });
}]);


reportControllers.controller('ListController', ['$scope', '$http','reportService', function($scope, $http,reportService) {
	  $http.get('http://localhost:8080/ADS/gt/read/db/events').success(function(data) {
		
	    $scope.results = data;
		$scope.redirect = function(report){
				$window.location.href = '/edit/' + report.Id;
			}
		$scope.remove = function(report){
			reportService.removeReport(report).success(function(data){
				load();
				function load(){
					reportService.getAll.success(function(data){
						$scope.reports = data;
					});
				}
			});
		}
		
		console.log($scope.results);
	    //$scope.reportOrder = 'results.safetyreportid';
	  });
	}]);