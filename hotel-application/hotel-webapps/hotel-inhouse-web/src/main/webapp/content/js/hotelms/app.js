var hotelms = angular.module('hotelms', ['ui.compat', 'hotelms-routes', 'ngResource', 'ui.bootstrap'])
.config(['$stateProvider', '$routeProvider', '$urlRouterProvider', 'routes',
  function ($stateProvider,   $routeProvider,   $urlRouterProvider, routes) {

    $urlRouterProvider
        .otherwise('/');

    $routeProvider
        .when('/', {
          templateUrl: routes.dashboard.template, controller: 'DashboardController'
        });

    $stateProvider
        .state('reservations', {
            url : "/reservations",
            templateUrl : routes.reservation.template
        })
        .state('reservations.browse', {
            url : "/browse",
            templateUrl :routes.reservation.browse.template,
            controller :  'ReservationsBrowseList'
        })
        .state('housekeeping', {
            url : "/housekeeping"
        })
        .state('billing', {
            url : "/billing"
        })
        .state('guests', {
            url : "/guests"
        })
        .state('reports', {
            url : "/reports"
        })
        .state('inclusions', {
            url : "/inclusions"
        })
        .state('nightaudit', {
            url : "/nightaudit"
        });
}])
.run(['$rootScope', '$state', '$stateParams', function ($rootScope,   $state,   $stateParams) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
}]);

hotelms.controller('AppController', ['$scope', 'routes', function($scope, routes){
    console.log('AppController')
    console.log(routes);

    $scope.routes = routes;
}]);