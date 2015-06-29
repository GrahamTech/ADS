var reportControllers = angular.module('reportControllers', ['ngAnimate']);

reportControllers.factory('reportService', ['$http', function($http){
	return{
		getAll: function (){
			return $http.get('http://localhost:8080/ADS/gt/read/db/events');
		},
		getRecord: function (){
			return $http.get('http://localhost:8080/ADS/gt/read/db/event');
		},
		removeReport: function (report){
			var currentId = report.event_id;
			if(currentId != null && parseInt(currentId, 10) > 0){
				if(window.confirm('Are you sure you want to delete this record?')){
					window.location.href = '#/list'
					return $http({
						url: 'http://localhost:8080/ADS/gt/delete/db/event',
						method: 'GET',
						params: {event_id : currentId}
						//may only need one or the other (data is passing event_id as well)
					})	
				}
			}
		},
		postReport: function (reportToedit){
			
			current_id = reportToedit.event_id;
			console.log(current_id + 'before post');
			$http({
				url: 'http://localhost:8080/ADS/gt/update/db/event',
				method: "POST",
				params: {event_id : current_id},
				data: reportToedit
				
			})	
		.then(function(response){
			if(response !== 'undefined'
				&& typeof (response) == 'object') {
				window.location.href = '#/list'
			}
		},
			function (response){
				//failed
			}
		
		);
		},
		saveReport: function (report){
			$http({
				url: 'http://localhost:8080/ADS/gt/create/db/event',
				method: "POST",
				data: report
				
			})	
		.then(function(response){
			if(response !== 'undefined'
				&& typeof (response) == 'object') {
				window.location.href = '#/list'
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
		
		report["safetyreportid"] = $scope.safetyreportid;
		report["senderorganization"] = $scope.sender;
		report["serious"] = $scope.serious;
		report["companynumb"] = $scope.companynumb;
		report["patient_reactions"] = $scope.reaction;
		reportService.saveReport(report);
	}	
	
	}
]);

reportControllers.controller('DetailsController', ['$scope', '$http','$routeParams', function($scope, $http, $routeParams) {
  $http.get('http://localhost:8080/ADS/gt/read/db/events').success(function(data) {
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

reportControllers.controller('EditController', ['$scope', '$http','$routeParams','reportService', function($scope, $http, $routeParams, reportService) {
  $http.get('http://localhost:8080/ADS/gt/read/db/event?event_id=' + $routeParams.itemId).success(function(data) {
    $scope.results = data.results;
    //$scope.whichItem = $routeParams.itemId;
    console.log($scope.results[0].event_id);
    $scope.event_id = $scope.results[0].event_id;
    $scope.safetyreportid = $scope.results[0].safetyreportid;
    $scope.senderorganization = $scope.results[0].sender.senderorganization;
    $scope.serious = $scope.results[0].serious;
    $scope.companynumb = $scope.results[0].companynumb;
    $scope.patient_reactions = $scope.results[0].patient_reactions;

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
    

    $scope.edit = function(){
		var report = {};
		report["event_id"] = $scope.event_id;
		report["safetyreportid"] = $scope.safetyreportid;
		report["senderorganization"] = $scope.senderorganization;
		report["serious"] = $scope.serious;
		report["companynumb"] = $scope.companynumb;
		report["patient_reactions"] = $scope.patient_reactions;
		reportService.postReport(report);
	}
  });
}]);


reportControllers.controller('ListController', ['$scope', '$http','$window','reportService', function($scope, $http,$window, reportService) {
	  $http.get('http://localhost:8080/ADS/gt/read/db/events').success(function(data) {
		
	    $scope.results = data.results;
		$scope.redirect = function(report){
				$window.location.href = '/edit/' + report.Id;
			}
		
		$scope.getReport = function(report){
			$http.get('http://localhost:8080/ADS/gt/get/drug/events/and/store/apikey/' + $scope.rowsRequest).success(function(data) {
				reportService.getAll().success(function(data1){
					
					$scope.reports = data1.results;
					console.log('got some records' + $scope.reports[0].safetyreportid);
					$window.location.href = 'http://localhost:8080/ADS/grahamtech/index.html#/details';
					
				});
				
			});
		}
		$scope.remove = function(report){
			reportService.removeReport(report).success(function(data){
				
				load();
				function load(){
					
					reportService.getAll().success(function(data1){
						
						$scope.reports = data1.results;
						console.log('got some records' + $scope.reports[0].safetyreportid);
						$window.location.href = 'http://localhost:8080/ADS/grahamtech/index.html#/details';
						
					});
				}
			});
		}
	
	  });
	}]);