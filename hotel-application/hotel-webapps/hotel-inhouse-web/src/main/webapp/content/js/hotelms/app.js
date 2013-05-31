var hotelms = angular.module('hotelms', ['ui.compat', 'hotelms-routes', 'ngResource', 'ui.bootstrap', '$strap'])
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
        .state('reservations.sheet', {
            url : '/sheet',

            views : {
                '' : {
                     templateUrl : routes.reservation.sheet.template,
                     controller : 'ReservationsSheetCtrl'
                },
                'legend' : {
                    templateUrl : routes.reservation.sheet.legend.template
                }
            }

        })
        .state('reservations.checkins', {
            url : '/checkins',
            templateUrl : routes.reservation.browse.template,
            controller : 'ReservationsWithStatusCtrl',
            data : {
                status : 'checkin'
            }
        })
        .state('reservations.checkouts', {
            url : '/checkouts',
            templateUrl : routes.reservation.browse.template,
            controller : 'ReservationsWithStatusCtrl',
            data : {
                status : 'checkout'
            }
        })
        .state('reservations.booking', {
            url : '/booking',
            templateUrl : routes.reservation.booking.template,
            controller : 'ReservationsBookingCtrl'
        })
        .state('reservations.edit', {
            url : "/edit/:reservationId",
            templateUrl : routes.reservation.details.template,
            controller : 'ReservationEditCtrl'
        })
        .state('housekeeping', {
            url : "/housekeeping"
        })
        .state('billing', {
            url : "/billing"
        })
        .state('guests', {
            url : "/guests",
            templateUrl : routes.guests.template
        })
        .state('guests.list', {
            url : "/list",
            templateUrl : routes.guests.list.template,
            controller : 'GuestsListCtrl'
        })
        .state('guests.add', {
            url : "/add",
            templateUrl : routes.guests.details.template,
            controller : 'CreateGuestCtrl'
        })
        .state('guests.edit', {
            url : "/edit/:guestId",
            templateUrl : routes.guests.details.template,
            controller : 'GuestEditCtrl'
        })
        .state('reports', {
            url : "/reports"
        })
        .state('inclusions', {
            url : "/inclusions"
        })
        .state('nightaudit', {
            url : "/nightaudit",
            templateUrl : routes.nightaudit.template,
            controller : 'NightauditCtrl'
        });
}])
.run(['$rootScope', '$state', '$stateParams', function ($rootScope,   $state,   $stateParams) {
        $rootScope.$state = $state;
        $rootScope.$stateParams = $stateParams;
        $rootScope.isDebug = false;
}]);

hotelms.controller('AppController', ['$scope', 'routes', function($scope, routes){
    $scope.routes = routes;
}]);