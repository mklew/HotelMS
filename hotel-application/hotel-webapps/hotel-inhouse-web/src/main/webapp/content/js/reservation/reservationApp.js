angular.module('ReservationApp', ['reservationResource']).
config(function($routeProvider) {
    $routeProvider.
      when('/', {controller:ReservationListCtrl, templateUrl:'list.html'}).
      // when('/edit/:reservationId', {controller:ReservationEditCtrl, templateUrl:'detail.html'}).
       // when('/new', {controller:ReservationCreateCtrl, templateUrl:'detail.html'}).
      otherwise({redirectTo:'/'});
  });

function ReservationListCtrl($scope, $location, $http, Reservation) {
  $scope.reservations = Reservation.query();

  function currentReservation(index)
  {
    return $scope.reservations[index];
  }

  $scope.checkIn = function(index)
  {
    var reservation = currentReservation(index);
    reservation.checkIn();
  }

  $scope.checkOut = function(index)
  {
   var reservation = currentReservation(index);
   reservation.checkOut(function(){});

  }

  $scope.cancel = function(index)
  {
     var reservation = currentReservation(index);
     reservation.cancel(function(){});
  }

  $scope.destroy = function(index)
  {
    var reservation = currentReservation(index);
    reservation.destroy(function(){
      $scope.reservations = Reservation.query();
    });
  }

  $scope.hasPerm = function(perm)
  {
      $http.get('/rest/perm', {params : {"perm" : perm}}).success(function (){
        $scope._canDelete = true;
      }).error(function (){
        $scope._canDelete = false;
      });
  }

  $scope._canDelete = undefined;

  $scope.canDelete = function()
  {
    if(angular.isUndefined($scope._canDelete))
    {
      $scope.hasPerm('reservation:delete');      
    }
    return $scope._canDelete;
  }
}

function ReservationCreateCtrl($scope, $location, Reservation) {
    $scope.save = function() {
      Reservation.save($scope.reservation, function(reservation) {
        $location.path('/edit/' + reservation.id);
      });
    }
}

function ReservationEditCtrl($scope, $routeParams, $location, Reservation)
{
  var self = this;

  Reservation.get({ id: $routeParams.reservationId }, function(reservation){
     self.original = reservation;
    $scope.original = reservation;
    $scope.reservation = new Reservation(self.original);
  });

  $scope.isClean = function() {
    return angular.equals(self.original, $scope.reservation);
  }
 
  $scope.destroy = function() {
    self.original.destroy(function() {
      $location.path('/');
    });
  };
 
  $scope.save = function() {
    $scope.reservation.update(function() {
      $location.path('/');
    });
  };
}