var hotelms = angular.module('hotelms');

hotelms.controller('ReservationDetailsCtrl', ['$scope', 'RoomService', 'RateService', 'ChargeService',
 'Reservation','$stateParams', '$state', function($scope, RoomService, RateService, ChargeService, Reservation, $stateParams, $state){

    var self = this;

    $scope.roomTypes = RoomService.getRoomTypes();
    var rooms = RoomService.getRooms();

    $scope.rooms = rooms;

    rooms.then(function(){
         Reservation.get({ id: $stateParams.reservationId }, function(reservation){
                  reservation.numberOfAdults = parseInt(reservation.numberOfAdults);
                  reservation.numberOfChildren = parseInt(reservation.numberOfChildren);
                  reservation.roomExtraBed = parseInt(reservation.roomExtraBed);
                  self.original = reservation;
                 $scope.original = reservation;
                 $scope.reservation = new Reservation(self.original);

                 $scope.reservation.room = _.find(rooms.$$v, function(room){
                    return $scope.reservation.roomName === room.id;
                 });

                 $scope.filterRooms();
                 $scope.filterMaxExtraBeds();
                  $scope.getRates();
                  $scope.reservation.rate = { name : reservation.rateType };
                  $scope.calculateCharges();

             }, function(){
                $state.transitionTo('reservations');
             });
    });



    $scope.filterRooms = function()
    {
        console.log('filtering rooms')
        $scope.rooms = _.filter(rooms.$$v, function(room){
            return room.roomType === $scope.reservation.roomType;
        });
    }

    $scope.filterMaxExtraBeds = function()
    {
        $scope.beds = RoomService.getExtraBedsForRoom($scope.reservation.room);
    }

    $scope.getRates = function(){
        $scope.rates = RateService.getRatesForRoom($scope.reservation.room);
    }

    $scope.calculateCharges = function()
    {
        $scope.reservation.rateType = $scope.reservation.rate.name;
        $scope.charges = ChargeService.calculateChargesForRoom($scope.reservation.room, $scope.reservation.checkin,
            $scope.reservation.checkout, $scope.reservation.rate);
    }

    $scope.isClean = function() {
        return angular.equals(self.original, $scope.reservation);
      }

      $scope.destroy = function() {
        self.original.destroy(function() {
          $state.transitionTo('reservations');
        });
      };

      $scope.save = function() {

        var reservation = $scope.reservation
        var r = angular.extend({}, reservation);
        r.checkin = moment(r.checkin ).format("YYYY-MM-DD");
        r.checkout = moment(r.checkout ).format("YYYY-MM-DD");
        r.roomType = reservation.room.roomType;
        r.roomName = reservation.room.id;
        r.rateType = reservation.rate.name;

        delete r.checkinDate;
        delete r.checkoutDate;
        delete r.rate;
        delete r.room;


        new Reservation(r).update(function(){
            $state.transitionTo('reservations');
        });

      };

}])