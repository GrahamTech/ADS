var myApp = angular.module('myApp', [
  'ngRoute',
  'reportControllers',
  'ui.bootstrap'
]);

myApp.config(['$routeProvider', function($routeProvider) {
  $routeProvider.
  when('/list', {
    templateUrl: 'views/list.html',
    controller: 'ListController'
  }).
  when('/details/:itemId', {
    templateUrl: 'views/details.html',
    controller: 'DetailsController'
  }).
  when('/add', {
    templateUrl: 'views/add.html',
    controller: 'AddController'
  }).
  when('/edit/:itemId', {
    templateUrl: 'views/edit.html',
    controller: 'EditController'
  }).
  otherwise({
    redirectTo: '/list'
  });
}]);