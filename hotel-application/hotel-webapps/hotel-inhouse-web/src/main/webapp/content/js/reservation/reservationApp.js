angular.module('ReservationApp', ['reservationResource']).
config(function($routeProvider) {
    $routeProvider.
      when('/', {controller:ReservationListCtrl, templateUrl:'list.html'}).
      when('/edit/:reservationId', {controller:ReservationEditCtrl, templateUrl:'detail.html'}).
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

function ReservationEditCtrl($scope, $routeParams, $location, $http, Reservation)
{
  var self = this;
  $scope.chargesCalculated = false;

  $scope.calculateCharges = function()
  {
    var room = getCurrentRoomObject()
    $http.get('/rest/charge/room/' + room.number + '/rate/' + escape($scope.reservation.rateType), {
      params : {"checkin" : $scope.reservation.checkin,
      "checkout" : $scope.reservation.checkout}
    }).success(function(data){
      $scope.amount = data.amount;
      $scope.chargesCalculated =  true;
    }); 
  }

  function getCurrentRoomObject()
  {   
    for(var room in $scope.rooms)
    {
      if($scope.rooms[room].id === $scope.reservation.roomName)
      {
        return $scope.rooms[room];
      }
    }
  }

  $scope.extraBeds = [{ extraBed: 0 },{extraBed: 1},{extraBed:2}, {extraBed:3},
                 { extraBed: 4 },{extraBed: 5},{extraBed:6}, {extraBed:7},
                 { extraBed: 8 },{extraBed: 9},{extraBed:10}, {extraBed:11}
                ]

  Reservation.get({ id: $routeParams.reservationId }, function(reservation){
     reservation.numberOfAdults = parseInt(reservation.numberOfAdults);
     reservation.numberOfChildren = parseInt(reservation.numberOfChildren);
     reservation.roomExtraBed = parseInt(reservation.roomExtraBed);
     self.original = reservation;
    $scope.original = reservation;
    $scope.reservation = new Reservation(self.original);
  });

  $http.get("/rest/room/types").success(function(data, status, headers, config){
    $scope.types = data;
  });

  $http.get("/rest/room").success(function(data, status, headers, config){
    $scope.rooms = data;
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