var editControllers = angular.module('editControllers', ['ngAnimate']);

editControllers.factory('editService', ['$http', '$window', function($http, $window){
	return{
		getData: function(){
			var currentReportId = this.getReportId();
			if(currentReportId != null){
				return $http.get('/app/getData',
				{params: {event_id: currentReportId}});
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
		postReport: function (reportToedit){
			reportToedit.Id = this.getReportId();
			$http({
				url: 'updateReport',
				method: "POST",
				params: {event_id : report.event_id},
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
		$scope.event_id = data.event_id;
		$scope.safetyreportid = data.safetyreportid;
		$scope.sender = data.sender;
		$scope.serious = data.serious;
		$scope.companynumb = data.companynumb;
		$scope.reaction = data.reaction;
	});
	
	$scope.edit = function(){
		var report = {};
		report["event_id"] = $scope.event_id;
		report["safetyreportid"] = $scope.safetyreportid;
		report["sender"] = $scope.sender;
		report["serious"] = $scope.serious;
		report["companynumb"] = $scope.companynumb;
		report["reaction"] = $scope.reaction;
		editService.postReport(report);
	}
	
	
	
	}
]);


