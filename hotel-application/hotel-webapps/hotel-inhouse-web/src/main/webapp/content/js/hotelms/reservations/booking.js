var hotelms = angular.module('hotelms');

hotelms.service('RequestWithPromise', ['$http', '$q', '$log', function($http, $q, $log){
    this.get = function requestWithPromise(url, valueOnFail, logMessage)
    {
        var deferred = $q.defer();
        $http.get(url).success(function(data){
            deferred.resolve(data);
        }).error(function(data){
            $log.error(logMessage + data);
            deferred.resolve(valueOnFail);
        });
        return deferred.promise;
    }

    this.req = function(params)
    {
        var deferred = $q.defer();
        $http(params).success(function(data){
            deferred.resolve(data);
        }).error(function(data){
            $log.error('Error occured during request' + data);
            deferred.resolve(params.valueOnFail);
        });
        return deferred.promise;
    }
}]);

hotelms.factory('RoomService', ['routes', 'RequestWithPromise', function(routes, RequestWithPromise){
    var roomService = {};

    roomService.getRoomTypes = function()
    {
        return RequestWithPromise.get(routes.room.resourceRoot + '/types', [], 'Error occurred while fetching room types');
    }

    roomService.getRooms = function()
    {
        return RequestWithPromise.get(routes.room.resourceRoot, [], 'Error occurred while fetching rooms');
    }

    var extraBeds = [{ extraBed: 0 },{extraBed: 1},{extraBed:2}, {extraBed:3},
                     { extraBed: 4 },{extraBed: 5},{extraBed:6}, {extraBed:7},
                     { extraBed: 8 },{extraBed: 9},{extraBed:10}, {extraBed:11}];

    roomService.getExtraBedsForRoom = function(room)
    {
        return _.filter(extraBeds, function(bed){
           return bed.extraBed <= room.maxExtraBeds;
        });
    }

    return roomService;
}]);

hotelms.factory('RateService', ['routes', 'RequestWithPromise', '$log', function(routes, RequestWithPromise, $log){
    var rateService = {};

    rateService.getRatesForRoom = function(room) {
        $log.info('getRatesForRoom');
        $log.info(room);
        return RequestWithPromise.get(routes.rates.resourceRoot + '/' + room.number, [], 'Error occured while fetching rates');
    }


    return rateService;
}]);

hotelms.factory('ChargeService', ['routes', 'RequestWithPromise', '$log', function(routes, RequestWithPromise, $log){
    var chargeService = {};

    chargeService.calculateChargesForRoom = function(room, checkin, checkout, rate)
    {

        return RequestWithPromise.req({
            method : "GET",
            url : routes.charges.resourceRoot + '/room/' + room.number + '/rate/' + rate.name,
            params : {
                checkin : moment(checkin).format('YYYY-MM-DD'),
                checkout : moment(checkout).format('YYYY-MM-DD')
            }
        });
    }

    return chargeService;
}]);

hotelms.service('GuestLookupService', ['routes', '$log', '$http', function(routes, $log, $http){
    this.findByCommonName = function(cname)
    {
        return $http.get(routes.guests.resourceRoot, {
            params : {
                q : cname
            }
        });
    }
}]);

hotelms.controller('ReservationsBookingCtrl', ['$scope', 'RoomService', 'RateService', 'ChargeService',
'GuestLookupService', 'Reservation','$state', function($scope, RoomService, RateService, ChargeService,
GuestLookupService, Reservation, $state){

    $scope.roomTypes = RoomService.getRoomTypes();
    var rooms = RoomService.getRooms();

    $scope.rooms = rooms;

    $scope.filterRooms = function()
    {
        $scope.rooms = _.filter(rooms.$$v, function(room){
            return room.roomType === $scope.reservation.roomType.id;
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
        $scope.charges = ChargeService.calculateChargesForRoom($scope.reservation.room, $scope.reservation.checkin,
            $scope.reservation.checkout, $scope.reservation.rate);
    }

    $scope.typeaheadValue = '';
    $scope.foundGuest = false;

    $scope.$watch('ownerFinderCName',function(newVal,oldVal){
        var g = _.find($scope.guests, function(g){ return g.displayed === newVal;  });
        if(g && $scope.reservation)
        {
            $scope.foundOwner = true;
            g.dateOfBirth = moment(g.dateOfBirth, "YYYY-MM-DD");
            $scope.reservation.owner = g;
        }
        else
        {
            GuestLookupService.findByCommonName(newVal).success(function(guests){
                $scope.guests = guests;
                $scope.ownerTypeahead = _.map(guests, function(guest){ return guest.displayed; });
            });
        }
    });

    $scope.createNewReservation = function(reservation)
    {
        var r = angular.extend({}, reservation);
        r.roomExtraBed = reservation.extraBeds.extraBed;
        r.checkin = moment(r.checkin ).format("YYYY-MM-DD");
        r.checkout = moment(r.checkout ).format("YYYY-MM-DD");
        r.roomType = reservation.roomType.id;
        r.roomName = reservation.room.id;
        r.rateType = reservation.rate.name;

        Reservation.save(r);

        $state.transitionTo('reservations.sheet')
    }
}]);